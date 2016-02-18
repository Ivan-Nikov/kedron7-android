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
import co.centroida.kedron.api.models.Household;

/**
 * Created by lkz on 17-Feb-16.
 */
public class HouseholdsAdapter extends ArrayAdapter<Household>{


    public HouseholdsAdapter(Context context, int res){
        super(context,R.layout.household_viewholder);
    }

    class HouseholdViewHolder {
        TextView householdName;
        TextView householdNumber;
        TextView householdFloorNumber;
    }
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.household_viewholder, parent, false);
        }

        Household house = new Household(getItem(position));

        HouseholdViewHolder holder = new HouseholdViewHolder();
        holder.householdName=(TextView) v.findViewById(R.id.household_name);
        holder.householdNumber= (TextView) v.findViewById(R.id.household_number);
        holder.householdFloorNumber = (TextView) v.findViewById(R.id.household_floor_number);

        if ( house != null) {
            holder.householdName.setText(house.getName());
            holder.householdNumber.setText(Integer.toString(house.getNumber()));
            holder.householdFloorNumber.setText(Integer.toString(house.getFloorNumber()));
        }

        return v;
    }
}