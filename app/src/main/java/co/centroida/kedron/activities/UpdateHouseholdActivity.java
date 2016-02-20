package co.centroida.kedron.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import co.centroida.kedron.R;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Household;
import co.centroida.kedron.api.services.IHouseholdService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateHouseholdActivity extends AppCompatActivity {
    IHouseholdService serv;
    Call<Household> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int i = 0;

        setContentView(R.layout.activity_update_household);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if(ServiceProvider.hasToken()) {
            serv = ServiceProvider.getHouseholdService();
            call = serv.getHouseholds(i);
            call.enqueue(new Callback<Household>() {
                @Override
                public void onResponse(Call<Household> call, Response<Household> response) {

                }

                @Override
                public void onFailure(Call<Household> call, Throwable t) {

                }
            });


        }
    }

}
