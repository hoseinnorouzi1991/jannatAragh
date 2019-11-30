package com.example.jannataragh.view.product;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.jannataragh.FragmentComments;
import com.example.jannataragh.FragmentDoctorProperties;
import com.example.jannataragh.FragmentProperties;
import com.example.jannataragh.R;
import com.example.jannataragh.view.base.BaseCommentFragment;
import com.example.jannataragh.view.base.BaseFragment;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ProductDetails extends AppCompatActivity {

    private static final String TAG = ProductDetails.class.getSimpleName();

    String url = "http://www.grafik.computertalk.ir/StoreCode/SelectProductById.php";
    String urlComment = "http://www.grafik.computertalk.ir/StoreCode/CommentProductById.php";

    JSONObject jsonObjectSendID;
    Bundle bundle = new Bundle();
    ImageView imgHeader;
    private ViewPager viewPager;

    /*private DataReceivedListener DataListener;

    public interface DataReceivedListener {
        void onDataReceived(JSONObject jsonObject);
    }

    public void setAboutDataListener(DataReceivedListener listener) {
        this.DataListener = listener;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);

        Intent intent = getIntent();
        String id =  intent.getStringExtra("id");

        url = url + "?id="+id;
        urlComment = urlComment + "?id="+id;
        try {
            jsonObjectSendID = new JSONObject();
            jsonObjectSendID.put("id",id);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //SendAndReceiveData();
        //Toast.makeText(ProductDetails.this,id,Toast.LENGTH_SHORT).show();

        imgHeader = (ImageView) findViewById(R.id.img_htab_header);
        Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        //if (getSupportActionBar() != null) getSupportActionBar().setTitle("عنوان محصول");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.htab_viewpager);
        setupViewPager(viewPager);
        viewPager.setRotationY(180);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.htab_tabs);
        tabLayout.setupWithViewPager(viewPager);

        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);


/*
        try {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.araghiat3);
            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                @SuppressWarnings("ResourceType")
                @Override
                public void onGenerated(Palette palette) {
                    int vibrantColor = palette.getVibrantColor(R.color.colorPrimary);
                    int vibrantDarkColor = palette.getDarkVibrantColor(R.color.colorPrimaryDark);
//                    collapsingToolbarLayout.setContentScrimColor(vibrantColor);
//                    collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
                }
            });

        } catch (Exception e) {
            // if Bitmap fetch fails, fallback to primary colors
            Log.e(TAG, "onCreate: failed to create bitmap from background", e.fillInStackTrace());
//            collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
//            collapsingToolbarLayout.setStatusBarScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));
        }*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());
                Log.d(TAG, "onTabSelected: pos: " + tab.getPosition());

                switch (tab.getPosition()) {
                    case 0:
                        // TODO: 31/03/17
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        new Task().execute();
    }

    public  void RecieveComments()
    {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, urlComment, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (adapter != null)
                    adapter.recieveData(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductDetails.this,"error with load comments",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    public void SendAndReceiveData()
    {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(response.getString("name"));
                    }

                    Glide.with(ProductDetails.this).load("http://www.grafik.computertalk.ir/"+response.getString("img"))
                            .listener(new RequestListener<Drawable>() {
                                @Override
                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                    return false;
                                }

                                @Override
                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                    //progressBar.setVisibility(View.GONE);
                                    return false;
                                }
                            }).into(imgHeader);
//                   bundle.putString("name",response.getString("name"));
//                   bundle.putString("desc",response.getString("desc"));

                    /*if (DataListener != null)
                    {
                        DataListener.onDataReceived(response);
                    }*/

                    if(adapter != null)
                        adapter.showData(response);

                    //response.getString("name");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductDetails.this,"not find",Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);


    }

    private ViewPagerAdapter adapter;

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());



        adapter.addFrag(new FragmentProperties(), "مشخصات");
        adapter.addFrag(new FragmentDoctorProperties(), "مشخصات درمانی");
        adapter.addFrag(new FragmentComments(), "نظرات کاربران");
        viewPager.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
//            case R.id.action_settings:
//                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private static class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public void showData(JSONObject json){
            for (Fragment fragment: mFragmentList){
                if(fragment instanceof BaseFragment)
                    ((BaseFragment) fragment).showData(json);
            }
        }

        public void recieveData(JSONArray jsonArray)
        {
            for(Fragment fragment:mFragmentList){
                if(fragment instanceof BaseCommentFragment)
                    ((BaseCommentFragment)fragment).recieveData(jsonArray);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            SendAndReceiveData();
            RecieveComments();
            return new String[0];
        }

        @Override
        protected void onPostExecute(String[] result) {
            // Call setRefreshing(false) when the list has been refreshed.
            //mRefreshLayout.finishRefreshing();
            //runLayoutAnimation(recycler);
            super.onPostExecute(result);
        }
    }



//    public static class DummyFragment extends Fragment {
//        int color;
//
//        public DummyFragment() {
//        }
//
//        @SuppressLint("ValidFragment")
//        public DummyFragment(int color) {
//            this.color = color;
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//            View view = inflater.inflate(R.layout.fragment_properties, container, false);
//
//            final FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.dummyfrag_bg);
//            frameLayout.setBackgroundColor(color);
//
//            RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
//            recyclerView.setLayoutManager(linearLayoutManager);
//            recyclerView.setHasFixedSize(true);
//
//            DessertAdapter adapter = new DessertAdapter(getContext());
//            recyclerView.setAdapter(adapter);
//
//            return view;
//        }
//    }
}


//  https://stackoverflow.com/questions/46050185/android-pass-data-from-activity-to-fragment-in-viewpager
