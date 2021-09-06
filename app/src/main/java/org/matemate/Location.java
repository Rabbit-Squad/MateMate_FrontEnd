package org.matemate;

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
