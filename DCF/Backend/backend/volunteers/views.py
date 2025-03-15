from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework.decorators import action
from .models import Volunteer, Delivery
from .serializers import VolunteerSerializer, DeliverySerializer
from django.utils.timezone import now
import requests
from rest_framework.decorators import api_view
from rest_framework.response import Response
from django.conf import settings
from .models import Delivery


class VolunteerViewSet(viewsets.ModelViewSet):
    queryset = Volunteer.objects.all()
    serializer_class = VolunteerSerializer

class DeliveryViewSet(viewsets.ModelViewSet):
    queryset = Delivery.objects.all()
    serializer_class = DeliverySerializer

    @action(detail=True, methods=['post'])
    def update_status(self, request, pk=None):
        delivery = self.get_object()
        new_status = request.data.get('status')
        if new_status in dict(Delivery._meta.get_field('status').choices):
            delivery.status = new_status
            if new_status == 'delivered':
                delivery.delivered_at = now()
            delivery.save()
            return Response({'message': 'Status updated successfully'})
        return Response({'error': 'Invalid status'}, status=400)


OSRM_URL = "http://router.project-osrm.org/route/v1/driving/"

@api_view(['GET'])
def calculate_itinerary(request, delivery_id):
    try:
        delivery = Delivery.objects.get(id=delivery_id)

        # Ensure all coordinates are available
        if not all([delivery.latitude, delivery.longitude, 
                    delivery.collection_point.latitude, delivery.collection_point.longitude]):
            return Response({'error': 'Missing location data'}, status=400)

        start = f"{delivery.latitude},{delivery.longitude}"  
        box = f"{delivery.collection_point.latitude},{delivery.collection_point.longitude}"  # Box Location
        recipient = f"{delivery.donation.latitude},{delivery.donation.longitude}"  # Recipient Location

        # Step 1: Route from Start -> Box
        route1 = requests.get(f"{OSRM_URL}{start};{box}?overview=false").json()
        if 'routes' not in route1:
            return Response({'error': 'Failed to fetch route to box'}, status=400)

        # Step 2: Route from Box -> Recipient
        route2 = requests.get(f"{OSRM_URL}{box};{recipient}?overview=false").json()
        if 'routes' not in route2:
            return Response({'error': 'Failed to fetch route to recipient'}, status=400)

        total_distance = route1['routes'][0]['distance'] + route2['routes'][0]['distance']
        total_duration = route1['routes'][0]['duration'] + route2['routes'][0]['duration']

        return Response({
            'route_to_box': route1['routes'][0]['legs'],
            'route_to_recipient': route2['routes'][0]['legs'],
            'total_distance_meters': total_distance,
            'total_duration_seconds': total_duration
        })

    except Delivery.DoesNotExist:
        return Response({'error': 'Delivery not found'}, status=404)