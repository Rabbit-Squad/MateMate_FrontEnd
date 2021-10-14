package org.matemate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

//각 신청 클릭시 나타나는 detail 신청정보 다루는 프래그먼트
public class NotificationDetailFragment extends Fragment {
    Button reject;
    ServiceApi serviceApi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.request_approval, container, false);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);

        reject = rootView.findViewById(R.id.approval_reject_btn);
        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApproveData data = new ApproveData(2);
                
            }
        });
    }


    //승인 & 거절
    public void approve(int isApprove, ApproveData data) {
        serviceApi.approve(isApprove, data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {

            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {

            }
        });
    }

}
