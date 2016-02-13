package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

public class Building {
    @SerializedName("Id")
    public int id;
    @SerializedName("Address")
    public String address;
    @SerializedName("Location")
    public String location;
    @SerializedName("FloorsCount")
    public int floors;

    public Building(Building building){
        this.id = building.getId();
        this.address = building.getAddress();
        this.location = building.getLocation();
        this.floors = building.getFloors();
    }

    public Building( ) {
        this.id = 0;
        this.address = "";
        this.location = "";
        this.floors = 0;
    }

    public String getLocation() {
        return location;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public int getFloors() {
        return floors;
    }

    public void from(Building building){
        this.id = building.getId();
        this.address = building.getAddress();
        this.location = building.getLocation();
        this.floors = building.getFloors();
    }
}
