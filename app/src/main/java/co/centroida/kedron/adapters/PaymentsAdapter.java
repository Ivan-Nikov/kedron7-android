package co.centroida.kedron.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.api.models.Payment;

/**
 * Created by rimmustafin on 2/18/16.
 */
public class PaymentsAdapter extends ArrayAdapter<Payment> {
    private List<Payment> paymentList;
    protected Context context;

    private class PaymentListView{
        TextView value;
        TextView expenseName;
    }

    public PaymentsAdapter(Context context, int res){
        super(context, res);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final PaymentListView holder;
        if(convertView == null){
            holder = new PaymentListView();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.debt_viewholder, parent, false);
            holder.expenseName = (TextView) convertView.findViewById(R.id.debt_name);
            holder.value = (TextView) convertView.findViewById(R.id.debt_value);

            convertView.setTag(holder);;
        }else{
            holder = (PaymentListView) convertView.getTag();
        }

        holder.expenseName.setText(getItem(position).getExpenseTypeName());
        holder.value.setText(String.valueOf(getItem(position).getValue()));

        return convertView;
    }
}
