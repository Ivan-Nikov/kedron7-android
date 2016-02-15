package co.centroida.kedron.api.services;

import co.centroida.kedron.api.models.Building;
import co.centroida.kedron.api.models.BuildingResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * An interface to create a retrofit service
 * Implement Building CRUD
 */
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

    /**
     * Creates a new building and uploads
     * @param building A building instance to upload
     * @return A new building with an ID
     */
    @POST("api/Buildings")
    Call<Building> createBuilding(@Body Building building);

    /**
     * Updates the building through a given ID
     * @param building A building instance to upload
     * @param id An ID of the building to update
     * @return An updated building with an ID
     */
    @PUT("api/Buildings/{id}")
    Call<Building> upadteBuilding(@Body Building building, @Path("id") int id);

    /**
     * Deletes the buiding by a given ID
     * @param id An ID of the building to delete
     * @return A response string with a status
     */
    @DELETE("api/Buildings/{id}")
    Call<String> deleteBuilding(@Path("id") int id);

}
