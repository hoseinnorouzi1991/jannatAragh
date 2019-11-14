package com.example.jannataragh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class FragmentProperties extends Fragment {

    String name,desc;
    RelativeLayout relativeProperties;
    TextView txtPropertyProductTitle;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        name = getArguments().getString("name");

        return inflater.inflate(R.layout.fragment_properties,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativeProperties = (RelativeLayout)view.findViewById(R.id.relative_properties);

        relativeProperties.setRotationY(180);

        txtPropertyProductTitle = (TextView)view.findViewById(R.id.txt_properties_product_title);
        txtPropertyProductTitle.setText(name);

    }
}
