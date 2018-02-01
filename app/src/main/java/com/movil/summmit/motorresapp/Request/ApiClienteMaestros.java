package com.movil.summmit.motorresapp.Request;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.CasoTecnico;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Cliente;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empleado;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Empresa;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Maestra;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.MaestraArgu;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Marca;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Modelo;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Usuario;
import com.movil.summmit.motorresapp.Models.Enity.Maestro.Vin;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by cgonzalez on 16/01/2018.
 */

public class ApiClienteMaestros {

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


        @GET("/Maestro/listaCasoTecnico")
        Call< List<CasoTecnico> > listaCasoTecnico();

        @GET("/Maestro/listaCliente")
        Call< List<Cliente> > listaCliente();

        @GET("/Maestro/listaEmpleado")
        Call< List<Empleado> > listaEmpleado();

        @GET("/Maestro/listaEmpresa")
        Call< List<Empresa> > listaEmpresa();

        @GET("/Maestro/listaMaestra")
        Call< List<Maestra> > listaMaestra();

        @GET("/Maestro/listaMaestraArgu")
        Call< List<MaestraArgu> > listaMaestraArgu();

        @GET("/Maestro/listaMarca")
        Call< List<Marca> > listaMarca();

        @GET("/Maestro/listaModelo")
        Call< List<Modelo> > listaModelo();

        @GET("/Maestro/listaUsuario")
        Call< List<Usuario> > listaUsuario();

        @GET("/Maestro/listaVin")
        Call< List<Vin> > listaVin();

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
    private  static  HttpLoggingInterceptor interceptor(){
        HttpLoggingInterceptor httpLoggingInterceptor= new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS);
        return httpLoggingInterceptor;
    }


}
