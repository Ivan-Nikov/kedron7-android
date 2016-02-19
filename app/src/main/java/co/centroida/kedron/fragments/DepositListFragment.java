package co.centroida.kedron.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.DepositsAdapter;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Deposit;
import co.centroida.kedron.api.services.ICashService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A placeholder fragment containing a simple view.
 */
public class DepositListFragment extends ListFragment implements ICashbookFragment{

    private DepositsAdapter depositsAdapter;
    private ICashService debtService;

    @Override
    public void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);


        Log.d("Deposit", "Debt is creating...");

        //TODO: receive a household id from an intent

        //TODO: Check on this one
        depositsAdapter = new DepositsAdapter(getActivity().getApplicationContext(), 0);
        depositsAdapter.setNotifyOnChange(true);
        setListAdapter(depositsAdapter);

        updateList();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateList(){
        if(ServiceProvider.hasToken()){

            if(!depositsAdapter.isEmpty()) depositsAdapter.clear();

            Log.d("Deposit", "Token is in place...");
            debtService = ServiceProvider.getCashService();

            Call<List<Deposit>> call = debtService.getHouseholdDeposits(1);

            Log.d("Deposit", "A call is formed");

            call.enqueue(new Callback<List<Deposit>>() {
                @Override
                public void onResponse(Call<List<Deposit>> call, Response<List<Deposit>> response) {
                    if(response.isSuccess()){
                        Log.d("Deposit", "Response achieved");
                        depositsAdapter.addAll(response.body());
                    }else{
                        Log.e("Deposit", "Failed to retrieve the Deposit for a given id");
                        try {
                            Log.e("Deposit", response.errorBody().string());
                        } catch (IOException e) {
                            Log.e("Deposit", e.getLocalizedMessage());
                        }

                    }
                }

                @Override
                public void onFailure(Call<List<Deposit>> call, Throwable t) {
                    Log.e("Deposit", t.getMessage() + " FAILED");
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
        return  getString(R.string.deposits_fragment_name);
    }


}
