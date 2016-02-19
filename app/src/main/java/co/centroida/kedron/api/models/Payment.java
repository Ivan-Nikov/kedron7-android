package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by rimmustafin on 2/18/16.
 */

public class Payment {
    @SerializedName("Id")
    private int Id;
    @SerializedName("Value")
    private double Value;
    @SerializedName("DateMade")
    private Date DateMade;
    @SerializedName("DatePaid")
    private Date DatePaid;
    @SerializedName("ExpenseTypeName")
    private String ExpenseTypeName;

    public Payment (Payment A) {
        this.Id=A.Id;
        this.Value=A.Value;
        this.DateMade=A.DateMade;
        this.DatePaid=A.DatePaid;
        this.ExpenseTypeName=A.ExpenseTypeName;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public double getValue() {
        return Value;
    }

    public void setValue(double value) {
        Value = value;
    }

    public String getExpenseTypeName() {
        return ExpenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        ExpenseTypeName = expenseTypeName;
    }

    public Date getDateMade() {
        return DateMade;
    }

    public void setDateMade(Date dateMade) {
        DateMade = dateMade;
    }

    public Date getDatePaid() {
        return DatePaid;
    }

    public void setDatePaid(Date datePaid) {
        DatePaid = datePaid;
    }
}
