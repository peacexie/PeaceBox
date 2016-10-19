package module.puzzler;
import begin.*;

public class Sudoku {
 
 private static String sysLine = System.getProperty("line.separator");
 
 // A,B,C,D,E,F,G,H,I;�ֱ����ͬ�ľŸ�����
 // A B C
 // D E F
 // G H I
 
 // ���ɵ�˳��Ϊ A B D C G F H E I
 static int A[][], B[][], C[][], D[][], E[][], F[][], G[][], H[][], I[][];

 // ��ʱ�ı��������� ���ڴ洢���ɵ���ʱ�������ֵ ������÷��� getint1() �� getint2()
 // ��һά����ת��Ϊ��ά���� ���ս����ɵĶ�ά�����鸳ֵ����Ӧ������
 static int temp[];

 // tt1 3��3��
 // tt2 6��3��
 // tt3 6��6��
 // ���ڴ洢���������������ض�����
 static int tt1[][], tt2[][], tt3[][];

 // intT ��ʱ���� ���ڴ洢���ɵ�������ֺ�1-9������
 // sum ��ʱ���� ���ڴ洢ѭ���Ĵ��� �����ض�ֵ(intSum)ʱ ����ִ��
 // FHsum��ʱ���� ����(getFH()����)�洢ѭ���Ĵ��� �����ض�ֵ(intSum)ʱ ����ִ��
 static int intT, sum, FHsum = 0;

 // intT1 intT2 ��Ϊ3��3�е����� ����getFH()���� ��¼ÿ�����ɵļ�¼������
 // �����ж����ɵ�F H �����Ƿ����Ҫ��(ֵ��Ϊ4ʱ�ŷ���Ҫ��)
 static int intI1[][], intI2[][];

 // �ض�ֵ ���ڿ���ִ�еĴ���
 static final int intSum = 40;

 //���췽��
 public Sudoku() {
  temp = new int[9];
  tt1 = new int[3][3];
  tt2 = new int[6][3];
  tt3 = new int[6][6];
  I = new int[3][3];
  E = new int[3][3];

  getA();
  getB();
  getD();
  getC();
  getG();
  getF();
  getH();
  
  boolean b = false;
  // ʹ��ѭ�����õ����� F H
  while (getFH() == false) {
   if (b) {
    b = false;
    getF();
   } else {
    b = true;
    getH();
   }
  }
  getE();
  getI(); 	
 }

/*
 public void fillTab() {

 }
 
 //���Ƴ����������
 public void mainTest() {
   //fillTab();
   //printAll();
 }
 */

 // �������ɵ����鲻����Ҫ���ʱ�� ������������B D C G F H
 public void mainTest1() {
  getB();
  getD();

  getC();
  getG();

  getF();
  getH();
 }


 // �������� A
 public void getA() {
  sum = 0;
  intT = 0;
  
  //���ɵĲ��ظ��������ֵ  ���丳ֵ��temp��ʱ����
  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);

   //sum�����ض�ֵʱ ���������Լ����� ����ֵȫ�����Ϊ0
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    //�ж����ɵ�ֵ�Ƿ��Ѿ����� ���ھ͸�ֵΪ0 �˳�����ѭ��
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   //�ж����ɵ�ֵ�Ƿ���ֹ� ����0����û�г��ֹ� ��¼��ѭ��������һ��ֵ
   if (intT != 0) {
    temp[i] = intT;
    i++;
   }
  }
  // System.out.println(sum + " A");
  A = getint1(temp);//���÷�����һά����ת��Ϊ��ά����
  // printInt(A);
 }

 // �������� B
 public void getB() {
  sum = 0;
  intT = 0;

  //���������ж�����ʱ������ ���洢����ʱ����tt1��
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[i][j];
   }
  }

  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    //�����ж��Ƿ��ڱ���������г��ֹ�
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   
   //ѭ���ж�����
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     // С��3 ��ʾ��һ�� �������һ�бȽ��ж� ���ֹ��������������� ���˳�����ѭ��
     if (i < 3) {
      if (intT == tt1[0][k]) {
       intT = 0;
       break;
      }
      //С��6 ��ʾ��һ�� ������ڶ��бȽ��ж� ���ֹ��������������� ���˳�����ѭ��
     } else if (i < 6) {
      if (intT == tt1[1][k]) {
       intT = 0;
       break;
      }
      //С��9 ��ʾ��һ�� ����������бȽ��ж� ���ֹ��������������� ���˳�����ѭ��
     } else {
      if (intT == tt1[2][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " B");
  B = getint1(temp);
  // printInt(B);

 }

 
 // �������� D
 public void getD() {
  sum = 0;
  intT = 0;

  //ͬB
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[j][i];
   }
  }

  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     if (i < 3) {
      if (intT == tt1[0][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt1[1][k]) {
       intT = 0;
       break;
      }
     } else {
      if (intT == tt1[2][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " D");
  D = getint2(temp);
  // printInt(D);
 }

 
 // �������� C
 public void getC() {
  sum = 0;
  intT = 0;

  //ͬB
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[i][j];
    tt2[i + 3][j] = B[i][j];
   }
  }

  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     if (i < 3) {
      if (intT == tt2[0][k] || intT == tt2[3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt2[1][k] || intT == tt2[4][k]) {
       intT = 0;
       break;
      }
     } else {
      if (intT == tt2[2][k] || intT == tt2[5][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " C");
  C = getint1(temp);
  // printInt(C);
 }

 
 // �������� G
 public void getG() {
  sum = 0;
  intT = 0;

  //ͬB
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[j][i];
    tt2[i + 3][j] = D[j][i];
   }
  }
  
  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     if (i < 3) {
      if (intT == tt2[0][k] || intT == tt2[3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt2[1][k] || intT == tt2[4][k]) {
       intT = 0;
       break;
      }
     } else {
      if (intT == tt2[2][k] || intT == tt2[5][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " G");
  G = getint2(temp);
  // printInt(G);
 }

 
 // �������� F
 public void getF() {
  sum = 0;
  intT = 0;

  //ͬB
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = D[i][j];
    tt2[i + 3][j] = C[j][i];
   }
  }
  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     if (i < 3) {
      if (intT == tt2[0][k] || intT == tt2[i + 3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt2[1][k] || intT == tt2[i][k]) {
       intT = 0;
       break;
      }
     } else {
      if (intT == tt2[2][k] || intT == tt2[i - 3][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " F");
  F = getint1(temp);
  // printInt(F);
 }

 
 // �������� H
 public void getH() {
  sum = 0;
  intT = 0;

  //ͬB
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = G[i][j];
    tt2[i + 3][j] = B[j][i];
   }
  }
  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     if (i < 3) {
      if (intT == tt2[0][k] || intT == tt2[i + 3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt2[1][k] || intT == tt2[i][k]) {
       intT = 0;
       break;
      }
     } else {
      if (intT == tt2[2][k] || intT == tt2[i - 3][k]) {
       intT = 0;
       break;
      }
     }
    }
    if (intT != 0) {
     temp[i] = intT;
     i++;
    }

   }
  }
  // System.out.println(sum + " H");
  H = getint1(temp);
  // printInt(H);
 }

 
 // �������� E
 public void getE() {
  intT = 0;
  
  //��ʱ����intTemp�������ɴ洢1-9������
  int intTemp[] = new int[9];
  for (int i = 0; i < 9; i++) {
   intTemp[i] = i + 1;
  }
  
  //�����жϵ�����
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = D[i][j];
    tt3[i][j + 3] = F[i][j];
    tt3[i + 3][j] = B[j][i];
    tt3[i + 3][j + 3] = H[j][i];
   }
  }

  for (int i = 0; i < 9;)

  {
   for (int j = 0; j < 9; j++) {
    intT = intTemp[j];
    if (intT == 0) {
     continue;
    }
    //ѭ���ж���������
    for (int k = 0; k < 6; k++) {
     if (i < 3) {
      if (intT == tt3[0][k] || intT == tt3[i + 3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt3[1][k] || intT == tt3[i][k]) {
       intT = 0;
       break;
      }
     } else if (i < 9) {
      if (intT == tt3[2][k] || intT == tt3[i - 3][k]) {
       intT = 0;
       break;
      }
     }

    }
    //�ж��Ƿ�������� 
    if (intT != 0) {
     //���������͸�ֵ������ �������ʾΪ 0
     temp[i] = intT;
     intTemp[j] = 0;
     //����һ��ѭ�� 
     i++;
     //�˳�ѭ��
     break;
    }

   }

  }
  // System.out.println(sum + " E");
  E = getint1(temp);
  // printInt(E);

 }

  
 // �������� I
 public void getI() {
  intT = 0;
  //ͬE
  int intTemp[] = new int[9];
  for (int i = 0; i < 9; i++) {
   intTemp[i] = i + 1;
  }

  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = G[i][j];
    tt3[i][j + 3] = H[i][j];
    tt3[i + 3][j] = C[j][i];
    tt3[i + 3][j + 3] = F[j][i];
   }
  }

  for (int i = 0; i < 9;)

  {
   for (int j = 0; j < 9; j++) {
    intT = intTemp[j];
    if (intT == 0) {
     continue;
    }
    for (int k = 0; k < 6; k++) {
     if (i < 3) {
      if (intT == tt3[0][k] || intT == tt3[i + 3][k]) {
       intT = 0;
       break;
      }
     } else if (i < 6) {
      if (intT == tt3[1][k] || intT == tt3[i][k]) {
       intT = 0;
       break;
      }
     } else if (i < 9) {
      if (intT == tt3[2][k] || intT == tt3[i - 3][k]) {
       intT = 0;
       break;
      }
     }

    }
    if (intT != 0) {
     temp[i] = intT;
     intTemp[j] = 0;
     i++;
     break;
    }

   }

  }
  // System.out.println(sum + " I");
  I = getint1(temp);
  // printInt(I);
 }

 // �ж����ɵ����� F H �Ƿ����Ҫ�� ֱ���õ����� F H
 public boolean getFH() {

  //�жϱ�ʾֵ����ֵ�Ƿ�����ض�ֵ
  if (FHsum > intSum) {
   FHsum = 0;
   //����������������ɳ���
   mainTest1();
  }

  //���ɵ��������ڴ洢��ʾֵ
  intI1 = new int[3][3];
  intI2 = new int[3][3];

  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = G[i][j];
    tt3[i][j + 3] = H[i][j];
    tt3[i + 3][j] = C[j][i];
    tt3[i + 3][j + 3] = F[j][i];
   }
  }
  for (int k = 0; k < 3;) {
   for (int i = 0; i < 6; i++) {

    for (int j = 0; j < 6; j++) {
     if (tt3[k][i] == tt3[3][j]) {
      intI1[k][0]++;
     } else if (tt3[k][i] == tt3[4][j]) {
      intI1[k][1]++;
     } else if (tt3[k][i] == tt3[5][j]) {
      intI1[k][2]++;
     }
    }

   }
   //�жϱ�ʾֵ ��Ϊ4ʱ ִ���´�ѭ��  
   //�ж�ֵΪ4 ���ض�������
   if (intI1[k][0] == 4 && intI1[k][1] == 4 && intI1[k][2] == 4) {
    k++;
   } else {
    FHsum++;//��ʾֵ�Լ�
    return false;

   }
  }

  
  //ͬ��
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = D[i][j];
    tt3[i][j + 3] = F[i][j];
    tt3[i + 3][j] = B[j][i];
    tt3[i + 3][j + 3] = H[j][i];
   }
  }
  for (int k = 0; k < 3;) {
   for (int i = 0; i < 6; i++) {

    for (int j = 0; j < 6; j++) {
     if (tt3[k][i] == tt3[3][j]) {
      intI2[k][0]++;
     } else if (tt3[k][i] == tt3[4][j]) {
      intI2[k][1]++;
     } else if (tt3[k][i] == tt3[5][j]) {
      intI2[k][2]++;
     }
    }

   }

   if (intI2[k][0] == 4 && intI2[k][1] == 4 && intI2[k][2] == 4) {
    k++;
   } else {
    FHsum++;
    return false;

   }
  }

  return true;
 }

 //��������
 public static void main(String[] args) {
  // TODO �Զ����ɷ������
  new Sudoku();
  printAll();
 }
 
 public static String getTab() {
  new Sudoku();
  return returnStr();
 }

 // ��һά����ת��Ϊ��ά���� ����
 public int[][] getint1(int temp[]) { // ����
  int t[][] = new int[3][3];
  int tnum = 0;

  //���ν�һά�����ֵ��ֵ����ά����
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    t[i][j] = temp[tnum++];
   }
  }

  return t;
 }

 // ��һά����ת��Ϊ��ά���� ����
 public int[][] getint2(int temp[]) { // ����
  int t[][] = new int[3][3];
  int tnum = 0;

  //ͬgetint1();
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    t[j][i] = temp[tnum++];
   }
  }

  return t;
 }

  
 // ��ӡ�ض������� ���ڲ���ʱʹ�� �򵥶���ӡ�ض������������ʱ ʹ��
 public void printInt(int tt[][]) {
  //ѭ����ӡ
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    System.out.print(tt[i][j]);
   }
   System.out.println();
  }
 }

 public static void printAll() { 
 	 System.out.print(returnStr());
 }

 // ��ӡȫ���������ֵ ����һ���ĸ�ʽ
 public static String returnStr() {
  String res = "";
  
  res += sysLine;
  for (int i = 0; i < A.length; i++) {
   for (int j = 0; j < A[i].length; j++) {
    if(j==0) res += " ";
    res += A[i][j] + " "; 
   }
   for (int j = 0; j < B[i].length; j++) {
    if(j==0) res += " ";
    res += B[i][j] + " "; 
   }
   for (int j = 0; j < C[i].length; j++) {
    if(j==0) res += " ";
    res += C[i][j] + " "; 
   }
   res += sysLine;
  }
  
  res += sysLine;
  for (int i = 0; i < D.length; i++) {
   for (int j = 0; j < D[i].length; j++) {
    if(j==0) res += " ";
    res += D[i][j] + " "; 
   }
   for (int j = 0; j < E[i].length; j++) {
    if(j==0) res += " ";
    res += E[i][j] + " "; 
   }
   for (int j = 0; j < F[i].length; j++) {
    if(j==0) res += " ";
    res += F[i][j] + " "; 
   }
   res += sysLine;
  }
  
  res += sysLine;
  for (int i = 0; i < G.length; i++) {
   for (int j = 0; j < G[i].length; j++) {
    if(j==0) res += " ";
    res += G[i][j] + " "; 
   }
   for (int j = 0; j < H[i].length; j++) {
    if(j==0) res += " ";
    res += H[i][j] + " "; 
   }
   for (int j = 0; j < I[i].length; j++) {
    if(j==0) res += " ";
    res += I[i][j] + " "; 
   }
   res += sysLine;
  }
  
  return res;
 }

}

/*

���������������˼·:


һ �������ɵ�����: ʹ�÷���getint1();
  1>D �ж�A��������е��������� 
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[j][i];
   }
  }
  �� tt1 �����м�¼����ֵ  3��3��
  2>G �ж�A D ��������е���������
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[j][i];
    tt2[i + 3][j] = D[j][i];
   }
  }
  �� tt2 �����м�¼����ֵ  6��3��
  
  
�� �������ɵ�����: ʹ�÷���getint2();
  1>B �ж�A�ĺ�����е��������� 
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[i][j];
   }
  }
  �� tt1 �����м�¼����ֵ  3��3��
  2>C �ж�A B �ĺ�����е���������   F H ����C��ͬ
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[i][j];
    tt2[i + 3][j] = B[i][j];
   }
  }
  �� tt2 �����м�¼����ֵ  6��3��
  3>E �ж�D F �ĺ�����е��������� ���ж�B H ��������е���������   I������ͬ
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = D[i][j];
    tt3[i][j + 3] = F[i][j];
    tt3[i + 3][j] = B[j][i];
    tt3[i + 3][j + 3] = H[j][i];
   }
  }
  �� tt3 �����м�¼����ֵ  6��6��
  

�����еĹؼ���:
 1>���ɵ������˳�� 
 2>���ɲ�ͬ������ʱ�ķ�����ͬ ע������ 
  1.A
  2.B D
  3.C G
  4.F H
  5.E I
 3>F H ���������  ������getFH()
 4>������Ϊ4ʱ ��֤E I�����е�ÿ��λ�ö�ֻ��Ψһ��ֵ����
  ÿ��6������ ��12�� ��ͬ��ֵΪ4�� ��ͬ��ֵΪ4�� ��8��
  ÿ������9������ �Ѿ�ȷ��������8�� ��ôΨһ�����ֵ�λ�þ�ȷ����


//��������CSDN���ͣ�ת�������������
//http://blog.csdn.net/xiaoyu13620781041/archive/2007/11/30/1910066.aspx


*/



