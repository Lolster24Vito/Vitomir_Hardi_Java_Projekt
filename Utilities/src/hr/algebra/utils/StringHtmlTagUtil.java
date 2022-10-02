/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.algebra.utils;

/**
 *
 * @author vitom
 */
public class StringHtmlTagUtil {
    private StringHtmlTagUtil(){
    }
    public static String RemoveHTMLTags(String line){
        String nohtml = line.replaceAll("\\<.*?>","");
        return nohtml;
    }
}
