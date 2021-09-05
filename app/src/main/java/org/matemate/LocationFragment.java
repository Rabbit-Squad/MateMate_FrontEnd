package org.matemate;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class LocationFragment extends Fragment implements OnBackPressedListener{
    EditText locationInput;
    Geocoder geocoder;
    FragmentManager fragmentManager;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.location_search, container, false);
        locationInput = rootView.findViewById(R.id.location_input);
        fragmentManager = getActivity().getSupportFragmentManager();
        geocoder = new Geocoder(this.getContext());
        locationInput.addTextChangedListener(new TextWatcher() {
            //List<Address> list = new ArrayList<>();
            //String address = locationInput.getText().toString();
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
            for(int i = 0; i<list.size();i++) {
                //System.out.println(list.get(i).getAddressLine(0));
                System.out.println(list.get(i));
            }

            try{

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(LocationFragment.this).commit();
        fragmentManager.popBackStack();
    }
}
