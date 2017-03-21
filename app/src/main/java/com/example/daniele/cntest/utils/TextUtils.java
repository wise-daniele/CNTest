package com.example.daniele.cntest.utils;

/**
 * Created by daniele on 21/03/17.
 */

public class TextUtils {

    public static boolean isNameValid(String name){
        if(!name.contains(" ")){
            return false;
        }
        return true;
    }

    public static String[] getNameSplitted(String name){
        String[] nameSplitted = name.split(" ");
        String firstName = nameSplitted[0];
        String lastName = nameSplitted[1];
        for(int i = 2; i < nameSplitted.length; i++){
            lastName = lastName + " " + nameSplitted[i];
        }
        return new String[] {firstName, lastName};
    }

}
