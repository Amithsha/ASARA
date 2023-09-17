package com.example.asara;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class TrackMyActiveCases extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Case> caseList;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_track_cases);
        recyclerView = findViewById(R.id.recyclerViewCases);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize your list of cases (you can load this from a data source)
        caseList = new ArrayList<>();
        caseList.add(new Case(34243, "Pet Recovered", "Closed", "Injury in right leg."));

        caseList.add(new Case(65435, "Ambulance On the Way", "In-Progress", "Make sure that the water intake is happening"));
        // Add more cases as needed

        CaseAdapter adapter = new CaseAdapter(this, caseList, new CaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Case caseItem) {
                // Handle item click, e.g., navigate to CaseDetailsActivity
                Intent intent = new Intent(TrackMyActiveCases.this, CaseDetailsActivity.class);
                intent.putExtra("case_id", caseItem.getId()); // Pass case ID as an extra
                intent.putExtra("shortDescription", caseItem.getShortDescription()); // Pass short description as an extra
                intent.putExtra("case_status", caseItem.getStatus()); // Pass case status as an extra
                intent.putExtra("fullDescription", caseItem.getFullDescription()); // Pass full description as an extra
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
    }
}