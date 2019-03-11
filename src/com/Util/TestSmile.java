package com.Util;
import javax.swing.*;
import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
import java.net.URL;

import static com.sun.javafx.scene.control.skin.Utils.getResource;

/**
 * Класс читаем смайлики,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class TestSmile{
  private static final String ICON_DIR = "/com/res/smiledir/";
  private static TestSmile _instance = null;
  private static String[] smileName = null;

  private TestSmile() {
    smileName = new File(TestSmile.class.getResource(ICON_DIR)
              .getFile()).list((dir, namelink) -> namelink.endsWith(".png"));
  }
  public static TestSmile getInstance(){
    return _instance = _instance == null ? new TestSmile() : _instance;
  }

  public String[] getSmileName() throws NullPointerException{
    return smileName;
  }
}
