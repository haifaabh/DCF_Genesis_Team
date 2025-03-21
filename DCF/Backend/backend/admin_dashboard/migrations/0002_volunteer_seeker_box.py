# Generated by Django 4.2.7 on 2025-03-15 22:14

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('donors', '0004_collectionpoint_latitude_collectionpoint_longitude_and_more'),
        ('admin_dashboard', '0001_initial'),
    ]

    operations = [
        migrations.CreateModel(
            name='Volunteer',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user', models.OneToOneField(limit_choices_to={'role': 'volunteer'}, on_delete=django.db.models.deletion.CASCADE, related_name='admin_volunteer', to='donors.user')),
            ],
        ),
        migrations.CreateModel(
            name='Seeker',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('user', models.OneToOneField(limit_choices_to={'role': 'seeker'}, on_delete=django.db.models.deletion.CASCADE, related_name='admin_seeker', to='donors.user')),
            ],
        ),
        migrations.CreateModel(
            name='Box',
            fields=[
                ('id', models.BigAutoField(auto_created=True, primary_key=True, serialize=False, verbose_name='ID')),
                ('identifier', models.CharField(max_length=100, unique=True)),
                ('content', models.TextField()),
                ('created_at', models.DateTimeField(auto_now_add=True)),
                ('assigned_seeker', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='received_boxes', to='admin_dashboard.seeker')),
                ('assigned_volunteer', models.ForeignKey(blank=True, null=True, on_delete=django.db.models.deletion.SET_NULL, related_name='assigned_boxes', to='admin_dashboard.volunteer')),
            ],
        ),
    ]
