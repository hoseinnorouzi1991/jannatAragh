package com.example.jannataragh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.json.JSONException;
import org.json.JSONObject;


public class FragmentDoctorProperties extends Fragment implements ProductDetails.dataReceivedListenerDoctorProperties{

    RelativeLayout relativePropertiesDoctor;

    TextView txtExpandableText;
    ExpandableTextView expTv1;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_doctor_properties,container,false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ProductDetails mActivity = (ProductDetails) getActivity();
        mActivity.setDataReceivedListenerDoctorProperties(FragmentDoctorProperties.this);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativePropertiesDoctor = (RelativeLayout)view.findViewById(R.id.relative_properties_doctor);
        txtExpandableText = (TextView)view.findViewById(R.id.expandable_text);

        relativePropertiesDoctor.setRotationY(180);

// sample code snippet to set the text content on the ExpandableTextView

        expTv1 = (ExpandableTextView) view.findViewById(R.id.expand_text_view)
                .findViewById(R.id.expand_text_view);

// IMPORTANT - call setText on the ExpandableTextView to set the text content to display


    }

    @Override
    public void onDataRecievedDoctorProperties(JSONObject jsonObject) {
        try {
            //txtExpandableText.setText(jsonObject.getString("health_property"));

            //expTv1.setText(getString(R.string.properties_doctor_desc));
            expTv1.setText(jsonObject.getString("health_property"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
