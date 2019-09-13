package com.example.jannataragh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imgMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgMenu = (ImageView)findViewById(R.id.imgHambergerMenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView = (NavigationView)findViewById(R.id.navigationView);

        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(MainActivity.this," homw click", Toast.LENGTH_SHORT).show();

                    case R.id.list_product:
                        Toast.makeText(MainActivity.this," list product click",Toast.LENGTH_SHORT).show();
                    case R.id.basket:
                        Toast.makeText(MainActivity.this,"basket click",Toast.LENGTH_SHORT).show();
                    case R.id.setting:
                        Toast.makeText(MainActivity.this," setting click",Toast.LENGTH_SHORT).show();
                    case R.id.aboutUs:
                        Toast.makeText(MainActivity.this," about us click",Toast.LENGTH_SHORT).show();

                }
                return false;

            }
        });
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(Gravity.START))
        {
            drawerLayout.closeDrawer(Gravity.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}
