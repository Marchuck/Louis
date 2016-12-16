package pl.kitowcy.louis.meetup;

import pl.kitowcy.louis.meetup.model.EventsResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public interface MeetupAPI {

    String endpoint = "https://api.meetup.com/2/";

    @GET("events")
    rx.Observable<EventsResponse> getEvents(@Query("key") String meetupApiKey,
                                            @Query("group_urlname") String groupUrlName,
                                            @Query("sign") boolean signed);
}
