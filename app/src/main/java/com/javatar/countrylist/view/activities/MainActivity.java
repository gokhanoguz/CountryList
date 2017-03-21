package com.javatar.countrylist.view.activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.javatar.countrylist.R;
import com.javatar.countrylist.api.CountriesApi;
import com.javatar.countrylist.api.CountriesApiClient;
import com.javatar.countrylist.model.Country;
import com.javatar.countrylist.view.adapters.CountryListRecyclerViewAdapter;
import com.javatar.countrylist.view.adapters.CountryViewHolder;
import com.javatar.countrylist.view.util.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.javatar.countrylist.view.activities.CountryDetailsActivity.COUNTRY_KEY;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener,
        CountryListRecyclerViewAdapter.CountryClickListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private CountryListRecyclerViewAdapter adapter;
    private List<Country> countryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        adapter = new CountryListRecyclerViewAdapter(countryList, this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        // show loader and fetch messages
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        loadCountries();
                    }
                }
        );
    }

    private void loadCountries() {
        swipeRefreshLayout.setRefreshing(true);

        CountriesApi apiService = CountriesApiClient.getClient().create(CountriesApi.class);
        Call<List<Country>> call = apiService.getCountries();
        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

                countryList.clear();
                countryList.addAll(response.body());
                adapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_LONG).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onCountryClick(Country country, TextView textView) {
        Intent intent = new Intent(MainActivity.this, CountryDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(COUNTRY_KEY, country);
        intent.putExtras(bundle);
        String transitionName = getString(R.string.country_name);
        ActivityOptions transitionActivityOptions = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this, textView, transitionName);
            startActivity(intent, transitionActivityOptions.toBundle());
        } else {
            startActivity(intent);
        }
    }

    @Override
    public void onRefresh() {
        loadCountries();
    }
}
