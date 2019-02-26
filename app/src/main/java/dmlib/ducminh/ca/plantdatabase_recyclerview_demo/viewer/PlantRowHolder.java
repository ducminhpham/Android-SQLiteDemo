package dmlib.ducminh.ca.plantdatabase_recyclerview_demo.viewer;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import dmlib.ducminh.ca.plantdatabase_recyclerview_demo.R;

public class PlantRowHolder extends RecyclerView.ViewHolder {

    private TextView nameTextView;
    private TextView typeTextView;

    // Constructor
    public PlantRowHolder(@NonNull View itemView) {
        super(itemView);
        nameTextView = itemView.findViewById(R.id.name_textView);
        typeTextView = itemView.findViewById(R.id.type_textView);
    }

    // Update
    public void update(String name, String type) {
        nameTextView.setText(name);
        typeTextView.setText(type);
    }
}
