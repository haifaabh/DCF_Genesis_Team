from rest_framework import viewsets
from rest_framework.response import Response
from rest_framework.decorators import action
from rest_framework import status
from .models import Admin, Leaderboard, Statistics, Box, Volunteer, Seeker
from .serializers import (
    AdminSerializer, LeaderboardSerializer, StatisticsSerializer, BoxSerializer, AssignBoxSerializer
)

class AdminViewSet(viewsets.ModelViewSet):
    queryset = Admin.objects.all()
    serializer_class = AdminSerializer

class StatisticsViewSet(viewsets.ModelViewSet):
    queryset = Statistics.objects.all()
    serializer_class = StatisticsSerializer

class BoxViewSet(viewsets.ModelViewSet):
    queryset = Box.objects.all()
    serializer_class = BoxSerializer

    @action(detail=True, methods=['post'])
    def assign(self, request, pk=None):
        try:
            box = self.get_object()
            serializer = AssignBoxSerializer(data=request.data)
            if serializer.is_valid():
                volunteer_id = serializer.validated_data.get('volunteer_id')
                seeker_id = serializer.validated_data.get('seeker_id')

                if volunteer_id:
                    volunteer = Volunteer.objects.get(id=volunteer_id)
                    box.assign_to_volunteer(volunteer)
                
                if seeker_id:
                    seeker = Seeker.objects.get(id=seeker_id)
                    box.assign_to_seeker(seeker)

                return Response({'message': 'Box assigned successfully'}, status=status.HTTP_200_OK)

            return Response(serializer.errors, status=status.HTTP_400_BAD_REQUEST)
        
        except Box.DoesNotExist:
            return Response({'error': 'Box not found'}, status=status.HTTP_404_NOT_FOUND)
        except Volunteer.DoesNotExist:
            return Response({'error': 'Volunteer not found'}, status=status.HTTP_404_NOT_FOUND)
        except Seeker.DoesNotExist:
            return Response({'error': 'Seeker not found'}, status=status.HTTP_404_NOT_FOUND)
