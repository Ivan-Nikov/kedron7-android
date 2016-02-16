package co.centroida.kedron.api.services;

import co.centroida.kedron.api.models.Debt;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by rimmustafin on 2/16/16.
 */

public interface IDebtService {

    @PUT("api/debts/{id}pay")
    Call<String> payDebt(@Path("id") int id);

    @GET("api/Debts/{id}")
    Call<Debt> getDebt(@Path("id") int id);

    @DELETE("api/Debts/{id}")
    Call<String> deleteDebt(@Path("id") int id);

    @PUT("api/Debts/{id}")
    Call<Debt> updateDet(@Path("id") int id);
}
