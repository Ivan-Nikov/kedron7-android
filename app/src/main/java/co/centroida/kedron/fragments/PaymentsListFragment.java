package co.centroida.kedron.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.io.IOException;
import java.util.Date;

import co.centroida.kedron.adapters.PaymentsAdapter;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.PaymentResponse;
import co.centroida.kedron.api.services.ICashService;
import co.centroida.kedron.helpers.EndlessScrollListener;
import co.centroida.kedron.helpers.OnRequestEndlessListDataListener;
import co.centroida.kedron.helpers.SwipeRefreshListFragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PaymentsListFragment extends SwipeRefreshListFragment implements RefreshListFragment {
    private PaymentsAdapter paymentAdapter;
    private ICashService debtService;

    //Callback for the endless scroll
    EndlessScrollListener scrollListener = new EndlessScrollListener(5, 5,
            new OnRequestEndlessListDataListener() {
        @Override
        public void onLoadMore(int top, int skip) {
            loadPage(top, skip);
        }
    });

    private Date lower_date_paid = null;
    private Date upper_date_paid = null;
    private Date lower_date_made = null;
    private Date upper_date_made = null;

    private String searchString;

    private final static int max_skip = 5;

    @Override
    public void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        //TODO: receive a household id from an intent
        paymentAdapter = new PaymentsAdapter(getActivity().getApplicationContext(), 0);
        paymentAdapter.setNotifyOnChange(true);
        setListAdapter(paymentAdapter);

        loadPage(max_skip,0);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        getListView().setOnScrollListener(scrollListener);
        this.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updateList();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadPage(int count, int skip_top){
        if (ServiceProvider.hasToken()) {
            debtService = ServiceProvider.getCashService();

            Call<PaymentResponse> call = debtService.getHouseholdPayments(1, count, skip_top,
                    ServiceProvider.convertDate(lower_date_paid),
                    ServiceProvider.convertDate(upper_date_paid));


            call.enqueue(new Callback<PaymentResponse>() {
                @Override
                public void onResponse(Call<PaymentResponse> call, Response<PaymentResponse> response) {
                    if (response.isSuccess()) {
                        Log.d("Payment", "Response achieved");
                        paymentAdapter.addAll(response.body().getPayments());
                    } else {
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
        }else{
            //TODO: exit
        }
    }

    @Override
    public void updateList() {
        updateList(null, null);
    }

    public void updateList(Date lower, Date upper) {
        lower_date_paid = lower;
        upper_date_paid = upper;
        paymentAdapter.clear();
        loadPage(max_skip, 0);
        scrollListener.reset();
        setRefreshing(false);
    }

    @Override
    public void search(String query) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

    }




}