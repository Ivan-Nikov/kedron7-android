package co.centroida.kedron.api;

import java.util.List;

import co.centroida.kedron.api.models.Building;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IBuildingService {
    @GET("api/buildings/{id}")
    Call<Building> getBuilding(@Path("id") Integer id);

    @GET("api/buildings")
    Call<List<Building>> getBuildings();
}
