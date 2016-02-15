package co.centroida.kedron.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.centroida.kedron.adapters.BuildingsAdapter;
import co.centroida.kedron.api.services.IBuildingService;

public class MainActivity extends AppCompatActivity {

    BuildingsAdapter buildingsAdapter;
    IBuildingService iBuildingService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



}