package com.company.interview;


public class DiDiInterview {
    // 代码
    // 减法函数 大数减法 入参数 两个字符串 存的是数字 a b 返回值是字符串
    //    解题思路
    //            比较大小
    //
    //    首先判断 a 和 b 的大小关系。如果 a < b，则交换两者，并在结果前添加负号。
    //    补零处理
    //
    //    为了便于逐位相减，我们将较短的字符串前面补零，使得两者的长度一致。
    //    逐位减法
    //
    //    从字符串的末尾（即最低位）开始，逐位相减，并处理借位逻辑。
    //    去除前导零
    //
    //    最终结果可能包含前导零，需要将其去除。
    //    处理负号
    //
    //    如果交换过 a 和 b，则在结果前添加负号。
    public static String subtract(String a, String b) {
        // 1. 比较大小
        int compare = compare(a, b);
        boolean isNegative = false;

        if (compare < 0) {
            // a < b，交换并设置负号
            String temp = a;
            a = b;
            b = temp;
            isNegative = true;
        } else if (compare == 0) {
            // a == b，结果为0
            return "0";
        }

        // 2. 补零到相同长度
        int maxLength = Math.max(a.length(), b.length());
        a = padZero(a, maxLength);
        b = padZero(b, maxLength);

        // 3. 逐位减法
        int borrow = 0;
        char[] result = new char[maxLength];

        for (int i = maxLength - 1; i >= 0; i--) {
            int digitA = a.charAt(i) - '0';
            int digitB = b.charAt(i) - '0';
            int sub = digitA - digitB - borrow;

            if (sub < 0) {
                sub += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result[i] = (char) (sub + '0');
        }

        // 4. 去除前导零
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (char c : result) {
            if (leadingZero && c == '0') {
                continue;
            }
            leadingZero = false;
            sb.append(c);
        }

        String res = sb.length() == 0 ? "0" : sb.toString();

        // 5. 添加负号
        if (isNegative) {
            res = "-" + res;
        }

        return res;
    }

    private static int compare(String a, String b) {
        if (a.length() != b.length()) {
            return a.length() - b.length();
        }
        for (int i = 0; i < a.length(); i++) {
            int diff = a.charAt(i) - b.charAt(i);
            if (diff != 0) {
                return diff;
            }
        }
        return 0;
    }

    private static String padZero(String s, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length - s.length(); i++) {
            sb.append('0');
        }
        sb.append(s);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(subtract("123", "79"));      // 输出: 44
        System.out.println(subtract("100", "1"));       // 输出: 99
        System.out.println(subtract("5", "3"));         // 输出: 2
        System.out.println(subtract("1000", "999"));    // 输出: 1
        System.out.println(subtract("123", "123"));     // 输出: 0
        System.out.println(subtract("123", "456"));     // 输出: -333
    }
}
