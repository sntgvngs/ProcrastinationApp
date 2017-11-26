package com.team1.cmsc434.procrastinationapp;

import android.arch.persistence.room.*;

import java.util.Date;

/**
 * Created by Santi on 11/26/2017.
 */

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static Task.Type fromTypeString(String typeString) {
        return typeString == null ? null : Task.Type.valueOf(typeString);
    }

    @TypeConverter
    public static String typeToString(Task.Type type) {
        return type == null ? null : type.name();
    }

    @TypeConverter
    public static Task.Difficulty fromDiffString(String diffString) {
        return diffString == null ? null : Task.Difficulty.valueOf(diffString);
    }

    @TypeConverter
    public static String diffToString(Task.Difficulty diff) {
        return diff == null ? null : diff.name();
    }
}
