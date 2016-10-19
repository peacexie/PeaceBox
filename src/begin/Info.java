package begin;

import java.util.*;
import java.io.*;
import java.nio.charset.*;

public class Info {  
  
	public static Properties getJDKInfo() // getJVMInfo
	{
	  Properties pps = System.getProperties();
    return pps;
    /*
		Properties ps = getJDKInfo();
		System.out.println(ps.getProperty("os.name"));
		ps.list(System.out);  
    System.out.println("java_vendor:" + System.getProperty("java.vendor")); //Java ����ʱ������Ӧ��                                               
    ystem.out.println("java_vendor_url:" + System.getProperty("java.vendor.url")); //Java ��Ӧ�̵� URL                                            
    System.out.println("java_home:" + System.getProperty("java.home")); //Java ��װĿ¼                                                           
    System.out.println("java_class_version:" + System.getProperty("java.class.version")); //Java ���ʽ�汾��                                     
    System.out.println("java_class_path:" + System.getProperty("java.class.path")); //Java ��·��                                                 
    System.out.println("os_name:" + System.getProperty("os.name")); //����ϵͳ������                                                              
    System.out.println("os_arch:" + System.getProperty("os.arch")); //����ϵͳ�ļܹ�                                                              
    System.out.println("os_version:" + System.getProperty("os.version")); //Java ����ʱ�����汾                                                   
    System.out.println("user_name:" + System.getProperty("user.name")); //�û����˻�����                                                          
    System.out.println("user_home:" + System.getProperty("user.home")); // �û�����Ŀ¼                                                           
    System.out.println("user_dir:" + System.getProperty("user.dir")); //�û��ĵ�ǰ����Ŀ¼                                                        
    System.out.println("java_vm_specification_version:" + System.getProperty("java.vm.specification.version")); //Java ������淶�汾             
    System.out.println("java_vm_specification_vendor:" + System.getProperty("java.vm.specification.vendor")); //Java ������淶��Ӧ��             
    System.out.println("java_vm_specification_name:" + System.getProperty("java.vm.specification.name")); //Java ������淶����                   
    System.out.println("java_vm_version:" + System.getProperty("java.vm.version")); //Java �����ʵ�ְ汾                                         
    System.out.println("java_vm_vendor:" + System.getProperty("java.vm.vendor")); //Java �����ʵ�ֹ�Ӧ��                                         
    System.out.println("java_vm_name:" + System.getProperty("java.vm.name")); //Java �����ʵ������                                               
    System.out.println("java_ext_dirs:" + System.getProperty("java.ext.dirs")); //һ��������չĿ¼��·��                                        
    System.out.println("file_separator:" + System.getProperty("file.separator")); //�ļ��ָ������� UNIX ϵͳ���ǡ�/��?                            
    System.out.println("path_separator:" + System.getProperty("path.separator")); //·���ָ������� UNIX ϵͳ���ǡ�:��?                            
    System.out.println("line_separator:" + System.getProperty("line.separator")); //�зָ������� UNIX ϵͳ���ǡ�/n���� 		  
    */
  }

	public static String[] getCharsets()
	{		
    Map m = Charset.availableCharsets();                                    
    Set names = m.keySet();                                                 
    Iterator it = names.iterator();  
    String[] a = new String[256];                                   
    int i = 0;                                                                         
    while(it.hasNext())                                    
    {                                                                       
      i++;
      a[i] = it.next().toString();                                                  
    } 
    a[0] = String.valueOf(i);
    return a; 
		/* Demo: 
		String[] a = getCharsets();
		int m = Integer.valueOf(a[0]);
		for(int i=1;i<=m;i++){
			System.out.println(i+":"+a[i]+";");   
		}
		*/                                                                   	
  }

	public static Properties getConfigs(String fName,String cSet)
	{
	  fName = getBasePath()+fName;
	  Properties pps = new Properties(); 
	  try { 
	  	pps.load(new FileInputStream(fName)); 
	  } catch(Exception e) { 
	  	if(fName.indexOf("PeaceBox.ini")>0) {
	  	  System.out.println("Reset: PeaceBox.ini"); 
	    } else {
	    	System.out.println("Error getConfig:"+e.toString()); 
	    }
	  } 
	  if(cSet.equals("")) cSet = pps.getProperty("cfgCSet","ISO-8859-1");    
	  if(!cSet.equals("ISO-8859-1")) {                      
	    Enumeration en = pps.propertyNames();                              
	    while(en.hasMoreElements()) {                                      
	      String strKey = (String)en.nextElement();                        
	      String strValue = pps.getProperty(strKey); 
	      try {
	        strValue = new String(strValue.getBytes("ISO-8859-1"),cSet); 
	      } catch(Exception e) { 
	    	  System.out.println("Error getConfig:"+fName+e.toString()); 
	      }                                                             
	      pps.setProperty(strKey, strValue);                               
	    }  
	  }                                                                 
	  return pps;        
	  /*
		Properties cfg = getConfigs("cfgSystem.ini","GB2312");
		System.out.println(cfg.getProperty("PoolID"));
		cfg.list(System.out);
	  */                                                
	}
	
	public static String getBasePath()
	{
	  String udir = System.getProperty("user.dir");  
	  String fsep = File.separator;
	  int n = udir.length();
	  String s3 = udir.substring(n-3,n);
	  if(s3.equals("bin")||s3.equals("src"))
	  {
	  	udir = udir.substring(0,n-3);
	  } else {
	  	udir += "\\";
	  }
	  return udir;
	}
     	
}

