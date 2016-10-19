package begin;

import java.util.regex.*;
//import java.util.ArrayList;
import java.text.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;
import javax.swing.text.html.*;

public class Func {  
  
  private static String stringValue;
  private static int num;
  private static String pCTab = "0123456789ABCDEFGHJKLMNPQRSTUVWXY";
  private static String sysLine = System.getProperty("line.separator");
  
  private static SimpleDateFormat tmTmp1 = new SimpleDateFormat("yyyy-MM-dd");
  private static SimpleDateFormat tmTmp2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  private static SimpleDateFormat tmTmp3 = new SimpleDateFormat("HH:mm:ss");
  private static SimpleDateFormat tmTmp4 = new SimpleDateFormat("SSS");  
  private static String tmStr;
  private static Date tmObj = new Date();
  private static Calendar tmCal  = Calendar.getInstance();
  
  public static void doException(Exception e, String s) {
    String eMsg = e.toString(); 
    int p = eMsg.indexOf(":")+1; 
    int q = eMsg.lastIndexOf("(");
    String sMsg = eMsg.substring(0,p);
    if(q>(p+5)) sMsg += sysLine+eMsg.substring(p,q);
    sMsg += sysLine+eMsg.substring(q,eMsg.length()); 
    JOptionPane.showMessageDialog(null, "[Error] "+s+"\n\r"+sMsg); 
  }
  
  public static String getYYYYMMDD(String xTStr) 
  {
	  int nStr = xTStr.length();
	  if(nStr<5){
	      tmStr = tmTmp1.format(tmCal.getTime());
	  }else{
	    if(nStr>10){
	  	try { tmObj = tmTmp2.parse(xTStr); }
	  	catch(Exception e) {;}
	  	tmStr = tmTmp2.format(tmObj.getTime());
	  	tmStr = tmStr.substring(0,10);
	    }else{
	  	try { tmObj = tmTmp1.parse(xTStr);}
	  	catch(Exception e) {;}
	  	tmStr = tmTmp1.format(tmObj.getTime());
	    }	
	  }
    return tmStr.replace("-","");
  }
  
  public static String getHHMMSS() {
	tmStr = tmTmp3.format(tmCal.getTime());
	return tmStr.replace(":","");
  }
  
  public static String getMSec() {
	tmStr = tmTmp4.format(tmCal.getTime());
	return tmStr;
  }
  
  public static String get999ID(String xStr,int xLen) {
	if(xLen>24){ xLen = 24; }
	tmStr = "000000000000000000000000"+xStr; 
	int n = tmStr.length();
	return tmStr.substring(n-xLen,n);
  }
  
  public static String getAutoID(int xLen) {
	if(xLen>24){ xLen = 24; }
	tmStr = tmTmp2.format(tmCal.getTime()); //yyyy-MM-dd HH:mm:ss
	tmStr = tmStr.replace("-","").replace(":","").replace(" ","");
	String sCTab = pCTab; 

	String s = tmStr; 
	int t = 0;
	tmStr = s.substring(0,4); // yyyy
	t = Integer.parseInt(s.substring(4,6));
	tmStr += sCTab.substring(t,t+1); // mm
	t = Integer.parseInt(s.substring(6,8));
	tmStr += sCTab.substring(t,t+1); // dd
	t = Integer.parseInt(s.substring(8,10));
	tmStr += sCTab.substring(t,t+1); // HH
	
	int nMin = Integer.parseInt(s.substring(10,12));
	int nSec = Integer.parseInt(s.substring(12,14));
	int nSed = (int)(nSec/2); // 附加
	nSec = (int)((nMin*60+nSec)/4); // mmss - XX
	nMin = (int)Math.floor(nSec/32);
	nSec = nSec%32;
	tmStr += sCTab.substring(nMin,nMin+1);
	tmStr += sCTab.substring(nSec,nSec+1);
	tmStr += sCTab.substring(nSed,nSed+1); // 附加
	
	tmStr = tmStr+getRndID("",15); 
	return tmStr.substring(0,xLen);

  }
  
  public static String getRndID(String xType,int xLen) {
	Random random = new Random();
    String sCTab = pCTab; 
    int sCMax = 33;
	String sRand = ""; // Num,ABC,abc,Key,Full
	if(xType.equals("0")){
      sCTab = "0123456789"; 
      sCMax = 10;
	}
	if(xType.equals("A")){
      sCTab = "ABCDEFGHJKLMNPQRSTUVWXY"; 
      sCMax = 23;
	}
	if(xType.equals("a")){
      sCTab = "abcdefghijkmnpqrstuvwxy"; 
      sCMax = 23;
	}
	if(xType.equals("F")){
      sCTab = pCTab+"abcdefghijkmnpqrstuvwxy"; 
      sCMax = 56;
	}
    for (int i=0;i<xLen;i++){
      int rPos = random.nextInt(sCMax);
      String rand = sCTab.substring(rPos,rPos+1); 
	    sRand += rand;
    }
    return sRand;
  }  

  /* http://www.cppblog.com/biao/archive/2009/11/04/100130.html?opt=admin
  ANSI：　　　　　　　　无格式定义；
  Unicode： 　　　　　　前两个字节为FFFE；
  Unicode big endian：　前两字节为FEFF；　 
  UTF-8：　 　　　　　　前两字节为EFBB；
  */
  public static String get_charset( byte[] first3Bytes ) {   
    String charset = ""; //GBK
    try {    
      if ( first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE ) {   
        charset = "UTF-16LE";    
      }   
      else if ( first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF ) {   
        charset = "UTF-16BE";   
      }   
      else if ( first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF ) {   
        charset = "UTF-8";   
      }   
    } catch ( Exception e ) {   
      e.printStackTrace();   
    }   
    return charset;   
  }   

     	
}

