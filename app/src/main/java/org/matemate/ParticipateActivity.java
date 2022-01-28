package org.matemate;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ParticipateActivity extends AppCompatActivity implements OnBackPressedListener{
    //게시물 정보 View
    TextView nickname;
    TextView title;
    Button report_btn;
    ImageView profile_img;
    TextView postlocation;
    TextView people;
    TextView text;

    //신청 정보 작성 View
    TextView participate_location;
    TextView time;
    TextView participate_message;
    Button participate_btn;
    Button cancel_btn;

    SharedPreferences requestSharedPreferences; //request를 위한 sharedPreference
    LocationFragment locationFragment = new LocationFragment();
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    FragmentManager fragmentManager;
    int postIdx;

    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_participate);

        nickname = findViewById(R.id.participate_nickname);
        title = findViewById(R.id.participate_title);
        report_btn = findViewById(R.id.edit_report_btn);
        profile_img = findViewById(R.id.participate_profile_img);
        postlocation = findViewById(R.id.participate_location);
        people = findViewById(R.id.participate_people);
        text = findViewById(R.id.participate_text);
        participate_location = findViewById(R.id.participate_edit_location);
        time = findViewById(R.id.participate_edit_time);
        participate_message = findViewById(R.id.participate_edit_text);
        participate_btn = findViewById(R.id.participate_submit_btn);
        cancel_btn = findViewById(R.id.participate_cancel_btn);

        fragmentManager = getSupportFragmentManager();
        requestSharedPreferences = getSharedPreferences("Request", Context.MODE_PRIVATE);

        SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);
        final int userIdx = sharedPreferences.getInt("userIdx", -1);
        // 참가할 글의 정보 불러오기

        Intent participateIntent = getIntent();
        final String postNickname = participateIntent.getStringExtra("nickname");
        final String postTitle = participateIntent.getStringExtra("title");
        final String postLocation = participateIntent.getStringExtra("location");
        final String postMember = participateIntent.getIntExtra("cur_num", -1)+"/"+participateIntent.getIntExtra("min_num", -1);
        final String postContent = participateIntent.getStringExtra("contents");

        postIdx = participateIntent.getIntExtra("id", -1);
        //participate_location.setText(participateIntent.getStringExtra("placeName") + " " + participateIntent.getStringExtra("detail"));
        participate_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //사용자의 현재 위치 입력
                SharedPreferences.Editor editor = requestSharedPreferences.edit(); //그 전 상태 저장
                //post 정보
                editor.putString("nickname", postNickname);
                editor.putString("title", postTitle);
                editor.putString("location", postLocation);
                editor.putString("people",postMember);
                editor.putString("content", postContent);

                // 참가자 정보
                editor.putString("time", time.getText().toString());
                editor.putString("content", participate_message.getText().toString());
                editor.commit();
                fragmentManager.beginTransaction().add(R.id.container, locationFragment).commit();
            }
        });

        String temp = requestSharedPreferences.getString("nickname", "");
        if (!temp.equals("")) { // requestSharedPreferences 가 비어있지 않음 (locationFragment 넘어갔다 온 경우)
            nickname.setText(requestSharedPreferences.getString("nickname", ""));
            title.setText(requestSharedPreferences.getString("title", ""));
            postlocation.setText(requestSharedPreferences.getString("location", ""));
            people.setText(requestSharedPreferences.getString("people", ""));
            text.setText(requestSharedPreferences.getString("content", ""));

            //Location 정보 기입
            Intent intent = getIntent();
            try {
                String locations = intent.getExtras().getString("detail") + " " +intent.getExtras().getString("placeName");
                participate_location.setText(locations);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            nickname.setText(postNickname);
            title.setText(postTitle);
            postlocation.setText(postLocation);
            people.setText(postMember);
            text.setText(postContent);
        }

        SharedPreferences.Editor editor = requestSharedPreferences.edit();
        editor.clear();
        editor.commit();

        //button 설정
        participate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData data = new RequestData(userIdx, participate_message.getText().toString(), time.getText().toString());
                startParticipate(postIdx, data);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    public void startParticipate(int postIdx, RequestData data) {
        service.sendRequest(postIdx, data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getStatus() == 200) {
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "요청 전송 오류", Toast.LENGTH_SHORT).show();
                Log.e("요청 전송 에러 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}