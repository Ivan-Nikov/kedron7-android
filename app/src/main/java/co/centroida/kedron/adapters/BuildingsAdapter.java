package co.centroida.kedron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.api.models.Building;

/**
 * Created by clstl on 2/13/16.
 */
public class BuildingsAdapter extends ArrayAdapter<Building> {
    private List<Building> buildingList;
    protected Context context;

    public BuildingsAdapter(Context context, int res){
        super(context, res);
    }

    class BuildingViewHolder {
        TextView buildingLocation;
        TextView buildingAddress;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BuildingViewHolder holder;
        if(convertView == null){
            holder = new BuildingViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.building_viewholder, parent, false);
            holder.buildingAddress = (TextView) convertView.findViewById(R.id.building_address);
            holder.buildingLocation = (TextView) convertView.findViewById(R.id.building_location);

            convertView.setTag(holder);
        }else{
            holder = (BuildingViewHolder) convertView.getTag();
        }

        holder.buildingLocation.setText(getItem(position).getLocation());
        holder.buildingAddress.setText(getItem(position).getAddress());

        return convertView;
    }
}
