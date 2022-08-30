/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author vitom
 */
public class StringHelperUtils {
    private StringHelperUtils(){
        
    }
    public static List<String> seperateStringToList(String s,String seperator){
    return Arrays.asList(s.split(seperator));
    }
    public static int getIdFromLink(String url){
        String result = url.substring(url.indexOf("/root/")+"/root/".length());     
        String result2 = result.substring(result.indexOf("/")+1,result.indexOf("//"));
        int number=Integer.parseInt(result2);
        return number;
    } 
}
