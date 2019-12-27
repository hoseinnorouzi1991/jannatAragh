package com.example.jannataragh.view.product;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.jannataragh.R;
import com.example.jannataragh.view.base.BaseFragment;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class FragmentDoctorProperties extends BaseFragment {

    ExpandableTextView expTv1;
    RelativeLayout relativePropertiesDoctor;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor_properties,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativePropertiesDoctor = (RelativeLayout)view.findViewById(R.id.relative_properties_doctor);

        relativePropertiesDoctor.setRotationY(180);

// sample code snippet to set the text content on the ExpandableTextView
        expTv1 = (ExpandableTextView) view.findViewById(R.id.expand_text_view)
                .findViewById(R.id.expand_text_view);

// IMPORTANT - call setText on the ExpandableTextView to set the text content to display
        //expTv1.setText(getString(R.string.properties_doctor_desc));

    }

    @Override
    public void showData(JSONArray jsonArray) {

        try {
            JSONObject jsonObject = jsonArray.getJSONObject(0);
            expTv1.setText(jsonObject.getString("health_property"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
