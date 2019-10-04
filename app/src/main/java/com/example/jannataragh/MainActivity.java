package com.example.jannataragh;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageView imgMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    FloatingActionButton fab;

    private CollapsingToolbarLayout collapsingToolbar;
    private AppBarLayout appBarLayout;
    private Menu collapsedMenu;
    private boolean appBarExpanded = true;

    RecyclerView recyclerView_porforoosh;
    RecyclerView recyclerView_porkhasiat;
    RecyclerAdapter recyclerAdapter;
    ArrayList<News> sampleNews;

    SwipeRefreshLayout swipeRefreshLayout;

    ImageView imgDrawerMenu;

    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        imgMenu = (ImageView)findViewById(R.id.imgHambergerMenu);
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView = (NavigationView)findViewById(R.id.navigationView);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        //imgDrawerMenu = (ImageView)findViewById(R.id.imgDrawerMenu);

        recyclerView_porkhasiat = (RecyclerView)findViewById(R.id.recycler_porkhasiat);
        recyclerView_porkhasiat.setHasFixedSize(true);

        recyclerView_porforoosh = (RecyclerView)findViewById(R.id.recycler_porfroosh);
        recyclerView_porforoosh.setHasFixedSize(true);
        sampleNews = new ArrayList<>();
        //swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipRefreh);

        for (int i = 0; i <10 ; i++) {
            News news = new News();
            news.setId(i+1);
            news.setTitle("عرق نعنا");
            news.setDesc("دارای خواص دارویی زیادی می باشد. در ادامه به خصوصیات آن بیشتر اشاره شده است...");
            news.setPrice("4000");
            news.setToman("تومان");

            sampleNews.add(news);
        }


        recyclerView_porkhasiat.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        recyclerView_porforoosh.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,2,LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));
//        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        recyclerAdapter = new RecyclerAdapter(sampleNews,this);
        recyclerView_porforoosh.setAdapter(recyclerAdapter);

        recyclerView_porkhasiat.setAdapter(recyclerAdapter);


        //swipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this,R.color.colorPrimary),ContextCompat.getColor(this,R.color.colorAccent));

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                sampleNews.clear();
//
//                for (int i = 0; i <10 ; i++) {
//                    News news = new News();
//                    news.setId(i+1);
//                    news.setTitle("خبر ورزشی!");
//                    news.setDesc("این یک خبر ورزشی تستی می باشد برای تست ریسایکلر ویوو");
//
//                    sampleNews.add(news);
//                }
//
//                recyclerAdapter.notifyDataSetChanged();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });


        Typeface typeface = ResourcesCompat.getFont(this,R.font.vazirmediumfd);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.anim_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_menu_home);
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Drawable newdrawable = new BitmapDrawable(getResources(),Bitmap.createScaledBitmap(bitmap,70,70,true));
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
                //Log.d(AnimateToolbar.class.getSimpleName(), "onOffsetChanged: verticalOffset: " + verticalOffset);

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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.START);
            }
        });
//        imgMenu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                drawerLayout.openDrawer(Gravity.START);
//            }
//        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();

                switch(id)
                {
                    case R.id.home:
                        Toast.makeText(MainActivity.this," home click", Toast.LENGTH_SHORT).show();

                    case R.id.menu_list_product:
                        Toast.makeText(MainActivity.this," list product click",Toast.LENGTH_SHORT).show();
                    case R.id.menu_basket:
                        Toast.makeText(MainActivity.this,"basket click",Toast.LENGTH_SHORT).show();
                    case R.id.submenu_setting:
                        Toast.makeText(MainActivity.this," setting click",Toast.LENGTH_SHORT).show();
                    case R.id.submenu_aboutUs:
                        Toast.makeText(MainActivity.this," about us click",Toast.LENGTH_SHORT).show();

                }
                return false;

            }
        });
    }



    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (collapsedMenu != null && (!appBarExpanded || collapsedMenu.size() != 1)) {
            //collapsed

            collapsedMenu.add("Add")
                    .setIcon(R.drawable.ic_shopping_basket)
                    .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        } else {

            //expanded
        }
        return super.onPrepareOptionsMenu(collapsedMenu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        collapsedMenu = menu;
        return true;
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

        if(drawerLayout.isDrawerOpen(Gravity.START))
        {
            drawerLayout.closeDrawer(Gravity.START);
        }
        else
        {
            super.onBackPressed();
        }

    }
}

//https://blog.iamsuleiman.com/toolbar-animation-with-android-design-support-library/