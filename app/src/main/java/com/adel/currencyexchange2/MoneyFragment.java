package com.adel.currencyexchange2;


import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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
import java.util.List;

import de.mateware.snacky.Snacky;
import es.dmoral.toasty.Toasty;

import static com.adel.currencyexchange2.Gen.closeKeyboard;


public class MoneyFragment extends Fragment{


    ArrayList<MoneyDataModel> dataModels;
    ListView listView;
    private static MoneyAdapter adapter;
    Contract.Presentation presentation = new Presentation();

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



        adapter= new MoneyAdapter(Gen.MoneydataModels,MoneyFragmentView.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                MoneyDataModel  dataModel= dataModels.get(position);



                String mes = dataModel.getTitle()+"\n"+dataModel.getCommentl();
                if (dataModel.getProcess() == 1)

                Gen.ShowSuccess(view,null, mes );

                else if (dataModel.getProcess() == 0)

                   Gen.ShowInfo(view,null, mes );
                else

                    Gen.ShowError(view,null, mes );

            }
        });




        return MoneyFragmentView ;
    }



}

