package co.centroida.kedron.api;

import java.util.List;

import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBuildingService {
    @GET("api/buildings/{id}")
    Call<Building> getBuilding(@Path("id") Integer id);

    @GET("api/buildings")
    Call<BuildingResponse> getBuildings();

    @GET("api/Buildings")
    Call<BuildingResponse> getBuildingsTop(@Query("top") int top, @Query("skip") int skip,
                                           @Query("orderby") String orderby,
                                           @Query("filter")  String filter);
}
