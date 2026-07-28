package com.week0;



public class UrlEncoder {
  private String base62List = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
  public String encode(int num) {
    int number = num;
    String string = "";
    while(num>0) {
      int mod = num % 62;
      string+=base62List.charAt(mod);
      num = num/62;
    }
    return string;
  }
}
