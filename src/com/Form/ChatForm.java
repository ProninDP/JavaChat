package com.Form;

import com.Util.Smile;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
/**
 * Класс формы оправки-чтения сообщений,
 * @author Пронин Дмитрий Павлович slidernode@yandex.ru
 * @version 0.1
 */
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
  private Smile smile;

  public ChatForm(Smile smile){
    this.smile = smile;
    this.getContentPane().add(rootPanel);
    //tabbedPane1.addTab(name, new TabbPane());
  }
  public void addtab(ArrayList<String> name){
    tabbedPane1.addTab(name.get(0), jpanel);
    nameField.setText(name.get(1));
    nameADField.setText(name.get(0));
    statField.setText(name.get(2));
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
    buttSmile.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        JFrame smilefram = new JFrame();
        smilefram.setUndecorated(true);
        smilefram.setVisible(true);
        smilefram.setSize(250, 300);
        JPanel contents = new JPanel();
        contents.setBorder(new EmptyBorder(5, 5, 5, 5));
        JButton exit = new JButton("Exit");
        JList<String> jList = new JList<>(smile.getSmileName());
        exit.setSize(200, 200);
        contents.add(new JScrollPane(jList));
        contents.add(exit);
        smilefram.setContentPane(contents);
        exit.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            smilefram.dispose();
          }
        });
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
