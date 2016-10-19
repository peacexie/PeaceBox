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

public class MHelp {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysLine = System.getProperty("line.separator");
  String sysBasePath = Info.getBasePath();
  Properties sysConfig = PeaceBox.sysConfig;

  public void setMenu(JMenuBar sysMenu) 
  { 
    JMenu m = new JMenu(sysLTab.getProperty("hpSubj","Help(H)")); sysMenu.add(m);     m.setMnemonic('H'); 
    
    JMenuItem mHelp = new JMenuItem(sysLTab.getProperty("hpIHelp","Help ..."));       m.add(mHelp); 
    JMenuItem mOnline = new JMenuItem(sysLTab.getProperty("hpIOnline","Online ...")); m.add(mOnline); mOnline.setEnabled(false);
    m.addSeparator();                                                  
    JMenuItem mIJDK = new JMenuItem(sysLTab.getProperty("hpIJDK","Info JDK"));        m.add(mIJDK); 
    JMenuItem mICSet = new JMenuItem(sysLTab.getProperty("hpICSet","Info Charset"));  m.add(mICSet); 
    JMenuItem mIProp = new JMenuItem(sysLTab.getProperty("hpIProp","Info Config"));   m.add(mIProp); 
    m.addSeparator();                                   
    JMenuItem mAbout = new JMenuItem(sysLTab.getProperty("hpIAbout","About ..."));    m.add(mAbout);

    mHelp.addActionListener(new aHelp());
    mIJDK.addActionListener(new aIJDK());
    mICSet.addActionListener(new aICSet());
    mIProp.addActionListener(new aICfg());
    mAbout.addActionListener(new aAbout());
    
  } 
  
  public void aboutDialog() 
  {
    JDialog he = new JDialog();
    he.setTitle("About Peace Box");
    he.setBounds(150,150,300,200);
    he.setLayout(null);
    he.setVisible(true);
    he.setResizable(false); //不可调整大小
    he.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    he.setIconImage(Toolkit.getDefaultToolkit().getImage(sysBasePath+"img\\logo\\icon.png"));
    
    JLabel img1,img2,ab1,ab2,ab3,ab4,ab5,ab6;
    
    img1 = new JLabel(); img1.setIcon(new ImageIcon(sysBasePath+"img\\logo\\logo2.jpg"));
    he.add(img1);        img1.setBounds(20,15,68,60);
    img2 = new JLabel(); img2.setIcon(new ImageIcon(sysBasePath+"img\\logo\\logo.jpg"));
    he.add(img2);        img2.setBounds(130,15,120,60);
    
    ab1=new JLabel("Version: 1.00");         he.add(ab1); ab1.setBounds(20,102,100,15);
    ab2=new JLabel("Author: Peace");         he.add(ab2); ab2.setBounds(20,122,100,15);
    ab3=new JLabel("Build: 2010-02-23");     he.add(ab3); ab3.setBounds(20,142,100,15);
    
    ab4=new JLabel("QQ: 80893510");          he.add(ab4); ab4.setBounds(130,102,140,15);
    ab5=new JLabel("Mail: xpigeon@163.com"); he.add(ab5); ab5.setBounds(130,122,140,15);
    ab6=new JLabel("Http: www.dg.gd.cn/");   he.add(ab6); ab6.setBounds(130,142,140,15); 
  } 

	class aHelp implements ActionListener
	{
	  public void actionPerformed(ActionEvent e) { 
	  	try {
	  	  String cmd = "cmd.exe /c start iexplore "+sysBasePath+"data\\help.htm";
	  	  Runtime.getRuntime().exec(cmd); 
	    } catch (Exception ex) {
	    	Func.doException(ex, sysLTab.getProperty("hpMOpen")+":(m82Open)");
	    }
	  }
	} // Help 

  class aIJDK implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
 		  sysEditor.setText("");
 		  Properties p = Info.getJDKInfo();                                                                                      
      Enumeration en = p.propertyNames();                          
      int i = 0;                                                           
      while(en.hasMoreElements()){                                         
        i++;                                                               
        String sKey = (String)en.nextElement();                          
        String sValue = p.getProperty(sKey);  
        sValue = sValue.replace("\n","\\n");
        sValue = sValue.replace("\r","\\r");
        sysEditor.append(i+":"+sKey+"="+sValue+""+sysLine);                        
      } 
    }
  } // 信息m83JDK
  class aICSet implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
      sysEditor.setText("");
		  String[] a = Info.getCharsets();
		  int m = Integer.valueOf(a[0]);
		  for(int i=1;i<=m;i++){
		  	sysEditor.append(i+":"+a[i]+sysLine);
		  }
    }
  } // 设置m84CSet
  class aICfg implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { 
      sysEditor.setText("");
      Enumeration en = sysConfig.propertyNames();                          
      int i = 0;                                                           
      while(en.hasMoreElements()){                                         
        i++;                                                               
        String sKey = (String)en.nextElement();                          
        String sValue = sysConfig.getProperty(sKey); 
        sysEditor.append(i+":"+sKey+"="+sValue+""+sysLine);                           
      }
    }
  } // 设置m85Cfg
	
	class aAbout implements ActionListener
	{
    public void actionPerformed(ActionEvent e)
    { aboutDialog(); }
	} // 帮助m81Help


  
}


