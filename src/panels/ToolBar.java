package panels;

import begin.*;
import module.*;
import module.tools.*;
import module.puzzler.*;
import sframe.*;

//import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
//import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import java.net.*;

public class ToolBar extends JFrame {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysLine = System.getProperty("line.separator");
  String sysBasePath = Info.getBasePath();
  public JButton btSide = new JButton(sysLTab.getProperty("tbShow","Show"));

  public void setTools(JPanel pTool) 
  { 
    Insets tGap = new Insets(0,1,-1,1); 
    Insets tGaq = new Insets(-2,1,-2,1); //-2,1,-2,1
    // Side...
    btSide.setMargin(tGap); btSide.setFont(PeaceBox.sysFont);
    pTool.add(btSide); btSide.addActionListener(new sSide());
    // 关于...
    ImageIcon tAbout = new ImageIcon(sysBasePath+"img\\logo\\micon.png"); 
    JButton btnAbout = new JButton(tAbout); btnAbout.setMargin(tGaq); 
    pTool.add(btnAbout); btnAbout.addActionListener(new sAbout()); 
    // 工具Tools 
    JButton btScan = new JButton(sysLTab.getProperty("tbScan","IP Scan"));  btScan.setMargin(tGap); 
    JButton btLink = new JButton(sysLTab.getProperty("tbLink","Get Links")); btLink.setMargin(tGap); 
    JButton btDLnk = new JButton(sysLTab.getProperty("tbDLnk","Down Links")); btDLnk.setMargin(tGap); 
    pTool.add(btScan); btScan.addActionListener(new tScan());
    pTool.add(btLink); btLink.addActionListener(new tLink());
    pTool.add(btDLnk); btDLnk.addActionListener(new tDLnk()); 
    //  娱乐Puzzler
    JButton bpSudoku = new JButton(sysLTab.getProperty("tbSudoku","Get Sudoku")); bpSudoku.setMargin(tGap);
    JButton bpCal24 = new JButton(sysLTab.getProperty("tbCal24","Calculate 24")); bpCal24.setMargin(tGap);
    pTool.add(bpSudoku); bpSudoku.addActionListener(new pSudoku());
    pTool.add(bpCal24);  bpCal24.addActionListener(new pCal24());  
    // 退出...
    ImageIcon tExit = new ImageIcon(sysBasePath+"img\\button\\exit.gif"); 
    JButton btnExit = new JButton(tExit); btnExit.setMargin(tGaq); 
    pTool.add(btnExit); btnExit.addActionListener(new sExit()); 

  } 
  

  class tScan implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { IPScan.scanDialog(); } 
  } // IPScan 
  class tLink implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { Down.getLinks(); } 
  } // Link  
  class tDLnk implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { Down.start(); } 
  } // DLnk

  class pSudoku implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { 
	  		String t1,t2;
	  		t1 = sysEditor.getText();
	  		t2 = Sudoku.getTab();
	  		t2 += sysLine+"------------  "+Func.getHHMMSS()+sysLine;
	  		sysEditor.setText(t2 + t1);
    } 
  } // Sudoku
  class pCal24 implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { Cal24.calcu(sysLTab.getProperty("tbCal24","Calculate 24")); } 
  } // Cak24
  
  class sSide implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
    	PeaceBox.pSide.setVisible(true); 
    	btSide.setEnabled(false);
    }
  } // About
  class sAbout implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
    	MHelp mh = new MHelp();
    	mh.aboutDialog();
    	//new aAbout() 
    }
  } // About
  class sExit implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { PeaceBox.actExit(); }
  } // 退出Exit
  
}


