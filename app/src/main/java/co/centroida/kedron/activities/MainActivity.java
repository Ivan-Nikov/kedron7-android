package co.centroida.kedron.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

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
    Button refresh;
    EditText filter_text;
    TextView text;
    ListView buildingsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buildingsAdapter = new BuildingsAdapter(this, 1);

        if(ServiceProvider.hasToken()){
            IBuildingService service = ServiceProvider.getBuildingService();
            Call<BuildingResponse> call = service.getBuildingsTop(30, 0, null, null);

            call.enqueue(new Callback<BuildingResponse>() {
                @Override
                public void onResponse(Call<BuildingResponse> call, Response<BuildingResponse> response) {
                    if(response.isSuccess()){
                        text = (TextView) findViewById(R.id.header);
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
            filter_text = (EditText) findViewById(R.id.filter_text);

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