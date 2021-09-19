package org.matemate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.ViewHolder> implements OnLocationClickListener{

    TextView detail;
    TextView placeName;
    List<Location> locations;
    Context context;
    OnLocationClickListener listener;

    public LocationAdapter(Context context, List<Location> locations) {
        this.context = context;
        this.locations = locations;
    }

    @Override
    public void onItemClick(ViewHolder holder, View view, int position) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView, final OnLocationClickListener listener) {
            super(itemView);
            placeName = itemView.findViewById(R.id.location_name);
            detail = itemView.findViewById(R.id.location_detail);

            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getBindingAdapterPosition();
                    if(listener != null) {
                        listener.onItemClick(ViewHolder.this, v, position);
                    }
                }
            });
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
        return new ViewHolder(location, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationAdapter.ViewHolder holder, int position) {
            Location location = locations.get(position);
            holder.setLocation(location);
    }

    public void setOnItemClickListener(OnLocationClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {return position;}
    public int getItemCount() { return locations.size(); }
    public Location getLocation(int position) {return locations.get(position);}
}
