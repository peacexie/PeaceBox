package module.tools;

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

import java.net.*;

public class Coder extends JFrame {  
  
  static JTextArea sysArea = PeaceBox.sysEditor;
  static Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  static String sysLine = System.getProperty("line.separator");
  static String sysBasePath = Info.getBasePath();
  static Properties sysConfig = PeaceBox.sysConfig;
  
  private long fileSize = 0;
  private String fileCSet = "GBK";
  private String basePath = "";
  private static String fPathGap = System.getProperty("file.separator");
  
  private String fileExts = "";
  private String fillDirs = "";
    
  private String fileHead1 = sysLine+"/*******************************************************************************";
  private String fileHead2 = sysLine+" File : (fName) ";
  private String fileHead3 = sysLine+"*******************************************************************************/";
  private String fileHead = sysLine+sysLine+fileHead1+fileHead2+fileHead3+sysLine;
  
  private static boolean closeForm=false;

  private Coder() {
    ;
  }
  private Coder(String filePath) {
    getCSet(new File(filePath));
  }
  private Coder(File file) {
    getCSet(file);
  } 

  public static void CUnite(String myForm) {
  	
    final JTextField pBase = new JTextField(sysConfig.getProperty("fileOpen",sysBasePath));
    final JTextField fExts = new JTextField(sysConfig.getProperty("CDExts","asp|jsp|java|php|"));  
    final JTextField fFill = new JTextField(sysConfig.getProperty("CDFill","editor|pcode|"));
    final JDialog dp = new JDialog();
    
    dp.setTitle(myForm);
    dp.setBounds(150,150,300,150);
    dp.setVisible(true);
    dp.setLayout(null);
    dp.setResizable(false);
    dp.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    
    JLabel jlb1,jlb2,jlb3;
    jlb1 = new JLabel("跟目录:"); dp.add(jlb1); jlb1.setBounds(10,10,100,20);
    jlb2 = new JLabel("扩展名:"); dp.add(jlb2); jlb2.setBounds(10,35,100,20); 
    jlb3 = new JLabel("过滤项:"); dp.add(jlb3); jlb3.setBounds(10,60,100,20); 
    
    dp.add(pBase); pBase.setBounds(60,10,220,20);
    dp.add(fExts); fExts.setBounds(60,35,220,20);
    dp.add(fFill); fFill.setBounds(60,60,220,20);

    JButton bScn = new JButton("导出代码");
    dp.add(bScn); bScn.setBounds(180,85,100,20);
    
    bScn.addActionListener(new ActionListener(){
		  public void actionPerformed(ActionEvent e) { 
		  	
		  	String p = pBase.getText(); 
		  	int j = 0;
		  	
  	    Coder c1 = new Coder();
  	    c1.basePath = p;
        c1.fileExts = fExts.getText();
        c1.fillDirs = fFill.getText();
        
		  	c1.saveDir(p+"","(Root)",0);
		  	sysArea.append("(Root) OK"+sysLine);
        File d = new File(pBase.getText()); 
        File list[] = d.listFiles();  
        for(int i=0;i<list.length;i++){   
          String fName = list[i].getName();
          if(list[i].isFile()) {   
            ;
          } else {
          	j++;
          	c1.saveDir(p+fPathGap+fName,fName,j);
          	sysArea.append(fName+" OK"+sysLine);
          }  
        } 
		  	sysConfig.setProperty("fileOpen",pBase.getText());
		  	sysConfig.setProperty("CDExts",fExts.getText());
		  	sysConfig.setProperty("CDFill",fFill.getText());
		  	MSett.saveProp();
		  	dp.dispose();
		  }
		} );
  }


  public static void main(String[] args)
  {
  	
  	closeForm=true;
  	CUnite("Code Unite");

  }

  public StringBuffer getBDir(String fPath) {
	  StringBuffer sb = new StringBuffer();
    File d = new File(fPath); 
    File list[] = d.listFiles();  
    for(int i=0;i<list.length;i++) {   
      String fName = list[i].getName();
      String fullPath = fPath + fPathGap + fName; 
      if(list[i].isFile()){   
        String fExt = ""; //fileExts = "asp|jsp|java|php|apsx|cs|js|"
        int p = fName.lastIndexOf(".");
        if(p>0) {
        	fExt = fName.substring(p+1,fName.length());
        	fExt = fExt.toLowerCase();
        }
        if(fileExts.toLowerCase().indexOf(fExt+"|")>=0) {
          String hd = fileHead;
          hd = hd.replace("(fName)",fullPath);
          hd = hd.replace(basePath,"");
		      sb.append(hd+readFile(fullPath));
		    }
      } else {
		    if(fillDirs.indexOf(fName+"|")<0)
		    { sb.append(getBDir(fullPath)); } //fillDirs = "editor|pcode|"
	    } 
    } 
    return sb;
  }
  
  public StringBuffer getBRoot(String fPath) {
	  StringBuffer sb = new StringBuffer();
    File d = new File(fPath); 
    File list[] = d.listFiles();  
    for(int i=0;i<list.length;i++){   
      String fName = list[i].getName();
      String fullPath = fPath +fPathGap+ list[i].getName();
      if(list[i].isFile()){   
        String fExt = "";
        int p = fName.lastIndexOf(".");
        if(p>0) {
        	fExt = fName.substring(p+1,fName.length());
        	fExt = fExt.toLowerCase();
        }
        if(fileExts.toLowerCase().indexOf(fExt+"|")>=0) {
          String hd = fileHead;
          hd = hd.replace("(fName)",fullPath);
          hd = hd.replace(basePath,"");
		      sb.append(hd+readFile(fullPath));
		    }
	    } 
    } 
    return sb;
  }
  
  public void saveDir(String fPath, String fName, int n) {
  	StringBuffer sb = new StringBuffer();
  	String m = "0"; if(n>9) m = "";
  	String fp = sysBasePath+fPathGap+"temp"+fPathGap+"code-"+m+n+fName+".txt";
  	if(n==0) {
  	  sb = getBRoot(fPath);
  	  fp = sysBasePath+fPathGap+"temp"+fPathGap+"code-(root).txt";	
  	} else {
  		sb = getBDir(fPath);
  	}
  	if(sb.length()==0) return;
    File myfile = new File(fp); 
    PrintWriter output = null; 
    try { 
      output = new PrintWriter(myfile); 
      output.write(sb.toString()); 
    } catch (IOException ex) { 
      Func.doException(ex, "Coder:(fileSave)");
    } finally { 
      output.close(); 
      output = null; 
    } 
  }

  public static String readFile(String filePath) {
  	Coder c1 = new Coder(filePath);
  	int len = (int)c1.fileSize; 
  	String cset = c1.fileCSet;
  	String tmp = ""; 
  	byte[] buf = new byte[len];
  	try { 
      FileInputStream fis = new FileInputStream(filePath);
      fis.read(buf);
      if(!cset.equals("GBK")) { 
      	tmp = new String(buf,0,len,cset); 
      } else {
      	tmp = new String(buf,0,len);
      }
      fis.close();
    } catch (Exception ex) {
        Func.doException(ex, "Coder"+":(readFile)");
    }
    return tmp;
  }  

  private void getCSet( File file ) {
    String cset = "GBK";
    this.fileSize = file.length();
    byte[] first3Bytes = new byte[3];
    try {
      boolean f = false;
      BufferedInputStream bis = new BufferedInputStream( new FileInputStream( file ) );
      bis.mark( 0 );
      int read = bis.read( first3Bytes, 0, 3 );
      if ( read == -1 ) fileCSet = cset; //return cset;
      if ( first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE ) {
        cset = "UTF-16LE";
        f = true;
      }
      else if ( first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF ) {
        cset = "UTF-16BE";
        f = true;
      }
      else if ( first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF ) {
        cset = "UTF-8";
        f = true;
      }
      bis.reset();
      if (!f) {
        int loc = 0; //int len = 0;
        while ( (read = bis.read()) != -1 ) {
          loc++;
          if ( read >= 0xF0 ) break;
          if ( 0x80 <= read && read <= 0xBF ) // 单独出现BF以下的，也算是GBK
          break;
          if ( 0xC0 <= read && read <= 0xDF ) {
            read = bis.read();
            if ( 0x80 <= read && read <= 0xBF ) // 双字节 (0xC0 - 0xDF) (0x80 // - 0xBF),也可能在GB编码内                                           
            continue;
            else break;
          }
          else if ( 0xE0 <= read && read <= 0xEF ) { // 也有可能出错，但是几率较小
            read = bis.read();
            if ( 0x80 <= read && read <= 0xBF ) {
              read = bis.read();
              if ( 0x80 <= read && read <= 0xBF ) {
                cset = "UTF-8";
                break;
              }
              else break;
            }
            else break;
          }
        } //System.out.println( loc + " " + Integer.toHexString( read ) );
      }
      bis.close(); 
    } catch ( Exception ex ) {
        Func.doException(ex, "Coder"+":(getCSet)");
    } 
    fileCSet = cset; //return cset;
  } 
  
}
