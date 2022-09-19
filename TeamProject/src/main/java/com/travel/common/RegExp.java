package com.travel.common;

import java.util.regex.*;

//���� ǥ���� �˻� Ŭ����
public class RegExp {
  public static final int ARTICLE_NUM = 0; //����� ���,switch������ ���ڷ��ϸ� �������� ��
  public static final int ARTICLE_TITLE = 1;
  public static final int ARTICLE_CONTENT = 2;
  public static final int MEMBER_ID = 3;
  public static final int MEMBER_PWD = 4;
  public static final int MEMBER_NICK = 5;
  public static final int MEMBER_EMAIL = 6;
  public static final int IS_NUMBER=7;

  public static final String EXP_ARTICLE_NUM = "[0-9]*$"; //���� ��
  public static final String EXP_ARTICLE_TITLE = "^.{1,100}$"; //�� ���� 100�ڱ��� ����
  public static final String EXP_ARTICLE_CONTENT = "^.{1,65535}$"; //�� ����
  public static final String EXP_MEMBER_ID = "^[a-z0-9]{4,20}$"; //ȸ�� ID
  public static final String EXP_MEMBER_PWD = "^.{4,30}$"; //ȸ�� ��й�ȣ
  public static final String EXP_MEMBER_NICK = "^[a-z��-�R]{2,20}$";
  public static final String EXP_MEMBER_EMAIL = "^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
  public static final String EXP_IS_NUMBER = "[0-9]*$";

  public static boolean checkString(int type, String data) { //Ÿ��,���� ������
      boolean result = false;
      //Ÿ�� �˻�
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