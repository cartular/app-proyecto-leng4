package com.varasccatalina.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //this line hide statusbar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        navigationView = findViewById(R.id.bottom_navigation);
        getSupportFragmentManager().beginTransaction().replace(R.id.body_container, new HomeFragment()).commit();
        navigationView.setSelectedItemId(R.id.bottom_home);

        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()

            {
                @Override
                public boolean onNavigationItemSelected (@NonNull MenuItem item){
                Fragment fragment = null;
                int itemId = item.getItemId();

                if (itemId == R.id.bottom_home) {
                    fragment = new HomeFragment();
                } else if (itemId == R.id.bottom_mapa) {
                    fragment = new MapaFragment();
                } else if (itemId == R.id.bottom_horario) {
                    fragment = new HorarioFragment();
                } else if (itemId == R.id.bottom_profile) {
                    Intent intent = new Intent(MainActivity.this, PerfilFragment.class);
                    startActivity(intent);
                }

                if (fragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.body_container, fragment).commit();
                }

                return true;
            }
            });

        }
}

