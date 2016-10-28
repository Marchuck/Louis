package pl.kitowcy.louis.utils;

import java.util.Collection;

/**
 * Project "Louis"
 * <p/>
 * Created by Lukasz Marczak
 * on 28.10.16.
 */
public class Is {

    public static <T> boolean nonEmpty(Collection<T> collection) {
        return collection != null && collection.size() > 0;
    }

    public static <T> boolean nonEmpty(T[] collection) {
        return collection != null && collection.length > 0;
    }
}
