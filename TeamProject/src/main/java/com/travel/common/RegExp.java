package com.travel.common;

import java.util.regex.*;

//Á¤±Ô Ç¥Çö½Ä °Ë»ç Å¬·¡½º
public class RegExp {
  public static final int ARTICLE_NUM = 0; //»ó¼ö·Î »ç¿ë,switch¹®¿¡¼­ ¼ýÀÚ·ÎÇÏ¸é ¹¹°¡¹ºÁö ¸ð¸§
  public static final int ARTICLE_TITLE = 1;
  public static final int ARTICLE_CONTENT = 2;
  public static final int MEMBER_ID = 3;
  public static final int MEMBER_PWD = 4;
  public static final int MEMBER_NICK = 5;
  public static final int MEMBER_EMAIL = 6;
  public static final int IS_NUMBER=7;

  public static final String EXP_ARTICLE_NUM = "[0-9]*$"; //¼ýÀÚ ºñ±³
  public static final String EXP_ARTICLE_TITLE = "^.{1,100}$"; //±Û Á¦¸ñ 100ÀÚ±îÁö ÀÎÁö
  public static final String EXP_ARTICLE_CONTENT = "^.{1,65535}$"; //±Û ³»¿ë
  public static final String EXP_MEMBER_ID = "^[a-z0-9]{4,20}$"; //È¸¿ø ID
  public static final String EXP_MEMBER_PWD = "^.{4,30}$"; //È¸¿ø ºñ¹Ð¹øÈ£
  public static final String EXP_MEMBER_NICK = "^[a-z°¡-ÆR]{2,20}$";
  public static final String EXP_MEMBER_EMAIL = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
  public static final String EXP_IS_NUMBER = "[0-9]*$";

  public static boolean checkString(int type, String data) { //Å¸ÀÔ,ºñ±³ÇÒ µ¥ÀÌÅÍ
      boolean result = false;
      //Å¸ÀÔ °Ë»ç
      switch (type) {
          case ARTICLE_NUM:
              result = Pattern.matches(EXP_ARTICLE_NUM, data);
              break;
          case ARTICLE_TITLE:
              result = Pattern.matches(EXP_ARTICLE_TITLE, data);
              break;
          case ARTICLE_CONTENT:
              result = Pattern.matches(EXP_ARTICLE_CONTENT, data);
              break;
          case MEMBER_ID:
              result = Pattern.matches(EXP_MEMBER_ID, data);
              break;
          case MEMBER_PWD:
              result = Pattern.matches(EXP_MEMBER_PWD, data);
              break;
          case MEMBER_NICK:
              result = Pattern.matches(EXP_MEMBER_NICK, data);
              break;
          case MEMBER_EMAIL:
              result = Pattern.matches(EXP_MEMBER_EMAIL, data);
              break;
          case IS_NUMBER:
              result = Pattern.matches(EXP_IS_NUMBER, data);
              break;
      }
      return result;
  }
}