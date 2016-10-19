package module.puzzler;
import begin.*;

/**
* 求解Sodoku SuPuz的工具类
* @author Eastsun
*/
public class Solver
{
  protected static final int SIZE = SuPuz.SIZE;
  protected static String sysLine = System.getProperty("line.separator");
  //避免生成该类实例
  protected Solver(){
  }
  public static boolean solve(SuPuz p)
  {
    int[][] num =new int[SIZE][SIZE];
    boolean[][] rFlags =new boolean[SIZE][SIZE+1],
    cFlags =new boolean[SIZE][SIZE+1],
    zFlags =new boolean[SIZE][SIZE+1];
    for(int r=0;r<SIZE;r++)
    for(int c=0;c<SIZE;c++)
    if(p.isFixed(r,c)){
      int t =p.getNumber(r,c);
      num[r][c] =t;
      rFlags[r][t] =true;
      cFlags[c][t] =true;
      zFlags[r/3*3+c/3][t] =true;
    }
    int r =0,c =0;
    outLoop:
    for(;;){//&#
      if(p.isFixed(r,c)){
        c ++;
        if(c>=SIZE){
          c =0;
          r ++;
          if(r>=SIZE) break outLoop;
        }
        continue outLoop;
      } //&#if(p.isFixed())
      int t =SIZE;
      for(c++;;){//&#
        if(t>=SIZE){
        c --;
        if(c<0){
        c =SIZE -1;
        r --;
        if(r<0) break outLoop;
        }
        if(p.isFixed(r,c)) continue;
        t =num[r][c];
        if(t!=0){
        rFlags[r][t] =false;
        cFlags[c][t] =false;
        zFlags[r/3*3+c/3][t] =false;
        num[r][c] =0;
        }
        } else {
      t ++;
      if(!(rFlags[r][t]||
      cFlags[c][t]||
      zFlags[r/3*3+c/3][t])
      ) break;
        }
      }//&#for(c++;;);
      num[r][c] =t;
      rFlags[r][t] =true;
      cFlags[c][t] =true;
      zFlags[r/3*3+c/3][t] =true;
      c ++;
      if(c>=SIZE){
      c =0;
      r ++;
      if(r>=SIZE) break outLoop;
      }
    }
    if(r<0) return false;
    for(r=0;r<SIZE;r++)
      for(c=0;c<SIZE;c++)
        if(!p.isFixed(r,c)) p.setNumber(r,c,num[r][c]);
    return true;
  }
    //test
  public static void main(String[] args){
    SuPuz p =new SuPuz();
    int[][] data ={
      {0,0,0, 0,0,4, 0,7,6},
      {8,0,1, 0,0,0, 0,3,0},
      {0,4,6, 0,0,3, 0,0,0},
      
      {0,0,0, 0,2,0, 7,0,1},
      {1,0,0, 7,0,6, 0,0,5},
      {5,0,7, 0,3,0, 0,0,0},
      
      {0,0,0, 9,0,0, 8,1,0},      
      {0,5,0, 0,0,0, 2,0,4},      
      {9,1,0, 8,0,0, 0,0,0}       
    };
    p.setSuPuz(data);
    System.out.println(solve(p));
    for(int r=0;r<SIZE;r++){
      for(int c=0;c<SIZE;c++) System.out.print(p.getNumber(r,c)+" ");
        System.out.println();
    }
  }
  
  public static String getTab(int[][] data) {
    String s = "";
    SuPuz p =new SuPuz();
    p.setSuPuz(data);
    boolean f = solve(p);
    for(int r=0;r<SIZE;r++) {
      if((r==0)||(r==3)||(r==6)) s += sysLine;
      for(int c=0;c<SIZE;c++) { 
      	if((c==0)||(c==3)||(c==6)) s += " ";
      	s += p.getNumber(r,c)+" ";
      }
      s += sysLine;
    }
    return f+":"+sysLine+s;
  }
  
  public static int[][] getData(String s) {
      int[][] d = new int[9][9];
      int a=-1,b=-1;
      String ch = "0";
	  	//String s = sysEditor.getText();
		  s = s.replace("\r\n","\r");
		  s = s.replace("\n","\r");
		  s = s.replace("\r\r","\r");
      String[] arr = s.split("\r"); 
      try {
        for(int i=0;i<arr.length;i++) {
          arr[i] = arr[i].replace(" ","").replace(",","");
          arr[i] = arr[i].replace("-","0").replace("x","0").replace("X","0");
          if(arr[i].length()>=9) {
            a++; b=-1; //System.out.print(sysLine+"r"+a+":");
            for(int j=0;j<arr[i].length();j++) {
            	ch = arr[i].substring(j,j+1); 
            	try {
            	  b++;
            	  d[a][b] = Integer.valueOf(ch);
            	  //System.out.print("v"+b+":"+ch+" "); 
            	} catch (Exception ex) {
            		//System.out.print("x"+b+":"+ch+" ");
            	}
            }
            //System.out.println(); 
          }
        }
      } catch (Exception ex) {
      	//sysEditor.setText("输入错误！");
      }
      return d;
  }
  
}

