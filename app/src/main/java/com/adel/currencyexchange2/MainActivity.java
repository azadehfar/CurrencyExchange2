package com.adel.currencyexchange2;

import com.github.abdularis.buttonprogress.DownloadButtonProgress;
import com.ogaclejapan.smarttablayout.SmartTabLayout;


import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;
import com.weiwangcn.betterspinner.library.BetterSpinner;
import com.yy.mobile.rollingtextview.RollingTextView;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.ComponentName;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONArray;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import es.dmoral.toasty.Toasty;


@EActivity (R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements Contract.View{







    ArrayList<MoneyDataModel> dataModels;
    static MoneyAdapter adapter;
    final ArrayList<SpinnerItemData> list = new ArrayList<>();
    Contract.Presentation presentation = new Presentation();

    private int[] tabIcons = {
            R.drawable.dollar3,
            R.drawable.coin2,
            R.drawable.exchange2
    };



    @ViewById
    Toolbar toolbar;


    @ViewById
    TabLayout tabLayout;

    @ViewById
    ViewPager viewPager;

    @ViewById
    TextView datetitle, updatemessage2 , updatemessage;

    @ViewById
    SwipeRefreshLayout swipeLayout;

    Spinner currencyy;
    CircularProgressButton circularProgressButton;
    DownloadButtonProgress btn;






    @AfterViews
void init() {

     //   getSupportActionBar().hide();
        presentation.AttachView(this);
        ReloadResult();


        //Swipe Reload Data
            swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // To keep animation for 4 seconds
                ReloadResult();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        // Stop animation (This will be after 3 seconds)
                        swipeLayout.setRefreshing(false);
                    }
                }, 1000); // Delay in millis
            }
        });
        swipeLayout.setColorSchemeColors(
                getResources().getColor(android.R.color.holo_blue_bright),
                getResources().getColor(android.R.color.holo_green_light),
                getResources().getColor(android.R.color.holo_orange_light),
                getResources().getColor(android.R.color.holo_red_light)
        );







        setupViewPager(viewPager);


        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        viewPager.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        setupTabIcons();


       datetitle.setTypeface(Typeface.createFromAsset(getAssets(),Gen.GeneralTextForn));
        updatemessage.setTypeface(Typeface.createFromAsset(getAssets(),Gen.GeneralTextForn));




    /*    String languageToLoad  = "fa"; // your language
        Locale locale = new Locale(languageToLoad);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());*/










         btn = findViewById(R.id.button_reload);
         btn.addOnClickListener(new DownloadButtonProgress.OnClickListener() {
            @Override
            public void onIdleButtonClick(View view) {

                ReloadResult();
                new Thread(new SampleTask(new Handler(), btn, updatemessage2)).start();
                btn.setIdle();
            //    updatemessage.setText(getResources().getString(R.string.dataisupdate));
            }

            @Override
            public void onCancelButtonClick(View view) {
                // called when cancel button/icon is clicked
            }

            @Override
            public void onFinishButtonClick(View view) {
                // called when finish button/icon is clicked
                btn.setIdle();
                updatemessage.setText(getResources().getString(R.string.dataisupdate));
            }
        });


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();


                if (position!=2)
                   Gen.closeKeyboard(Gen.getRunningActivity());
                else
                   Gen.showKeyboard(Gen.getRunningActivity());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {


            }
        });





    }




    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new MoneyFragment(),getResources().getString(R.string.Fragment1Title));
        adapter.addFrag(new GoldFragment(), getResources().getString(R.string.Fragment2Title));
        adapter.addFrag(new ChangeFragment(), getResources().getString(R.string.Fragment3Title));
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onShowResult() {
        Gen.ShowSuccess(getCurrentFocus(),null,getResources().getString(R.string.currencyisupdate));
        updatemessage.setText(getResources().getString(R.string.dataisupdate));
    }

    @Override
    public void onFailureResult() {
        Gen.ShowError(getCurrentFocus(),null,getResources().getString(R.string.updatefailed));
        updatemessage.setText(getResources().getString(R.string.updatefailed));

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    private void ReloadResult() {

     presentation.getResult();


    }



}