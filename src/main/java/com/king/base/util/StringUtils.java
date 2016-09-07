package com.king.base.util;


/**
 * @author Jenly
 */
public class StringUtils {

    private StringUtils() {
        throw new AssertionError();
    }

    public static boolean isBlank(CharSequence str) {
        return (isEmpty(str) || str.toString().trim().length()==0);
    }


    public static boolean isEmpty(CharSequence str) {
        return (str == null || str.length() == 0);
    }

    public static int length(CharSequence str) {
        return str == null ? 0 : str.length();
    }


    public static boolean isNotBlank(Object str){
        if(str == null){
            return false;
        }else{
            if(String.valueOf(str).trim().length() == 0){
                return false;
            }
        }
        return true;
    }

    public static boolean equals(String str1,String str2){

        return str1 != null ? str1.equals(str2) : str2 == null;

    }

}
