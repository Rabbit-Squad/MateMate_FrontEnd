package org.matemate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyPostFragment extends Fragment implements OnBackPressedListener{

    ServiceApi serviceApi= RetrofitClient.getClient().create(ServiceApi.class);
    RecyclerView recyclerView;
    DrawerLayout drawer;
    NavigationView detailMenu;
    ImageView menu;
    MyPostAdapter adapter;
    int userIdx;
    List<ListData> Lists;
    List<Post> Posts;
    FragmentManager fragmentManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_myposts, container, false);
        fragmentManager = getActivity().getSupportFragmentManager();
        recyclerView = rootView.findViewById(R.id.my_post_list);
        detailMenu = rootView.findViewById(R.id.detail_menu);
        drawer = rootView.findViewById(R.id.my_posts_layout);
        menu = rootView.findViewById(R.id.menu_button);
        LinearLayoutManager manager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        userIdx = sharedPreferences.getInt("userIdx", -1);
        initUI(rootView);
        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        Lists = new ArrayList();
        Posts = new ArrayList();
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });
        detailMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.my_posts) {

                }
                if(menuItem.getItemId() == R.id.notifications) {

                }
                if(menuItem.getItemId() == R.id.setting) {

                }
                drawer.closeDrawers();
                return false;
            }
        });
        getUserList();
    }

    private void getUserList() {
        serviceApi.getUserList(userIdx).enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                System.out.println(response.toString());
                ListResponse listResponse = response.body();
                try {
                    Lists = listResponse.getData();
                    for (int i = 0; i < Lists.size(); i++) {
                        Posts.add(new Post(Lists.get(i).getId(), Lists.get(i).getNickname(), Lists.get(i).getDeadline().toString(), Lists.get(i).getLocation(), Lists.get(i).getMin_num(), Lists.get(i).getCur_num(), Lists.get(i).getTitle(), Lists.get(i).getContent(), Lists.get(i).getClosed()));
                    }
                    Collections.reverse(Posts);
                    adapter = new MyPostAdapter(getContext(), Posts); //adapter설정 + itemCount도 7인것 확인.
                    recyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(getContext(), "서버 에러", Toast.LENGTH_SHORT).show();
                Log.e("서버 에러", t.getMessage());
                t.printStackTrace();
            }
        });


    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(MyPostFragment.this).commit();
        fragmentManager.popBackStack();
    }


}

