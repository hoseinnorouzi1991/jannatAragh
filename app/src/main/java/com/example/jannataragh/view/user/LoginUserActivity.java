package com.example.jannataragh.view.user;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.jannataragh.R;
import com.example.jannataragh.view.registerUser.RegisterUserActivity;
import com.example.jannataragh.date.IStoreService;
import com.example.jannataragh.view.base.BaseRetrofit;
import com.example.jannataragh.view.base.BasetUrl;
import com.example.jannataragh.view.base.BasicAuthInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginUserActivity extends AppCompatActivity {

    TextView txtRegisterUser,txtForgetPassword,txtError;
    ImageView imgLoginBack;

    EditText edtPhoneNumber,edtPassword;

    RelativeLayout rlLogin;

    IStoreService mStoreService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

        imgLoginBack = (ImageView)findViewById(R.id.img_login_back);
        txtRegisterUser = (TextView)findViewById(R.id.txt_register_user);
        txtForgetPassword = (TextView)findViewById(R.id.txt_forget_pasword);
        txtError = (TextView)findViewById(R.id.txt_error);
        edtPhoneNumber = (EditText)findViewById(R.id.edt_phone_number);
        edtPassword = (EditText)findViewById(R.id.edt_password);
        rlLogin = findViewById(R.id.rl_login);

        txtError.setVisibility(View.GONE);

        rlLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtPhoneNumber.getText().equals("") || edtPassword.getText().equals(""))
                {
                    Toast.makeText(LoginUserActivity.this,
                            "لطفا نامه کاربری یا رمز عبور خود را وارد نمایید.",
                            Toast.LENGTH_LONG).show();

                    return;
                }

                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(new BasicAuthInterceptor(edtPhoneNumber.getText().toString().trim(),edtPassword.getText().toString().trim()))
                        .build();

                Retrofit retrofit = new Retrofit.Builder().baseUrl(BasetUrl.baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                mStoreService = retrofit.create(IStoreService.class);

                mStoreService.getLoginedInUser().enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {

                        if(!response.isSuccessful())
                        {
                            BaseRetrofit baseRetrofit = new BaseRetrofit(mStoreService,BasetUrl.baseUrl);
                            baseRetrofit.RetrofitExecute();
                            return;
                        }

                        if (edtPhoneNumber.getText().equals(response.body().getPhone_number()) &&
                                edtPassword.getText().equals(response.body().getPassword()))
                        {
                            Toast.makeText(LoginUserActivity.this,"نام کاربری و رمز عبور صحیحی می باشد.",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

            }
        });

        imgLoginBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtRegisterUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_user = new Intent(LoginUserActivity.this, RegisterUserActivity.class);
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
                    txtError.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtError.setVisibility(View.GONE);
                }
            }
        });

        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                txtError.setVisibility(View.GONE);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}

/*
animations:
https://mindorks.com/android/store/Animations/daimajia/androidviewanimations
*/