package co.centroida.kedron.api.models;

/**
 * Created by lkz on 16-Feb-16.
 */
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class HouseholdResponse {
    @SerializedName("Items")
    private List<Household> households;
    @SerializedName("Count")
    private int count;

    public HouseholdResponse() {
        households = new ArrayList<Household>();
    }

    public List<Household> getHouseholds() {

        return households;
    }
    public int getCount() {
        return  count;
    }
}
