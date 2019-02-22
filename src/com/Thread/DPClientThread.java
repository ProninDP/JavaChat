package com.Thread;

import com.Server.DPClient;
import com.Util.NameList;
import com.Util.Status;

public class DPClientThread implements Runnable{
  private DPClient dpClient = new DPClient();
  public Thread t;
  private NameList nameList;
  private Status status;


  public DPClientThread(String threadname, NameList nameList, Status status){
    this.status = status;
    this.nameList = nameList;
    t = new Thread(this, threadname);
    System.out.println("Новый поток: " + t);
    t.start();
  }

  @Override
  public void run() {
    try {
      while (true){
        dpClient.dpClient(nameList, status.getStatus());
        Thread.sleep(10000);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
