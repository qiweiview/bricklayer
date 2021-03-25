package build.utils;



public class StringUtils4V {


    /**
     * 下划线字符串转首字母
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

    public static String lowercaseFirstLetter(String str) {
        if (null==str||0==str.length()){
            return "";
        }
        return str.substring(0,1).toLowerCase()+str.substring(1);
    }


}
