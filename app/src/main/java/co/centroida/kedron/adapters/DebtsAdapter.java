package co.centroida.kedron.adapters;

/**
 * Created by rimmustafin on 2/17/16.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.api.models.Debt;

public class DebtsAdapter extends ArrayAdapter<Debt>{
    private List<Debt> debtList;
    protected Context context;

    private class DebtViewHolder{
        TextView value;
        TextView expenseName;
    }

    public DebtsAdapter(Context context, int res){
        super(context, res);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final DebtViewHolder holder;
        if(convertView == null){
            holder = new DebtViewHolder();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.debt_viewholder, parent, false);
            holder.expenseName = (TextView) convertView.findViewById(R.id.debt_name);
            holder.value = (TextView) convertView.findViewById(R.id.debt_value);

            convertView.setTag(holder);;
        }else{
            holder = (DebtViewHolder) convertView.getTag();
        }

        holder.expenseName.setText(getItem(position).getExpenseTypeName());
        holder.value.setText(String.valueOf(getItem(position).getValue()));

        return convertView;
    }
}
