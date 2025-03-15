from django.urls import path, include
from rest_framework.routers import DefaultRouter
from .views import AdminViewSet, BoxViewSet, StatisticsViewSet

router = DefaultRouter()
router.register(r'admins', AdminViewSet)
router.register(r'boxes', BoxViewSet)
router.register(r'statistics', StatisticsViewSet)

urlpatterns = [
    path('', include(router.urls)),
    path('boxes/<int:pk>/assign/', BoxViewSet.as_view({'post': 'assign'}), name='assign-box'),
]
