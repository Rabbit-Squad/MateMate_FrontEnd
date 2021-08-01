package org.matemate;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ParticipateFragment extends Fragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_participate, container, false);

        initUI(rootView);

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
    }
}