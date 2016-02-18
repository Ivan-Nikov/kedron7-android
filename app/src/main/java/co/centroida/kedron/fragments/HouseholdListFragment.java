package co.centroida.kedron.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.HouseholdsAdapter;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Household;
import co.centroida.kedron.api.models.HouseholdResponse;
import co.centroida.kedron.api.services.IHouseholdService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by lkz on 17-Feb-16.
 */
public class HouseholdListFragment extends ListFragment{
    IHouseholdService serv;
    Call<HouseholdResponse> call;


    HouseholdsAdapter adapt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapt = new HouseholdsAdapter(getActivity(),0);

        setListAdapter(adapt);
        adapt.setNotifyOnChange(true);
        if(ServiceProvider.hasToken()) {
            serv= ServiceProvider.getHouseholdService();
            call = serv.getHouseholdsTop(1, 30, 0, null, null);
            call.enqueue(new Callback<HouseholdResponse>() {
                @Override
                public void onResponse(Call<HouseholdResponse> call, Response<HouseholdResponse> response) {
                    Log.e("Households", String.valueOf(response.code()));
                    adapt.addAll(response.body().getHouseholds());
                    adapt.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<HouseholdResponse> call, Throwable t) {

                }
            });


        }
    }


}
