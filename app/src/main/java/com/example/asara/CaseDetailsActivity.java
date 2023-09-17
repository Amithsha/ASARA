package com.example.asara;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CaseDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_details);

        // Get case details from the intent or wherever you store them
        int caseId = getIntent().getIntExtra("case_id", 453425);
        String caseShortDescription = getIntent().getStringExtra("shortDescription");
        String caseStatus = getIntent().getStringExtra("case_status");
        String caseFullDescription = getIntent().getStringExtra("fullDescription");

        // Initialize TextViews to display case details
        TextView idTextView = findViewById(R.id.textViewCaseId);
        TextView shortDescriptionTextView = findViewById(R.id.textViewCaseShortDescription);
        TextView statusTextView = findViewById(R.id.textViewCaseStatus);
        TextView fullDescriptionTextView = findViewById(R.id.textViewCaseFullDescription);

        // Set the values to TextViews
        idTextView.setText(String.valueOf(caseId));
        shortDescriptionTextView.setText(caseShortDescription);
        statusTextView.setText(caseStatus);
        fullDescriptionTextView.setText(caseFullDescription);

        // Initialize image views for tracking and calling
        //ImageView callImageView = findViewById(R.id.imageViewCall);

        // Set click listeners for tracking and calling
        TextView trackingLinkTextView = findViewById(R.id.textViewTrackingLink);

        // Create a SpannableString to include both text and image
        SpannableString spannableString = new SpannableString("  Track Ambulance");

        // Add an image to the SpannableString
        Drawable trackingIcon = getResources().getDrawable(R.drawable.track_ambulance); // Replace with your tracking image
        trackingIcon.setBounds(0, 0, ((Drawable) trackingIcon).getIntrinsicWidth(), trackingIcon.getIntrinsicHeight());
        ImageSpan imageSpan = new ImageSpan(trackingIcon, ImageSpan.ALIGN_BOTTOM);
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        // Set the SpannableString to the TextView
        trackingLinkTextView.setText(spannableString);
        trackingLinkTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CaseDetailsActivity.this, LocationSimulationActivity.class);
                startActivity(intent);
            }
        });

        String driverPhoneNumber = "Driver Phone: +1234567890"; // Replace with the actual phone number
        spannableString = new SpannableString(driverPhoneNumber);

// Add an image to the SpannableString
        Drawable callIcon = getResources().getDrawable(R.drawable.baseline_call_24); // Replace with your call image
        callIcon.setBounds(0, 0, callIcon.getIntrinsicWidth(), callIcon.getIntrinsicHeight());
        spannableString.setSpan(imageSpan, 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

        Button callButton = findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle calling action (e.g., initiate a phone call)
                // You can add your calling logic here
                Log.d("CaseDetailsActivity", "Call button clicked");
            }
        });
        Button doctorCallButton = findViewById(R.id.doctorCallButton);
        doctorCallButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle doctor's calling action (e.g., initiate a phone call)
                // You can add your calling logic here
                Log.d("CaseDetailsActivity", "Doctor Call button clicked");
            }
        });


    }
}
