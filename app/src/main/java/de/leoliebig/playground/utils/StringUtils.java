package de.leoliebig.playground.utils;

/**
 * Helper for processing strings.
 * Created by Leo on 02.04.2017.
 */
public abstract class StringUtils {

    public static final String EMPTY = "";

    public static boolean isEmpty(final String string){
        return (string==null || string.isEmpty());
    }

    public static boolean notEmpty(final String string){
        return (string!=null && !string.isEmpty());
    }

}
