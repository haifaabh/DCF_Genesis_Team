from django.db import models
from django.contrib.auth.models import AbstractUser, Group, Permission

class User(AbstractUser):
    ROLE_CHOICES = [('donor', 'Donor'), ('volunteer', 'Volunteer'), ('admin', 'Admin')]
    role = models.CharField(max_length=10, choices=ROLE_CHOICES, default='donor')

    groups = models.ManyToManyField(Group, related_name="custom_user_groups", blank=True)
    user_permissions = models.ManyToManyField(Permission, related_name="custom_user_permissions", blank=True)

class DonorProfile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, related_name="donor_profile")
    donation_points = models.IntegerField(default=0)
    phone_number = models.CharField(max_length=15, blank=True, null=True)
    address = models.TextField(blank=True, null=True)
    latitude = models.FloatField(blank=True, null=True)
    longitude = models.FloatField(blank=True, null=True)

class CollectionPoint(models.Model):
    name = models.CharField(max_length=255)
    location = models.TextField()
    city = models.CharField(max_length=100, blank=True, null=True)
    contact_number = models.CharField(max_length=15, blank=True, null=True)
    capacity = models.IntegerField()
    available_slots = models.IntegerField()
    latitude = models.FloatField(blank=True, null=True)
    longitude = models.FloatField(blank=True, null=True)

class Donation(models.Model):
    donor = models.ForeignKey(User, on_delete=models.CASCADE, limit_choices_to={'role': 'donor'})
    status = models.CharField(max_length=20, choices=[
        ('pending', 'Pending'), ('approved', 'Approved'), ('collected', 'Collected'),
        ('delivered', 'Delivered')
    ], default='pending')
    created_at = models.DateTimeField(auto_now_add=True)
    collection_point = models.ForeignKey(CollectionPoint, on_delete=models.SET_NULL, null=True, blank=True)

class FoodItem(models.Model):
    donation = models.ForeignKey(Donation, on_delete=models.CASCADE, related_name="items", null=True, blank=True)
    name = models.CharField(max_length=255)
    quantity = models.IntegerField()
    expiry_date = models.DateField(null=True, blank=True)
    barcode = models.CharField(max_length=50, null=True, blank=True)
    status = models.CharField(max_length=20, choices=[
        ('fresh', 'Fresh'), ('expiring_soon', 'Expiring Soon'), ('expired', 'Expired')
    ], default='fresh')
