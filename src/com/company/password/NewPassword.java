package com.company.password;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class NewPassword {

public static void main(String[] args) {
    String[] pswdStr = { "qwertyuiopasdfghjklzxcvbnm", "QWERTYUIOPASDFGHJKLZXCVBNM", "0123456789", "~!@#$%^&*()_+{}|<>?:{}" };
  int pswdLen = 10;
  String pswd = " ";
  char[] chs = new char[pswdLen];
  for (int i = 0; i < pswdStr.length; i++) {
  int idx = (int) (Math.random() * pswdStr[i].length());
  chs[i] = pswdStr[i].charAt(idx);
  }

  for (int i = pswdStr.length; i < pswdLen; i++) {
    int arrIdx = (int) (Math.random() * pswdStr.length);
    int strIdx = (int) (Math.random() * pswdStr[arrIdx].length());
    chs[i] = pswdStr[arrIdx].charAt(strIdx);
  }

  for (int i = 0; i < 1000; i++) {
    int idx1 = (int) (Math.random() * chs.length);
    int idx2 = (int) (Math.random() * chs.length);
    if (idx1 == idx2) {
    continue;
  }
    char tempChar = chs[idx1];
    chs[idx1] = chs[idx2];
    chs[idx2] = tempChar;
  }
  pswd = new String(chs);
//  System.out.println(pswd);
  System.out.println(generatePassword(10));

  }

  public static String generatePassword (int length) {
    // 最终生成的密码
    String password = "";
    Random random = new Random();
    for (int i = 0; i < length; i ++) {
      // 随机生成0或1，用来确定是当前使用数字还是字母 (0则输出数字，1则输出字母)
      int charOrNum = random.nextInt(2);
      if (charOrNum == 1) {
        // 随机生成0或1，用来判断是大写字母还是小写字母 (0则输出小写字母，1则输出大写字母)
        int temp = random.nextInt(2) == 1 ? 65 : 97;
        password += (char) (random.nextInt(26) + temp);
      } else {
        // 生成随机数字
        password += random.nextInt(10);
      }
    }
    return password;
  }

  public String aaa(){

    return "1";
  }

}


