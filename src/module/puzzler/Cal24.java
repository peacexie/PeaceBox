package module.puzzler;
import begin.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;

import java.awt.*;
import java.awt.event.*;

public class Cal24 {
	
  private double[] input;
  private int[] trunk;
  public static final double PRECISION = 0.00001;
  //private static boolean creatForm=false; //true;
  private static boolean closeForm=false;

  Cal24(double[] input) {
    this.input = input;
    this.trunk = new int[input.length];
  }

  private ArrayList<double[]> getAllSort() {
    ArrayList<double[]> allSort = new ArrayList<double[]>();
    int length = input.length;
    int flag = 0;
    for (int i = 0; i < length; i++) {
        trunk[i] = i;
    }
    flag = length - 1;
    while (true) {
        flag = forward();
        if (flag < 0) {
            break;
        }
        allSort.add(getOneSort(trunk));
        Arrays.sort(trunk, flag + 1, length);
        updateTrunk(flag);
    }
    return allSort;
  }

  private void updateTrunk(int flag) {
    int[] after = Arrays.copyOfRange(trunk, flag, input.length);
    for (int i = 0; i < after.length; i++) {
        if (trunk[flag] < after[i]) {
            int temp = trunk[flag];
            int nextIndex = getIndexFromTrunk(after[i]);
            trunk[flag] = after[i];
            trunk[nextIndex] = temp;
            Arrays.sort(trunk, flag + 1, trunk.length);
            break;
        }
    }
  }

  private int getIndexFromTrunk(int data) {
    for (int i = 0; i < input.length; i++) {
        if (trunk[i] == data) {
            return i;
        }
    }
    return -1;
  }

  private double[] getOneSort(int[] indexTrunk) {
    int length = input.length;
    double[] oneSort = new double[length];
    for (int i = 0; i < length; i++) {
        oneSort[i] = input[indexTrunk[i]];
    }
    return oneSort;
  }

  private int forward() {
    int[] temp = trunk.clone();
    int flag = input.length - 2;
    while (true) {
        int[] before = Arrays.copyOfRange(temp, flag, input.length);
        if (trunk[flag] == getMaxValue(before)) {
            flag--;
            if (flag < 0) {
                break;
            }
        }
        else {
            break;
        }
    }
    return flag;
  }

  private int getMaxValue(int[] array) {
    Arrays.sort(array);
    return array[array.length - 1];
  }

  private String find24Result(ArrayList<double[]> allSort) {
    int[] symbol = new int[input.length - 1];
    for (int i = 0; i < allSort.size(); i++) {
        for (int pos2 = 0; pos2 < 4; pos2++) {
            for (int pos1 = 0; pos1 < 4; pos1++) {
                for (int pos0 = 0; pos0 < 4; pos0++) {
                    symbol[0] = pos0;
                    symbol[1] = pos1;
                    symbol[2] = pos2;
                    double[] data = allSort.get(i);
                    double result = 0;
                    for (int braceType = 0; braceType < 4; braceType++) {
                        result = calculate(data, symbol, braceType);
                        if (result == 24) {
                            return getEquationStr(data, symbol, braceType);
                        }
                    }
                }
            }
        }
    }
    return "No answer!";
  }

  private String getEquationStr(double[] data, int[] symbol, int braceType) {
    String result = "";
    for (int i = 0; i < data.length - 1; i++) {
        if (i == 0) {
            result = "," + data[i] + ",";
        }
        switch (symbol[i]) {
        case 0:
            result += " + ," + data[i + 1] + ",";
            break;
        case 1:
            result += " - ," + data[i + 1] + ",";
            break;
        case 2:
            result += " * ," + data[i + 1] + ",";
            break;
        case 3:
            result += " / ," + data[i + 1] + ",";
            break;
        }
    }
    String[] a = result.trim().split(",");
    switch (braceType) {
    case 0:
        result = "((("+a[1]+a[2]+a[3]+")"+a[4]+a[5]+")"+a[6]+a[7]+")";
        break;
    case 1:
        result = "(("+a[1]+a[2]+a[3]+")"+a[4]+"("+a[5]+a[6]+a[7]+"))";
        break;
    case 2:
        result = "("+a[1]+a[2]+"("+a[3]+a[4]+"("+a[5]+a[6]+a[7]+")))";
        break;
    case 3:
        result = "("+a[1]+a[2]+"(("+a[3]+a[4]+a[5]+")"+a[6]+a[7]+"))";
        break;
    }
    return result;
  }

  private double calculate(double[] data, int[] symbol, int braceType) {
    double result = 0f;
    if (braceType == 0) {
        for (int i = 0; i < data.length - 1; i++) {
            if (i == 0) {
                result = data[i];
            }
            switch (symbol[i]) {
            case 0:
                result = result + data[i + 1];
                break;
            case 1:
                result = result - data[i + 1];
                break;
            case 2:
                result = result * data[i + 1];
                break;
            case 3:
                result = result / data[i + 1];
                break;
            }
        }
    }
    else if (braceType == 1) {
        double result1 = 0, result2 = 0;
        // step1
        result1 = data[0];
        switch (symbol[0]) {
        case 0:
            result1 = result1 + data[1];
            break;
        case 1:
            result1 = result1 - data[1];
            break;
        case 2:
            result1 = result1 * data[1];
            break;
        case 3:
            result1 = result1 / data[1];
            break;
        }
        // step2
        result2 = data[2];
        switch (symbol[2]) {
        case 0:
            result2 = result2 + data[3];
            break;
        case 1:
            result2 = result2 - data[3];
            break;
        case 2:
            result2 = result2 * data[3];
            break;
        case 3:
            result2 = result2 / data[3];
            break;
        }
        // step3
        switch (symbol[1]) {
        case 0:
            result = result1 + result2;
            break;
        case 1:
            result = result1 - result2;
            break;
        case 2:
            result = result1 * result2;
            break;
        case 3:
            result = result1 / result2;
            break;
        }
    }
    else if (braceType == 2) {
        double result1 = 0, result2 = 0;
        // step1
        result1 = data[2];
        switch (symbol[2]) {
        case 0:
            result1 = result1 + data[3];
            break;
        case 1:
            result1 = result1 - data[3];
            break;
        case 2:
            result1 = result1 * data[3];
            break;
        case 3:
            result1 = result1 / data[3];
            break;
        }
        // step2
        result2 = data[1];
        switch (symbol[1]) {
        case 0:
            result2 = data[1] + result1;
            break;
        case 1:
            result2 = data[1] - result1;
            break;
        case 2:
            result2 = data[1] * result1;
            break;
        case 3:
            result2 = data[1] / result1;
            break;
        }
        // step3
        switch (symbol[0]) {
        case 0:
            result = data[0] + result2;
            break;
        case 1:
            result = data[0] - result2;
            break;
        case 2:
            result = data[0] * result2;
            break;
        case 3:
            result = data[0] / result2;
            break;
        }
    }
    else if (braceType == 3) {
        double result1 = 0;
        // step1
        result1 = data[1];
        switch (symbol[1]) {
        case 0:
            result1 = result1 + data[2];
            break;
        case 1:
            result1 = result1 - data[2];
            break;
        case 2:
            result1 = result1 * data[2];
            break;
        case 3:
            result1 = result1 / data[2];
            break;
        }
        // step2
        switch (symbol[2]) {
        case 0:
            result1 = result1 + data[3];
            break;
        case 1:
            result1 = result1 - data[3];
            break;
        case 2:
            result1 = result1 * data[3];
            break;
        case 3:
            result1 = result1 / data[3];
            break;
        }
        // step3
        switch (symbol[0]) {
        case 0:
            result = data[0] + result1;
            break;
        case 1:
            result = data[0] - result1;
            break;
        case 2:
            result = data[0] * result1;
            break;
        case 3:
            result = data[0] / result1;
            break;
        }
    }
    if (Math.abs(result - 24) < PRECISION) {
        result = 24;
    }
    return result;
  }

  /* for Dos */
  public static void mainDemo(String[] agrs) {
    BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
    BufferedWriter stdout = new BufferedWriter(new OutputStreamWriter(System.out));
    String in = "";
    while (true) {
        try {
            stdout.write("Please input 4 number split with space: e.g. 3 3 8 8\ntype\"exit\" to quit.\n");
            stdout.flush();
            in = stdin.readLine();
            if (in.equalsIgnoreCase("exit")) {
                System.exit(1); //ip.dispose();
            }
            String[] inputArr = in.split(" ");
            stdout.flush();
            double input[] = new double[4];
            try {
                for (int i = 0; i < 4; i++) {
                    input[i] = Long.parseLong(inputArr[i]);
                }
            } catch (Exception e) {
                stdout.write("Input Error!\n");
                continue;
            }
            Cal24 cal24 = new Cal24(input);
            ArrayList<double[]> allSort = cal24.getAllSort();
            String result = cal24.find24Result(allSort);
            stdout.write("Result: " + result + "\n");
        } catch (IOException e) {
        }
    }
  }
  
  private static String FillText(String s)
  {
    String t = ""; 
    s = s.replace(" ",",");
    s = s.replace(",,",",");
    for(int i=0;i<s.length();i++)
    {
    	t += s.substring(i,i+1);
    	if((i+1)<s.length()) t+= ",";
    	// JQKA=11,12,13,1
    }
    t = t.replace(",,,",",");
    t = t.replace(",,",",");
    return t;
  }
  
  public static void calcu(String myForm) {
      
    final Frame f;  
    f = new Frame(myForm); //Dialog
    f.addWindowListener(new WindowAdapter() { 
      public void windowClosing(WindowEvent e) { 
        if(closeForm){
          System.exit(0); 
        } else {
        	f.dispose(); 
        }
      } 
    }); 
    f.setSize(new Dimension(150, 92)); 
    f.setLocation(240,240);
    final Label l = new Label("请输入(如:3,3,8,8或3388)"); f.add(l,"North"); 
    final TextField t = new TextField();                   f.add(t,"Center"); 
	  final Button b = new Button("计算24...");              f.add(b,"South"); 
	  
    b.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        
        Button b = (Button)e.getSource();
        if(b.getLabel().equals("Again")) {
        	
        	l.setText("请输入(如:3,3,8,8或3388)");
        	((Button)e.getSource()).setLabel("计算24...");
        	t.setText("");
        	t.setEnabled(true);
        	
        } else {
        	
          String s = FillText(t.getText());
          String[] a = s.split(",");
          double d[] = new double[4];
          try {
              for (int i = 0; i < 4; i++) {
                  d[i] = Long.parseLong(a[i]);
              }
          } catch (Exception ex) {
              t.setText("Input Error!"); 
          }
          
          Cal24 cal24 = new Cal24(d);
          ArrayList<double[]> as = cal24.getAllSort();
          String res = cal24.find24Result(as);
          res = res.replace(".0","");
          
          l.setText("计算:"+s);
          t.setText(res);  
          t.setEnabled(false);
          ((Button)e.getSource()).setLabel("Again"); 
          
        }        
      } 
    }); 

	  f.setVisible(true); 
  }
  
  public static void main(String[] agrs) {
  	
  	//creatForm=true;
  	closeForm=true;
  	calcu("计算24");
	  
	  /*  	
    double[] input = { 4, 5, 6, 7 }; 
    Cal24 cal24 = new Cal24(input);
    ArrayList<double[]> allSort = cal24.getAllSort(); 
    String result = cal24.find24Result(allSort); 
    System.out.println("Result: " + result + "\n");
    */
  }
    
}

/*
  同事在讨论面试，讨论到了微软的那个经典面试，3 3 8 8计算24，然后又突发奇想，写个程序，让电脑来计算24，马克思说过，人嘛，自己不行，就寻求劳动工具。从此自虐的过程开始。后悔以后我千万不要再突发奇想了。
    难点1：如何遍历4个数字的所有排列组合
    难点2：如何遍历所有 + - * /
    难点2不是问题，四重循环轻松搞定，关键就是在如何列出所有的排列组合上了。当然4重循环也可以搞定，但是觉得太简单，最重要的原因是不利于程序扩展，如果以后程序要改成7个数字计算100，那程序改动将很大。想到了递归，但是个人实在是，脑细胞死光了也不知道最后一层递归怎么写。最后决定用无限循环+标志位来实现。当然也可以传个参数n!来限定循环的次数，但是我有标志位，就不用了，程序会自己跳出来。
    这个算法还真不好写啊，首先，要保证我自己列4个数的排列组合的时候不能出错-_-||
    核心算法是 1 flag记录当前固定的位置，默认指向末尾的数；2 flag向前移动； 3 当前组合保存进ArrayList； 4 flag以后的数字升序排列 5 flag指向的数字与下一个需要交换的数字交换；6 flag以后的数字们再升序排列；7 flag 返回到最末尾。无限循环，直到flag=-1。最难的地方就是flag如何正确前移，和正确得到要交换的数字。具体过程就一部周末血泪史，一直搞到昨晚2点。还考虑到了加括号的情况。当然，加括号如果程序要扩展就要自己加情况了。我还没有想好，如何遍历所有的可能的括号的情况。也千万不要再要求我根据先乘除后加减的方式正确加括号，实在累的不行了，我的一个周末都搞到这个程序里了。
    花絮：写好了当然要爽一爽，随便搞几个，么有问题！来几个难的： 7733 7744 也没有问题 开始3388 nnd程序说无解。查了半天，3388算出结果有一个是24.000000...001的时候判断出不是24.微软就是微软，3388程序算考虑的问题都比较多。
    需要改进：1 当然是列出所有排列组合的情况，觉得自己写的不如递归有效率，这个是程序开销最大的地方。2 计算24 有时候明显得不出24的情况就直接跳掉，后面就不用算了。3 如何遍历所有括号还没有想好，现在是写死在程序里的。
    大家可以去http://pickup.mofile.com/5747012680889965提取文件，自己也和电脑比一比谁快。顺便看看有没有情况你算出来了电脑说无解。基于jre1.6.0_03开发，要有java环境才能运行。建议用java -jar Cal24.jar来运行。那个bat能用，但是有点问题，看看大家能不能发现。
    肯定有人要看看看我的代码，贴出来吧，注释很少。如果有更好的算法，或者我的算法有什么问题，或者有什么bug，或者我写程序有么有什么坏习惯，麻烦告诉我，共同进步嘛：
http://hi.baidu.com/wolf_m/blog/item/b5ff9913251b00846438db3c.html

*/
