package org.matemate;

import com.google.gson.annotations.SerializedName;

public class JoinData {
    @SerializedName("nickname")
    public String nickname;

    @SerializedName("email")
    public String email;

    @SerializedName("pw")
    public String password;

    public JoinData(String nickname, String email, String password)
    {
        this.nickname = nickname;
        this.email = email;
        this.password = password;
    }
}
