package com.adel.currencyexchange2;





import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v4.view.ViewPager;
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

import java.util.ArrayList;

import de.mateware.snacky.Snacky;

import static com.adel.currencyexchange2.MainActivity.adapter;


public class GoldFragment extends Fragment{

    ArrayList<MoneyDataModel> dataModels;
    ListView listView;
    private static MoneyAdapter adapter;


    public GoldFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View MoneyFragmentView = inflater.inflate(R.layout.gold_fragment, container, false);

        listView=(ListView)MoneyFragmentView.findViewById(R.id.listg);
        dataModels= new ArrayList<>();
        dataModels.add(new MoneyDataModel("424100", "گرم طلای 18", "100 تومان افزایش قیمت",1));
        dataModels.add(new MoneyDataModel("565460", "گرم طلای 24", "بدون تغییر",0));
        dataModels.add(new MoneyDataModel("4400000", "سکه بهار آزادی", "200 تومان افزایش قیمت",1));
        dataModels.add(new MoneyDataModel("4610000", "سکه امامی", "10 تومان کاهش قیمت",-1));
        dataModels.add(new MoneyDataModel("2350000", "نیم سکه", "بدون تغییر",0));
        dataModels.add(new MoneyDataModel("1175000", "ربع سکه", "بدون تغییر",0));
        dataModels.add(new MoneyDataModel("680000", "سکه گرمی", "بدون تغییر",0));
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
