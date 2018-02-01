package com.movil.summmit.motorresapp.LogicMethods;

import android.net.Uri;
import android.util.Log;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Request.ApiClienteInformes;
import com.movil.summmit.motorresapp.Request.ReturnValue;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cgonzalez on 31/01/2018.
 */

public class LogicGeneral {

    FilesControl filesControl = new FilesControl();

    public int syncInformeTecnico(String data, File fileScanner, File fileAceite, File fileCombustible, File fileVin, File fileKmHoras)
    {
        try
        {
            RequestBody dataJson =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, data);

            MultipartBody.Part bodyScanner = getObjtectReuest(fileScanner, "fileScanner");
            MultipartBody.Part bodyAceite = getObjtectReuest(fileAceite, "fileAceite");
            MultipartBody.Part bodyCombustible = getObjtectReuest(fileCombustible, "fileCombustible");
            MultipartBody.Part bodyVin = getObjtectReuest(fileVin, "fileVin");
            MultipartBody.Part bodyKmHoras = getObjtectReuest(fileKmHoras, "fileKmHoras");

            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().syncInformeTecnico(dataJson, bodyScanner, bodyAceite, bodyCombustible, bodyVin, bodyKmHoras);
            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call, Response<ReturnValue> response) {

                    Log.v("Upload", "success");

                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {

                    Log.e("Upload error:", t.getMessage());
                    Log.d("Upload logic egenral:", t.getMessage());
                }
            });


            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public int syncDatainforme(String data, File fileScanner)
    {
        try
        {
            RequestBody dataJson =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, data);

            MultipartBody.Part bodyScanner = getObjtectReuest(fileScanner, "fileScanner");


            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().syncInformeTecnicoDos(dataJson, bodyScanner);
            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call, Response<ReturnValue> response) {

                    Log.d("Upload", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {

                    Log.d("Upload error:", t.getMessage());

                }
            });


            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    public int syncmultifiles(String data, List<File> files, File fileScanner)
    {
        try
        {
            RequestBody dataJson =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, data);

           MultipartBody.Part bodyScanner = getObjtectReuest(fileScanner, "fileScanner");


            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().syncInformeTecnicoDos(dataJson, bodyScanner);
            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call, Response<ReturnValue> response) {

                    Log.d("Upload", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {

                    Log.d("Upload error:", t.getMessage());

                }
            });


            return 1;
        }
        catch (Exception e)
        {
            return 0;
        }
    }

    private MultipartBody.Part getObjtectReuest(File archivo , String name)
    {

        Uri fileUri = Uri.fromFile(archivo);

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(filesControl.getMimeType(fileUri.getPath())),
                        archivo
                );

       return MultipartBody.Part.createFormData(name, archivo.getName(), requestFile);

        //return body;
    }
}
