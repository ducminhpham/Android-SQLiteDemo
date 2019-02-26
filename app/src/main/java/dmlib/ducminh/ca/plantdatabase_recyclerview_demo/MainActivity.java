package dmlib.ducminh.ca.plantdatabase_recyclerview_demo;

import android.content.ContentValues;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb.PlantDbContract;
import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb.PlantDbManipulator;

public class MainActivity extends AppCompatActivity {

    private PlantDbManipulator plantDb;

    private Button insertButton;
    private EditText plantNameEditText;
    private EditText plantTypeEditText;

    private Button allPlantsButton;
    private EditText queryTypeEditText;
    private Button queryTypeButton;
    private Button clearAllButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // UI views
        plantNameEditText = findViewById(R.id.plantName_editText);
        plantTypeEditText = findViewById(R.id.plantType_editText);
        insertButton = findViewById(R.id.insert_button);
        allPlantsButton = findViewById(R.id.allPlants_button);
        queryTypeEditText = findViewById(R.id.queryType_editText);
        queryTypeButton = findViewById(R.id.queryType_button);
        clearAllButton = findViewById(R.id.clearAll_button);

        // Create database manipulator
        plantDb = new PlantDbManipulator(getApplicationContext());

        // Add EditText's TextWatcher
        plantNameEditText.addTextChangedListener(new PlantInfoTextWatcher());
        plantTypeEditText.addTextChangedListener(new PlantInfoTextWatcher());

        // Plant insertion event
        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long rowid = plantDb.insertToPlantEntry(
                        plantNameEditText.getText().toString(),
                        plantTypeEditText.getText().toString());
                // RowID == -1 means insertion was failed
                if (rowid == -1) {
                    Toast.makeText(getApplicationContext(), "Insertion was FAILED!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Insertion was SUCCESSFUL!", Toast.LENGTH_SHORT).show();
                    // Clear edit text
                    plantNameEditText.setText("");
                    plantTypeEditText.setText("");
                }
            }
        });

        // Listener of all-plants-button
        allPlantsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlantActivity.class);
                startActivity(intent);
            }
        });

        // Listener to queryTypeButton
        queryTypeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<ContentValues> plants =
                        plantDb.selectTypeFromPlantEntry(queryTypeEditText.getText().toString());
                StringBuilder sb = new StringBuilder();
                for (ContentValues plant : plants) {
                    sb.append(String.format("%s : %s\r\n",
                            plant.getAsString(PlantDbContract.PlantEntry.COLUMN_NAME),
                            plant.getAsString(PlantDbContract.PlantEntry.COLUMN_TYPE)));
                }
                Toast.makeText(getApplicationContext(), sb.toString(), Toast.LENGTH_LONG).show();
            }
        });

        // Listen to clearAllButton
        clearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                plantDb.clearPlantEntry();
                Toast.makeText(getApplicationContext(), "All plants have been deleted!", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        // Close database
        plantDb.close();
        super.onDestroy();
    }

    // TextWatcher for plant name and type --------------------------

    public class PlantInfoTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // Only enable insert button if plant name and type are provided
            insertButton.setEnabled(
                    plantNameEditText.getText().length() > 0
                            && plantTypeEditText.getText().length() > 0);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    }

}
