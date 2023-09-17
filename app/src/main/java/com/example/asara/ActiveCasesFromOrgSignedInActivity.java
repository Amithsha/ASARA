package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ActiveCasesFromOrgSignedInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_cases_from_org_signed_in);
        Button nearestCase = findViewById(R.id.nearestCase);
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
                    mainLayout.setVisibility(View.VISIBLE);
                }
            }
        }, 3000);

        nearestCase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActiveCasesFromOrgSignedInActivity.this, MapImg3Activity.class);
                startActivity(intent);
            }
        });
    }
}