package module.tools;
import begin.*;
import sframe.*;

//import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
//import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

//import java.io.PrintWriter;
import java.net.*;

public class IPScan {  
  
  static JTextArea sysArea = PeaceBox.sysEditor;
  static Properties sysConfig = PeaceBox.sysConfig;
  
  public static void scanDialog() 
  {
  	
    final JTextField ipBase = new JTextField(IPScan.getLocal(""));
    final JTextField ipMin = new JTextField(sysConfig.getProperty("IPMin","2"));  
    final JTextField ipMax = new JTextField(sysConfig.getProperty("IPMax","254"));
    final JCheckBox ipFlag = new JCheckBox("显示未使用的"); 
    final JDialog ip = new JDialog();
    if(sysConfig.getProperty("IPFlag").equals("NA")) ipFlag.setSelected(true);
    
    ip.setTitle("IP扫描设置");
    ip.setBounds(150,150,300,150);
    ip.setVisible(true);
    ip.setLayout(null);
    ip.setResizable(false);
    ip.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
    JLabel jlb1,jlb2,jlb3;
    jlb1 = new JLabel("IP头:"); ip.add(jlb1); jlb1.setBounds(20,10,100,20);
    jlb2 = new JLabel("范围:"); ip.add(jlb2); jlb2.setBounds(20,40,100,20); 
    jlb3 = new JLabel("选项:"); ip.add(jlb3); jlb3.setBounds(20,70,100,20); 
    
    ip.add(ipBase); ipBase.setBounds(50,10,220,20);
    ip.add(ipMin);  ipMin.setBounds(50,40,80,20);
    ip.add(ipMax);  ipMax.setBounds(190,40,80,20);
    
    ip.add(ipFlag); ipFlag.setBounds(50,70,110,20);
    JButton bScn = new JButton("扫描(S)"); bScn.setMnemonic('S');
    ip.add(bScn); bScn.setBounds(190,70,80,20);
    
    bScn.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) { 
		  	sysArea.setText("");
		  	String flag = "OK";
		  	if(ipFlag.isSelected()==true) { flag = "NA"; }
		  	String base = ipBase.getText();
		  	int min = Integer.valueOf(ipMin.getText());
		  	int max = Integer.valueOf(ipMax.getText());
		  	final String f = flag;
        for(int id=min;id<=max;id++) {   
          final String ip = base+"."+id; 
          new Thread() {   
            public void run() {   
              IPScan.getHost(ip, f, sysArea);
            } // run   
          }.start(); // new Thread 
        } // for End
		  	sysConfig.setProperty("IPMin",ipMin.getText());
		  	sysConfig.setProperty("IPMax",ipMax.getText());
		  	sysConfig.setProperty("IPFlag",flag);
		  	MSett.saveProp();
		  	ip.dispose();
		  }
		} );
		return;
  }
  
  public static void scan(String base, int min, int max, String flag) 
  {
    final String f = flag; // OK,NA
    for(int id=min;id<=max;id++) {   
      final String ip = base+"."+id; 
      new Thread() {   
        public void run() {   
          String s = getHost(ip, f);
          if(!s.equals("")) { 
        	  System.out.println(s);
          } 
        } // run   
      }.start(); // new Thread 
    } // for 	s
    /*
    IPScan.scan("192.168.37", 2, 254, "OK");
    */
  }

  public static String getHost(String ip, String flag) {   
    String s0 = "", f = flag;
    InetAddress ha = null;
    try {   
      ha = InetAddress.getByName(ip); 
      if(!ha.getHostName().equalsIgnoreCase(ha.getHostAddress())) {
        if(f.equals("OK")) { 
        	s0 = getMac(ip)+" "+ip+" "+ha.getHostName(); 
        } 
      } else {
      	if(f.equals("NA")) { 
      		s0 = ip+": [Null] "; 
      	} 
      }  
	  } catch(Exception e) { 
	  	System.out.println("Error getConfig:"+e.toString()); 
	  }         
    return s0;
  }
  
  public static void getHost(String ip, String flag, JTextArea sysArea) {   
    String s0 = "", f = flag;
    InetAddress ha = null;
    try {   
      ha = InetAddress.getByName(ip); 
      if(!ha.getHostName().equalsIgnoreCase(ha.getHostAddress())) {
        if(f.equals("OK")) { 
        	s0 = getMac(ip)+" "+ip+" "+ha.getHostName(); 
        } 
      } else {
      	if(f.equals("NA")) { 
      		s0 = ip+": [Null] "; 
      	} 
      }  
	  } catch(Exception e) { 
	  	System.out.println("Error getConfig:"+e.toString()); 
	  }         
    if(!s0.equals("")) { 
      sysArea.append(s0+System.getProperty("line.separator"));
    }
  }
 
  public static String getMac(String IP)
  {
    String str = "", mac = "";
    try {
      Process pp = Runtime.getRuntime().exec("nbtstat -A " + IP);
      InputStreamReader ir = new InputStreamReader(pp.getInputStream());
      LineNumberReader input = new LineNumberReader(ir);
      for (int i = 1; i < 100; i++) {
        str = input.readLine();
        if (str != null) {
          if (str.indexOf("MAC Address") > 1) {
            mac = str.substring(str.indexOf("MAC Address") + 14, str.length());
            break;
          }
        }
      }
	  } catch(Exception e) { 
	  	System.out.println("Error getConfig:"+e.toString()); 
	  } 
    return mac;
    /* C:\Windows>nbtstat -A 192.168.1.168
      |        NetBIOS Remote Machine Name Table      |
      |                                               |
      |    Name               Type         Status     |
      | --------------------------------------------- |
      | TIEXIN         <00>  UNIQUE      Registered   |
      | TIEXIN         <20>  UNIQUE      Registered   |
      | WORKGROUP      <00>  GROUP       Registered   |
      | WORKGROUP      <1E>  GROUP       Registered   |
      | WORKGROUP      <1D>  UNIQUE      Registered   |
      | ..__MSBROWSE__.<01>  GROUP       Registered   |
      |                                               |
      | MAC Address = 00-1A-4D-61-7D-C0               |
    */
  }

  public static String getLocal()
  {
    try {   
      InetAddress inet = InetAddress.getLocalHost(); 
      return inet.getHostAddress();        
	  } catch(Exception e) { 
	  	System.out.println("Error getConfig:"+e.toString());
	  	return ""; 
	  }
	  /*
	  System.out.println("Local IP:" + getLocal()); 	  
	  */ 
  }  
  
  public static String getLocal(String flag)
  {
    String ip = getLocal();
    int p = ip.lastIndexOf(".");
    return ip.substring(0,p);
	  /*
	  System.out.println("Local IP:" + getLocal("")); 	  
	  */ 
  } 
     	
}

