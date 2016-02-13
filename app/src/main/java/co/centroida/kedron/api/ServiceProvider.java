package co.centroida.kedron.api;

import android.util.Log;

import java.io.IOException;

import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.Token;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static final String apiAddress = "http://kedronka.azurewebsites.net";
    private static String token = "";
    //TODO: save the token on the device till the expiration date
    private static boolean isTokenPresent = false;

    private static Retrofit retrofit;
    private static OkHttpClient client;

    private static IKedronService kedronService;
    private static IBuildingService buildingService;

    private static Building building;

    public static boolean hasToken(){
        return isTokenPresent;
    }

    public static <S> S createService(Class<S> serviceClass) throws Exception{
        if(isTokenPresent)
            return retrofit.create(serviceClass);
        else
            throw new Exception("Token is missing");
    }

    public static IBuildingService getBuildingService(){
        if(buildingService == null)
            buildingService = retrofit.create(IBuildingService.class);
        return  buildingService;
    }

    public static IKedronService getKedronService(){
        if(kedronService == null)
            kedronService = retrofit.create(IKedronService.class);
        return kedronService;
    }

    public static void init(){
        //Create an HTTP client
        client = new OkHttpClient();

        //Construct a HTTP builder
        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(apiAddress)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Form an API to receive a token
        kedronService = retrofit.create(IKedronService.class);
    }


    public static void requestToken(String username, String password){
        Call<Token> call = kedronService.getToken("password", "icaka@icaka.bg", "123456q");
        try {
            token = "Bearer " + call.execute().body().getToken();
            Log.i("TOKEN", token);
            Log.i("TOKEN", "Token received");
            isTokenPresent = true;
        } catch (IOException e) {
            Log.e("TOKEN", "Failed to retrieve the token");
        }
    }

    private static void bindToken() {
        if (token != null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", token)
                            .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });

            client = httpClient.build();

            retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(apiAddress)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            //Form an API to receive a token
            kedronService = retrofit.create(IKedronService.class);
            Log.i("TOKEN", "The token is bound to the client");
            isTokenPresent = true;
        }
    }


}
