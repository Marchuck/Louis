package pl.kitowcy.louis.meetup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public class Venue {

    public String zip;
    public String country;

    @SerializedName("localized_country_name")

    public String localizedCountryName;
    public String city;
    public String address1;
    public Double lon;
    public String phone;
    public String name;
    public Integer id;
    public String state;
    public Double lat;
    public Boolean repinned;

}