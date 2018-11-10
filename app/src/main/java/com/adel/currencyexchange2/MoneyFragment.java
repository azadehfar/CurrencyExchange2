package com.adel.currencyexchange2;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;


import com.ogaclejapan.smarttablayout.utils.v4.Bundler;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import de.mateware.snacky.Snacky;


public class MoneyFragment extends Fragment{


    ArrayList<MoneyDataModel> dataModels;
    ListView listView;
    private static MoneyAdapter adapter;

    public MoneyFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       View MoneyFragmentView = inflater.inflate(R.layout.money_fragment, container, false);

        listView=(ListView)MoneyFragmentView.findViewById(R.id.list);
        dataModels= new ArrayList<>();
        dataModels.add(new MoneyDataModel("113000", "دلار کانادا", "100 تومان افزایش قیمت",1));
        dataModels.add(new MoneyDataModel("126500", "دلار امریکا", "بدون تغییر",0));
        dataModels.add(new MoneyDataModel("143600", "یورو", "200 تومان افزایش قیمت",1));
        dataModels.add(new MoneyDataModel("12600", "لیر ترکیه", "10 تومان کاهش قیمت",-1));
        dataModels.add(new MoneyDataModel("15000", "درهم امارات", "بدون تغییر",0));
        adapter= new MoneyAdapter(dataModels,MoneyFragmentView.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MoneyDataModel  dataModel= dataModels.get(position);

             //   Snackbar.make(view, dataModel.getTitle()+"\n"+dataModel.getCommentl()+" API: "+dataModel.getValue(), Snackbar.LENGTH_LONG)
               //        .setAction("No action", null).show();

                String mes = dataModel.getTitle()+"\n"+dataModel.getCommentl();
                if (dataModel.getProcess() == 1)
                   Snacky.builder()
                        .setView(view)
                        .setText(mes)
                        .setDuration(Snacky.LENGTH_SHORT)
                        .success()
                        .show();
                else if (dataModel.getProcess() == 0)
                    Snacky.builder()
                            .setView(view)
                            .setText(mes)
                            .setDuration(Snacky.LENGTH_SHORT)
                            .build()
                            .show();
                else
                    Snacky.builder()
                            .setView(view)
                            .setText(mes)
                            .setDuration(Snacky.LENGTH_SHORT)
                            .error()
                            .show();


            }
        });


        return MoneyFragmentView ;
    }

}

