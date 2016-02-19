package co.centroida.kedron.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.api.models.Payment;

/**
 * Created by rimmustafin on 2/18/16.
 */
public class PaymentsAdapter extends ArrayAdapter<Payment> {
    protected Context context;

    private class PaymentListView{
        TextView value;
        TextView expenseName;
        TextView datePaid;
    }

    public PaymentsAdapter(Context context, int res){
        super(context, res);

        DateFormat[] formats = new DateFormat[] {
                DateFormat.getDateInstance(),
                DateFormat.getDateTimeInstance(),
                DateFormat.getTimeInstance(),
        };

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final PaymentListView holder;
        if(convertView == null){
            holder = new PaymentListView();
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.payment_viewholder, parent, false);
            holder.expenseName = (TextView) convertView.findViewById(R.id.payment_name);
            holder.value = (TextView) convertView.findViewById(R.id.payment_value);
            holder.datePaid = (TextView) convertView.findViewById(R.id.payment_date);

            convertView.setTag(holder);;
        }else{
            holder = (PaymentListView) convertView.getTag();
        }

        Payment payment = getItem(position);

        holder.expenseName.setText(payment.getExpenseTypeName());
        holder.value.setText(String.valueOf(payment.getValue()));
        holder.datePaid.setText(DateFormat.getDateInstance().format(payment.getDatePaid()));

        return convertView;
    }
}
