package com.Util;

import java.io.File;
import java.util.ArrayList;


public class NameList {
  private String name;
  private String[] listf;

  public NameList() {
    name = System.getProperty("user.name");
    listf = new File("C:\\Users\\" + name + "\\Contacts")
            .list((dir, namelink) -> namelink.endsWith(".contact"));
  }
  public ArrayList<String> nl(String status) {
    ArrayList<String> list = new ArrayList<>();
    list.add(name);
    for (String s : listf)
      list.add(s.substring(0, s.indexOf(".")));
    list.add(status);
    return list;
  }
}
