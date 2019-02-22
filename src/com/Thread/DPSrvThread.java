package com.Thread;

import com.Server.DPSrv;
import com.Util.ReceivedNL;

public class DPSrvThread implements Runnable{
  DPSrv dpSrv = new DPSrv();
  String name;
  public Thread t;
  ReceivedNL receivedNL;
  public DPSrvThread(String threadname, ReceivedNL receivedNL){
    name = threadname;
    this.receivedNL = receivedNL;
    t = new Thread(this, name);
    System.out.println("Новый поток: " + t);
    t.start();
  }
  @Override
  public void run() {
    try {
      dpSrv.DPServer(receivedNL);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
