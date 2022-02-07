package org.matemate.LocationFind;

import com.google.gson.annotations.SerializedName;

import org.matemate.LocationFind.Place;

import java.util.List;

public class LocationResponse{
    @SerializedName("documents")
    List<Place> documents;

    public List<Place> getDocuments() {
        return documents;
    }
}
