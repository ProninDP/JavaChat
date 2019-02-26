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
  private ConcurrentHashMap<InetAddress, ArrayList<String>> hmap = new ConcurrentHashMap<>();

  public ConcurrentHashMap<InetAddress, ArrayList<String>> getHmap() {
    return hmap;
  }

  public synchronized void entrySet(InetAddress ipadr, ArrayList<String> name) {
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
