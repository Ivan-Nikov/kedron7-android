package co.centroida.kedron.api.services;

import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import co.centroida.kedron.api.models.Household;
import co.centroida.kedron.api.models.HouseholdResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface IHouseholdService {


    @GET("api/household/{id}")
    Call<Household> getHouseholds(@Path("id") Integer id);


    @GET("api/households")
    Call<HouseholdResponse> getHouseholds();


    @GET("api/households")
    Call<HouseholdResponse> getHouseholdsTop(@Query("top") int top, @Query("skip") int skip,
                                           @Query("orderby") String orderby,
                                           @Query("filter") String filter);


    @POST("api/Households")
    Call<Household> createHousehold(@Body Household house);

    @PUT("api/households/{id}")
    Call<Household> upadteHousehold(@Body Household house, @Path("id") int id);


    @DELETE("api/households/{id}")
    Call<String> deleteHousehold(@Path("id") int id);

}
