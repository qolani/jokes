package com.example.jokes.ui.activities;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.jokes.R;
public class MainActivity extends AppCompatActivity {

    private final static int REQUEST_ID = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /****
     * Initialize view or any other component required by the view
     */
    private void OnInitialize(){

    }

    /****
     * Setup
     */
    private void OnsetUpNav(){

    }

    /****
     * Call all methods in the fragments
     */
    private void OnCallMethods(){

    }

    private void onRequest_Permissions() {
        String[] permission = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                 Manifest.permission.CAMERA};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[0]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[1]) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this.getApplicationContext(), permission[2]) == PackageManager.PERMISSION_GRANTED) {

        } else {
            ActivityCompat.requestPermissions(this, permission, REQUEST_ID);
        }
    }
}