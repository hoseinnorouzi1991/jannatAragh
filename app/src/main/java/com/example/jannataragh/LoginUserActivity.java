package com.example.jannataragh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LoginUserActivity extends AppCompatActivity {

    TextView txtRegisterUser,txtForgetPassword,txtError;

    EditText edtPhoneNumber,edtPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        txtRegisterUser = (TextView)findViewById(R.id.txt_register_user);
        txtForgetPassword = (TextView)findViewById(R.id.txt_forget_pasword);
        txtError = (TextView)findViewById(R.id.txt_error);
        edtPhoneNumber = (EditText)findViewById(R.id.edt_phone_number);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        txtError.setVisibility(View.GONE);

        txtRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_user = new Intent(LoginUserActivity.this,RegisterUserActivity.class);
                startActivity(intent_register_user);
            }
        });

        txtForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strPhoneNumber = edtPhoneNumber.getText().toString();

                if(strPhoneNumber.equals(""))
                {
                    YoYo.with(Techniques.FadeIn)
                            .duration(100)
                            .repeat(1)
                            .playOn(findViewById(R.id.txt_error));

                    txtError.setText("لطفا شماره موبایل را وارد کنید.");
                    YoYo.with(Techniques.Shake)
                            .duration(200)
                            .repeat(1)
                            .playOn(findViewById(R.id.edt_phone_number));


                }
                else
                {
                    txtError.setVisibility(View.GONE);
                }
            }
        });

    }
}

/*
animations:
https://mindorks.com/android/store/Animations/daimajia/androidviewanimations
*/