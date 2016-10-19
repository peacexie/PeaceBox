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
    final Label l = new Label("������(��:3,3,8,8��3388)"); f.add(l,"North"); 
    final TextField t = new TextField();                   f.add(t,"Center"); 
	  final Button b = new Button("����24...");              f.add(b,"South"); 
	  
    b.addActionListener(new ActionListener() { 
      public void actionPerformed(ActionEvent e) { 
        
        Button b = (Button)e.getSource();
        if(b.getLabel().equals("Again")) {
        	
        	l.setText("������(��:3,3,8,8��3388)");
        	((Button)e.getSource()).setLabel("����24...");
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
          
          l.setText("����:"+s);
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
  	calcu("����24");
	  
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
  ͬ�����������ԣ����۵���΢����Ǹ��������ԣ�3 3 8 8����24��Ȼ����ͻ�����룬д�������õ���������24�����˼˵��������Լ����У���Ѱ���Ͷ����ߡ��Ӵ���Ű�Ĺ��̿�ʼ������Ժ���ǧ��Ҫ��ͻ�������ˡ�
    �ѵ�1����α���4�����ֵ������������
    �ѵ�2����α������� + - * /
    �ѵ�2�������⣬����ѭ�����ɸ㶨���ؼ�����������г����е�����������ˡ���Ȼ4��ѭ��Ҳ���Ը㶨�����Ǿ���̫�򵥣�����Ҫ��ԭ���ǲ����ڳ�����չ������Ժ����Ҫ�ĳ�7�����ּ���100���ǳ���Ķ����ܴ��뵽�˵ݹ飬���Ǹ���ʵ���ǣ���ϸ��������Ҳ��֪�����һ��ݹ���ôд��������������ѭ��+��־λ��ʵ�֡���ȻҲ���Դ�������n!���޶�ѭ���Ĵ������������б�־λ���Ͳ����ˣ�������Լ���������
    ����㷨���治��д�������ȣ�Ҫ��֤���Լ���4������������ϵ�ʱ���ܳ���-_-||
    �����㷨�� 1 flag��¼��ǰ�̶���λ�ã�Ĭ��ָ��ĩβ������2 flag��ǰ�ƶ��� 3 ��ǰ��ϱ����ArrayList�� 4 flag�Ժ�������������� 5 flagָ�����������һ����Ҫ���������ֽ�����6 flag�Ժ�����������������У�7 flag ���ص���ĩβ������ѭ����ֱ��flag=-1�����ѵĵط�����flag�����ȷǰ�ƣ�����ȷ�õ�Ҫ���������֡�������̾�һ����ĩѪ��ʷ��һֱ�㵽����2�㡣�����ǵ��˼����ŵ��������Ȼ���������������Ҫ��չ��Ҫ�Լ�������ˡ��һ�û����ã���α������еĿ��ܵ����ŵ������Ҳǧ��Ҫ��Ҫ���Ҹ����ȳ˳���Ӽ��ķ�ʽ��ȷ�����ţ�ʵ���۵Ĳ����ˣ��ҵ�һ����ĩ���㵽����������ˡ�
    ������д���˵�ȻҪˬһˬ�����㼸����ô�����⣡�������ѵģ� 7733 7744 Ҳû������ ��ʼ3388 nnd����˵�޽⡣���˰��죬3388��������һ����24.000000...001��ʱ���жϳ�����24.΢�����΢��3388�����㿼�ǵ����ⶼ�Ƚ϶ࡣ
    ��Ҫ�Ľ���1 ��Ȼ���г�����������ϵ�����������Լ�д�Ĳ���ݹ���Ч�ʣ�����ǳ��������ĵط���2 ����24 ��ʱ�����Եò���24�������ֱ������������Ͳ������ˡ�3 ��α����������Ż�û����ã�������д���ڳ�����ġ�
    ��ҿ���ȥhttp://pickup.mofile.com/5747012680889965��ȡ�ļ����Լ�Ҳ�͵��Ա�һ��˭�졣˳�㿴����û�������������˵���˵�޽⡣����jre1.6.0_03������Ҫ��java�����������С�������java -jar Cal24.jar�����С��Ǹ�bat���ã������е����⣬��������ܲ��ܷ��֡�
    �϶�����Ҫ�������ҵĴ��룬�������ɣ�ע�ͺ��١�����и��õ��㷨�������ҵ��㷨��ʲô���⣬������ʲôbug��������д������ô��ʲô��ϰ�ߣ��鷳�����ң���ͬ�����
http://hi.baidu.com/wolf_m/blog/item/b5ff9913251b00846438db3c.html

*/
