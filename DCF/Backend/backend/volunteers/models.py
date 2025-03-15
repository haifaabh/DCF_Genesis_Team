from django.db import models
from donors.models import Donation
from donors.models import User
from donors.models import CollectionPoint

class Volunteer(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    phone_number = models.CharField(max_length=15)
    rating = models.FloatField(default=5.0)
    total_deliveries = models.IntegerField(default=0)
    latitude = models.FloatField(blank=True, null=True)
    longitude = models.FloatField(blank=True, null=True)

class Delivery(models.Model):
    volunteer = models.ForeignKey(Volunteer, on_delete=models.CASCADE)
    donation = models.ForeignKey(Donation, on_delete=models.CASCADE)
    collection_point = models.ForeignKey(CollectionPoint, on_delete=models.CASCADE)
    assigned_at = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=20, choices=[
        ('assigned', 'Assigned'), ('picked_up', 'Picked Up'), ('delivered', 'Delivered')
    ], default='assigned')
    delivered_at = models.DateTimeField(null=True, blank=True)
    latitude = models.FloatField(blank=True, null=True)
    longitude = models.FloatField(blank=True, null=True)
    
