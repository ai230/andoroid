package com.example.yamamotoai.runtimepermissionexample;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button check_permission, reqest_permission;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        check_permission = (Button) findViewById(R.id.btn_check);
        reqest_permission = (Button) findViewById(R.id.btn_request);

        check_permission.setOnClickListener(this);
        reqest_permission.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {

        int id = view.getId();

        switch (id)
        {
            case R.id.btn_check:

                if(checkPermission()){
                    Toast.makeText(this, "PERMISSION IS ALREADY GRANTED", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "PERMISSION IS NOT GRANTED, REQUEST THE PERMISSION", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.btn_request:

                if(!checkPermission()){

                    requestPermision();

                }else{

                    Toast.makeText(this, "PERMISSION IS ALREADY GRANTED", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }

    private boolean checkPermission(){

        int request = ContextCompat.checkSelfPermission(
                getApplicationContext(),
                CAMERA);
        boolean return_request;
        if (request == PackageManager.PERMISSION_GRANTED){
            return_request = true;
        }else{
            return_request = false;
        }

        return return_request;
    }

    private void requestPermision(){

        ActivityCompat.requestPermissions(this, new String[] {CAMERA}, 200);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //if it is accepted those will be 200
        boolean camera_accepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;

        if(camera_accepted){
            Toast.makeText(this, "ACCEPTED", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "NOT ACCEPTED", Toast.LENGTH_SHORT).show();
            //M == marshmallow
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(shouldShowRequestPermissionRationale(CAMERA)){
                    showMessage("You need to allow to access for camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                                requestPermision();
                            }
                        }
                    });
                }

            }
        }
    }

    //check if SDK >= 23
    private void showMessage(String explenation, DialogInterface.OnClickListener listener){

        new AlertDialog.Builder(MainActivity.this)
                .setMessage(explenation)
                .setPositiveButton("OK",listener)
                .setNegativeButton("CANCEL",null)
                .create()
                .show();

    }
}
