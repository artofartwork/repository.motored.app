package com.movil.summmit.motorresapp;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.movil.summmit.motorresapp.Listeners.OnRequestListener;
import com.movil.summmit.motorresapp.LogicMethods.LogicMaestro;
import com.movil.summmit.motorresapp.LogicMethods.LogicSync;
import com.movil.summmit.motorresapp.LogicMethods.Repository;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.HelpMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.SyncMaestro;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Usuario;
import com.movil.summmit.motorresapp.Request.ApiClienteMaestros;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;
import com.movil.summmit.motorresapp.Storage.PreferencesHelper;
import com.movil.summmit.motorresapp.Storage.db.repository.MaestraRepository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends AppCompatActivity implements OnRequestListener{

    EditText eteUsername, etePassword;
    TextInputLayout tmpUser, tmpPass;
   // UsuarioRepository usuarioRepository;
    Repository repository;
    private View flayLoading, container;
    private final int MY_PERMISSIONS = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        repository = new Repository(this);
        //usuarioRepository = new UsuarioRepository(this);
        isStoragePermissionGranted();

        init();
        // showLoading();
    }

    public void init(){
        container=findViewById(R.id.container);
        flayLoading=findViewById(R.id.flayLoading);
        eteUsername = (EditText)findViewById(R.id.eteUsername);
        etePassword = (EditText)findViewById(R.id.etePassword);
        tmpUser = (TextInputLayout)findViewById(R.id.tmpUser);
        tmpPass = (TextInputLayout)findViewById(R.id.tmpPass);
    }

    public void Logueo(View view) {

        if (validateForm())
        {
            //showLoading();

            Usuario objUsuario = repository.usuarioRepository().LoginUser(eteUsername.getText().toString().trim(), etePassword.getText().toString().trim());
            if (objUsuario != null)
            {
                saveSession(objUsuario);
                Intent inte =new Intent(LoginActivity.this, ListaInformesActivity.class);
                startActivity(inte);

            }
            else
            {
                onMessageError();
            }

            // hideLoading();

        }




        //
    }
    private void saveSession(Usuario obj) {

        PreferencesHelper.saveSession(this, obj.getNombre(),obj.getPasswordHash(), obj.getIdUsuario());
    }

    public Boolean validateForm()
    {
        if (eteUsername.getText().toString().trim().isEmpty())
        {
            tmpUser.setError("Este campo es obligatorio.");
            return false;
        }
        if (etePassword.getText().toString().trim().isEmpty())
        {
            tmpPass.setError("Este campo es obligatorio.");
            return false;
        }

        return true;

    }

    private void showLoading(){
        flayLoading.setVisibility(View.VISIBLE);
    }
    private void hideLoading(){
        flayLoading.setVisibility(View.GONE);
    }

    public void onMessageError() {
        Snackbar snackbar = Snackbar
                .make(container,"¡USUARIO Y PASSWORD INCORRECTO!", Snackbar.LENGTH_LONG);

        snackbar.show();
    }
    public  boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23)
        {
            if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED )
            {
                return true;
            }
            else
            {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.INTERNET}, 1);

                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            return true;
        }




    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        setTitle("Login");
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_update:
                sincronizarDatos();
                break;

        }


        //return super.onOptionsItemSelected(item);
        return true;
    }

    public void sincronizarDatos(){

        showLoading();
        LogicMaestro logicMaestro = new LogicMaestro(this);
        logicMaestro.getProgressDialog(flayLoading);

        LogicSync logicSync = new LogicSync(this);
        logicSync.SyncMaestrosAud();


    }

    @Override
    public void OnRespuestaSyncMaestros(List<SyncMaestro> listaSync) {

        LogicMaestro logicMaestro = new LogicMaestro(this);
        logicMaestro.getProgressDialog(flayLoading);

        List<SyncMaestro> lstalocal = repository.syncMaestroRepository().findAll();
        if (lstalocal.size() == 0)
        {
            for (SyncMaestro obj:listaSync  )
            {
                repository.syncMaestroRepository().create(obj);
            }
        }
        else
        {
            for (SyncMaestro objSyn: listaSync )
            {
                SyncMaestro objlocal = repository.syncMaestroRepository().getMaestroSync(objSyn.getNombreTabla());

                if ( ! objSyn.getAudFechaModifica().equals(objlocal.getAudFechaModifica()))
                {
                    repository.syncMaestroRepository().update(objSyn);
                    switch (objSyn.getNombreTabla())
                    {
                        case HelpMaestro.MAESTRO_CASOTECNICO:
                            logicMaestro.SyncCasoTecnico();
                            break;
                        case HelpMaestro.MAESTRO_CLIENTE:
                            logicMaestro.SyncCliente();
                            break;
                        case HelpMaestro.MAESTRO_EMPLEADO:
                            logicMaestro.SyncEmpleado();
                            break;
                        case HelpMaestro.MAESTRO_EMPRESA:
                            logicMaestro.SyncEmpresa();
                            break;
                        case HelpMaestro.MAESTRO_MAESTRA:
                            logicMaestro.SyncMaestra();
                            break;
                        case HelpMaestro.MAESTRO_MAESTRAARGU:
                            logicMaestro.SyncMaestraArgu();
                            break;
                        case HelpMaestro.MAESTRO_MARCA:
                            logicMaestro.SyncMarca();
                            break;
                        case HelpMaestro.MAESTRO_MODELO:
                            logicMaestro.SyncModelo();
                            break;
                        case HelpMaestro.MAESTRO_USUARIO:
                            logicMaestro.SyncUsuario();
                            break;
                        case HelpMaestro.MAESTRO_VIN:
                            logicMaestro.SyncVin();
                            break;
                    }



                }

            }

        }
    }


   /* public int onMessageExitoSync() {
        Snackbar snackbar = Snackbar
                .make(container,"¡SINCRONIZACION EXITOSA!", Snackbar.LENGTH_LONG);

        snackbar.show();
        return  1;
    }*/

}
