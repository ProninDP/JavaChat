package com.Util;
import java.io.File;
/**
 * Класс читаем смайлики,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class Smile {
  private static Smile _instance = null;
  private static String[] smileName = null;

  private Smile(){
      smileName = new File(Smile.class.getResource("/com/res/smiledir")
              .getFile()).list((dir, namelink) -> namelink.endsWith(".png"));
  }
  public static Smile getInstance(){
    return _instance = _instance == null ? new Smile() : _instance;
  }

  public String[] getSmileName() throws NullPointerException{
    return smileName;
  }
}
