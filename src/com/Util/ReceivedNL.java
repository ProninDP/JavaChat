package com.Util;

import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Класс данных принятых от других клиент-серверов,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
public class ReceivedNL {
  public ConcurrentHashMap<InetAddress, ArrayList<String>> hmap = new ConcurrentHashMap<>();

  public synchronized void entrySet(InetAddress ipadr, ArrayList<String> name) {
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
    hmap.put(ipadr, name);
  }
  public synchronized void delSet(InetAddress ipadr) {
    hmap.remove(ipadr);
  }


  public synchronized void print(){ //проверить что список создается и наполняется
    Enumeration en = hmap.keys();
    while (en.hasMoreElements()) {
      InetAddress key = (InetAddress) en.nextElement();
      ArrayList<String> value = hmap.get(key);
      System.out.println(key + " <-> " + value);
    }
  }
}
