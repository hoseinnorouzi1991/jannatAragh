package com.example.jannataragh.view.registerUser;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.jannataragh.R;

public class RegisterUserActivity extends AppCompatActivity {

    ImageView imgRegisterBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        imgRegisterBack = (ImageView)findViewById(R.id.img_register_back);

        imgRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
