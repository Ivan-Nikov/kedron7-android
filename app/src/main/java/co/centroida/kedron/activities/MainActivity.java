package co.centroida.kedron.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import co.centroida.kedron.R;
import co.centroida.kedron.adapters.BuildingsAdapter;
import co.centroida.kedron.api.IBuildingService;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    BuildingsAdapter buildingsAdapter;
    Button refresh;
    Button delete;
    EditText filter_text;
    TextView text;
    ListView buildingsView;

    //TODO: Removce
    EditText address;
    EditText location;
    EditText floors_count;
    Button create_building;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.header);
        buildingsAdapter = new BuildingsAdapter(this, 1);

        if(ServiceProvider.hasToken()){
            IBuildingService service = ServiceProvider.getBuildingService();
            Call<BuildingResponse> call = service.getBuildingsTop(30, 0, null, null);

            call.enqueue(new Callback<BuildingResponse>() {
                @Override
                public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                    if(response.isSuccess()){
                        text.setText("Total: " + response.body().getCount());

                        buildingsAdapter.addAll(response.body().getBuildings());
                        buildingsAdapter.notifyDataSetChanged();

                        buildingsView = (ListView) findViewById(R.id.buildings_view);
                        buildingsView.setAdapter(buildingsAdapter);
                    }
                }

                @Override
                public void onFailure(Call<BuildingResponse> call, Throwable t) {
                }
            });

            refresh = (Button) findViewById(R.id.refresh_button);
            delete = (Button) findViewById(R.id.test_delete);
            filter_text = (EditText) findViewById(R.id.filter_text);

            final Context cont = this;

            address = (EditText) findViewById(R.id.create_address);
            location= (EditText) findViewById(R.id.create_location);
            floors_count = (EditText) findViewById(R.id.create_floor);;
            create_building = (Button) findViewById(R.id.test_create);

            create_building.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String add = address.getText().toString();
                    String loc = location.getText().toString();
                    int flrs = Integer.parseInt(floors_count.getText().toString());

                    IBuildingService buildingService = ServiceProvider.getBuildingService();
                    Call<Building> call = buildingService.createBuilding( new Building(add, loc, flrs));

                    call.enqueue(new Callback<Building>() {
                        @Override
                        public void onResponse(Call<Building> call, Response<Building> response) {
                            text.setText("Created: " + response.body().getLocation());
                        }

                        @Override
                        public void onFailure(Call<Building> call, Throwable t) {

                        }
                    });
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    IBuildingService service = ServiceProvider.getBuildingService();
                    Log.e("sda", filter_text.getText().toString());
                    int del = Integer.parseInt(filter_text.getText().toString());


                    Call<String> call = service.deleteBuilding(del);

                    call.enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            text.setText(response.body());
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }
            });

            refresh.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IBuildingService service = ServiceProvider.getBuildingService();
                    String filter = "Location:" + filter_text.getText().toString();

                    Log.i("sd", filter);

                    Call<BuildingResponse> call = service.getBuildingsTop(30, 0, null, filter);

                    call.enqueue(new Callback<BuildingResponse>() {
                        @Override
                        public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                            if(response.isSuccess()){
                                text = (TextView) findViewById(R.id.header);
                                text.setText("Total: " + response.body().getCount());

                                buildingsAdapter.clear();
                                buildingsAdapter.addAll(response.body().getBuildings());
                                buildingsAdapter.notifyDataSetChanged();

                                buildingsView = (ListView) findViewById(R.id.buildings_view);
                                buildingsView.setAdapter(buildingsAdapter);
                            }
                        }

                        @Override
                        public void onFailure(Call<BuildingResponse> call, Throwable t) {
                        }
                    });

                }
            });
        }
    }

}