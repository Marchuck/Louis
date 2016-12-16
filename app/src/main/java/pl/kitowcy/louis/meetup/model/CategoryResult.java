package pl.kitowcy.louis.meetup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public class CategoryResult {

    public String name;
    @SerializedName("sort_name")
    public String sortName;
    public Integer id;
    public String shortname;
}
