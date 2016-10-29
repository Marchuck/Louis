package pl.kitowcy.louis.proposition;

/**
 * Created by Patryk Mieczkowski on 29.10.2016
 */

public class PropItem {

    private String title;
    private int image;
    private String text;
    private String hours;
    private String location;

    public PropItem(String title, String text, String hours, String location, int image) {
        this.title = title;
        this.image = image;
        this.text = text;
        this.hours = hours;
        this.location = location;
    }


    public String getTitle() {
        return title;
    }

    public int getImage() {
        return image;
    }

    public String getText() {
        return text;
    }

    public String getHours() {
        return hours;
    }

    public String getLocation() {
        return location;
    }
}
