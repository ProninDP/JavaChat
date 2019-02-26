package com.Thread;

import com.Server.DSSrv;
import com.Util.ReceivedNL;
/**
 * Класс отправки сообщений ping,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */

public class DSCpingThread implements Runnable{
  private DSSrv dsSrv = new DSSrv();
  private ReceivedNL receivedNL;

  public DSCpingThread(String threadname, ReceivedNL receivedNL) {
    this.receivedNL = receivedNL;
    Thread t = new Thread(this, threadname);
    System.out.println("Новый поток: " + t);
    t.start();
    System.out.println("Запущен DSSrv.DSCping: " + t.isAlive());
  }

  @Override
  public void run() {
    try {
      while (true) {
        dsSrv.DSCping(receivedNL);
        Thread.sleep(15000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
