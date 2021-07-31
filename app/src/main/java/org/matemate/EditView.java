package org.matemate;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EditView extends Fragment {

    //UI View
    EditText title_input;
    EditText location_input;
    EditText time_input;
    EditText min_num_input;
    EditText text_input;

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
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
