package com.javatar.countrylist.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.javatar.countrylist.R;
import com.javatar.countrylist.model.Country;
import com.javatar.countrylist.view.util.svg.SvgImageLoader;

public class CountryDetailsActivity extends AppCompatActivity {

    public static final String COUNTRY_KEY = "com.javatar.countrylist.view.activities.CountryDetailsActivity.COUNTRY";

    private Country country;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        country = getIntent().getExtras().getParcelable(COUNTRY_KEY);
        if (country != null) {
            initUI();
        }
    }

    private void initUI() {
        TextView countryNameTextView = (TextView) findViewById(R.id.country_name_textview);
        TextView populationTextView = (TextView) findViewById(R.id.country_population_textview);
        ImageView flagImageView = (ImageView) findViewById(R.id.detail_flag_imageview);

        countryNameTextView.setText(country.getName());
        populationTextView.setText(""+ country.getPopulation());
        SvgImageLoader imageLoader = new SvgImageLoader(this);
        imageLoader.loadImage(country.getFlag(), flagImageView);
    }
}
