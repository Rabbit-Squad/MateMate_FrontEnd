package org.matemate;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

public class EditFragment extends Fragment {

    EditText title_input;
    EditText location_input;
    EditText time_input;
    EditText min_num_input;
    EditText text_input;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_edit, container, false);
        initUI(rootView);

        return rootView;
    }

    private void initUI(ViewGroup rootView) {
        title_input = rootView.findViewById(R.id.edit_title);
        location_input = rootView.findViewById(R.id.edit_location);
        time_input = rootView.findViewById(R.id.edit_time);
        min_num_input = rootView.findViewById(R.id.edit_min_num);
        text_input = rootView.findViewById(R.id.edit_text);
    }
}