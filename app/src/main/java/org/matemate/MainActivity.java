package org.matemate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditFragment editFragment = new EditFragment();

    private ServiceApi serviceApi;
    Gson gson = new GsonBuilder().setDateFormat("HH:mm:ss").create();
    List<ListData> Lists = new ArrayList();
    List<Post> Posts = new ArrayList();
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
        ImageView menu = findViewById(R.id.menu_button);
        final DrawerLayout drawer = findViewById(R.id.main_layout);

        serviceApi.getList().enqueue(new Callback<ListResponse>() {
            @Override
            public void onResponse(Call<ListResponse> call, Response<ListResponse> response) {
                System.out.println(response.toString());
                ListResponse listResponse = response.body();
                try {
                    Lists = listResponse.getData();
                    //adapter = new PostAdapter(Lists);

                    for(int i=0; i<Lists.size(); i++) {
                        Posts.add(new Post(Lists.get(i).getNickname(), Lists.get(i).getDeadline().toString(), Lists.get(i).getLocation(), Lists.get(i).getMin_num(), Lists.get(i).getCur_num(), Lists.get(i).getTitle(), Lists.get(i).getContent(), Lists.get(i).getClosed()));
                    }
                    adapter = new PostAdapter(Posts); //adapter설정 + itemCount도 7인것 확인.
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
        recyclerView.setAdapter(adapter); //setadapter도 완료..
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.LEFT);
            }
        });

        final FloatingActionButton addPost = findViewById(R.id.add_post);
        addPost.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, editFragment).commit();
            }
        });

    }

    @Override
    public void onBackPressed() {
        getSupportFragmentManager().beginTransaction().remove(editFragment).commit();
        getSupportFragmentManager().popBackStack();
    }
}
