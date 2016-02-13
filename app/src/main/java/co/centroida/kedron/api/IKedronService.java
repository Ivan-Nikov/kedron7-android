package co.centroida.kedron.api;

import co.centroida.kedron.api.models.Token;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface IKedronService {

    @Headers({"Accept: application/json"})
    @FormUrlEncoded
    @POST("token")
    Call<Token> getToken(@Field("grant_type") String grantType, @Field("username") String email, @Field("password") String password);

}