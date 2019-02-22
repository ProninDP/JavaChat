package com.Util;

import java.net.InetAddress;
import java.util.*;

public class ReceivedNL {
  public Hashtable htable = new Hashtable();

  public void entrySet(InetAddress ipadr, ArrayList<String> name) {
    //Тест!!!
    /*String s = name.get(2);
    String st = "";
    System.out.println(s);
    Enumeration en = htable.keys();
    while (en.hasMoreElements()) {
      InetAddress key = (InetAddress) en.nextElement();
      ArrayList<String> value = (ArrayList<String>) htable.get(key);
      st=value.get(2);
      System.out.println(st);
    }
    if (!s.equals(st)) {
      //htable.remove(ipadr);
      htable.put(ipadr, name);
    }
    /*
    if (s.equals(st) && !htable.containsKey(ipadr)) {
      htable.remove(ipadr);
      htable.put(ipadr, name);
    }
     */
    //Тест завершон!!!
    htable.put(ipadr, name);
  }
  public void delSet(InetAddress ipadr) {
      htable.remove(ipadr);
  }


  public void print(){ //проверить что список создается и наполняется
    Enumeration en = htable.keys();
    while (en.hasMoreElements()) {
      InetAddress key = (InetAddress) en.nextElement();
      ArrayList<String> value = (ArrayList<String>) htable.get(key);
      System.out.println(key + " <-> " + value);
    }
  }
}
