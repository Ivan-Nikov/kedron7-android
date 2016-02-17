package co.centroida.kedron.api.models;

/**
 * Created by lkz on 16-Feb-16.
 */
        import com.google.gson.annotations.SerializedName;

        import java.util.Date;

public class Debt {
    @SerializedName("Id")
    public int Id;
    @SerializedName("Value")
    public double Value;
    @SerializedName("IsPaid")
    public boolean IsPaid;
    @SerializedName("DateMade")
    public String DateMade;
    @SerializedName("DatePaid")
    public String DatePaid;
    @SerializedName("ExpenseTypeName")
    public String ExpenseTypeName;
    @SerializedName("HouseholdName")
    public String HouseholdName;

    public Debt (Debt A) {
        this.Id=A.Id;
        this.Value=A.Value;
        this.IsPaid=A.IsPaid;
        this.DateMade=A.DateMade;
        this.DatePaid=A.DatePaid;
        this.ExpenseTypeName=A.ExpenseTypeName;
        this.HouseholdName=A.HouseholdName;
    }

    public void Eq (Debt A) {
        this.Id=A.Id;
        this.Value=A.Value;
        this.IsPaid=A.IsPaid;
        this.DateMade=A.DateMade;
        this.DatePaid=A.DatePaid;
        this.ExpenseTypeName=A.ExpenseTypeName;
        this.HouseholdName=A.HouseholdName;
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

    public boolean isPaid() {
        return IsPaid;
    }

    public void setIsPaid(boolean isPaid) {
        IsPaid = isPaid;
    }

    public String getExpenseTypeName() {
        return ExpenseTypeName;
    }

    public void setExpenseTypeName(String expenseTypeName) {
        ExpenseTypeName = expenseTypeName;
    }

    public String getHouseholdName() {
        return HouseholdName;
    }

    public void setHouseholdName(String householdName) {
        HouseholdName = householdName;
    }
}

