package module.puzzler;
import begin.*;

public class Prime {
	
  static String sysLine = System.getProperty("line.separator");

  Prime() {
  	;
  }

  public static void main(String[] agrs) {
  	String s = getList(1, 100);
  	s = s.replace(sysLine," ");
    s = s.replace(" All:",sysLine);
    System.out.print(s);
    System.out.println("");
    System.out.print(sysLine+getSup(10000,99999,2));
    System.out.print(sysLine+getSup(200000,399999,1));
  }
  
  public static String getSup(int n1,int n2,int f) {
  	String s="";
  	int n=0;
  	boolean b;
  	if(n1%2==0) n1++;
  	for(int i=n1;i<=n2;i=i+2) { 
  	  if(f==1) b = isSup1(i);
  	  else b = isSup2(i);
  	  if(b) {
  	    s += i+sysLine; 
  	    n++;
  	  }
  	}
  	return s+"All:"+n+sysLine;
  }
  
  public static String getSup(int f) {
  	String s="";
  	int n=0;
  	boolean b;
  	for(int i=100001;i<=999999;i=i+2) { 
  	  if(f==1) b = isSup1(i);
  	  else b = isSup2(i);
  	  if(b) {
  	    s += i+sysLine; 
  	    n++;
  	  }
  	}
  	return s+"All:"+n+sysLine;
  }
   
  public static String getList(int a, int b) {
  	String s = "";
  	int n = 0;
  	for(int i=a;i<=b;i++) {
  	  if(isPrime(i)){
  	    s += i+sysLine; 
  	    n++;
  	  }
  	}
  	return s+"All:"+n;
  } 
  
  public static boolean isSup2(int i) {
  	String t = Integer.toString(i);
  	int k = t.length(); 
  	int[] a = new int[k];   
  	int n1,n2,n3,n4,n5,n6;
  	for(int j=0;j<k;j++) { 
  		a[j] = Integer.valueOf(t.substring(j,j+1));
  	}
  	n1 = Integer.valueOf(a[0]);
  	if(isPrime(n1)) {
  	n2 = Integer.valueOf(a[0]+""+a[1]);
  	if(isPrime(n2)) {
  	n3 = Integer.valueOf(a[0]+""+a[1]+""+a[2]); //abc
  	if(isPrime(n3)) {
  	n4 = Integer.valueOf(a[0]+""+a[1]+""+a[2]+""+a[3]);	
  	if(isPrime(n4)) {
  	n5 = Integer.valueOf(a[0]+""+a[1]+""+a[2]+""+a[3]+""+a[4]);
  	if(isPrime(n5)) {
  	if(isPrime(i)) {
  	            return true;
  	} } } } } }
  	return false;
  } //规则2: abcdef六位数素数; a,ab,abc,abcd,abcde组成5个素数; 
   
  public static boolean isSup1(int i) {
  	String t = Integer.toString(i);
  	int k = t.length(); 
  	int[] a = new int[k];   
  	int n1,n2,n3,n4,n5,n6;
  	for(int j=0;j<k;j++) { 
  		a[j] = Integer.valueOf(t.substring(j,j+1));
  	}
  	n1 = Integer.valueOf(a[0]+""+a[1]+""+a[2]);
  	n2 = Integer.valueOf(a[3]+""+a[4]+""+a[5]);
  	if(isPrime(n1)) {
  	if(isPrime(n2)) {
  	    n1 = Integer.valueOf(a[0]+""+a[1]);
  	    n2 = Integer.valueOf(a[2]+""+a[3]);
  	    n3 = Integer.valueOf(a[4]+""+a[5]);
  	    if(isPrime(n1)) {
  	    if(isPrime(n2)) {
  	    if(isPrime(n3)) {   
  	          if(isPrime(i)) {
  	              return true;
  	          }
  	    } } }
  	} }
    return false;
  } //规则1: abcdef六位数素数; abc,def组成2个3位数素数; ab,cd,ef组成3个2位数素数。
 
  
  private static boolean isPrime(int x)
  {
  	boolean b = true;
  	if(x<2) return false;
  	if(x==2) return true;
  	int m = (int)Math.pow(x,0.5d);
  	for(int i=2;i<=m;i++)
  	{
  		if((x%i)==0) return false;
  	}
  	return b;
  }
    
}

