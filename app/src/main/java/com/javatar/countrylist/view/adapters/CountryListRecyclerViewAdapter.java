package com.javatar.countrylist.view.adapters;

import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.GenericRequestBuilder;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.caverock.androidsvg.SVG;
import com.javatar.countrylist.R;
import com.javatar.countrylist.model.Country;
import com.javatar.countrylist.view.util.svg.SvgImageLoader;

import java.io.InputStream;
import java.util.List;

/**
 * Created by gokogu on 21/03/2017.
 */

public class CountryListRecyclerViewAdapter extends RecyclerView.Adapter<CountryViewHolder> {

    private List<Country> countryList;
    private Context mContext;
    private CountryClickListener clickLister;
    private GenericRequestBuilder<Uri, InputStream, SVG, PictureDrawable> requestBuilder;
    private SvgImageLoader imageLoader;

    public CountryListRecyclerViewAdapter(List<Country> countryList, Context context, CountryClickListener listener) {
        this.countryList = countryList;
        this.mContext = context;
        this.clickLister = listener;
        imageLoader = new SvgImageLoader(mContext);
    }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.country_list_item, parent, false);

        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CountryViewHolder holder, int position) {
        final Country country = countryList.get(position);
        holder.countryNameTextView.setText(country.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickLister.onCountryClick(country, holder.countryNameTextView);
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public interface CountryClickListener {
        public void onCountryClick(Country country, TextView textView);
    }
}
