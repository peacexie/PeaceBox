package module.puzzler;
import begin.*;

import static java.util.Arrays.*;

public class SuPuz{
  
  public static final int SIZE =9;
  private boolean[][] fixed =new boolean[SIZE][SIZE];
  private int[][] number =new int[SIZE][SIZE];
  
  public SuPuz(){
  }
  
  public SuPuz(int[][] p){
    setSuPuz(p);
  }
  
  /**
  * ��һ����ά����ȥ���ø�����
  * ע��:�����ά����Ӧ��ֻ����0~9�е�����,Ϊ0ʱ��ʾ�ô�����
  * �÷�������p�������ǺϷ���,����������κμ��
  */
  public void setSuPuz(int[][] p){
    for(int i=0;i<SIZE;i++) {
      for(int j=0;j<SIZE;j++){
        if(p[i][j] ==0){
        fixed[i][j] =false;
        number[i][j] =0;
        } else{
        number[i][j] =p[i][j];
        fixed[i][j] =true;
        }
      } // for 
    } // for  
  }
  
  /**
  * ���
  */
  public void clear(){
    for(int n=0;n<SIZE;n++){
      fill(fixed[n],false);
      fill(number[n],0);
    }
    return;
  }
  
  /**
  * λ��i,j�Ƿ�̶�,����̶���ʾ�ô������ֲ��ܸ���
  */
  public boolean isFixed(int i,int j){
    return fixed[i][j];
  }
  /**
  * �õ�λ��i,j��������
  */
  public int getNumber(int i,int j){
    return number[i][j];
  }
  /**
  * ����i,j��������.����ô������ǹ̶���,���׳��쳣
  */
  public void setNumber(int i,int j,int num){
    if(num<0||num>9) throw new IllegalArgumentException("number is out of 0~9 :"+num);
    if(isFixed(i,j)) throw new IllegalStateException("puzzler("+i+","+j+") is fixed");
    number[i][j] =num;
  }
}

