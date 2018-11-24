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

   //     import com.ogaclejapan.smarttablayout.SmartTabLayout;
    //   import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;



      //  import com.ogaclejapan.smarttablayout.utils.v4.Bundler;

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
import es.dmoral.toasty.Toasty;

import static com.adel.currencyexchange2.Gen.GolddataModels;
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

        View GoldFragmentView = inflater.inflate(R.layout.gold_fragment, container, false);

        listView=(ListView)GoldFragmentView.findViewById(R.id.listg);


        adapter= new MoneyAdapter(GolddataModels,GoldFragmentView.getContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String mes = GolddataModels.get(position).getTitle()+"\n"+GolddataModels.get(position).getCommentl();
                if (GolddataModels.get(position).getProcess() == 1)

                    Gen.ShowSuccess(view,null, mes );
                else if (GolddataModels.get(position).getProcess() == 0)

                    Gen.ShowInfo(view,null, mes );
                else

                Gen.ShowError(view,null, mes );

            }
        });


        return GoldFragmentView ;
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();

        }
    }

}
