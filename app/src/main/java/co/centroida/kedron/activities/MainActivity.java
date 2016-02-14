package co.centroida.kedron.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.BuildingsAdapter;
import co.centroida.kedron.api.IBuildingService;
import co.centroida.kedron.api.IKedronService;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BuildingsAdapter buildingsAdapter;
    IBuildingService iBuildingService;

    EditText search_text;
    ListView buildings_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildingsAdapter = new BuildingsAdapter(this, 1);

        search_text = (EditText) findViewById(R.id.search_text);
        buildings_view = (ListView) findViewById(R.id.buildings_view);
        buildings_view.setAdapter(buildingsAdapter);

        if(ServiceProvider.hasToken()){
            iBuildingService = ServiceProvider.getBuildingService();

            Call<BuildingResponse> call = iBuildingService.getBuildingsTop(30, 0, null, null);

            call.enqueue(new Callback<BuildingResponse>() {
                @Override
                public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                    buildingsAdapter.addAll(response.body().getBuildings());
                    buildingsAdapter.setNotifyOnChange(true);
                    buildingsAdapter.notifyDataSetChanged();
                }

                @Override
                public void onFailure(Call<BuildingResponse> call, Throwable t) {

                }
            });

            search_text.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Call<BuildingResponse> call = iBuildingService.getBuildingsTop(30, 0, null,
                            "Location:"+search_text.getText().toString());
                    Log.e("sd",search_text.getText().toString());
                    call.enqueue(new Callback<BuildingResponse>() {
                        @Override
                        public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                            buildingsAdapter.clear();
                            buildingsAdapter.addAll(response.body().getBuildings());
                            buildingsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<BuildingResponse> call, Throwable t) {

                        }
                    });


                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });

            search_text.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                    Call<BuildingResponse> call = iBuildingService.getBuildingsTop(30, 0, null,
                            "Location:"+search_text.getText().toString());
                    Log.e("sd",search_text.getText().toString());
                    call.enqueue(new Callback<BuildingResponse>() {
                        @Override
                        public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                            buildingsAdapter.clear();
                            buildingsAdapter.addAll(response.body().getBuildings());
                            buildingsAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onFailure(Call<BuildingResponse> call, Throwable t) {

                        }
                    });

                    return true;
                }
            });
        }
    }

    @Override
    protected void onStart(){
        super.onStart();

    }

}