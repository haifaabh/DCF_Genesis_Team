from django.db import models
from donors.models import User

class Admin(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)

class Volunteer(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, limit_choices_to={'role': 'volunteer'}, related_name='admin_volunteer')

class Seeker(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE, limit_choices_to={'role': 'seeker'}, related_name='admin_seeker')

class Leaderboard(models.Model):
    donor = models.ForeignKey(User, on_delete=models.CASCADE, limit_choices_to={'role': 'donor'})
    points = models.IntegerField(default=0)

class Statistics(models.Model):
    total_donations = models.IntegerField(default=0)
    total_volunteers = models.IntegerField(default=0)
    total_families_helped = models.IntegerField(default=0)

class Box(models.Model):
    identifier = models.CharField(max_length=100, unique=True)
    content = models.TextField()
    assigned_volunteer = models.ForeignKey(Volunteer, on_delete=models.SET_NULL, null=True, blank=True, related_name='assigned_boxes')
    assigned_seeker = models.ForeignKey(Seeker, on_delete=models.SET_NULL, null=True, blank=True, related_name='received_boxes')
    created_at = models.DateTimeField(auto_now_add=True)

    def assign_to_volunteer(self, volunteer):
        self.assigned_volunteer = volunteer
        self.save()

    def assign_to_seeker(self, seeker):
        self.assigned_seeker = seeker
        self.save()
