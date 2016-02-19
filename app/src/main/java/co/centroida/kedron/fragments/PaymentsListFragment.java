package co.centroida.kedron.fragments;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
public class PaymentsListFragment extends ListFragment implements IHouseholdBookFragment {
    private PaymentsAdapter paymentAdapter;
    private ICashService debtService;
    EndlessScrollListener scrollListener = new EndlessScrollListener();

    private Date lower_date = null;
    private Date upper_date = null;

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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void loadPage(int count, int skip_top){
        if (ServiceProvider.hasToken()) {

            Log.d("Debt", "Token is in place...");
            debtService = ServiceProvider.getCashService();

            Call<PaymentResponse> call = debtService.getHouseholdPayments(1, count, skip_top,
                    ServiceProvider.convertDate(lower_date),
                    ServiceProvider.convertDate(upper_date));

            Log.d("Debt", "A call is formed");

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
        }
    }


    @Override
    public void updateList() {
        updateList(null, null);
    }

    public void updateList(Date lower, Date upper) {
        lower_date = lower;
        upper_date = upper;
        paymentAdapter.clear();
        loadPage(max_skip, 0);
        scrollListener.reset();
    }

    @Override
    public void search(String query) {

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {


    }

    public class EndlessScrollListener implements AbsListView.OnScrollListener {
        private int currentPage = 0;
        private int previousTotal = 0;
        private boolean loading = true;

        public void reset(){
            currentPage = 0;
            previousTotal = 0;
        }

        public EndlessScrollListener() {
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            if (loading) {
                if (totalItemCount > previousTotal) {
                    loading = false;
                    previousTotal = totalItemCount;
                }
            }
            if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + 2)) {
                loading = true;
                currentPage++;
                loadPage(max_skip, currentPage*max_skip);
            }
        }

        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {

        }
    }

}