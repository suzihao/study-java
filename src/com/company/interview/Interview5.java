package com.company.interview;

public class Interview5 {


    // 实现将一个正整数转换为类似 Excel 列名的字符串（如 1→A，2→B，27→AA，53→BA 等）
    public static String convertToExcelColumn(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--; // 将1~26调整为0~25
            int remainder = n % 26;
            char c = (char) ('A' + remainder);
            sb.insert(0, c); // 字符从左向右构建
            n = n / 26;
        }
        return sb.toString();
    }

    // 实现一个方法，将类似 2a3b10abc 的字符串解析为 2:a 3:b 10:abc”的格式输出
    public static String parseAndFormat(String input) {
        StringBuilder result = new StringBuilder();
        int pos = 0;

        while (pos < input.length()) {
            // Step 1: 提取数字部分
            StringBuilder numBuilder = new StringBuilder();
            while (pos < input.length() && Character.isDigit(input.charAt(pos))) {
                numBuilder.append(input.charAt(pos));
                pos++;
            }

            if (numBuilder.length() == 0) {
                // 没有数字，跳过或抛出异常（视需求而定）
                break;
            }

            int number = Integer.parseInt(numBuilder.toString());

            // Step 2: 提取字母部分
            StringBuilder letterBuilder = new StringBuilder();
            while (pos < input.length() && !Character.isDigit(input.charAt(pos))) {
                letterBuilder.append(input.charAt(pos));
                pos++;
            }

            // Step 3: 拼接结果
            if (result.length() > 0) {
                result.append(" ");
            }
            result.append(number).append(":").append(letterBuilder);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToExcelColumn(1));   // A
        System.out.println(convertToExcelColumn(26));  // Z
        System.out.println(convertToExcelColumn(27));  // AA
        System.out.println(convertToExcelColumn(53));  // BA
        System.out.println(convertToExcelColumn(703)); // AAA

        System.out.println(parseAndFormat("2a3b10abc")); // 输出: 2:a 3:b 10:abc
        System.out.println(parseAndFormat("12ab3c"));    // 输出: 12:ab 3:c
        System.out.println(parseAndFormat("1a"));        // 输出: 1:a
        System.out.println(parseAndFormat("0x"));        // 输出: 0:x
        System.out.println(parseAndFormat("2a3"));
    }
}
