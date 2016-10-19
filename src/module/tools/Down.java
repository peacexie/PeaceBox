package module.tools;
import begin.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.regex.*;
import java.awt.*; 
import javax.swing.*; 

public class Down {  
  
  public static String DownLine = "[ === Download Result ================================================ ]";
  private static  String line = System.getProperty("line.separator");
  private static String DownFlag = ""; 
  private static int DownThread = 8;
  static JTextArea sysArea = PeaceBox.sysEditor;
  
  public static void start(String urlFiles) 
  { 
    String[] a = urlFiles.split("\\|");
    for(int i=0;i<a.length;i++)
    {
      final String url = a[i];
      if(url.equals(DownLine)) 
      { 
      	i = a.length + 2; 
      	break;
      }
      if((url.length()>10)&&(url.indexOf("//")>0))
      {
        new Thread() {   
          public void run() {   
            String s = down(url); 
          } // run   
        }.start(); // new Thread   
      }
    }
    /* Demo as Main() */
  }
  
  public static void start() 
  { 
		String sUrl = new String(sysArea.getText());
		sUrl = getListPart(sUrl);
    if((sUrl.length()>10)&&(sUrl.indexOf("//")>0))
    {
			DownFlag = "";//.setText("");
			String line = System.getProperty("line.separator");
			sysArea.setText(sUrl); 
			sysArea.append(line+line+Down.DownLine+line);
		  sUrl = sUrl.replace("\n\r","|");
		  sUrl = sUrl.replace("\n","|");
		  sUrl = sUrl.replace("\r","|");
		  sUrl = sUrl.replace("||","|");
      String[] a = sUrl.split("\\|");
      for(int i=0;i<a.length;i++)
      {
        final String url = a[i];
        if(url.equals(DownLine)) 
        { 
        	i = a.length + 2; 
        	break;
        }
        if((url.length()>10)&&(url.indexOf("//")>0))
        {
    	    if(flagSleep()) { //////////// start 调度程序
            i--; //这个不算,再循环
            while(flagSleep()) {
            	try {
            		Thread.sleep(500);
            	} catch (Exception Exp) { 
            		;//
            	}
            }
          } else { ///////////////////// else 调度程序 
            flagNew();
            new Thread() {   
              public void run() {   
                String s = down(url); 
              } // run   
            }.start(); // new Thread 
          } //////////////////////////// end 调度程序 
        }  
      }
		} else {
      String eMsg = "Error:下载设置错误! 错误代码:(mu4DLnk)\n\r"+"请设置URL地址";
      JOptionPane.showMessageDialog(null, eMsg);
		}
  }

  private static String down(String str)
  {
     String file = getFileName(str);
     try {
       URL url = new URL(str);
       URLConnection uConn = url.openConnection(); //该URL方法返回一个URLConnection的对象！
       InputStream is = uConn.getInputStream();  //Returns an input stream that reads from this open connection.
       // ??? 缓冲
       FileOutputStream fos = new FileOutputStream(Info.getBasePath()+"temp\\"+file);  //保存到本地硬盘
       int data;
       while((data=is.read())!=-1)   //读取数据文件（如图片）时，用字节流；
       {
         fos.write(data);   //写入字节，一个字节一个字节地读、写！
       }
       is.close();
       fos.close();
       sysArea.append(uConn.getContentType()+" ("+uConn.getContentLength()+"B) "+str+line);
       flagEnd("V");
     } catch (Exception ex) {
       sysArea.append("Error:("+str+")"+ex.toString()+line);
       flagEnd("X");
     }
     //System.out.println(DownFlag);
     return ""; 
  } 
  
  public static void getLinks() 
  { 
		String sUrl = new String(sysArea.getText());
		String line = System.getProperty("line.separator");
		sysArea.setText("");
    if((sUrl.length()>10)&&(sUrl.indexOf("//")>0))
    {
			String[][] aUrl = Down.getLinks(sUrl);
      for(int j=0;j<aUrl.length;j++)
      {
      	sysArea.append(aUrl[j][0]+System.getProperty("line.separator")); 
      }
		} else {
      String eMsg = "Error:链接提取错误! 错误代码:(mu3Link)\n\r"+"请加载内容";
      JOptionPane.showMessageDialog(null, eMsg);
		}
	}
  
  public static String[][] getLinks(String str)
  {
    String reg = "<a\\s*href\\s*=\\s*[\"|']([^\\s]*)[\"|']\\s*[^>]*\\s*>([^ <]*)<\\s*/a\\s*>";
                //<a.+?href=(\"|'|)\\b(.+?)\\b\\1(?:\\s.*)?>(.+?)</a>

    Pattern p = Pattern.compile(reg);
    Matcher m = p.matcher(str);  
    
    ArrayList<String> a1 = new ArrayList<String>(); 
    ArrayList<String> a2 = new ArrayList<String>(); 
    int i = 0;
    while(m.find()){
      a1.add(m.group(1));
      a2.add(m.group(2));
      i++;
    }

    String[] b1 = (String[]) a1.toArray(new String[0]);
    String[] b2 = (String[]) a2.toArray(new String[0]);
    String a[][] = new String[i][];
    for(int j=0;j<i;j++)
    {
    	String t = b1[j];
    	if(t.substring(0,1).equals("/"))
    	  t = "http://domain"+t;
    	a[j] = new String[2];
    	a[j][0] = t; //b1[j]; 
      a[j][1] = b2[j];
      //System.out.println(a[j][0]+" = "+a[j][1]);
    }
    return a;
  }  
  
  private static String getFileName(String url)
  {
  	int p = url.lastIndexOf("/");
  	String s = url;
  	s = s.replace("|","`").replace(":","`"); /* [?:\|/*<>"] */
  	if((p+1)==url.length()||(p<10)) // http://www.txjia.com/ 
  	{
  		p = url.indexOf("://")+3;
  		s = url.substring(p,url.length())+".htm";
  		//System.out.println(s);
  	} else {
  		p++;
  		s = url.substring(p,url.length());
  		//System.out.println(s);
  	}
  	if(s.indexOf(".")<0) { s = s+".htm"; }
  	if(s.indexOf("?")>0) { s = s+".htm"; }
  	s = s.replace("/.",".").replace("/","#").replace("?","~");
  	return s;
  }
  
  private static String getListPart(String str)
  {
  	int p = str.indexOf(line+line+Down.DownLine+line);
  	String s = str;
  	if(p>0) {
  		s = str.substring(0,p);
  		//System.out.println(":aa:"+p+s+":bb:");
  	}
  	return s;
  }
  
  public static void main(String[] args)
  {
    String urls = "http://www.txjia.com/";
    urls += "|http://www.txjia.com/index.asp";
    urls += "|http://www.txjia.com/web/ind.asp?ARNM=东莞";
    urls += "|http://www.txjia.com/?ARNM=东莞";
    urls += "|http://www.txjia.com/?ARNM=东莞:#TestFalg";
    urls += "|http://www.txjia.com/blog/";
    urls += "|http://www.txjia.com/blog";
    urls += "|http://www.txjia.com/web/bb/nview.asp?KeyID=0BA7DD4E009EYT8FX5MBDGP5";
    start(urls);
  }
  
  private static boolean flagSleep() 
  {
  	if(DownFlag.length()-DownFlag.replace("-","").length()>=DownThread) return true;
  	else return false;
  }
  
  private static void flagNew() 
  {
  	DownFlag += "-"; 
  }
  
  private static void flagEnd(String r) 
  {
  	String c,s="",t = DownFlag;
  	if(t.indexOf("-")>=0) {
  		boolean f = true;
  		for(int i=0;i<t.length();i++) {
  		  c = t.substring(i,i+1); 
  		  if(f&&c.equals("-")) {
  		  	s += r;
  		  	f = false;
  		  } else {
  		    s += c;
  		  }
  		}
    }
    DownFlag = s;
  }
       	
}

