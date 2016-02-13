package co.centroida.kedron.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import co.centroida.kedron.R;
import co.centroida.kedron.api.ServiceProvider;

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