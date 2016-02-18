package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rimmustafin on 2/18/16.
 */
public class PaymentResponse {
    @SerializedName("Items")
    private List<Payment> payments;
    @SerializedName("Count")
    private int count;

    public PaymentResponse() {
        payments = new ArrayList<Payment>();
    }

    public List<Payment> getPayments() {
        return payments;
    }
    public int getCount() {
        return  count;
    }
}
