package org.matemate.LocationFind;

import com.google.gson.annotations.SerializedName;

public class Place {
    @SerializedName("place_name")
    public String place_name; //장소 or 업체명
    @SerializedName("address_name")
    public String address_name; //지번주소
    @SerializedName("road_address_name")
    public String road_address_name; //도로명 주소
    @SerializedName("phone")
    public String phone; //전화번호

    public Place(String place, String address, String road_address, String phone) {
        this.place_name = place;
        this.address_name = address;
        this.road_address_name = road_address;
        this.phone = phone;
    }

    public String getPlace_name() {
        return place_name;
    }

    public String getAddress_name() {
        return address_name;
    }

    public String getRoad_address_name() {
        return road_address_name;
    }

    public String getPhone() {
        return phone;
    }
}
