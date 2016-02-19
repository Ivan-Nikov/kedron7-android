package co.centroida.kedron.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.Date;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.PaymentsAdapter;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.PaymentResponse;
import co.centroida.kedron.api.services.ICashService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class PaymentsListFragment extends ListFragment implements ICashbookFragment{

    private PaymentsAdapter paymentAdapter;
    private ICashService debtService;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        Log.d("Payment", "Payment is creating...");

        //TODO: receive a household id from an intent

        //TODO: Check on this one
        paymentAdapter = new PaymentsAdapter(getActivity().getApplicationContext(), 0);
        paymentAdapter.setNotifyOnChange(true);
        setListAdapter(paymentAdapter);

        updateList();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void updateList() { updateList(null, null); }

    public void updateList(Date lower, Date upper){
        if(ServiceProvider.hasToken()){

            if(!paymentAdapter.isEmpty()) paymentAdapter.clear();

            Log.d("Debt", "Token is in place...");
            debtService = ServiceProvider.getCashService();

            Call<PaymentResponse> call = debtService.getHouseholdPayments(2, 30,
                    ServiceProvider.convertDate(lower),
                    ServiceProvider.convertDate(upper));

            Log.d("Debt", "A call is formed");

            call.enqueue(new Callback<PaymentResponse>() {
                @Override
                public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                    if(response.isSuccess()){
                        Log.d("Payment", "Response achieved");
                        paymentAdapter.addAll(response.body().getPayments());
                    }else{
                        Log.e("Payment", "Failed to retrieve the Payments for a given id");
                        try {
                            Log.e("Payment", response.errorBody().string());
                        } catch (IOException e) {
                            Log.e("Payment", e.getLocalizedMessage());
                        }

                    }
            }
                @Override
                public void onFailure(Call<PaymentResponse> call, Throwable t) {
                    Log.e("Payment", "Failed to receive a response", t);
                }
            });
        }
    }

    @Override
    public void search(String query) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


    }

    public String getClassName(){
        return getString(R.string.payments_fragment_name);
    }
}
