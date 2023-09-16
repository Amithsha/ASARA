package com.example.asara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_IMAGE_CAPTURE = 1;
    Button registered_animals, case_history, nearest_vet_facility,my_profile,sos;
    CreateLoadLocalData createLoadLocalData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView authenticateCard = findViewById(R.id.authenticateCard);
        CardView reportNowCard = findViewById(R.id.reportNowCard);
        authenticateCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
            }
        });

        reportNowCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Intent intent = new Intent();
                    intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // The image was successfully captured.
            // You can access the image data from the intent.
            Bitmap imageBitmap = (Bitmap) data.getExtras().get("data");

            // Now, you can use the captured image as needed.
            Intent intent = new Intent(MainActivity.this, SendDetailsActivity.class);
            intent.putExtra("imageBitmap", imageBitmap);
            startActivity(intent);
            // Save the image to storage, send it to a server, or process it further.
        }

    }

}
