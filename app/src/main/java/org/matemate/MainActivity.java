package org.matemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditFragment editFragment = new EditFragment();
    MyPostFragment myPostFragment = new MyPostFragment();
    LocationFragment locationFragment = new LocationFragment();

    private ServiceApi serviceApi;
    Gson gson = new GsonBuilder().setDateFormat("HH:mm:ss").create();
    List<ListData> Lists;
    List<Post> Posts;
    PostAdapter adapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.post_list); //리사이클러뷰
        serviceApi = RetrofitClient.getClient().create(ServiceApi.class);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager); // 레이아웃 매니저 설정
        final ImageView menu = findViewById(R.id.menu_button);
        final DrawerLayout drawer = findViewById(R.id.main_layout);
        NavigationView detailMenu = findViewById(R.id.detail_menu);
        Lists = new ArrayList();
        Posts = new ArrayList();

        serviceApi.getList().enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                System.out.println(response.toString());
                ListResponse listResponse = response.body();
                try {
                    Lists = listResponse.getData();

                    for(int i=0; i<Lists.size(); i++) {
                        Posts.add(new Post(Lists.get(i).getId(), Lists.get(i).getNickname(), Lists.get(i).getDeadline().toString(), Lists.get(i).getLocation(), Lists.get(i).getMin_num(), Lists.get(i).getCur_num(), Lists.get(i).getTitle(), Lists.get(i).getContent(), Lists.get(i).getClosed()));
                    }
                    Collections.reverse(Posts);
                    adapter = new PostAdapter(getApplicationContext(), Posts); //adapter설정 + itemCount도 7인것 확인.
                    recyclerView.setAdapter(adapter);

                    adapter.setOnItemClickListener(new OnPostItemClickListener() {
                        @Override
                        public void onItemClick(PostAdapter.ViewHolder holder, View view, int position) {
                            Post post = adapter.getPost(position);
                            //Toast.makeText(getApplicationContext(), "게시물 선택:" + post.getTitle(), Toast.LENGTH_SHORT).show();

                            ParticipateFragment fragment = new ParticipateFragment();

                            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragment).commit();

                            //Fragment에 보낼 번들
                            Bundle item = new Bundle();
                            item.putString("nickname", post.getNickname());
                            item.putString("title", post.getTitle());
                            item.putString("location", post.getLocation());
                            item.putInt("cur_num", post.getCur_num());
                            item.putInt("min_num", post.getMin_num());
                            item.putString("contents", post.getContent());
                            item.putInt("id", post.getId());
                            fragment.setArguments(item);
                        }
                    });

                    adapter.notifyDataSetChanged();
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "서버 에러", Toast.LENGTH_SHORT).show();
                Log.e("서버 에러", t.getMessage());
                t.printStackTrace();
            }
        });

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
                    getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, myPostFragment).commit();
                }
                if(menuItem.getItemId() == R.id.notifications) {

                }
                if(menuItem.getItemId() == R.id.setting) {

                }
                drawer.closeDrawers();
                return false;
            }

        });

        final FloatingActionButton addPost = findViewById(R.id.add_post);
        addPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, editFragment).commit(); //header 제외한 부분에 프래그먼트 띄움
            }
        });

    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        for(Fragment fragment : fragmentList) {
            if(fragment instanceof OnBackPressedListener) {
                ((OnBackPressedListener)fragment).onBackPressed();
            }
        }
    }

    public void fragmentChange(int index) {
        if(index == 1) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_layout, locationFragment).commit();
        }
    }

}
