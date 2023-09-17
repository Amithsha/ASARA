package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SendAllAnimalInfoAndReporteeInfo extends AppCompatActivity {



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save your data to the bundle
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore your data from the bundle
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_all_animal_info_and_reportee_info);

        Button submitButton = findViewById(R.id.submitButton);
        LinearLayout checkIconAndCaseId = findViewById(R.id.checkIconAndCaseId);
        EditText name = findViewById(R.id.name);
        EditText address = findViewById(R.id.address);
        EditText phone_no = findViewById(R.id.phone_no);
        Button seeNearbyVet = findViewById(R.id.seeNearbyVet);

        seeNearbyVet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendAllAnimalInfoAndReporteeInfo.this, NearbyFailitiesAfterSendinPicActivity.class);
                startActivity(intent);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                if(!name.getText().toString().isEmpty() && !address.getText().toString().isEmpty() && !phone_no.getText().toString().isEmpty()) {
                    ProgressDialog progressDialog;

                    progressDialog = new ProgressDialog(SendAllAnimalInfoAndReporteeInfo.this);
                    progressDialog.setMessage("Submitting");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    // Create a Handler to post a delayed task to dismiss the ProgressDialog after 2 seconds
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (progressDialog != null && progressDialog.isShowing()) {
                                progressDialog.dismiss();
                                checkIconAndCaseId.setVisibility(View.VISIBLE);
                            }

                        }
                    }, 2000);
                }
                else {
                    Toast.makeText(SendAllAnimalInfoAndReporteeInfo.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                }
                

            }
        });

    }
}