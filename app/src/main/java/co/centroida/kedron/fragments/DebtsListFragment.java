package co.centroida.kedron.fragments;

import android.app.ListFragment;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.DebtsAdapter;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.DebtResponse;
import co.centroida.kedron.api.services.IDebtService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class DebtsListFragment extends ListFragment {

    private DebtsAdapter debtsAdapter;
    private IDebtService debtService;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);

        Log.d("Debts", "Debt is creating...");

        //TODO: receive a household id from an intent

        //TODO: Check on this one
        debtsAdapter = new DebtsAdapter(getActivity().getApplicationContext(), 0);
        debtsAdapter.setNotifyOnChange(true);
        setListAdapter(debtsAdapter);

        if(ServiceProvider.hasToken()){

            Log.d("Debt", "Token is in place...");
            debtService = ServiceProvider.getDebtService();

            Call<DebtResponse> call = debtService.getHouseholdDebts(1, 30);

            Log.d("Debt", "A call is formed");

            call.enqueue(new Callback<DebtResponse>() {
                @Override
                public void onResponse(Call<DebtResponse> call, Response<DebtResponse> response) {
//                    if(response.isSuccess()){
//                        Log.d("Debts", "Response achieved");
                        debtsAdapter.addAll(response.body().getDebts());
//                    }else{
//                        Log.e("Debts", "Failed to retrieve the debts for a given id");
//                        try {
//                            Log.e("Debts", response.errorBody().string());
//                        } catch (IOException e) {
//                            Log.e("dsd", e.getLocalizedMessage());
//                        }
//
//                    }
                }

                @Override
                public void onFailure(Call<DebtResponse> call, Throwable t) {
                        Log.e("Debts", "Failed to receive a response", t);
                }
            });
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //TODO: Pay for the selected item
        super.onListItemClick(l, v, position, id);
    }
}
