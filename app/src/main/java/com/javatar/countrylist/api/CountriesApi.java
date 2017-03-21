package com.javatar.countrylist.api;

import com.javatar.countrylist.model.Country;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by gokogu on 21/03/2017.
 */

public interface CountriesApi {
    @GET("all")
    Call<List<Country>> getCountries();
}
