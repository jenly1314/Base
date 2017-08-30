/*
 Copyright © 2015, 2016 Jenly Yu <a href="mailto:jenly1314@gmail.com">Jenly</a>

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.

 */
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
