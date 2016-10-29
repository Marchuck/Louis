package pl.kitowcy.louis.facedetection.api;

import retrofit2.http.GET;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 29.10.16.
 */
public class PlacesService {
    public static final String key = "AIzaSyBzBRFp3p5s4twnDVnPMtlMvMMfanAi94Q";
    public String s = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=500&type=restaurant&keyword=cruise&key=YOUR_API_KEY";
    public static String endpoint = "https://maps.googleapis.com/maps/";
    interface API {
        @GET("api/place/nearbysearch/json")
        rx.Observable<Poi> getPlacesNearby();
    }
}
