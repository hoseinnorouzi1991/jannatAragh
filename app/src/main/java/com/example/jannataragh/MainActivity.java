package com.example.jannataragh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.jannataragh.view.basket.BasketActivity;
import com.rbddevs.splashy.Splashy;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import ru.nikartm.support.ImageBadgeView;

public class MainActivity extends AppCompatActivity {

    ImageView imgMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
//    FloatingActionButton fab;

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private Menu collapsedMenu;
    private boolean appBarExpanded = true;

    RecyclerView recyclerView_porforoosh;
    RecyclerView recyclerView_porkhasiat;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Product> product;

    int verticalOffsetTotal;

    SwipeRefreshLayout swipeRefreshLayout;

    ImageView imgDrawerMenu;
    ImageView imgUser;

    TextView textCartItemCount;
    int mCartItemCount = 10;

    static Context context;

    MenuItem menuItem;

    TextView txtBestSeller,txtBestProperty;

    ImageBadgeView ibv_basket;

    String url = "http://www.grafik.computertalk.ir/StoreCode/product.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Splashy splashy = new Splashy(this);
        splashy.setLogo(R.drawable.aragh)
                .setTitle("عرقیات جنت شهر")
                .setTitleColor("#FFFFFF")
                .setSubTitle("محصولات کاملا ارگانیک و بدون ناخالصی")
                .setSubTitleColor("#FFFFFF")
                .setProgressColor("#FFFFFF")
                .showProgress(true)
                .setBackgroundResource(R.color.colorPrimary)
                .setFullScreen(true)
                .setTime(500)
                .show();

        context = getApplicationContext();

        ibv_basket = (ImageBadgeView)findViewById(R.id.ibv_basket);
        txtBestSeller = (TextView)findViewById(R.id.txt_best_seller);
        txtBestProperty = (TextView)findViewById(R.id.txt_best_property);
        imgMenu = (ImageView) findViewById(R.id.imgHambergerMenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
        imgUser = (ImageView)findViewById(R.id.img_user);
//        fab = (FloatingActionButton) findViewById(R.id.fab);
        //imgDrawerMenu = (ImageView)findViewById(R.id.imgDrawerMenu);

        recyclerView_porkhasiat = (RecyclerView) findViewById(R.id.recycler_porkhasiat);
        recyclerView_porkhasiat.setHasFixedSize(true);

        recyclerView_porforoosh = (RecyclerView) findViewById(R.id.recycler_porfroosh);
        recyclerView_porforoosh.setHasFixedSize(true);
        product = new ArrayList<>();
        recyclerView_porforoosh.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        showData();
        //swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipRefreh);

//        for (int i = 0; i < 10; i++) {
//            Product products = new Product();
//            products.setId(i + 1);
//            products.setTitle("عرق نعنا");
//            products.setDesc("دارای خواص دارویی زیادی می باشد. در ادامه به خصوصیات آن بیشتر اشاره شده است...");
//            products.setPrice("4000");
//            products.setToman("تومان");
//
//            product.add(products);
//        }

        recyclerView_porkhasiat.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
//        recyclerAdapter = new RecyclerAdapter(product, this);
//        recyclerView_porforoosh.setAdapter(recyclerAdapter);

//        recyclerView_porkhasiat.setAdapter(recyclerAdapter);

        //swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimary),ContextCompat.getColor(this,R.color.colorAccent));

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                product.clear();
//
//                for (int i = 0; i <10 ; i++) {
//                    Product news = new Product();
//                    news.setId(i+1);
//                    news.setTitle("خبر ورزشی!");
//                    news.setDesc("این یک خبر ورزشی تستی می باشد برای تست ریسایکلر ویوو");
//
//                    product.add(news);
//                }
//
//                recyclerAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


        Typeface typeface = ResourcesCompat.getFont(this, R.font.vazirmediumfd);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_home);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable newdrawable = new BitmapDrawable(getResources(), Bitmap.createScaledBitmap(bitmap, 70, 70, true));
            //newdrawable.setColorFilter(Color.BLUE, PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(newdrawable);
        }

        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(getString(R.string.header_toolbar));
        collapsingToolbar.setCollapsedTitleTypeface(typeface);
        collapsingToolbar.setExpandedTitleTypeface(typeface);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.araghiat);

        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {
//                int vibrantColor = palette.getVibrantColor(R.color.colorAccent);
//                collapsingToolbar.setContentScrimColor(vibrantColor);
                collapsingToolbar.setStatusBarScrimColor(R.color.colorPrimary);
            }
        });


        //  Use when your list size is constant for better performance

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                Log.d(MainActivity.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

                verticalOffsetTotal = verticalOffset;
                //  Vertical offset == 0 indicates appBar is fully expanded.
                if (Math.abs(verticalOffset) > 200) {
                    appBarExpanded = false;
                    invalidateOptionsMenu();
                } else {
                    appBarExpanded = true;
                    invalidateOptionsMenu();
                }
            }
        });

        //https://blog.iamsuleiman.com/toolbar-animation-with-android-design-support-library/


//        imgDrawerMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.START);
//            }
//        });

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent_basket = new Intent(MainActivity.this, BasketActivity.class);
//                startActivity(intent_basket);
////                drawerLayout.openDrawer(Gravity.START);
//            }
//        });
//        imgMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.START);
//            }
//        });

        ibv_basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_basket = new Intent(MainActivity.this, BasketActivity.class);
                startActivity(intent_basket);
            }
        });

        txtBestSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_best_seller = new Intent(MainActivity.this,BestSellerActivity.class);
                startActivity(intent_best_seller);
            }
        });

        imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_register_user = new Intent(MainActivity.this, LoginUserActivity.class);
                startActivity(intent_register_user);
            }
        });

        txtBestProperty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_best_property = new Intent(MainActivity.this,BestPropertyActivity.class);
                startActivity(intent_best_property);
            }
        });


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch (id) {
                    case R.id.menu_home:
                        drawerLayout.closeDrawer(Gravity.START);
                        //navigationView.getMenu().getItem(0).setChecked(true);
                        break;
//                    case R.id.menu_list_product:
//                        Toast.makeText(MainActivity.this," list product click",Toast.LENGTH_SHORT).show();
//                        break;
                    case R.id.menu_basket_navigation:
                        Intent intent_basket = new Intent(MainActivity.this, BasketActivity.class);
                        startActivity(intent_basket);
                        //navigationView.getMenu().getItem(1).setChecked(true);
                        break;
                    case R.id.submenu_setting:
                        Toast.makeText(MainActivity.this, " setting click", Toast.LENGTH_SHORT).show();
                        //navigationView.getMenu().getItem(4).setChecked(true);
                        break;
                    case R.id.submenu_aboutUs:
                        Toast.makeText(MainActivity.this, " about us click", Toast.LENGTH_SHORT).show();
                        //navigationView.getMenu().getItem(5).setChecked(true);
                        break;
                    case R.id.menu_best_property_navigation:
                        Intent intent_best_property = new Intent(MainActivity.this,BestPropertyActivity.class);
                        startActivity(intent_best_property);
                        //navigationView.getMenu().getItem(3).setChecked(true);
                        break;
                    case R.id.menu_best_seller_navigation:
                        Intent intent_best_seller = new Intent(MainActivity.this,BestSellerActivity.class);
                        startActivity(intent_best_seller);
                        //navigationView.getMenu().getItem(2).setChecked(true);
                        break;

                }
                return false;

            }
        });
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed

//            collapsedMenu.add("Add")
//                    .setIcon(R.drawable.ic_shopping_basket)
//                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        } else {

            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.basket, menu);
//        collapsedMenu = menu;
//
//        menuItem = menu.findItem(R.id.menu_basket);
//
//        View actionView = menuItem.getActionView();
//        textCartItemCount = (TextView) actionView.findViewById(R.id.cart_badge);
//
//
//
//        //Toast.makeText(MainActivity.this,"break point",Toast.LENGTH_SHORT).show();
//
//        actionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onOptionsItemSelected(menuItem);
//            }
//        });
//
//        setupBadge();

        return true;
    }

    private void setupBadge() {

        if (textCartItemCount != null) {
            if (mCartItemCount == 0) {
                if (textCartItemCount.getVisibility() != View.GONE) {
                    textCartItemCount.setVisibility(View.GONE);
                }
            } else {
                textCartItemCount.setText(String.valueOf(Math.min(mCartItemCount, 99)));
                if (textCartItemCount.getVisibility() != View.VISIBLE) {
                    textCartItemCount.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //finish();
                drawerLayout.openDrawer(Gravity.START);
                return true;
            case R.id.submenu_aboutUs:
                return true;
        }
        if (item.getTitle() == "Add") {
            //Toast.makeText(this, "clicked add", Toast.LENGTH_SHORT).show();
            drawerLayout.openDrawer(Gravity.START);
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(Gravity.START)) {
            drawerLayout.closeDrawer(Gravity.START);
        } else {
            super.onBackPressed();
        }

    }

    private void showData() {

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        product.add(new Product(jsonObject.getString("id"),
                                jsonObject.getString("name"),
                                jsonObject.getString("desc"),
                                jsonObject.getString("price"),
                                jsonObject.getString("img")));

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                recyclerAdapter = new RecyclerAdapter(product, MainActivity.this);
                recyclerView_porforoosh.setAdapter(recyclerAdapter);

                recyclerView_porkhasiat.setAdapter(recyclerAdapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "مشکلی رخ داده است.اتصال اینترنت خود را بررسی کنید.", Toast.LENGTH_SHORT).show();
                //progressDialog.dismiss();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);

//        Animation animation = null;
//        animation = AnimationUtils.loadAnimation(MainActivity.context, R.anim.listview);
//        animation.setDuration(200);
//        listView.startAnimation(animation);
//        animation = null;


    }


    private class Task extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... voids) {
            showData();
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
}

//https://blog.iamsuleiman.com/toolbar-animation-with-android-design-support-library/

/*
links:
rate
https://www.freecodecamp.org/news/30-new-android-libraries-released-in-the-spring-of-2017-which-deserve-your-attention-faea359a1915/

search
https://uxplanet.org/top-15-search-github-ui-libraries-and-components-java-swift-8d7403e73aa8

Expand text
https://github.com/Blogcat/Android-ExpandableTextView
 */