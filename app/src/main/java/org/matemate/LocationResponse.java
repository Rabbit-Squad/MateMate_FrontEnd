package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse{
    @SerializedName("documents")
    List<Place> documents;
}

class Place {
    @SerializedName("place_name")
    String place_name; //장소 or 업체명
    @SerializedName("address_name")
    String address_name; //지번주소
    @SerializedName("road_address_name")
    String road_address_name; //도로명 주소
    @SerializedName("phone")
    String phone; //전화번호
}