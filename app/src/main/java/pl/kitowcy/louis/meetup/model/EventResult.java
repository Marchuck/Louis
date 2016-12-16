package pl.kitowcy.louis.meetup.model;

import com.google.gson.annotations.SerializedName;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */


public class EventResult {

    @SerializedName("utc_offset")
    public Integer utcOffset;
    public Venue venue;
    @SerializedName("rsvp_limit")
    public Integer rsvpLimit;
    public Integer headcount;
    public String visibility;
    @SerializedName("waitlist_count")
    public Integer waitlistCount;
    public Integer created;
    public Fee fee;
    @SerializedName("maybe_rsvp_count")
    public Integer maybeRsvpCount;

    public String description;
    @SerializedName("event_url")
    public String eventUrl;
    public Integer yesRsvpCount;
    public String name;
    public String id;
    public Integer time;
    public Integer updated;
    public Group group;
    public String status;
}
