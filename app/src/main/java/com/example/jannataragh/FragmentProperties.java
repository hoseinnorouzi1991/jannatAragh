package com.example.jannataragh;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FragmentProperties extends Fragment implements ProductDetails.OnAboutDataReceivedListener{

    String name1="12",desc;
    RelativeLayout relativeProperties;
    TextView txtPropertyProductTitle;
    TextView txtPropertiesExplain;
    TextView txtPropertiesPrice;
    Activity mActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = new Bundle();
        bundle = getArguments();

        if (bundle != null) {
            String name = bundle.getString("name");
            Toast.makeText(MainActivity.context, name, Toast.LENGTH_SHORT).show();
        }
        return inflater.inflate(R.layout.fragment_properties,container,false);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mActivity = (ProductDetails) getActivity();
        mActivity.setAboutDataListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        relativeProperties = (RelativeLayout)view.findViewById(R.id.relative_properties);

        relativeProperties.setRotationY(180);

        txtPropertyProductTitle = (TextView)view.findViewById(R.id.txt_properties_product_title);
        txtPropertiesExplain = (TextView)view.findViewById(R.id.txt_properties_explain);
        txtPropertiesPrice = (TextView)view.findViewById(R.id.txt_properties_price);

    }

    @Override
    public void onDataReceived(News model) {
        txtPropertyProductTitle.setText(model.getTitle());
        txtPropertiesExplain.setText(model.getDesc());
        txtPropertiesPrice.setText(model.getPrice());
    }
}
