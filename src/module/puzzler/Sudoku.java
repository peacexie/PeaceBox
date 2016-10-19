package module.puzzler;
import begin.*;

public class Sudoku {
 
 private static String sysLine = System.getProperty("line.separator");
 
 // A,B,C,D,E,F,G,H,I;分别代表不同的九个区域
 // A B C
 // D E F
 // G H I
 
 // 生成的顺序为 A B D C G F H E I
 static int A[][], B[][], C[][], D[][], E[][], F[][], G[][], H[][], I[][];

 // 临时的变量的数组 用于存储生成的临时的区域的值 最后利用方法 getint1() 或 getint2()
 // 将一维数组转换为二维数组 最终将生成的二维的数组赋值个相应的区域
 static int temp[];

 // tt1 3行3列
 // tt2 6行3列
 // tt3 6行6列
 // 用于存储用于生成条件的特定数组
 static int tt1[][], tt2[][], tt3[][];

 // intT 临时变量 用于存储生成的随机数字和1-9的数字
 // sum 临时变量 用于存储循环的次数 大于特定值(intSum)时 重新执行
 // FHsum临时变量 用于(getFH()方法)存储循环的次数 大于特定值(intSum)时 重新执行
 static int intT, sum, FHsum = 0;

 // intT1 intT2 均为3行3列的数组 用于getFH()方法 记录每次生成的纪录的数字
 // 用于判断生成的F H 数组是否符合要求(值均为4时才符合要求)
 static int intI1[][], intI2[][];

 // 特定值 用于控制执行的次数
 static final int intSum = 40;

 //构造方法
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
  // 使用循环来得到数组 F H
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
 
 //控制程序的主方法
 public void mainTest() {
   //fillTab();
   //printAll();
 }
 */

 // 用于生成的数组不符合要求的时候 重新生成数组B D C G F H
 public void mainTest1() {
  getB();
  getD();

  getC();
  getG();

  getF();
  getH();
 }


 // 生成数组 A
 public void getA() {
  sum = 0;
  intT = 0;
  
  //生成的不重复的数组的值  将其赋值给temp临时数组
  for (int i = 0; i < 9;) {
   intT = (int) (Math.random() * 9 + 1);

   //sum大于特定值时 重新生成自己本身 并将值全部清空为0
   if (sum++ > intSum) {
    intT = 0;
    i = 0;
    sum = 0;
   }
   for (int j = 0; j < i; j++) {
    //判断生成的值是否已经存在 存在就赋值为0 退出本次循环
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   //判断生成的值是否出现过 不是0就是没有出现过 记录并循环生成下一个值
   if (intT != 0) {
    temp[i] = intT;
    i++;
   }
  }
  // System.out.println(sum + " A");
  A = getint1(temp);//调用方法将一维数组转换为二维数组
  // printInt(A);
 }

 // 生成数组 B
 public void getB() {
  sum = 0;
  intT = 0;

  //生成用于判读条件时的数组 并存储与临时变量tt1中
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
    //首先判断是否在本身的数组中出现过
    if (intT == temp[j] && i != 0) {
     intT = 0;
     break;
    }
   }
   
   //循环判断条件
   if (intT != 0) {

    for (int k = 0; k < 3; k++) {
     // 小于3 表示第一行 和数组第一行比较判断 出现过就重新生成数字 并退出本次循环
     if (i < 3) {
      if (intT == tt1[0][k]) {
       intT = 0;
       break;
      }
      //小于6 表示第一行 和数组第二行比较判断 出现过就重新生成数字 并退出本次循环
     } else if (i < 6) {
      if (intT == tt1[1][k]) {
       intT = 0;
       break;
      }
      //小于9 表示第一行 和数组第三行比较判断 出现过就重新生成数字 并退出本次循环
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

 
 // 生成数组 D
 public void getD() {
  sum = 0;
  intT = 0;

  //同B
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

 
 // 生成数组 C
 public void getC() {
  sum = 0;
  intT = 0;

  //同B
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

 
 // 生成数组 G
 public void getG() {
  sum = 0;
  intT = 0;

  //同B
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

 
 // 生成数组 F
 public void getF() {
  sum = 0;
  intT = 0;

  //同B
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

 
 // 生成数组 H
 public void getH() {
  sum = 0;
  intT = 0;

  //同B
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

 
 // 生成数组 E
 public void getE() {
  intT = 0;
  
  //临时数组intTemp用于生成存储1-9个数字
  int intTemp[] = new int[9];
  for (int i = 0; i < 9; i++) {
   intTemp[i] = i + 1;
  }
  
  //生成判断的条件
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
    //循环判断条件数组
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
    //判断是否符合条件 
    if (intT != 0) {
     //符合条件就赋值到数组 并将其标示为 0
     temp[i] = intT;
     intTemp[j] = 0;
     //做下一次循环 
     i++;
     //退出循环
     break;
    }

   }

  }
  // System.out.println(sum + " E");
  E = getint1(temp);
  // printInt(E);

 }

  
 // 生成数组 I
 public void getI() {
  intT = 0;
  //同E
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

 // 判断生成的数组 F H 是否符合要求 直到得到数组 F H
 public boolean getFH() {

  //判断标示值的数值是否大于特定值
  if (FHsum > intSum) {
   FHsum = 0;
   //如果成立就重新生成程序
   mainTest1();
  }

  //生成的数组用于存储标示值
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
   //判断标示值 均为4时 执行下次循环  
   //判断值为4 是特定的条件
   if (intI1[k][0] == 4 && intI1[k][1] == 4 && intI1[k][2] == 4) {
    k++;
   } else {
    FHsum++;//标示值自加
    return false;

   }
  }

  
  //同上
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

 //程序的入口
 public static void main(String[] args) {
  // TODO 自动生成方法存根
  new Sudoku();
  printAll();
 }
 
 public static String getTab() {
  new Sudoku();
  return returnStr();
 }

 // 将一维数组转换为二维数组 横行
 public int[][] getint1(int temp[]) { // 横行
  int t[][] = new int[3][3];
  int tnum = 0;

  //依次将一维数组的值赋值给二维数组
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    t[i][j] = temp[tnum++];
   }
  }

  return t;
 }

 // 将一维数组转换为二维数组 竖行
 public int[][] getint2(int temp[]) { // 纵行
  int t[][] = new int[3][3];
  int tnum = 0;

  //同getint1();
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    t[j][i] = temp[tnum++];
   }
  }

  return t;
 }

  
 // 打印特定的数组 用于测试时使用 或单独打印特定的区域的数组时 使用
 public void printInt(int tt[][]) {
  //循环打印
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

 // 打印全部的数组的值 按照一定的格式
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

生成条件的数组的思路:


一 纵向生成的数组: 使用方法getint1();
  1>D 判断A的纵向的行的所有数字 
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[j][i];
   }
  }
  即 tt1 数组中记录的数值  3行3列
  2>G 判断A D 的纵向的行的所有数字
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[j][i];
    tt2[i + 3][j] = D[j][i];
   }
  }
  即 tt2 数组中记录的数值  6行3列
  
  
二 横向生成的数组: 使用方法getint2();
  1>B 判断A的横向的行的所有数字 
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt1[i][j] = A[i][j];
   }
  }
  即 tt1 数组中记录的数值  3行3列
  2>C 判断A B 的横向的行的所有数字   F H 均和C相同
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt2[i][j] = A[i][j];
    tt2[i + 3][j] = B[i][j];
   }
  }
  即 tt2 数组中记录的数值  6行3列
  3>E 判断D F 的横向的行的所有数字 并判断B H 的纵向的行的所有数字   I与其相同
  for (int i = 0; i < 3; i++) {
   for (int j = 0; j < 3; j++) {
    tt3[i][j] = D[i][j];
    tt3[i][j + 3] = F[i][j];
    tt3[i + 3][j] = B[j][i];
    tt3[i + 3][j + 3] = H[j][i];
   }
  }
  即 tt3 数组中记录的数值  6行6列
  

程序中的关键点:
 1>生成的区域的顺序 
 2>生成不同的区域时的方法不同 注意区分 
  1.A
  2.B D
  3.C G
  4.F H
  5.E I
 3>F H 数组的生成  即方法getFH()
 4>条件均为4时 保证E I区域中的每个位置都只有唯一的值出现
  每组6个数字 共12个 相同的值为4个 不同的值为4个 即8个
  每个区域共9个数字 已经确定的数字8个 那么唯一的数字的位置就确定了


//本文来自CSDN博客，转载请标明出处：
//http://blog.csdn.net/xiaoyu13620781041/archive/2007/11/30/1910066.aspx


*/



