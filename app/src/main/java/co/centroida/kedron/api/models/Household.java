package co.centroida.kedron.api.models;


import com.google.gson.annotations.SerializedName;
public class Household {
    @SerializedName("Id")
    public int id;
    @SerializedName("BuildingId")
    public int BuildingId;
    @SerializedName("Name")
    public String Name;
    @SerializedName("Number")
    public int Number;
    @SerializedName("FloorNumber")
    public int FloorNumber;
    @SerializedName("AdultCount")
    public int AdultCount;
    @SerializedName("ChildrenCount")
    public int ChildrenCount;
    @SerializedName("BuildingFloors")
    public int BuildingFloors;

    public Household(Household B) {
        this.id = B.id;
        this.BuildingId=B.BuildingId;
        this.Name=B.Name;
        this.Number=B.Number;
        this.FloorNumber=B.FloorNumber;
        this.AdultCount=B.AdultCount;
        this.ChildrenCount=B.ChildrenCount;
        this.BuildingFloors=B.BuildingFloors;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(int buildingId) {
        BuildingId = buildingId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getNumber() {
        return Number;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getFloorNumber() {
        return FloorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        FloorNumber = floorNumber;
    }

    public int getAdultCount() {
        return AdultCount;
    }

    public void setAdultCount(int adultCount) {
        AdultCount = adultCount;
    }

    public int getChildrenCount() {
        return ChildrenCount;
    }

    public void setChildrenCount(int childrenCount) {
        ChildrenCount = childrenCount;
    }

    public int getBuildingFloors() {
        return BuildingFloors;
    }

    public void setBuildingFloors(int buildingFloors) {
        BuildingFloors = buildingFloors;
    }


    public void Eq(Household B) {
        this.id = B.id;
        this.BuildingId=B.BuildingId;
        this.Name=B.Name;
        this.Number=B.Number;
        this.FloorNumber=B.FloorNumber;
        this.AdultCount=B.AdultCount;
        this.ChildrenCount=B.ChildrenCount;
        this.BuildingFloors=B.BuildingFloors;
    }


}
