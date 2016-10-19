package sframe;
import begin.*;

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

public class MSett {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  static String sysLine = System.getProperty("line.separator");
  static Properties sysConfig = PeaceBox.sysConfig;
  static Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String setLang = PeaceBox.sysLang;
  
  JCheckBoxMenuItem mWrap;
  String[] mStr = sysLTab.getProperty("stICName","White-Black(Def)").replace(" ","").split(";"); 
  int mLen = mStr.length;
  JCheckBoxMenuItem[] mBack = new JCheckBoxMenuItem[mLen];

  public void setMenu(JMenuBar sysMenu) 
  { 
    JMenu m = new JMenu(sysLTab.getProperty("stSubj","Settin(S)")); sysMenu.add(m);  m.setMnemonic('S'); 
    
    mWrap = new JCheckBoxMenuItem(sysLTab.getProperty("stIWrap","Wrap"));            m.add(mWrap);
    m.addSeparator();                                                  
    JMenuItem mLang = new JMenuItem(sysLTab.getProperty("stILang","Language"));      m.add(mLang); 
    JMenuItem mProp = new JMenuItem(sysLTab.getProperty("stIPara","Parameter"));     m.add(mProp); mProp.setEnabled(false);
    m.addSeparator();
    String[] sBack = sysLTab.getProperty("stICBack","255,255,255").split(";");
    String[] sFore = sysLTab.getProperty("stICFore","0,0,0").split(";");
    int mLen = mStr.length;
    final Color[] mCorB = new Color[mLen];
    final Color[] mCorF = new Color[mLen]; 
  	for(int i=0;i<mLen;i++)
  	{  
      final int j = i;
      mBack[i] = new JCheckBoxMenuItem(mStr[i]); m.add(mBack[i]);
      String[] ta = sBack[i].replace(" ","").split(","); 
      mCorB[i] = new Color(Integer.valueOf(ta[0]),Integer.valueOf(ta[1]),Integer.valueOf(ta[2])); 
      String[] tb = sFore[i].replace(" ","").split(","); 
      mCorF[i] = new Color(Integer.valueOf(tb[0]),Integer.valueOf(tb[1]),Integer.valueOf(tb[2])); 
      mBack[i].setBackground(mCorB[i]); 
      mBack[i].setForeground(mCorF[i]); 
      mBack[i].addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        setBack(mCorB, mCorF, j);
      } 
      });
  	}
    mBack[0].setState(true); mBack[0].setEnabled(false);

    mWrap.addActionListener(new aWrap());
    mLang.addActionListener(new aLang());
    
  } 

  public void setBack(Color[] c1, Color[] c2, int j)
  {                 
  	for(int i=0;i<mBack.length;i++)
  	{                                                  
  	  mBack[i].setEnabled(true);   
  	  mBack[i].setState(false);  
  	}                             
    mBack[j].setEnabled(false);  
    mBack[j].setState(true); 
    sysEditor.setBackground(c1[j]);
    sysEditor.setForeground(c2[j]);   
  }
  
  public static void saveProp()
  {  
  	String ps="",t="";
	  Properties p = PeaceBox.sysConfig; 
	  ps += "# System"+sysLine;  
	  ps += "cfgCSet="+p.getProperty("cfgCSet","ISO-8859-1")+sysLine;  
	  ps += "cfgLang="+p.getProperty("cfgLang","English.ini")+sysLine;
	  ps += "cfgFont="+p.getProperty("cfgFont","Fixedsys")+sysLine;  
	  ps += "# File"+sysLine; 
	  t = p.getProperty("fileSave",PeaceBox.sysBasePath+"temp"); 
	  ps += "fileSave="+t.replace("\\","\\\\")+sysLine;  
	  t = p.getProperty("fileOpen",PeaceBox.sysBasePath+"temp"); 
	  ps += "fileOpen="+t.replace("\\","\\\\")+sysLine; 
	  ps += "# IP"+sysLine;  
	  ps += "IPMin="+p.getProperty("IPMin","2")+sysLine;  
	  ps += "IPMax="+p.getProperty("IPMax","254")+sysLine;
	  ps += "IPFlag="+p.getProperty("IPFlag","OK")+sysLine;
	  ps += "# Coder"+sysLine;  
	  ps += "CDExts="+p.getProperty("CDExts","asp|jsp|java|php|")+sysLine;  
	  ps += "CDFill="+p.getProperty("CDFill","editor|pcode|")+sysLine;
	  
  	try {
	    File fp = new File(PeaceBox.sysBasePath+"data\\PeaceBox.ini");
	    if(!fp.exists()) fp.createNewFile();
	    FileWriter r = new FileWriter(fp);   
      PrintWriter f = new PrintWriter(r); 
      f.print(ps); 
      r.close();  
    } catch(Exception ex) {   
	    Func.doException(ex, sysLTab.getProperty("stLESave","(saveProp)"));
    }
  	
  }

  class aWrap implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { 
      if(mWrap.getState())
        sysEditor.setLineWrap(true);
      else
        sysEditor.setLineWrap(false);
    } 
  } // 换行mWrap

  class aLang implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { 
      final JDialog d = new JDialog();      d.setVisible(true); 
      d.setTitle(sysLTab.getProperty("stLSubj","Set Language")); d.setLayout(null);
      d.setBounds(150,150,300,150);         d.setResizable(false);
      d.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
      
      JComboBox c = new JComboBox();    d.add(c);  c.setBounds(30,20,240,20);
      JButton b1 = new JButton(sysLTab.getProperty("stLSet","Set"));       d.add(b1); b1.setBounds(30,60,100,20);  
      JButton b2 = new JButton(sysLTab.getProperty("stLCancel","Cancel")); d.add(b2); b2.setBounds(170,60,100,20); 
      
      File dir = new File(PeaceBox.sysBasePath+"img\\lang\\"); 
      File f[] = dir.listFiles();  
      for(int i=0;i<f.length;i++){   
        String fn = f[i].getName();   
        if(f[i].isFile()){   
	        c.addItem(fn); 
	        if(PeaceBox.sysLang.equals(fn)) c.setSelectedItem(fn);
	      }  
      } 
      
      c.addItemListener(new ItemListener() {
        public void itemStateChanged(ItemEvent ie) {
          if(ie.getStateChange() == 1) {
            setLang = ie.getItem().toString();
          }
        }
      }); // 更改
      b1.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) { 
          sysConfig.setProperty("cfgLang",setLang);
          PeaceBox.sysLang = setLang;
          saveProp();
		    	JOptionPane.showMessageDialog(null,sysLTab.getProperty("stLFSave","Set OK,Please Restart It!"));
		    	d.dispose();
		    }
		  } );  // 确认
      b2.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e) { 
		    	d.dispose();
		    }
		  } );	// 取消
		  
    } 
  } // Lang

  
}


