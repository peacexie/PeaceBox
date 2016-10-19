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

public class SideBar extends JFrame {  
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysLine = System.getProperty("line.separator");
  String sysBasePath = Info.getBasePath();
  
  public static JPanel pSide = PeaceBox.pSide; 
  public static JPanel pBox1 = new JPanel(null); 
  public static JPanel pBox2 = new JPanel(null); 
  
  private JButton btSide = PeaceBox.tooBar.btSide; 
  private Color csGack = new Color(255,255,255); //* 210,210,210 240,240,240 248,248,248 255,255,255
  private Color ctGack = new Color(210,210,210);
  
  private JPanel pTitle = new JPanel(new BorderLayout(0,0)); 
  private JPanel pUrl   = new JPanel(new BorderLayout(0,0)); 
  private JPanel pTop   = new JPanel(new BorderLayout(0,0)); 
  public JTextField tUrl = new JTextField(sysBasePath,12); // 12参数
  //public JLabel pLab = new JLabel("[Parent Directory]");
  private String fPathOpen = PeaceBox.sysBasePath; //PeaceBox.sysConfig.fileOpen
  private String fPathGap = System.getProperty("file.separator");

  public void setSide() // 初始化Side
  { 
    pSide.setVisible(false); 
    pSide.setLayout(new BorderLayout(0,0));
     
    setTitle();
    setBox();
  	fillBox();
  } 
   
  private void reBox() // 重新填写Box1,Box2
  {
  	pBox1.removeAll();
  	pBox2.removeAll();
  	pBox1.repaint(); 
  	pBox2.repaint();
  	fillBox(); 
  } 

  // fillItem()
  private void fillBox() // 填写Box1,Box2主控制台
  { 
  	String sTab = getFiles(fPathOpen);
  	String[] aTmp = sTab.split("\\?"); 
  	String[] aDir = aTmp[0].split("\\|"); int n1 = aDir.length+1;
  	String[] aFil = aTmp[1].split("\\|"); int n2 = aFil.length;
  	if(n1<10) n1 = 10; 
  	if(n2<17) n2 = 17; 
  	pBox1.setLayout(new GridLayout(n1,1,0,0)); 
  	pBox2.setLayout(new GridLayout(n2,1,0,0));
  	fillPDir();
  	fillList(pBox1, aDir, 1);
  	fillList(pBox2, aFil, 2);
  }
  
  private void fillList(JPanel p, String[] a, final int f) //填写单个Box1,Box2
  { 
  	for(int i=0;i<a.length;i++) { 
  		
  	  final JLabel iLab = new JLabel("");
  	  iLab.setBackground(new Color(255,255,255)); 
  	  iLab.setOpaque(true);
  	  iLab.setToolTipText(a[i]);
  	  if(a[i].length()>25) iLab.setText(a[i].substring(0,22)+"...");
  	  else iLab.setText(a[i]);
  		p.add(iLab);
  		if(iLab.getText().indexOf(":No")<0)
  		{
  		  iLab.addMouseListener(new MouseAdapter() { 
          public void mouseEntered(MouseEvent e) {
            iLab.setBackground(new Color(240,240,240));
          } 
          public void mouseExited(MouseEvent e) {
          	iLab.setBackground(new Color(255,255,255));
          }
          public void mouseClicked(MouseEvent e) { 
            if(e.getClickCount() == 2){ 
              if(f==1) {
                fPathOpen += iLab.getToolTipText()+fPathGap; 
                tUrl.setText(fPathOpen); 
                reBox(); 
                sysEditor.setText(tUrl.getText());
              } else {
                // Open Files
                sysEditor.setText(Coder.readFile(tUrl.getText()+iLab.getToolTipText()));
                //sysEditor.setText(iLab.getToolTipText());
              }
            } 
          } // end mouseClicked
        });   
      } else {
      	iLab.setBackground(new Color(180,180,180)); 
      }
  	}	
  }
  
  private void fillPDir() // (返回)上级目录
  {
  	final JLabel pLab = new JLabel("[Parent Directory]"); 
  	pLab.setHorizontalAlignment(SwingConstants.CENTER);
  	pLab.setBackground(new Color(255,255,255)); 
  	pLab.setOpaque(true); 
  	pBox1.add(pLab);  
  	pLab.addMouseListener(new MouseAdapter() { 
      public void mouseEntered(MouseEvent e) { 
        pLab.setBackground(new Color(240,240,240)); 
      } 
      public void mouseExited(MouseEvent e) {
      	pLab.setBackground(new Color(255,255,255));
      }
      public void mouseClicked(MouseEvent e) { 
        if(e.getClickCount() == 2) { 
          String p = tUrl.getText();
          p = p.substring(0,p.length()-1); 
          int n = p.lastIndexOf(fPathGap); 
          if(n>0) { 
          	p = p.substring(0,n+1); 
          	fPathOpen = p; 
          	tUrl.setText(p); 
          	reBox(); 
          	sysEditor.setText(p); 
          }
        }
      } 
  	}); // End Parent Dir .... 
  }

  private void setTitle()  // 初始化Title
  { 
    Insets bGap = new Insets(0,0,0,0);
    JPanel pTop2 = new JPanel(new GridLayout(2,1,1,1));
    pTop2.add(pTitle,"North");  
    pTop2.add(pUrl,"South"); 
    pTop.add(pTop2,"North");
    
    pTitle.setBackground(ctGack);
    JButton sHide = new JButton(sysLTab.getProperty("tbHide","Hide")); 
    sHide.setMargin(bGap); 
    pTitle.add(new JLabel(" Side Title"),"Center"); 
    pTitle.add(sHide,"East");  
    
    //JButton bOpen = new JButton(sysLTab.getProperty("tbOpen","Open")); 
    //bOpen.setMargin(bGap); 
    //pUrl.add(bOpen,"East"); 
    tUrl.setText(fPathOpen); 
    pUrl.add(tUrl,"Center"); 
    
    sHide.addActionListener(new aHide()); 
    tUrl.addCaretListener(new updUrl());
  } 
  
  private void setBox() // Box
  { 
    //pBox1.setLayout(new GridLayout(8,1,0,0));
    pBox1.setBackground(csGack);
    pBox1.setBorder(BorderFactory.createEmptyBorder(2,3,1,1));
    JScrollPane pScr1 = new JScrollPane(pBox1);
    pScr1.setPreferredSize(new Dimension(180, 160));
    pScr1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pScr1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pTop.add(pScr1,"Center");  
    pSide.add(pTop,"North"); 	

  	//pBox2.setLayout(new GridLayout(24,1,0,0));
  	pBox2.setBackground(csGack);
  	pBox2.setBorder(BorderFactory.createEmptyBorder(2,3,1,1));
    JScrollPane pScr2 = new JScrollPane(pBox2);
    pScr2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    pScr2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
    pSide.add(pScr2,"Center"); 
  }
  
  public String getFiles(String fPath) {
	  String fn, sd = "", sf = "";
    File d = new File(fPath); 
    File list[] = d.listFiles();    
    for(int i=0;i<list.length;i++){   
      fn = list[i].getName();   
      if(list[i].isFile()){   
		    sf += fn +"|";
      } else { 
		    sd += fn +"|";
	    }  
    } 
    if(sd.equals("")) sd = ":NoSubDir:";
    if(sf.equals("")) sf = ":NoFile:";
    sd = sd +"?"+ sf; //System.out.println(sd);
	  return sd;
  } 

  class aHide implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { 
    	pSide.setVisible(false);
    	btSide.setEnabled(true); 
    } 
  } // aHide
 
 	class updUrl implements CaretListener
	{
    public void caretUpdate(CaretEvent e1) {	
    tUrl.addKeyListener(new KeyAdapter(){ 
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER) {
          fPathOpen = tUrl.getText(); 
          File f = new File(fPathOpen);   
          if(fPathOpen.equals("")) { 
          	tUrl.setText("C:"+fPathGap);
          	fPathOpen = tUrl.getText();
          } // End Not Null
          if(!f.isDirectory()) { 
          	tUrl.setText("C:"+fPathGap);
          	fPathOpen = tUrl.getText();
          } // End Not Dir 
          if(!fPathOpen.substring(fPathOpen.length()-1,fPathOpen.length()).equals(fPathGap))
          { fPathOpen += fPathGap; tUrl.setText(fPathOpen); } 
          reBox(); 
          sysEditor.setText(tUrl.getText());
        } 
      } // end keyPressed
    }); 
    } 
	} // updUrl

 
}

 