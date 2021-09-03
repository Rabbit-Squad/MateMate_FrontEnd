package org.matemate;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
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
import android.widget.Toast;

public class EditFragment extends Fragment implements OnBackPressedListener {

    EditText title_input;
    EditText location_input;
    EditText text_input;
    Spinner hour;
    Spinner minute;
    Spinner min_num_spinner;
    Button postBtn;

    ServiceApi service = RetrofitClient.getClient().create(ServiceApi.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_edit, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        title_input = rootView.findViewById(R.id.edit_title);
        location_input = rootView.findViewById(R.id.edit_location);
        text_input = rootView.findViewById(R.id.edit_text);
        postBtn = rootView.findViewById(R.id.edit_submit_btn);
        hour = rootView.findViewById(R.id.edit_time);
        minute = rootView.findViewById(R.id.edit_minute);
        min_num_spinner = rootView.findViewById(R.id.edit_min_num);

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("login", Context.MODE_PRIVATE);
                int idx = sharedPreferences.getInt("userIdx", -1);

                String _hour = hour.getSelectedItem().toString();
                String _minute = minute.getSelectedItem().toString();

                int _min_num = Integer.parseInt(min_num_spinner.getSelectedItem().toString());

                String time = _hour + ":" + _minute + ":00";

                NewPostData data = new NewPostData(idx, time, location_input.getText().toString(), _min_num, title_input.getText().toString(), text_input.getText().toString());
                startPosting(data);
            }
        });
    }

    private void startPosting(NewPostData data) {
        service.addPost(data).enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                PostResponse result = response.body();
                Toast.makeText(getContext(), result.getMessage(), Toast.LENGTH_SHORT).show();

                if (result.getStatus() == 200) {
                    System.out.println("게시물 작성 성공!!");
                }
            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {
                Toast.makeText(getContext(), "게시물 작성 오류", Toast.LENGTH_SHORT).show();
                Log.e("게시물 작성 오류 발생", t.getMessage());
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        fragmentManager.beginTransaction().remove(EditFragment.this).commit();
        fragmentManager.popBackStack();
    }
}