package org.matemate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestNotification extends Fragment implements OnBackPressedListener {
    FragmentManager fragmentManager;
    ServiceApi serviceApi;
    Button reject;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notification, container, false);
        initUI(rootView);
        return rootView;
    }

    public void initUI(ViewGroup rootView) {
        fragmentManager = getActivity().getSupportFragmentManager();
        reject = rootView.findViewById(R.id.approval_reject_btn);
        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);
        serviceApi.getNotification(1).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {

            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {

            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(RequestNotification.this).commit();
        fragmentManager.popBackStack();
    }
}
