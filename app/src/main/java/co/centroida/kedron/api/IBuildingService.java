package co.centroida.kedron.api;

import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IBuildingService {

    /**
     * Returns the building of the particular ID
     * @param id The ID of the building
     * @return Building call
     */
    @GET("api/buildings/{id}")
    Call<Building> getBuilding(@Path("id") Integer id);

    /**
     * Returns first 10 buildings
     * @return Building response call
     */
    @GET("api/buildings")
    Call<BuildingResponse> getBuildings();

    /**
     * Returns, by default 10, buildings according to the query
     * @param top Sets size of the list
     * @param skip Sets the amount of skipped entries from the top
     * @param orderby Orders the list according to the API docs
     * @param filter Filters the list by Address and Location. Basic syntax Location:{Name} or
     *               Address:{Name}. Intersect query by commas
     * @return Building response call
     */
    @GET("api/Buildings")
    Call<BuildingResponse> getBuildingsTop(@Query("top") int top, @Query("skip") int skip,
                                           @Query("orderby") String orderby,
                                           @Query("filter")  String filter);
}
