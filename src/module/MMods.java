package module;

import begin.*;
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

public class MMods {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysLine = System.getProperty("line.separator");
  String sysBasePath = Info.getBasePath();
  Properties sysConfig = PeaceBox.sysConfig;
  // FETPSH

  public void setTool(JMenuBar sysMenu) 
  { 
    JMenu m = new JMenu(sysLTab.getProperty("tlSubj","Tools(T)")); sysMenu.add(m); m.setMnemonic('T'); 
    
    JMenuItem tScan = new JMenuItem(sysLTab.getProperty("tlIScan","IP Scan(T)"),'I');      m.add(tScan);
    m.addSeparator();
    JMenuItem tDown = new JMenuItem(sysLTab.getProperty("tlIDown","Download(D)"),'D');     m.add(tDown); tDown.setEnabled(false);
    JMenuItem tLink = new JMenuItem(sysLTab.getProperty("tlILink","Get Links(E)"),'E');    m.add(tLink);
    JMenuItem tDLnk = new JMenuItem(sysLTab.getProperty("tlIDLnk","Down Links(L)"),'L');   m.add(tDLnk);
    m.addSeparator();
    JMenuItem tFRep = new JMenuItem(sysLTab.getProperty("tlIFRep","File Replace(R)"),'R'); m.add(tFRep); tFRep.setEnabled(false);
    JMenuItem tCode = new JMenuItem(sysLTab.getProperty("tlICode","Coder Report(C)"),'C'); m.add(tCode);                                                
    m.addSeparator();
    JMenuItem tIIS = new JMenuItem(sysLTab.getProperty("tlIQIIS","IIS Qurey(Q)"),'Q');     m.add(tIIS); tIIS.setEnabled(false);

    tScan.addActionListener(new taScan());
    //tDown.addActionListener(new aDown());
    tLink.addActionListener(new taLink());
    tDLnk.addActionListener(new taDLnk());
    //tFRep.addActionListener(new taFRep());
    tCode.addActionListener(new taCode());
    
    tScan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,InputEvent.CTRL_MASK));
    tDown.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK));
    tCode.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK));
    
  } 

  public void setPuzz(JMenuBar sysMenu) 
  { 
    JMenu m = new JMenu(sysLTab.getProperty("pzSubj","Puzzle(P)")); sysMenu.add(m); m.setMnemonic('P'); 
    
    JMenuItem pAscii = new JMenuItem(sysLTab.getProperty("pzIAscii","Ascii Code ..."));   m.add(pAscii); 
    m.addSeparator();                                                                     
    JMenuItem pP100 = new JMenuItem(sysLTab.getProperty("pzIP100","Prim100(P)"),'P');     m.add(pP100);  
    JMenuItem pSup1 = new JMenuItem(sysLTab.getProperty("pzISup1","Sup1"));               m.add(pSup1);
    JMenuItem pSup2 = new JMenuItem(sysLTab.getProperty("pzISup2","Sup2"));               m.add(pSup2);
    JMenuItem pSup = new JMenuItem(sysLTab.getProperty("pzIPrime","Sup...(M)"),'M');      m.add(pSup); pSup.setEnabled(false);
    m.addSeparator();                                                                     
    JMenuItem pSudoku = new JMenuItem(sysLTab.getProperty("pzISudoku","Sudoku(E)"),'E');  m.add(pSudoku); 
    JMenuItem pSolver = new JMenuItem(sysLTab.getProperty("pzISolver","Solver(F)"),'F');  m.add(pSolver); 
    m.addSeparator();                                      
    JMenuItem pCal24 = new JMenuItem(sysLTab.getProperty("pzICal24","Calculate 24"),'U'); m.add(pCal24); 
    JMenuItem pCalcu = new JMenuItem(sysLTab.getProperty("pzICalcu","Calculate"));        m.add(pCalcu); 

    pAscii.addActionListener(new paAscii());
    pP100.addActionListener(new paP100());
    pSup1.addActionListener(new paSup1());
    pSup2.addActionListener(new paSup2());
    //pSup.addActionListener(new paSup());
    pSudoku.addActionListener(new paSudoku());
    pSolver.addActionListener(new paSolver());
    pCal24.addActionListener(new paCal24());
    pCalcu.addActionListener(new paCalcu());
    
    pP100.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,InputEvent.CTRL_MASK));
    pSudoku.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,InputEvent.CTRL_MASK));
    pCal24.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK));
    
  } 

	class taScan implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) 
	  { IPScan.scanDialog(); }
	} // IP扫描 
	
	class taLink implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) 
	  { Down.getLinks(); }
	} // 提取连接mu3Link	
	class taDLnk implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) 
	  { Down.start(); }
	} // 下载mu4DLnk
	class taCode implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) 
	  { Coder.CUnite("导出代码"); }
	} // Coder
	
	class paAscii implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	try {
	  	  String cmd = "cmd.exe /c start iexplore "+sysBasePath+"data\\ascii.htm";
	  	  Runtime.getRuntime().exec(cmd); 
	    } catch (Exception ex) {
	    	Func.doException(ex, sysLTab.getProperty("pzMAscii")+":(mt1Ascii)");
	    }
	  }
	} // Ascii
	
	class paP100 implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ sysEditor.setText(Prime.getList(1, 100)); }
	  }
	} // mt21Prime
	
	class paSup1 implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ sysEditor.setText(Prime.getSup(100001,499999,1)); }
	  }
	} // mt22Prime
	
	class paSup2 implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ sysEditor.setText(Prime.getSup(100001,999999,2)); }
	  }
	} // mt23Prime
	
	class paCal24 implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ Cal24.calcu(sysLTab.getProperty("pzMCal24","Calculate 24")); }
	  }
	} // Cal24 
	class paCalcu implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ CMath.Calcu(); }
	  }
	} // Calcu 

	class paSudoku implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	{ 	 
	  		String t1,t2;
	  		t1 = sysEditor.getText();
	  		t2 = Sudoku.getTab();
	  		t2 += sysLine+"------------  "+Func.getHHMMSS()+sysLine;
	  		sysEditor.setText(t2 + t1);
	  	}
	  }
	} // Sudoku	
	
	class paSolver implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) 
	  { 	 
      int d[][] = Solver.getData(sysEditor.getText());
      sysEditor.setText(Solver.getTab(d));
	  }
	} // Solver	


  
}


