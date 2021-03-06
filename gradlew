package com.example.tutorial2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class GetPermission extends AppCompatActivity {

    public  final int REQUEST_CODE = 1;
    public  final String address = "http://www.grafik.computertalk.ir/android/product.php";
    public  int downloadSize=0;
    public  int fileSize = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_permission);


        if(Build.VERSION.SDK_INT >= 23)
        {
            checkMyPermissions();
            
        }

    }

    private void checkMyPermissions() {
        if (ContextCompat.checkSelfPermission(GetPermission.this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetPermission.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else if (ContextCompat.checkSelfPermission(GetPermission.this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(GetPermission.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_CODE);
        } else {
            Toast.makeText(GetPermission.this, "All permission Granted...", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case REQUEST_CODE:
            {
                if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(GetPermission.this,"Thank you...",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(GetPermission.this,"Access denied!!!",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    public void Downloader(String address)
    {
        try {
            URL url = new URL(address);
            HttpURLConnection urlConnection  = (HttpURLConnection)url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.connect();

            urlConnection.getContentLength();

            String downloadPath = Environment.getExternalStorageDirectory().getAbsolutePath()+"/download/";
            File file = new File(downloadPath,getFileName(address));
            file.mkdirs();

            FileOutputStream fileOutputStream = new FileOutputStream(file);
            InputStream inputStream = urlConnection.getInputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length=inputStream.read(buffer))>0)
            {
                fileOutputStream.write(buffer,0,length);
                downloadSize += length;
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getFileName(String address){

        String[] name = address.split("/");

        return name[name.length-1];
    }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          