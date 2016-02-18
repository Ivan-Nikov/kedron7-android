package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rimmustafin on 2/18/16.
 */

//{
//        "Id": 1,
//        "Value": 2.0,
//        "AmountPaid": 3.0,
//        "DateMade": "2016-02-18T16:31:08.5532817+00:00",
//        "DatePaid": "2016-02-18T16:31:08.5532817+00:00",
//        "ExpenseTypeName": "sample string 5"
//        }

public class Payment {
    @SerializedName("Id")
    public int Id;
    @SerializedName("Value")
    public double Value;
    @SerializedName("DateMade")
    public String DateMade;
    @SerializedName("DatePaid")
    public String DatePaid;
    @SerializedName("ExpenseTypeName")
    public String ExpenseTypeName;

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

    public String getDateMade() {
        return DateMade;
    }

    public void setDateMade(String dateMade) {
        DateMade = dateMade;
    }

    public String getDatePaid() {
        return DatePaid;
    }

    public void setDatePaid(String datePaid) {
        DatePaid = datePaid;
    }

    public String getExpenseTypeName() {
        return ExpenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        ExpenseTypeName = expenseTypeName;
    }
}
