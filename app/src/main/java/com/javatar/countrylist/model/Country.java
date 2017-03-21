package com.javatar.countrylist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by gokogu on 21/03/2017.
 */

public class Country implements Parcelable {

    private String name;
    private String alpha2Code;
    private String alpha3Code;
    private String capital;
    private String region;
    private String flag;
    private int population;

    public Country() {
    }

    public Country(String name, String alpha2Code, String alpha3Code, String capital, String region, String flag, int population) {
        this.name = name;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.capital = capital;
        this.region = region;
        this.flag = flag;
        this.population = population;
    }

    protected Country(Parcel in) {
        name = in.readString();
        alpha2Code = in.readString();
        alpha3Code = in.readString();
        capital = in.readString();
        region = in.readString();
        flag = in.readString();
        population = in.readInt();
    }

    public static final Creator<Country> CREATOR = new Creator<Country>() {
        @Override
        public Country createFromParcel(Parcel in) {
            return new Country(in);
        }

        @Override
        public Country[] newArray(int size) {
            return new Country[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(capital);
        dest.writeString(region);
        dest.writeString(alpha2Code);
        dest.writeString(alpha3Code);
        dest.writeString(flag);
        dest.writeInt(population);
    }
}
