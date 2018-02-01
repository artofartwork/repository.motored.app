package com.movil.summmit.motorresapp.Request;

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

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by cgonzalez on 17/01/2018.
 */

public class ApiClienteInformes {
    private static final String API_BASE_URL="http://192.168.1.69:1515";

    private static ServicesApiInterface servicesApiInterface;
    private static OkHttpClient.Builder httpClient;

    public static ServicesApiInterface getMyApiClient() {

        if (servicesApiInterface == null) {

            Retrofit.Builder builder =new Retrofit.Builder()
                    .baseUrl(API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());
            httpClient =new OkHttpClient.Builder();
            httpClient.addInterceptor(interceptor());

            Retrofit retrofit = builder.client(httpClient.build()).build();
            servicesApiInterface = retrofit.create(ServicesApiInterface.class);
        }
        return servicesApiInterface;
    }

    public interface ServicesApiInterface {

        @Headers({
                "Content-Type: application/json"
        })
         @POST("/InformeTecnico/subirdata")
         Call<ReturnValue> sycInformeTecnico(@Body InformeTecnico informeTecnico,
                                             @Body List<InformeTecnicoAntecedente> informeTecnicoAntecedentes,
                                             @Body List<InformeTecnicoFalla> informeTecnicoFallas,
                                             @Body List<InformeTecnicoFallaxEmpleado> informeTecnicoFallaxEmpleados,
                                             @Body List<InformeTecnicoFallaDiagnostico> informeTecnicoFallaDiagnosticos,
                                             @Body List<InformeTecnicoFallaCausa> informeTecnicoFallaCausas,
                                             @Body List<InformeTecnicoFallaCorrectivos> informeTecnicoFallaCorrectivos,
                                             @Body List<InformeTecnicoConclusiones> informeTecnicoConclusiones,
                                             @Body List<InformeTecnicoRecomendaciones> informeTecnicoRecomendaciones,
                                             @Body InformeTecnicoAdjuntos informeTecnicoAdjuntos,
                                             @Body List<InformeTecnicoAdjuntosDetalle> informeTecnicoAdjuntosDetalles); //este funciona para subir data

        //este es para subir dta y archivos
        @Multipart
        @POST("/InformeTecnico/subirdata")
        Call<ReturnValue> syncInformeTecnico(
                @Part("dataJson") RequestBody dataJson,
                @Part MultipartBody.Part fileScanner,
                @Part MultipartBody.Part fileAceite,
                @Part MultipartBody.Part fileCombustible,
                @Part MultipartBody.Part fileVin,
                @Part MultipartBody.Part fileKmHoras

        );

        @Multipart
        @POST("/InformeTecnico/subirdata")
        Call<ReturnValue> syncInformeTecnicoDos(
                @Part("dataJson") RequestBody dataJson,
                @Part MultipartBody.Part fileScanner
        );

        @Multipart
        @POST("/InformeTecnico/uploadfiles")
        Call<ReturnValue> uploadfiles(
                @Part("dataJson") RequestBody dataJson,
                @Part MultipartBody.Part fileScanner
        );

        @Multipart
        @POST("/InformeTecnico/subirdataDos")
        Call<ReturnValue> uploadfilesmulti(
                @Part("dataJson") RequestBody dataJson,
                @Part List<MultipartBody.Part> files
        );


        //  @POST("/api/login")
        // Call<LogInResponse> login(@Body LogInRaw raw);


        //@POST("/api/users/register")
        //Call<UserEntity> register(@Body UserRaw raw);


        //@GET("/api/users/")
        //Call<UsersResponse> users();

        /*
        //v1/data/Notes
        @GET("/v1/data/Notes")
        Call<NotesResponse> notes();


        @Headers({
                "Content-Type: application/json",
                "application-id: B9D12B47-6B88-8471-FFAD-2B4FFD1EA100",
                "secret-key: 46C1AEC7-6BA7-D1C7-FF6A-FD9EA95C0C00",
                "application-type: REST"
        })
        @POST("/v1/data/Notes")
        Call<NotesResponse> addNote(@Body NoteRaw raw);*/

    }

    /*private static OkHttpClient.Builder client(){
        if(httpClient==null)httpClient=new OkHttpClient.Builder();
        return httpClient;
    }*/
    private  static HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return httpLoggingInterceptor;
    }

}
