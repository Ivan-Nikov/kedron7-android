package co.centroida.kedron.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import co.centroida.kedron.R;
import co.centroida.kedron.api.ServiceProvider;
import co.centroida.kedron.api.models.Building;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!ServiceProvider.hasToken()){
            Intent login = new Intent(this, LoginActivity.class);
            startActivity(login);
        }
    }

}