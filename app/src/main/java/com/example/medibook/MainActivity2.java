package com.example.medibook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity2 extends AppCompatActivity{
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    ActionBarDrawerToggle drawerToggle;

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerLayout.open();
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.home:
                        startActivity(new Intent(MainActivity2.this,MainActivity2.class));
                        break;

                    case R.id.addPat:
                        startActivity(new Intent(MainActivity2.this,AddPatient.class));
                        break;

                    case R.id.delPat:Pat:
                        startActivity(new Intent(MainActivity2.this,DeletePatient.class));
                        break;

                    case R.id.searchPatient:
                        startActivity(new Intent(MainActivity2.this,SearchPatient.class));
                        break;

                    case R.id.viewPat:Pat:
                        startActivity(new Intent(MainActivity2.this,ViewAllPatient.class));
                        break;

                    case R.id.addHistory:
                        startActivity(new Intent(MainActivity2.this,AddHistory.class));
                        break;

                    case R.id.searchHistory:
                        startActivity(new Intent(MainActivity2.this, SearchHistory.class));
                        break;

                    case R.id.delDoc:
                        startActivity(new Intent(MainActivity2.this, DeleteDoctor.class));
                        break;

                    case R.id.addDoc:
                        startActivity(new Intent(MainActivity2.this,AddDoctor.class));
                        break;

                    case R.id.viewDoc:
                        startActivity(new Intent(MainActivity2.this,ViewAllDoctor.class));
                        break;
                }
            return false;
            }
            
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();

        }
    }
}