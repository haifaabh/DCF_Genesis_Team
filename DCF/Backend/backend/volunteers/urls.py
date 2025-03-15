from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import VolunteerViewSet, DeliveryViewSet
from .views import calculate_itinerary

router = DefaultRouter()
router.register(r'volunteers', VolunteerViewSet)
router.register(r'deliveries', DeliveryViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('deliveries/<int:delivery_id>/itinerary/', calculate_itinerary, name='calculate_itinerary'),

]
