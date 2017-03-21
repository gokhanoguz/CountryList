package com.javatar.countrylist.view.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.javatar.countrylist.R;

/**
 * Created by gokogu on 21/03/2017.
 */

public class CountryViewHolder extends RecyclerView.ViewHolder {

    public TextView countryNameTextView;

    public CountryViewHolder(View itemView) {
        super(itemView);
        countryNameTextView = (TextView) itemView.findViewById(R.id.country_textview);
    }
}
