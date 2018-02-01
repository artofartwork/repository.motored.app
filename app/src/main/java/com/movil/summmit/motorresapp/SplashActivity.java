package com.movil.summmit.motorresapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.movil.summmit.motorresapp.Storage.PreferencesHelper;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class SplashActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 3000;
    private final int MY_PERMISSIONS = 100;
    private View container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        container=findViewById(R.id.container);

        //FilesControl carpeta = new FilesControl();
        //carpeta.getAlbumStorageDir(PreferencesHelper.getCarpetaGeneralNombre(this));

        //isStoragePermissionGranted();

        //mayRequestStoragePermission();

        final TimerTask task = new TimerTask()
        {
            @Override
            public void run() {
                Intent intent;
             //   Bundle bundle= new Bundle();
               // bundle.putString("KEY","VALOR");

                intent =new Intent(SplashActivity.this, LoginActivity.class);
               boolean session= PreferencesHelper.isSignedIn(SplashActivity.this);
               if(session)
                {
                    intent=new Intent(SplashActivity.this, ListaInformesActivity.class);
                }else {

                    intent = new Intent(SplashActivity.this, LoginActivity.class);


                }

                startActivity(intent);
                finish();
            }
        };

        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
    /*private boolean mayRequestStoragePermission() {

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
            return true;

        if((checkSelfPermission(WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED))
            return true;

        if((shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)) || (shouldShowRequestPermissionRationale(CAMERA))){
            Snackbar.make(container, "Los permisos son necesarios para poder usar la aplicación",
                    Snackbar.LENGTH_INDEFINITE).setAction(android.R.string.ok, new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.M)
                @Override
                public void onClick(View v) {
                    requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
                }
            });
        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA}, MY_PERMISSIONS);
        }

        return false;
    }*/
   /* public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                return true;
            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }
    }*/
}
