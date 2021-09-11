package org.matemate;
// location용 recyclerview에 넣을 데이터
public class Location {
    String placeName;
    String detail;

    public String getPlaceName() {
        return placeName;
    }

    public String getDetail() {
        return detail;
    }

    public Location(String placeName, String detail) {
        this.placeName = placeName;
        this.detail = detail;
    }
}
