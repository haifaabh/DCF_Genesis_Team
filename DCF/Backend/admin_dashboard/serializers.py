from rest_framework import serializers
from .models import Admin, Leaderboard, Statistics, Box, Volunteer, Seeker

class AdminSerializer(serializers.ModelSerializer):
    class Meta:
        model = Admin
        fields = '__all__'

class LeaderboardSerializer(serializers.ModelSerializer):
    class Meta:
        model = Leaderboard
        fields = '__all__'

class StatisticsSerializer(serializers.ModelSerializer):
    class Meta:
        model = Statistics
        fields = '__all__'

class BoxSerializer(serializers.ModelSerializer):
    class Meta:
        model = Box
        fields = '__all__'

class AssignBoxSerializer(serializers.Serializer):
    box_id = serializers.IntegerField()
    volunteer_id = serializers.IntegerField(required=False)
    seeker_id = serializers.IntegerField(required=False)
