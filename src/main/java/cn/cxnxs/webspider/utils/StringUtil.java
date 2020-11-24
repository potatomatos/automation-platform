package cn.cxnxs.webspider.utils;

import java.util.List;

/**
 * 字符串处理工具类
 */
public class StringUtil {


    public  static  boolean isEmpty(String str){
        return str==null||str.length()==0;
    }

    public  static  boolean isNotEmpty(String str){
        return str!=null&&str.length()>0;
    }

    public static String sqlLike(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return "%" + str + "%";
    }

    public static String sqlLlike(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return "%" + str;
    }

    public static String sqlRlike(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return str + "%";
    }

    /**
     * getter,setter属性处理
     *
     * @param s
     * @return
     */
    public static String decapitalize(String s) {
        if (s == null || s.length() == 0)
            // 空处理
            return s;
        if (s.length() > 1 && Character.isUpperCase(s.charAt(1)) && Character.isUpperCase(s.charAt(0))) {
            // 长度大于1，并且前两个字符大写时，返回原字符串
            return s;
        } else if (s.length() > 1 && Character.isUpperCase(s.charAt(1)) && Character.isLowerCase(s.charAt(0))) {
            // 长度大于1，并且第一个字符小写，第二个字符大写时，返回原字符串
            return s;
        } else if (Character.isUpperCase(s.charAt(0))) {
            //如果首字母大写，返回原字符
            return s;
        } else {
            // 其他情况下，把原字符串的首个字符大写处理后返回
            char ac[] = s.toCharArray();
            ac[0] = Character.toUpperCase(ac[0]);
            return new String(ac);
        }
    }

    /**
     * 下划线转驼峰
     * @param para
     * @return
     */
    public static String underline2Camel(String para) {
        StringBuilder result = new StringBuilder();
        String a[] = para.split("_");
        for (String s : a) {
            if (!para.contains("_")) {
                result.append(s);
                continue;
            }
            if (result.length() == 0) {
                result.append(s.toLowerCase());
            } else {
                result.append(s.substring(0, 1).toUpperCase());
                result.append(s.substring(1).toLowerCase());
            }
        }
        return result.toString();
    }

    /***
     * 驼峰命名转为下划线命名
     *
     * @param para
     *        驼峰命名的字符串
     */

    public static String camel2Underline(String para){
        StringBuilder sb=new StringBuilder(para);
        int temp=0;//定位
        if (!para.contains("_")) {
            for(int i=1;i<para.length();i++){
                if(Character.isUpperCase(para.charAt(i))){
                    sb.insert(i+temp, "_");
                    temp+=1;
                }
            }
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 列表查询排序条件拼接方法
     * @param field 排序字段名
     * @param ids 条件列表
     * @return
     */
    public  static  String listToOrderByClause(String field,List<String> ids){
        StringBuilder builder=new StringBuilder("FIELD");
        builder.append("(");
        builder.append(field);
        builder.append(",");
        for (int i=0;i<ids.size();i++){
            builder.append(ids.get(i));
            if (i<ids.size()-1){
                builder.append(",");
            }
        }
        builder.append(")");
        return  builder.toString();
    }

    /**
     * 列表转换字符串
     * @param ids
     * @return
     */
    public static String list2String(List<String> ids){
        StringBuilder builder=new StringBuilder();
        builder.append("(");
        for (int i=0;i<ids.size();i++){
            builder.append(ids.get(i));
            if (i<ids.size()-1){
                builder.append(",");
            }
        }
        builder.append(")");
        return  builder.toString();
    }
}
