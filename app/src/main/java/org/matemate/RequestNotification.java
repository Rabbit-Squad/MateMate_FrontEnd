package org.matemate;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.audiofx.NoiseSuppressor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RequestNotification extends Fragment implements OnBackPressedListener {
    FragmentManager fragmentManager;
    NotificationDetailFragment fragment = new NotificationDetailFragment();
    ServiceApi serviceApi;
    // Button reject;
    List<NotificationData> Data;
    NotificationAdapter adapter;
    RecyclerView recyclerView;

    ImageView menu;
    DrawerLayout drawer;
    NavigationView detailMenu;

//    MyPostFragment myPostFragment = new MyPostFragment();
//    SettingFragment settingFragment = new SettingFragment();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_notification, container, false);
        initUI(rootView);
        return rootView;
    }

    public void initUI(ViewGroup rootView) {
        fragmentManager = getActivity().getSupportFragmentManager();
        // reject = rootView.findViewById(R.id.approval_reject_btn);
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
                    adapter.setOnItemClickListener(new OnNotificationClickListener(){
                        @Override
                        public void onItemClick(NotificationAdapter.ViewHolder holder, View view, int position) {
                            //fragmentManager.beginTransaction().replace(R.id.fragment_content, fragment).commit();
                            ((MainActivity) getActivity()).viewChange(1);
                        }
                    });
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

        detailMenu = rootView.findViewById(R.id.detail_menu);
        drawer = rootView.findViewById(R.id.notification_layout);
        menu = rootView.findViewById(R.id.notification_menu_button);

//        menu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawer.openDrawer(Gravity.LEFT);
//            }
//        });
//        detailMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
//            @Override
//            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
//                if(menuItem.getItemId() == R.id.my_posts) {
//                    fragmentManager.beginTransaction().replace(getId(), myPostFragment).commit();
//                    System.out.println("1");
//                }
//                if(menuItem.getItemId() == R.id.notifications) {
//                    System.out.println("2");
//                }
//                if(menuItem.getItemId() == R.id.setting) {
//                    fragmentManager.beginTransaction().replace(getId(), settingFragment).commit();
//
//                }
//                drawer.closeDrawers();
//                return false;
//            }
//        });

    }


    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(RequestNotification.this).commit();
        fragmentManager.popBackStack();
    }
}
