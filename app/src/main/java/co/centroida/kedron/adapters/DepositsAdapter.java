package co.centroida.kedron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.api.models.Deposit;
import co.centroida.kedron.api.models.Payment;

/**
 * Created by rimmustafin on 2/18/16.
 */
public class DepositsAdapter extends ArrayAdapter<Deposit> {
    private List<Deposit> depositList;
    protected Context context;

    private class DepositListView{
        TextView date;
        TextView value;
    }

    public DepositsAdapter(Context context, int res){
        super(context, res);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final DepositListView holder;
        if(convertView == null){
            holder = new DepositListView();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.deposit_viewholder, parent, false);
            holder.date = (TextView) convertView.findViewById(R.id.deposit_date);
            holder.value = (TextView) convertView.findViewById(R.id.deposit_value);

            convertView.setTag(holder);
        }else{
            holder = (DepositListView) convertView.getTag();
        }

        //TODO: Make an easier transform


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMM dd");


        holder.date.setText(sdf.format(getItem(position).getDateCreated()));
        holder.value.setText(String.valueOf(getItem(position).getValue()));

        return convertView;
    }
}
