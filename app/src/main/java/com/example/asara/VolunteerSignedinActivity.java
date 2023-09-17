package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class VolunteerSignedinActivity extends AppCompatActivity {


    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int NOTIFICATION_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volunteer_signedin);
        boolean userLoggedIn = true;

        ImageView camera = findViewById(R.id.camera);
        ImageView userprofile = findViewById(R.id.userprofile);
        ImageView reportedCases = findViewById(R.id.userReportedCases);
        ImageView userTrackCases = findViewById(R.id.userTrackCard);

        userTrackCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerSignedinActivity.this, TrackMyActiveCases.class);
                startActivity(intent);
            }
        });

        reportedCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerSignedinActivity.this, LocationSimulationActivity.class);
                startActivity(intent);
            }
        });


        userprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VolunteerSignedinActivity.this, MyProfileActivity.class);
                startActivity(intent);

            }
        });
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                } catch (Exception e) {
                    Toast.makeText(VolunteerSignedinActivity.this, "error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });

        if (userLoggedIn) {
            // User has logged in, show a notification
            showNotification();
        }

    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("1",
                    "Your_Channel_Name", NotificationManager.IMPORTANCE_HIGH);
            channel.setDescription("Your_Channel_Description");

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }


    private void showNotification() {
        createNotificationChannel(); // Call this method to create the notification channel

        // Create an intent to open a specific activity when the notification is tapped
        Intent intent = new Intent(this, MyProfileActivity.class);
        int requestCode = 0; // Set your desired request code

        // Create a PendingIntent with FLAG_IMMUTABLE
        PendingIntent pendingIntent = PendingIntent.getActivity(
                this,
                requestCode,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Create a full-screen intent to show as a Heads-up notification
        PendingIntent fullScreenIntent = PendingIntent.getActivity(
                this,
                requestCode,
                intent,
                PendingIntent.FLAG_IMMUTABLE
        );

        // Build the notification with fullScreenIntent
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "1")
                .setSmallIcon(R.drawable.ic_notification_icon)
                .setContentTitle("Alert!!")
                .setContentText("Ambulance has been assigned !!!!")
                .setContentIntent(pendingIntent)
                .setFullScreenIntent(fullScreenIntent, true) // Set fullScreenIntent here
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Use HIGH priority for Heads-up notification
                .setAutoCancel(true);

        // Define a unique notification ID
        int NOTIFICATION_ID = 1; // Set your desired notification ID

        // Show the notification
        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // The image was successfully captured.
            // You can access the image data from the intent.
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

            // Now, you can use the captured image as needed.
            Intent intent = new Intent(VolunteerSignedinActivity.this, SendDetailsActivity.class);
            intent.putExtra("imageBitmap", imageBitmap);
            startActivity(intent);
            // Save the image to storage, send it to a server, or process it further.
        }

    }

}