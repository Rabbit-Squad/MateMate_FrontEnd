package org.matemate;

import com.google.gson.annotations.SerializedName;

public class LoginData {
    @SerializedName("email")
    private String email;

    @SerializedName("pw")
    private String pw;

    public LoginData(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }
}
