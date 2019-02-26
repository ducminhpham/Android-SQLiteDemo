package dmlib.ducminh.ca.plantdatabase_recyclerview_demo;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb.PlantDbManipulator;
import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.viewer.PlantAdapter;

public class PlantActivity extends AppCompatActivity {

    private RecyclerView plantRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant);

        // Find UI views
        plantRecyclerView = findViewById(R.id.plant_recyclerView);

        // Get data
        PlantDbManipulator db = new PlantDbManipulator(getApplicationContext());
        List<ContentValues> plants = db.selectAllFromPlantEntry();
        db.close();

        // Initialize recycler view
        initPlantRecyclerView(plants);
    }

    // Setup
    private void initPlantRecyclerView(List<ContentValues> plants) {
        // First, we need to set a layout manager
        plantRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        // Second, we need to set an adapter
        plantRecyclerView.setAdapter(new PlantAdapter(getApplicationContext(), plants));
    }
}
