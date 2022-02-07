package org.matemate.Participate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.matemate.Interface.OnBackPressedListener;
import org.matemate.Notification.NotificationAdapter;
import org.matemate.Notification.NotificationData;
import org.matemate.Notification.NotificationResponse;
import org.matemate.R;
import org.matemate.Server.RetrofitClient;
import org.matemate.Server.ServiceApi;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestNotification extends Fragment implements OnBackPressedListener {
    FragmentManager fragmentManager;
    ServiceApi serviceApi;
    List<NotificationData> Data;
    NotificationAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notification, container, false);
        initUI(rootView);
        return rootView;
    }

    public void initUI(ViewGroup rootView) {
        fragmentManager = getActivity().getSupportFragmentManager();
        recyclerView = rootView.findViewById(R.id.notification_list);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        int userIdx = sharedPreferences.getInt("userIdx", -1);
        serviceApi = RetrofitClient.getClient().create(ServiceApi.class); //test용으로 userIdx 2 집어넣음
        serviceApi.getNotification(2).enqueue(new Callback<NotificationResponse>() {
            @Override
            public void onResponse(Call<NotificationResponse> call, Response<NotificationResponse> response) {
                NotificationResponse notificationResponse = response.body();
                try {
                    Data = notificationResponse.getData();
                    Collections.reverse(Data);
                    adapter = new NotificationAdapter(Data);
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    /*
                    adapter.setOnItemClickListener(new OnNotificationClickListener(){
                        @Override
                        public void onItemClick(NotificationAdapter.ViewHolder holder, View view, int position) {

                        }
                    });
                    */
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<NotificationResponse> call, Throwable t) {
                Log.e("서버 에러", t.getMessage());
                t.printStackTrace();
            }
        });
    }


    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(RequestNotification.this).commit();
        fragmentManager.popBackStack();
    }
}
