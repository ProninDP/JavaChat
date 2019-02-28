package com.Util;
import java.io.File;
/**
 * Класс читаем смайлики,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class Smile {
  private static final String PATH_SMILE = "/com/res/smiledir";
  private String[] smileName;
  public Smile() {
    smileName = new File(Smile.class.getResource(PATH_SMILE)
            .getFile()).list((dir, namelink) -> namelink.endsWith(".png"));
  }
  public String[] getSmileName() {
    return smileName;
  }
}
