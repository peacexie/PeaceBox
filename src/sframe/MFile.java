package sframe;
import begin.*;
import module.tools.*;

//import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
//import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import java.io.PrintWriter;
//import java.net.*;

public class MFile {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysSavePath = "";

  public void setMenu(JMenuBar sysMenu) 
  {  
    JMenu m = new JMenu(sysLTab.getProperty("flSubj","File(F)")); sysMenu.add(m); m.setMnemonic('F');
    //m.setMargin(new Insets(10,3,10,1)); 
    
    JMenuItem mNew  = new JMenuItem(sysLTab.getProperty("flINew","New"));          m.add(mNew);  
    JMenuItem mOpen = new JMenuItem(sysLTab.getProperty("flIOpen","Open ..."));    m.add(mOpen); 
    m.addSeparator();
    JMenuItem mSave = new JMenuItem(sysLTab.getProperty("flISave","Save"));        m.add(mSave); 
    JMenuItem mSava = new JMenuItem(sysLTab.getProperty("flISava","Save As ...")); m.add(mSava); 
    JMenuItem mPrint = new JMenuItem(sysLTab.getProperty("flIPrint","Print ...")); m.add(mPrint); mPrint.setEnabled(false); 
    m.addSeparator();
    JMenuItem mExit = new JMenuItem(sysLTab.getProperty("flIExit","Exit(X)"),'X'); m.add(mExit);
    
    mNew.addActionListener(new aNew());
    mOpen.addActionListener(new aOpen());
    mSave.addActionListener(new aSave());
    mSava.addActionListener(new aSava());
    mExit.addActionListener(new aExit());
    
    mSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK));
    mExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,InputEvent.ALT_MASK));
    //m.setShortcut(new MenuShortcut('F')); // 编译OK,但不起作用
    //mExit.setShortcut(new MenuShortcut('X')); // OK
    
  } 
  
  public String fileSaveDialog() 
  { 
    String fPath = ""; 
    JFileChooser chooser = new JFileChooser(); 
    int returnVal = chooser.showSaveDialog(null); 
    if (returnVal == JFileChooser.APPROVE_OPTION) { 
        fPath = chooser.getSelectedFile().getPath(); 
    }
    return fPath; 
  } // 保存对话框
  
  public void fileSave(String path) 
  { 
    String text = sysEditor.getText();
    File myfile = new File(path); 
    PrintWriter output = null; 
    try { 
      output = new PrintWriter(myfile); 
      output.write(text); 
    } catch (IOException ex) { 
      //Func.doException(ex, sysLTab.getProperty("flMSave")+":(m13Save)");
      //sysSavePath = "";
    } finally { 
      output.close(); 
      output = null; 
    } 
    return; // true
  } // 保存
  
  class aNew implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { sysEditor.setText(""); }
  } // 新建aNew
  
  class aOpen implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
      FileDialog fd=new FileDialog(new Frame(),"Open File...",FileDialog.LOAD);      
      fd.setVisible(true);
      String strFile=fd.getDirectory()+fd.getFile();
      sysEditor.setText(Coder.readFile(strFile));
    }
  } // 打开aOpen
  
  class aSave implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
      if (sysSavePath == "") { 
        sysSavePath = fileSaveDialog(); 
        fileSave(sysSavePath); 
      } else { 
        fileSave(sysSavePath); 
      } 
    }
  } // 保存aSave
  
  class aSava implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
      sysSavePath = fileSaveDialog(); 
      fileSave(sysSavePath); 
    }
  } // 另存aSava
  
  class aExit implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { PeaceBox.actExit(); }
  } // 退出Exit
  
}


