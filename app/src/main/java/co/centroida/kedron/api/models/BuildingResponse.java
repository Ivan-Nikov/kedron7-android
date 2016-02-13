package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rimmustafin on 2/13/16.
 */

public class BuildingResponse {
    @SerializedName("Items")
    private List<Building> buildings;
    @SerializedName("Count")
    private int count;

    public BuildingResponse() {
        buildings = new ArrayList<Building>();
    }

    public List<Building> getBuildings() {
        return buildings;
    }
    public int getCount() { return  count;}
}