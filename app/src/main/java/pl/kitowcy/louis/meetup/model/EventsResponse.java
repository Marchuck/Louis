package pl.kitowcy.louis.meetup.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public class EventsResponse {

    public List<EventResult> eventResults = new ArrayList<>();

    public Meta meta;

    @Override
    public String toString() {
        return "EventsResponse{" +
                "eventResults=" + eventResults +
                ", meta=" + meta +
                '}';
    }
}
