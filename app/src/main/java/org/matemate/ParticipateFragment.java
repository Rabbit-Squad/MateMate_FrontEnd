package org.matemate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

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

import org.w3c.dom.Text;

public class ParticipateFragment extends Fragment implements OnBackPressedListener{
    //게시물 정보 View
    TextView nickname;
    TextView title;
    Button report_btn;
    ImageView profile_img;
    TextView location;
    TextView people;
    TextView text;

    //신청 정보 작성 View
    TextView participate_location;
    TextView time;
    TextView participate_message;
    Button participate_btn;
    Button cancel_btn;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);
    FragmentManager fragmentManager;
    int postidx;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_participate, container, false);

        initUI(rootView);

        fragmentManager = getActivity().getSupportFragmentManager();

        //Bundle로 받아온 데이터 세팅
        setPostToUI();

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        nickname = rootView.findViewById(R.id.participate_nickname);
        title = rootView.findViewById(R.id.participate_title);
        report_btn = rootView.findViewById(R.id.edit_report_btn);
        profile_img = rootView.findViewById(R.id.participate_profile_img);
        location = rootView.findViewById(R.id.participate_location);
        people = rootView.findViewById(R.id.participate_people);
        text = rootView.findViewById(R.id.participate_text);

        participate_location = rootView.findViewById(R.id.participate_edit_location);
        time = rootView.findViewById(R.id.participate_edit_time);
        participate_message = rootView.findViewById(R.id.participate_edit_text);
        participate_btn = rootView.findViewById(R.id.participate_submit_btn);
        cancel_btn = rootView.findViewById(R.id.participate_cancel_btn);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
        final int userIdx = sharedPreferences.getInt("userIdx", -1);


        //button 설정
        participate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestData data = new RequestData(userIdx, participate_message.getText().toString(), time.getText().toString());

                startParticipate(postidx, data);
            }
        });

        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void setPostToUI() {
        Bundle bundle = getArguments();

        if (bundle != null) {
            nickname.setText(bundle.getString("nickname"));
            title.setText(bundle.getString("title"));
            location.setText(bundle.getString("location"));
            text.setText(bundle.getString("contents"));
            people.setText(bundle.getInt("cur_num") + "/" + bundle.getInt("min_num"));
            postidx = bundle.getInt("id");
        }
    }

    @Override
    public void onBackPressed() {
        fragmentManager.beginTransaction().remove(ParticipateFragment.this).commit();
        fragmentManager.popBackStack();
    }

    public void startParticipate(int postIdx, RequestData data) {
        service.sendRequest(postIdx, data).enqueue(new Callback<JoinResponse>() {
            @Override
            public void onResponse(Call<JoinResponse> call, Response<JoinResponse> response) {
                JoinResponse result = response.body();
                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getStatus() == 200) {
                    onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<JoinResponse> call, Throwable t) {
                Toast.makeText(getContext(), "요청 전송 오류", Toast.LENGTH_SHORT).show();
                Log.e("요청 전송 에러 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }
}