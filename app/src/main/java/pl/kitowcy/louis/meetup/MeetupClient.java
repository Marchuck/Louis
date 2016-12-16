package pl.kitowcy.louis.meetup;

import pl.kitowcy.louis.meetup.model.EventsResponse;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */

public class MeetupClient {

    private String meetup_api_key;

    MeetupAPI api;

    public MeetupClient() {
        meetup_api_key = System.getProperty("meetup_api_key");

        api = new Retrofit.Builder()
                .baseUrl(MeetupAPI.endpoint)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(MeetupAPI.class);

    }


    public rx.Observable<EventsResponse> getEvents(String groupCode, boolean isSigned) {
        return api.getEvents(meetup_api_key, groupCode, isSigned);
    }


}
