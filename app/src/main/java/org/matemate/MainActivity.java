package org.matemate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    MyPostFragment myPostFragment = new MyPostFragment();
    SettingFragment settingFragment = new SettingFragment();
    RequestNotification requestNotification = new RequestNotification();
    private ServiceApi serviceApi;
    private long time = 0;

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
                        Posts.add(new Post(Lists.get(i).getId(), Lists.get(i).getNickname(), Lists.get(i).getDeadline().toString(), Lists.get(i).getLocation(), Lists.get(i).getMin_num() + 1, Lists.get(i).getCur_num(), Lists.get(i).getTitle(), Lists.get(i).getContent(), Lists.get(i).getClosed()));
                    }
                    Collections.reverse(Posts);
                    adapter = new PostAdapter(getApplicationContext(), Posts);
                    recyclerView.setAdapter(adapter);

                    adapter.setOnPostParticipateListener(new OnPostParticipateListener() {
                        @Override
                        public void onPostParticipateClick(PostAdapter.ViewHolder holder, View view, int position) {
                            Post post = adapter.getPost(position);

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
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, myPostFragment).commit();
                }
                if(menuItem.getItemId() == R.id.notifications) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, requestNotification).commit();
                }
                if(menuItem.getItemId() == R.id.setting) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, settingFragment).commit();
                }
                drawer.closeDrawers();
                return false;
            }

        });

        final FloatingActionButton addPost = findViewById(R.id.add_post);
        addPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, EditFragment.class);
                startActivity(intent);
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

        if(System.currentTimeMillis() - time >= 1500) {
            time = System.currentTimeMillis();
            Toast.makeText(getApplicationContext(), "종료하시려면 한번 더 눌러주세요!", Toast.LENGTH_SHORT).show();
        }
        else {
            finish();
        }
    }
}
