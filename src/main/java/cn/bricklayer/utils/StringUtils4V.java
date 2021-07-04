package cn.bricklayer.utils;


import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils4V {


    /**
     * 下划线转首字母大写
     * @param underLine
     * @param uncapFirst true 第一个单词首字母小写
     * @return
     */
    public static String underLine2UnCapFirst(String underLine,boolean uncapFirst) {
        StringBuilder stringBuilder = new StringBuilder();
        String[] s = underLine.split("_");
        for (int i = 0; i < s.length; i++) {
            String s1 = s[i].toLowerCase();
            if (uncapFirst&&i == 0) {
                stringBuilder.append(s1);
            } else {
                stringBuilder.append(s1.substring(0, 1).toUpperCase() + s1.substring(1));
            }

        }

        return stringBuilder.toString();
    }

    /**
     * 首字母小写
     * @param str
     * @return
     */
    public static String lowercaseFirstLetter(String str) {
        if (null==str||0==str.length()){
            return "";
        }
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }


    public static String systemPath2JavaPackagePath(String systemPath){
        String s = systemPath.replaceAll(Pattern.quote("\\"), ".").replaceAll(Pattern.quote("/"), ".");
        return s;

    }

    public static String javaPackagePath2SystemPath(String javaPackage){
        String s = javaPackage.replaceAll( "\\.", Matcher.quoteReplacement(File.separator));
        return s;

    }


}
