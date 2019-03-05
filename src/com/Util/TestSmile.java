package com.Util;

public class TestSmile {
  private static TestSmile ourInstance = new TestSmile();

  public static TestSmile getInstance() {
    return ourInstance;
  }

  private TestSmile() {
  }
}
