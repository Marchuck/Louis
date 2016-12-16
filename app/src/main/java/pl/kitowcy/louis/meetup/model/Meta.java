package pl.kitowcy.louis.meetup.model;

/**
 * Project "Louis"
 * <p>
 * Created by Lukasz Marczak
 * on 16.12.2016.
 */


public class Meta {

    public String next;
    public String method;
    public long totalCount;
    public String link;
    public long count;
    public String description;
    public String lon;
    public String title;
    public String url;
    public String signedUrl;
    public String id;
    public long updated;
    public String lat;

    @Override
    public String toString() {
        return "Meta{" +
                "next='" + next + '\'' +
                ", method='" + method + '\'' +
                ", totalCount=" + totalCount +
                ", link='" + link + '\'' +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", lon='" + lon + '\'' +
                ", title='" + title + '\'' +
                ", url='" + url + '\'' +
                ", signedUrl='" + signedUrl + '\'' +
                ", id='" + id + '\'' +
                ", updated=" + updated +
                ", lat='" + lat + '\'' +
                '}';
    }
}