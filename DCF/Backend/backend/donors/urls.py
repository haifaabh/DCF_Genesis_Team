from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import UserViewSet, CollectionPointViewSet, DonationViewSet, FoodItemViewSet

router = DefaultRouter()
router.register(r'users', UserViewSet)
# router.register(r'donors', DonorProfileViewSet)
router.register(r'collection-points', CollectionPointViewSet)
router.register(r'donations', DonationViewSet)
router.register(r'food-items', FoodItemViewSet)

urlpatterns = [
    path('', include(router.urls)),
]
