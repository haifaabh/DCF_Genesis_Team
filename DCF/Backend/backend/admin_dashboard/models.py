from django.db import models

from donors.models import User

class Admin(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)

class Leaderboard(models.Model):
    donor = models.ForeignKey(User, on_delete=models.CASCADE, limit_choices_to={'role': 'donor'})
    points = models.IntegerField(default=0)

class Statistics(models.Model):
    total_donations = models.IntegerField(default=0)
    total_volunteers = models.IntegerField(default=0)
    total_families_helped = models.IntegerField(default=0)
