from django.forms import ValidationError
from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework.decorators import action
from .models import User, Donation, FoodItem, CollectionPoint
from .serializers import (UserSerializer, DonationSerializer, FoodItemSerializer, CollectionPointSerializer)

class UserViewSet(viewsets.ModelViewSet):
    queryset = User.objects.all()
    serializer_class = UserSerializer

    def perform_create(self, serializer):
        """Allow creating users without authentication"""
        serializer.save()

class CollectionPointViewSet(viewsets.ModelViewSet):
    queryset = CollectionPoint.objects.all()
    serializer_class = CollectionPointSerializer

    def perform_create(self, serializer):
        """Allow creating collection points without authentication"""
        serializer.save()

class DonationViewSet(viewsets.ModelViewSet):
    queryset = Donation.objects.all()
    serializer_class = DonationSerializer

    def perform_create(self, serializer):
        if "donor" not in self.request.data:
           raise ValidationError({"donor_id": "Ce champ est obligatoire."})
        serializer.save()


    @action(detail=True, methods=['POST'])
    def add_items(self, request, pk=None):
        """Allow adding multiple food items to a donation."""
        donation = self.get_object()
        items = request.data.get('items', [])

        for item_id in items:
            try:
                item = FoodItem.objects.get(id=item_id)
                item.donation = donation
                item.save()
            except FoodItem.DoesNotExist:
                return Response({'error': f'FoodItem with ID {item_id} not found'}, status=400)

        return Response({'status': 'items added'})

class FoodItemViewSet(viewsets.ModelViewSet):
    queryset = FoodItem.objects.all()
    serializer_class = FoodItemSerializer

    def perform_create(self, serializer):
        """Allow creating food items without authentication"""
        serializer.save()
