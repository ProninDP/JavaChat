package com.Util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Класс локальных данных о клиент-сервере,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class NameList {
  private String name;
  private String[] listf;

  public NameList() {
    name = System.getProperty("user.name");
    listf = new File("C:\\Users\\" + name + "\\Contacts")
            .list(new FilenameFilter() {
              @Override
              public boolean accept(File dir, String namelink) {
                return namelink.endsWith(".contact");
              }
            });
  }
  public ArrayList<String> nl(String status) {
    ArrayList<String> list = new ArrayList<>();
    list.add(name);
    if(listf.length!=0) {
      for (String s : listf)
        list.add(s.substring(0, s.indexOf(".")));
    }else {
      list.add("NoFullName");
    }
    list.add(status);
    return list;
  }
}
