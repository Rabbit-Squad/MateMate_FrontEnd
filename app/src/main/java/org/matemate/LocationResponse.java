package org.matemate;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LocationResponse{
    @SerializedName("documents")
    List<Place> documents;

    public List<Place> getDocuments() {
        return documents;
    }
}
