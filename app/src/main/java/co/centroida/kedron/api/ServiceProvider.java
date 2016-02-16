package co.centroida.kedron.api;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import java.io.IOException;

import co.centroida.kedron.api.models.Token;
import co.centroida.kedron.api.services.IBuildingService;
import co.centroida.kedron.api.services.IDebtService;
import co.centroida.kedron.api.services.IHouseholdService;
import co.centroida.kedron.api.services.IKedronService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceProvider {

    private static final String apiAddress = "http://kedronka.azurewebsites.net";
    private static final String token_storage = "TOKEN_STORAGE";

    private static boolean isTokenPresent = false;
    private static Token auth_token;
    private static String header_token;

    private static Retrofit retrofit;
    private static OkHttpClient client;

    private static IKedronService kedronService;
    private static IBuildingService buildingService;
    private static IHouseholdService householdService;
    private static IDebtService debtService;

    public static boolean hasToken() {
        return isTokenPresent;
    }

    public static IBuildingService getBuildingService() {
        if (buildingService == null) {
            buildingService = retrofit.create(IBuildingService.class);
        }
        return buildingService;
    }

    public static IKedronService getKedronService() {
        if (kedronService == null) {
            kedronService = retrofit.create(IKedronService.class);
        }
        return kedronService;
    }

    public static IHouseholdService getHouseholdService() {
        if (householdService == null) {
            householdService = retrofit.create(IHouseholdService.class);
        }
        return householdService;
    }

    public static IDebtService getDebtService() {
        if(debtService == null){
            debtService = retrofit.create(IDebtService.class);
        }
        return debtService;
    }

    public static void init() {
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

    public static boolean checkSavedToken(Context context) {
        //Check whether the token exists
        SharedPreferences preferences = context.getSharedPreferences(token_storage, 0);
        String storage_token = preferences.getString("token", "notoken");

        if (!storage_token.equals("notoken")) {
            Log.d("Service", "Using stored token");
            header_token = "Bearer " + storage_token;
            isTokenPresent = true;
            bindToken();
            return true;
        }
        return false;
    }

    public static void requestToken(String username, String password, Context context) {

        //Save the retrieved token
        SharedPreferences preferences = context.getSharedPreferences(token_storage, 0);
        SharedPreferences.Editor editor = preferences.edit();
        Call<Token> call = kedronService.getToken("password", username, password);

        try {
            Log.d("Service", "Using a new token");
            auth_token = call.execute().body();

            Log.d("Service", "Token received");

            isTokenPresent = true;
        } catch (IOException e) {
            Log.e("TOKEN", "Failed to retrieve the token", e);
        }

        header_token = "Bearer " + auth_token;

        editor.putString("token", auth_token.getToken());
        editor.apply();

        bindToken();
    }


    public static void bindToken() {
        if (header_token != null) {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            httpClient.addInterceptor(new Interceptor() {
                @Override
                public okhttp3.Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .header("Authorization", header_token)
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
            Log.i("Service", "The token is bound to the client");
            isTokenPresent = true;
        }
    }


}
