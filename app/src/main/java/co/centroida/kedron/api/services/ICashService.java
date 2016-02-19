package co.centroida.kedron.api.services;

import java.util.Date;
import java.util.List;

import co.centroida.kedron.api.models.Debt;
import co.centroida.kedron.api.models.DebtResponse;
import co.centroida.kedron.api.models.Deposit;
import co.centroida.kedron.api.models.PaymentResponse;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by rimmustafin on 2/16/16.
 */

public interface ICashService {

    @PUT("api/debts/{id}/pay")
    Call<String> payDebt(@Path("id") int id);

    @GET("api/Debts/{id}")
    Call<Debt> getDebt(@Path("id") int id);

    @DELETE("api/Debts/{id}")
    Call<String> deleteDebt(@Path("id") int id);

    @PUT("api/Debts/{id}")
    Call<Debt> updateDet(@Path("id") int id);

    //TODO: Complete the call
    @GET("api/households/{id}/debts")
    Call<DebtResponse> getHouseholdDebts(@Path("id") int id, @Query("top") int top);

    @GET("api/households/{id}/payments")
    Call<PaymentResponse> getHouseholdPayments(@Path("id") int id, @Query("top") int top,
                                               @Query("skip") int skip,
                                               @Query("datePaidLowerBondary") String lower,
                                               @Query("datePaidUpperBoundary") String upper);

    @GET("api/households/{id}/deposits")
    Call<List<Deposit>> getHouseholdDeposits(@Path("id") int id);
}
