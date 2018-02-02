package com.movil.summmit.motorresapp.LogicMethods;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.movil.summmit.motorresapp.Models.Enity.InformeTecnico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAdjuntosDetalle;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoAntecedente;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoConclusiones;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFalla;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCausa;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaCorrectivos;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaDiagnostico;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoFallaxEmpleado;
import com.movil.summmit.motorresapp.Models.Enity.InformeTecnicoRecomendaciones;
import com.movil.summmit.motorresapp.Request.ApiClienteInformes;
import com.movil.summmit.motorresapp.Request.ReturnValue;
import com.movil.summmit.motorresapp.Storage.Files.FilesControl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

/**
 * Created by cgonzalez on 31/01/2018.
 */

public class LogicGeneral {

    FilesControl filesControl = new FilesControl();

    public int syncInformeTecnico(InformeTecnico informeTecnico,
                                  List<InformeTecnicoAntecedente> informeTecnicoAntecedentes,
                                  List<InformeTecnicoFalla> informeTecnicoFallas,
                                  List<InformeTecnicoFallaxEmpleado> informeTecnicoFallaxEmpleados,
                                  List<InformeTecnicoFallaDiagnostico> informeTecnicoFallaDiagnosticos,
                                  List<InformeTecnicoFallaCausa> informeTecnicoFallaCausas,
                                  List<InformeTecnicoFallaCorrectivos> informeTecnicoFallaCorrectivos,
                                  List<InformeTecnicoConclusiones> informeTecnicoConclusiones,
                                  List<InformeTecnicoRecomendaciones> informeTecnicoRecomendaciones,
                                  InformeTecnicoAdjuntos informeTecnicoAdjuntos,
                                  List<InformeTecnicoAdjuntosDetalle> informeTecnicoAdjuntosDetalles)
    {

        try
        {

            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient()
                    .sycInformeTecnico(informeTecnico, informeTecnicoAntecedentes, informeTecnicoFallas, informeTecnicoFallaxEmpleados,informeTecnicoFallaDiagnosticos,
                                        informeTecnicoFallaCausas,informeTecnicoFallaCorrectivos, informeTecnicoConclusiones, informeTecnicoRecomendaciones,
                                       informeTecnicoAdjuntos, informeTecnicoAdjuntosDetalles);

            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call, Response<ReturnValue> response) {

                    Log.d("Send data informe", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {
                    Log.d("Send data informe", "error");

                }
            });

            return 1;
        }
        catch (Exception ex)
        {
            return 0;
        }


    }


    public int syncInformeTecnico(String data, File fileScanner, File fileAceite, File fileCombustible, File fileVin, File fileKmHoras)
    {
        try
        {
            RequestBody dataJson =
                    RequestBody.create(
                            okhttp3.MultipartBody.FORM, data);

            MultipartBody.Part bodyScanner = prepareFilePart(fileScanner, "fileScanner");
            MultipartBody.Part bodyAceite = prepareFilePart(fileAceite, "fileAceite");
            MultipartBody.Part bodyCombustible = prepareFilePart(fileCombustible, "fileCombustible");
            MultipartBody.Part bodyVin = prepareFilePart(fileVin, "fileVin");
            MultipartBody.Part bodyKmHoras = prepareFilePart(fileKmHoras, "fileKmHoras");

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

            MultipartBody.Part bodyScanner = prepareFilePart(fileScanner, "fileScanner");


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

    public int syncmultifiles(String data, List<File> files)
    {
        try
        {

            List<MultipartBody.Part> parts = new ArrayList<>();

            int count = 0;
            for (File objfile: files)
            {
                parts.add(prepareFilePart(objfile, "file_" + 0 + "" ));
                count++;
            }

            RequestBody description = createPartFromString(data);

            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient().uploadfilesmulti(description, parts);
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

    @NonNull
    private MultipartBody.Part prepareFilePart(File archivo , String name)
    {

        Uri fileUri = Uri.fromFile(archivo);
        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse(filesControl.getMimeType(fileUri.getPath())),
                        archivo
                );
       return MultipartBody.Part.createFormData(name, archivo.getName(), requestFile);
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                okhttp3.MultipartBody.FORM, descriptionString);
    }

    public void syncInformeTecnico(InformeTecnico informeTecnico,
                                   List<InformeTecnicoAntecedente> informeTecnicoAntecedentes,   List<InformeTecnicoFalla> informeTecnicoFallas,  List<InformeTecnicoConclusiones> informeTecnicoConclusionesList,
                                   List<InformeTecnicoRecomendaciones> informeTecnicoRecomendacionesList,    InformeTecnicoAdjuntos informeTecnicoAdjuntos   )
    {
        try
        {
            informeTecnico.setListaAntecedentes(informeTecnicoAntecedentes);
            informeTecnico.setListaFallas(informeTecnicoFallas);
            informeTecnico.setListaConclusiones(informeTecnicoConclusionesList);
            informeTecnico.setListaRecomendaciones(informeTecnicoRecomendacionesList);
            informeTecnico.setInformeTecnicoAdjuntos(informeTecnicoAdjuntos);


            Call<ReturnValue> call = ApiClienteInformes.getMyApiClient()
                    .sycInformeTecnicoother(informeTecnico);//, informeTecnicoAntecedentes);

            call.enqueue(new Callback<ReturnValue>() {
                @Override
                public void onResponse(Call<ReturnValue> call, Response<ReturnValue> response) {

                    Log.d("Send data informe", "success");
                }

                @Override
                public void onFailure(Call<ReturnValue> call, Throwable t) {
                    Log.d("Send data informe", "error");

                }
            });


        }
        catch (Exception e)
        {
            String msg = e.getMessage();
        }
    }
}
