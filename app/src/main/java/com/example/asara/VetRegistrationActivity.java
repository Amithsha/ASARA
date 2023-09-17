package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class VetRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vet_registration);
        ImageView topBackArrow = findViewById(R.id.topBackArrow);
        Button submitButton = findViewById(R.id.submitButton);

        topBackArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VetRegistrationActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VetRegistrationActivity.this, OrgSignedInActivity.class);
                startActivity(intent);
            }
        });

    }
}