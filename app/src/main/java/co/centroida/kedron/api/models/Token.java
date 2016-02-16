package co.centroida.kedron.api.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rimmustafin on 2/12/16.
 */

public class Token {
    private String access_token;
    private String token_type;
    private int expires_in;
    private String userName;

    //TODO: cast to a Date object
    @SerializedName(".issued")
    private String issued;
    @SerializedName(".expires")
    private String expires;

    public String getToken() {
        return access_token;
    }
    public void setToken(String access_token) { this.access_token = access_token; };
}
