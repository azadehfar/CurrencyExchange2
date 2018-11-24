package com.adel.currencyexchange2;



import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
        import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.aldoapps.autoformatedittext.AutoFormatEditText;

//import com.ogaclejapan.smarttablayout.SmartTabLayout;
     //   import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;



    //    import com.ogaclejapan.smarttablayout.utils.v4.Bundler;
import com.thekhaeng.pushdownanim.PushDownAnim;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterTextChange;
import org.fabiomsr.moneytextview.MoneyTextView;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import de.mateware.snacky.Snacky;
import es.dmoral.toasty.Toasty;

import static com.thekhaeng.pushdownanim.PushDownAnim.MODE_STATIC_DP;


public class ChangeFragment extends Fragment  implements View.OnClickListener {



    final ArrayList<SpinnerItemData> list = new ArrayList<>();
    final ArrayList<String> listsymbol = new ArrayList<>();
    AutoFormatEditText amount  , result;
    TextView currencymessage;
    static View MoneyFragmentView ;
    Spinner currency_from , currency_to;
    CircularProgressButton circularProgressButton;
    int amountOldval=0;

    public ChangeFragment() {
        // Required empty public constructor
    }






    @Override
    public void onCreate(Bundle savedInstanceState) {






        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



        MoneyFragmentView = inflater.inflate(R.layout.change_fragment, container, false);

        bind(MoneyFragmentView);

      //  KeyboardDismisser.useWith(this);
       // Gen.ShowInfo(null,this,"okkkk");


      initiatCurrencyLOV();

        SpinnerAdapter adapter = new SpinnerAdapter (getActivity()  , R.layout.spinner_layout , R.id.txt , list );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        currency_from.setAdapter(adapter);
        currency_to.setAdapter(adapter);
        currency_from.setSelection(1); //USD
        currency_to.setSelection(0); //IRR
      //  result.setSymbol(listsymbol.get(0));

        init();

        //Typeface font = Typeface.createFromAsset(getContext().getAssets(),  Gen.GeneralTextForn);
//        viewHolder.title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),Gen.GeneralTextForn));

  //      amount.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));
    //    result.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));
      //  currencymessage.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));



      //  Gen.showKeyboard(getActivity());

      //  Toasty.info(get, Toast.LENGTH_SHORT, true).show();


   /*     amount.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            resetTextSize();
     }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

                ReloadResult();


            }
        });

      /*  amount.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                amount.setCursorVisible(true);
                 Snacky.builder()
                        .setView(view)
                        .setText("تبدیل شد")
                        .setDuration(Snacky.LENGTH_SHORT)
                        .info()
                        .show();

                return false;
            }
        });
*/




   /*     Button button = MoneyFragmentView.findViewById( R.id.reload2 );
        PushDownAnim.setPushDownAnimTo( button )
                .setDurationPush( PushDownAnim.DEFAULT_PUSH_DURATION )
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setScale( MODE_STATIC_DP, 15  )
                .setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick( View view ){

                ReloadResult();





            }

        } );

*/






        return MoneyFragmentView;
    }


    public  void init () {



        amount.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));
        result.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));
        currencymessage.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));

        amount.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

                resetTextSize();
            }

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            public void afterTextChanged(Editable s) {

                ReloadResult();


            }
        });



        Button button = MoneyFragmentView.findViewById( R.id.reload2 );
        PushDownAnim.setPushDownAnimTo( button )
                .setDurationPush( PushDownAnim.DEFAULT_PUSH_DURATION )
                .setDurationRelease( PushDownAnim.DEFAULT_RELEASE_DURATION )
                .setScale( MODE_STATIC_DP, 15  )
                .setOnClickListener( new View.OnClickListener(){
                    @Override
                    public void onClick( View view ){

                        ReloadResult();





                    }

                } );


        amount.requestFocus();


    }

    private void initiatCurrencyLOV() {

        list.clear();
        list.add(new SpinnerItemData("IRR", R.drawable.irr));
        list.add(new SpinnerItemData("USD", R.drawable.usd));
        list.add(new SpinnerItemData("EUR", R.drawable.eur));
        list.add(new SpinnerItemData("TRY", R.drawable.tryy));
        list.add(new SpinnerItemData("GBP", R.drawable.gbp));
        list.add(new SpinnerItemData("AED", R.drawable.aed));

        listsymbol.clear();
        listsymbol.add("ریال");
        listsymbol.add("$");
        listsymbol.add("€");
        listsymbol.add("₺");
        listsymbol.add("£");
        listsymbol.add("د.إ");

    }


 /*   public void closeKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard() {



        amount.setFocusableInTouchMode(true);
        amount.requestFocus();
        InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imgr.showSoftInput(amount, InputMethodManager.SHOW_IMPLICIT);
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
        );

    }
*/
    @Override
    public void onResume() {


//        Gen.showKeyboard(Gen.getRunningActivity());
        super.onResume();

     //  Gen.ShowInfo(MoneyFragmentView,null,"okkkk2");
        //Open Keyboard and Focus on Amount field
     //   amount = (AutoFormatEditText) MoneyFragmentView.findViewById(R.id.amount);
     //   amount.requestFocus();
     //   amount.setTypeface(Typeface.createFromAsset( getContext().getAssets()  ,Gen.GeneralTextForn));

      /*  amount.post(new Runnable() {
            @Override
            public void run() {
                amount.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(amount, InputMethodManager.SHOW_IMPLICIT);
            }
        });
        ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
        );
*/


     init();


    }

    @Override
    public void onPause() {

        super.onPause();
    }



    private void ReloadResult() {

        bind(MoneyFragmentView);
       try{
        final Long resultt=  Gen.currency_Cache * Long.parseLong(amount.getText().toString().replace(",",""));
       // result.setText( ""+ (Gen.currency_Cache * Long.parseLong(amount.getText().toString().replace(",","")) ));

           // Result number Change animation
           ValueAnimator valueAnimator = ValueAnimator.ofInt(amountOldval, resultt.intValue());
           valueAnimator.setDuration(400);
           valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
               @Override
               public void onAnimationUpdate(ValueAnimator valueAnimator) {
                   result.setText(valueAnimator.getAnimatedValue().toString());
                //   result.setText(resultt.toString());
               }
           });
           valueAnimator.start();
           amountOldval= Integer.parseInt(amount.getText().toString().replace(",",""));


       } catch (Exception e) {
           result.setText("0");
       }

    }

    private void bind( View MoneyFragmentView) {


         currency_from = (Spinner)  MoneyFragmentView.findViewById(R.id.currency_from);
         currency_to = (Spinner)  MoneyFragmentView.findViewById(R.id.currency_to);
         result = (AutoFormatEditText) MoneyFragmentView.findViewById(R.id.result);
         amount = (AutoFormatEditText) MoneyFragmentView.findViewById(R.id.amount);
        //amount = getamountInstance ();
         currencymessage = (TextView) MoneyFragmentView.findViewById(R.id.currencymessage);


    }


    private void resetTextSize () {

        if (result.getText().toString().length() > 18)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        else if (result.getText().toString().length() > 13)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
        else if (result.getText().toString().length() > 9)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 27);
        else if (result.getText().toString().length() > 7)
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        else
            result.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);

        if (amount.getText().toString().length() > 18)
            amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
        else if (amount.getText().toString().length() > 13)
            amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 23);
        else if (amount.getText().toString().length() > 9)
            amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 27);
        else if (amount.getText().toString().length() > 7)
            amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
        else
            amount.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);


    }


    @Override
    public void onClick(View view) {

        Gen.ShowInfo(view,null,"okkkk3");
        //KeyboardDismisser.useWith(this);
       // Gen.showKeyboard(Gen.getRunningActivity());

        amount.post(new Runnable() {
            @Override
            public void run() {
                amount.requestFocus();
                InputMethodManager imgr = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imgr.showSoftInput(amount, InputMethodManager.SHOW_IMPLICIT);
            }
        });
      ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                InputMethodManager.SHOW_FORCED,
                InputMethodManager.HIDE_IMPLICIT_ONLY
        );





    }


 /*  static AutoFormatEditText getamountInstance () {

        if (amount == null)
            amount = (AutoFormatEditText) MoneyFragmentView.findViewById(R.id.amount);

         return amount;


   }
*/



}

