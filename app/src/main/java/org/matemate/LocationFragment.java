package org.matemate;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LocationFragment extends Fragment implements OnBackPressedListener {
    EditText locationInput;
    List<Location> locations; // adapter에 넣을 배열.
    List<org.matemate.Place> placeList; //kakaoAPI로 받아온 placeList저장
    RecyclerView recyclerView;
    LocationAdapter adapter;
    KakaoAPI kakaoAPI;
    private final static String APP_KEY = BuildConfig.app_key;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.location_search, container, false);
        recyclerView = rootView.findViewById(R.id.location_view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager); // 레이아웃 매니저 설정
        locationInput = rootView.findViewById(R.id.location_input);
        kakaoAPI = KakaoRetrofit.searchLocation().create(KakaoAPI.class);

        locationInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchLocation();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) { }


    private void searchLocation() {
        String address = locationInput.getText().toString();
        kakaoAPI.searchLocation(APP_KEY, address).enqueue(new Callback<LocationResponse>() {
            @Override
            public void onResponse(Call<LocationResponse> call, Response<LocationResponse> response) {
                LocationResponse locationResponse = response.body();
                try {
                    placeList = locationResponse.getDocuments();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(placeList.size());
                if(placeList.size() > 0) {
                    locations = new ArrayList<>(); //adapter에 넣을 lists에 서버로부터 받아온 placeList 중 필요한 데이터만 간추려 넣음
                    for(int i = 0; i<placeList.size();i++) {
                        locations.add(new Location(placeList.get(i).place_name, placeList.get(i).getRoad_address_name()));
                    }
                }
                adapter = new LocationAdapter(getContext(), locations);
                recyclerView.setAdapter(adapter);
                adapter.setOnItemClickListener(new OnLocationClickListener() {
                    @Override
                    public void onItemClick(LocationAdapter.ViewHolder holder, View view, int position) {

                        Location location = adapter.getLocation(position);
                        String detail = location.getDetail();
                        String placeName = location.getPlaceName();

                        Intent intent = new Intent(getContext(), EditFragment.class);
                        intent.putExtra("detail", detail);
                        intent.putExtra("placeName", placeName);
                        startActivity(intent);

                    }
                });
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<LocationResponse> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

    }

    @Override
    public void onBackPressed() {
        getActivity().getSupportFragmentManager().beginTransaction().remove(LocationFragment.this).commit();
        getActivity().getSupportFragmentManager().popBackStack();
}

}
