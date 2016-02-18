package co.centroida.kedron.api.models;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rimmustafin on 2/18/16.
 */
public class Deposit {
    @SerializedName("Id")
    private int id;
    @SerializedName("Value")
    private double value;
    @SerializedName("DateCreated")
    private Date dateCreated;

    public Deposit(Deposit deposit){
        this.id = deposit.id;
        this.value = deposit.value;
        this.dateCreated = deposit.dateCreated;
    }

    public Deposit(int id, double value, Date dateCreated) {
        this.id = id;
        this.value = value;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
