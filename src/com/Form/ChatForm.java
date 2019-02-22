package com.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

public class ChatForm extends JFrame{
  private JPanel rootPanel;
  private JTabbedPane tabbedPane1;
  private JPanel tabP1;
  private JPanel tabP2;
  private JPanel tabP3;
  private JLabel nameL;
  private JTextField nameField;
  private JLabel nameAD;
  private JTextField nameADField;
  private JLabel emailL;
  private JTextField mailField;
  private JLabel stL;
  private JTextField statField;
  private JButton buttInfo;
  private JButton buttSmile;
  private JButton buttFont;
  private JButton buttBG;
  private JButton buttFile;
  private JButton buttHist;
  private JButton buttClear;
  private JButton buttCloseTab;
  private JButton buttCit;
  private JCheckBox checkAnswer;
  private JButton buttSM;
  private JScrollPane ScrollP1;
  private JScrollPane ScrollP2;
  private JPanel jpanel;
  private JTextPane textPaneIn;
  private JTextPane textPaneOut;

  public ChatForm(){

    this.getContentPane().add(rootPanel);
    //tabbedPane1.addTab(name, new TabbPane());
  }
  public void addtab(ArrayList name){
    tabbedPane1.addTab((String) name.get(0), jpanel);
    nameField.setText((String) name.get(0));
    nameADField.setText((String) name.get(1));
    statField.setText((String)name.get(2));
    buttCloseTab.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        int select = tabbedPane1.getSelectedIndex();
        if (select>=1){
          tabbedPane1.remove(select);
        } if (select==0){
          setVisible(false);
        }
      }
    });
  }
  public void restoreWindow() {
    setVisible(true);
    setExtendedState(getExtendedState() & (JFrame.ICONIFIED ^ 0xFFFF));
    requestFocus();
  }
  private void hideWindow() {
    setVisible(false);
  }
  class WindowCloser extends WindowAdapter {
    @Override
    public void windowClosing(WindowEvent e) {
      hideWindow();
    }
    @Override
    public void windowIconified(WindowEvent e) {
      hideWindow();
    }
  }

}
