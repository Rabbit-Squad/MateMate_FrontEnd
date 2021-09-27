package org.matemate;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditFragment extends AppCompatActivity implements OnBackPressedListener {

    EditText title_input;
    TextView location_input;
    EditText text_input;
    Spinner hour;
    Spinner minute;
    Spinner min_num_spinner;
    Button postBtn;
    String locations;
    FragmentManager fragmentManager;
    LocationFragment locationFragment = new LocationFragment();
    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_edit);
        fragmentManager = getSupportFragmentManager();
        title_input = findViewById(R.id.edit_title);
        location_input = findViewById(R.id.edit_location);
        text_input = findViewById(R.id.edit_text);
        postBtn = findViewById(R.id.edit_submit_btn);
        hour = findViewById(R.id.edit_time);
        minute = findViewById(R.id.edit_minute);
        min_num_spinner = findViewById(R.id.edit_min_num);
        location_input.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                fragmentManager.beginTransaction().replace(R.id.container, locationFragment).commit();
            }

        });

        Intent intent = getIntent();
        try {
            locations = intent.getExtras().getString("detail") + " " +intent.getExtras().getString("placeName");
            location_input.setText(locations);

        } catch (Exception e) {
            e.printStackTrace();
        }

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("login", Context.MODE_PRIVATE);

                int idx = sharedPreferences.getInt("userIdx", -1);

                String _hour = hour.getSelectedItem().toString();
                String _minute = minute.getSelectedItem().toString();

                int _min_num = Integer.parseInt(min_num_spinner.getSelectedItem().toString());

                String time = _hour + ":" + _minute + ":00";

                NewPostData data = new NewPostData(idx, time, location_input.getText().toString(), _min_num, title_input.getText().toString(), text_input.getText().toString());

//                if (location_input.getText().length() == 0 || locations == null || locations.length() == 0 || title_input.getText().toString() == null || text_input.getText().length() == 0 || text_input.getText().toString() == null) {
//                    Toast.makeText(getApplicationContext(), "모든 정보를 기재해주세요", Toast.LENGTH_SHORT).show();
//                }
//                else {
                    Intent intent = new Intent(EditFragment.this, MainActivity.class);
                    intent.putExtra("userIdx", idx);
                    intent.putExtra("time", time);
                    intent.putExtra("location", location_input.getText().toString());
                    intent.putExtra("min_num", _min_num);
                    intent.putExtra("title", title_input.getText().toString());
                    intent.putExtra("text", text_input.getText().toString());
                    startPosting(data);
                    startActivity(intent);
//                }
            }
        });
    }

    private void startPosting(NewPostData data) {
        service.addPost(data).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();
                Toast.makeText(getApplicationContext(), result.getMessage(), Toast.LENGTH_SHORT).show();
                if (result.getStatus() == 200) {
                    System.out.println("게시물 작성 성공!!");
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "게시물 작성 오류", Toast.LENGTH_SHORT).show();
                Log.e("게시물 작성 오류 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}
