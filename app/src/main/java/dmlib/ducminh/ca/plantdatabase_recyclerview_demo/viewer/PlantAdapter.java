package dmlib.ducminh.ca.plantdatabase_recyclerview_demo.viewer;

import android.content.ContentValues;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.R;
import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.plantdb.PlantDbContract;

public class PlantAdapter extends RecyclerView.Adapter<PlantRowHolder> {

    private Context context;
    private List<ContentValues> plants;
    private LayoutInflater inflater;

    // Constructor
    public PlantAdapter(Context context, List<ContentValues> plants) {
        this.context = context;
        this.plants = plants;
        this.inflater = LayoutInflater.from(context);
    }

    // Create a row from XML layout and pass it to a view holder
    @NonNull
    @Override
    public PlantRowHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate an item from XML design
        View itemView = this.inflater.inflate(R.layout.plant_row, viewGroup, false);
        return new PlantRowHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlantRowHolder plantRowHolder, int i) {
        plantRowHolder.update(
                plants.get(i).getAsString(PlantDbContract.PlantEntry.COLUMN_NAME),
                plants.get(i).getAsString(PlantDbContract.PlantEntry.COLUMN_TYPE));
    }

    @Override
    public int getItemCount() {
        return this.plants.size();
    }
}
