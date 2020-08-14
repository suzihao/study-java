package com.company.Regx;

import java.util.regex.Pattern;

public class IsNum {
    public static void main(String[] args) {
        String s = "a515151";
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        boolean isNum = pattern.matcher(s).matches();// 如果url末尾是数字说明浏览成功
    }
}
