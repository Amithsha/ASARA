package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class SendDetailsActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_send_details);

        Button requestHelpButton = findViewById(R.id.requestHelpButton);
//        LinearLayout imgDetailsAndSendBtnLayout = findViewById(R.id.helpLayout);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        ProgressDialog progressDialog;

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        // Create a Handler to post a delayed task to dismiss the ProgressDialog after 2 seconds
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        }, 3000);

        //since we are not using capturedImage now - commenting it out
//        if (intent != null) {
//            // Check if the Intent has the Bitmap extra
//            if (intent.hasExtra("imageBitmap")) {
//                // Retrieve the Bitmap from the Intent
//                Bitmap receivedBitmap = intent.getParcelableExtra("imageBitmap");
//
//                // Now you can use the received Bitmap as needed
//                imageView.setImageBitmap(receivedBitmap); // Display it in an ImageView
//                // Perform any other operations with the Bitmap
//            }
//        }

        final long delayMillis = 3000;

        // Create a Handler to post a delayed task
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Make the component visible after the specified delay
                mainLayout.setVisibility(View.VISIBLE);
            }
        }, delayMillis);

        requestHelpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SendDetailsActivity.this, SendAllAnimalInfoAndReporteeInfo.class);
                startActivity(intent);
            }
        });


//        TextView myTextView;
//        final long delayMillis = 5000;
//        myTextView = findViewById(R.id.invisibletext);
//
//        // Create a Handler to post a delayed task
//        handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // Make the component visible after the specified delay
//                myTextView.setVisibility(View.VISIBLE);
//            }
//        }, delayMillis);
    }
}