package com.adel.currencyexchange2;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aldoapps.autoformatedittext.AutoFormatEditText;

import org.fabiomsr.moneytextview.MoneyTextView;

import java.util.ArrayList;

public class MoneyAdapter extends ArrayAdapter<MoneyDataModel> implements View.OnClickListener{

    private ArrayList<MoneyDataModel> dataSet;
    Context mContext;



    // View lookup cache
    private static class ViewHolder {
      //   MoneyTextView value;
      TextView value;
        TextView title;
        TextView commentl;
        ImageView icon;
    }

    public MoneyAdapter(ArrayList<MoneyDataModel> data, Context context) {
        super(context, R.layout.money_layout, data);
        this.dataSet = data;
        this.mContext=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        MoneyDataModel dataModel=(MoneyDataModel) object;

        switch (v.getId())
        {
            case R.id.list:
            /*    Snackbar.make(v, "Release date " +dataModel.getTitle(), Snackbar.LENGTH_LONG)
                        .setAction("No action", null).show();*/
                Gen.ShowInfo(v,null, "Release date " +dataModel.getTitle() );
                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        MoneyDataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.money_layout, parent, false);
            viewHolder.value = (TextView) convertView.findViewById(R.id.value);
            viewHolder.title = (TextView) convertView.findViewById(R.id.title);
            viewHolder.commentl = (TextView) convertView.findViewById(R.id.commentl);
            viewHolder.icon = (ImageView) convertView.findViewById(R.id.icon);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

  //      Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
    //    result.startAnimation(animation);
      //  lastPosition = position;

        viewHolder.value.setText(dataModel.getValue());
        viewHolder.title.setText(dataModel.getTitle());
        viewHolder.commentl.setText(dataModel.getCommentl());
        viewHolder.title.setTypeface(Typeface.createFromAsset(getContext().getAssets(),Gen.GeneralTextForn));
        viewHolder.value.setTypeface(Typeface.createFromAsset(getContext().getAssets(),Gen.GeneralTextForn));
        viewHolder.commentl.setTypeface(Typeface.createFromAsset(getContext().getAssets(),Gen.GeneralTextForn));

        if (dataModel.getProcess() == 1)
         viewHolder.icon.setImageResource(R.drawable.up);
        if (dataModel.getProcess() == -1)
            viewHolder.icon.setImageResource(R.drawable.down);



        // viewHolder.icon.setOnClickListener(this);
      //  viewHolder.icon.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}