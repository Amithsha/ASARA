package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class MapImg3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_img3);
        ImageView mapImage = findViewById(R.id.mapImage);
        ViewSwitcher viewSwitcher = findViewById(R.id.viewSwitcher);
        Button reject = findViewById(R.id.reject);
        Button accept = findViewById(R.id.accept);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog progressDialog;
                progressDialog = new ProgressDialog(MapImg3Activity.this);
                progressDialog.setMessage("Accepting");
                progressDialog.setCancelable(false);
                progressDialog.show();

                // Create a Handler to post a delayed task to dismiss the ProgressDialog after 2 seconds
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (progressDialog != null && progressDialog.isShowing()) {
                            progressDialog.dismiss();
                            viewSwitcher.showNext();
                            Drawable newMap = getDrawable(R.drawable.map_img4); // Replace with your Drawable source
                            mapImage.setImageDrawable(newMap);

                        }

                    }
                }, 3000);
            }
        });
        
        
    }
}