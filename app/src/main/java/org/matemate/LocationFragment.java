package org.matemate;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import net.daum.mf.map.api.MapReverseGeoCoder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import noman.googleplaces.Place;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;


public class LocationFragment extends Fragment implements OnBackPressedListener, PlacesListener {
    EditText locationInput;
    Geocoder geocoder;
    List<Location> locations;
    RecyclerView recyclerView;
    LocationAdapter adapter;

    private final static String API_KEY = BuildConfig.api_key;
    FragmentManager fragmentManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.location_search, container, false);
        recyclerView = rootView.findViewById(R.id.location_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager); // 레이아웃 매니저 설정
        locationInput = rootView.findViewById(R.id.location_input);
        fragmentManager = getActivity().getSupportFragmentManager();
        geocoder = new Geocoder(this.getContext());
        locationInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchLocation();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
    }

    private void searchLocation() {
        List<Address> list = new ArrayList<>();
        String address = locationInput.getText().toString();
        try{
            list = geocoder.getFromLocationName(address, 5);
        } catch(Exception e){
            e.printStackTrace();
        }
        if(list.size() > 0) {
            locations = new ArrayList<>();
            for(int i = 0; i<list.size();i++) {
                locations.add(new Location("임시", list.get(i).getAddressLine(0)));
            }
            adapter = new LocationAdapter(getContext(), locations);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(LocationFragment.this).commit();
        fragmentManager.popBackStack();
    }

    @Override
    public void onPlacesFailure(PlacesException e) {

    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(List<Place> places) {

    }

    @Override
    public void onPlacesFinished() {

    }
}
