package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lkz on 16-Feb-16.
 */
public class DebtResponse {
    @SerializedName("Items")
    private List<Debt> debts;
    @SerializedName("Count")
    private int count;

    public DebtResponse() {

        debts = new ArrayList<Debt>();
    }

    public List<Debt> getDebts() {

        return debts;
    }
    public int getCount() {
        return  count;
    }
}
