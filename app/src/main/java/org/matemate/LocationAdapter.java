package org.matemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder>{

    TextView detail;
    TextView placeName;
    List<Location> locations;
    Context context;

    public LocationAdapter(Context context, List<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            placeName = itemView.findViewById(R.id.location_name);
            detail = itemView.findViewById(R.id.location_detail);
        }

        public void setLocation(Location item) {
            placeName.setText(item.getPlaceName());
            detail.setText(item.getDetail());
        }

        public Location getLocation(int position) {
            return locations.get(position);
        }
    }

    @NonNull
    @Override
    public LocationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View location = inflater.inflate(R.layout.location_item, parent, false);
        return new ViewHolder(location);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
            Location location = locations.get(position);
            holder.setLocation(location);
    }

    @Override
    public int getItemViewType(int position) {return position;}
    public int getItemCount() { return locations.size(); }
    public Location getLocation(int position) {return locations.get(position);}
}
