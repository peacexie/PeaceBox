package module.puzzler;
import begin.*;

import  java.awt.*;  
import  java.awt.event.*;  
import  javax.swing.*;  
import  java.util.Vector;  
// http://diary.goodmood.cn/2008/1017/0_192746.html

public  class  CMath  
{  
   
  private static boolean closeForm=false;
  private String sysLine = System.getProperty("line.separator");
  static JTextArea sysArea = PeaceBox.sysEditor;
   
  String  str1="0";  //������1  ��ֵһ��Ϊ0  Ϊ�˳���İ�ȫ  
  String  str2="0";  //������2  
  String  fh="+";  //�����  
  String  jg="";//���  
  
  //״̬����  ��Ҫ  
  int  k1=1;//����1  ����ѡ�����뷽��  ��Ҫд��str2��  str2  
  int  k2=1;//����2  ���ż�  ����  k2>1˵�����е���2+3-9+8  �����Ķ��������  
  int  k3=1;//����3  str1  �Ƿ���Ա���0  ==1ʱ����  !=1ʱ���ܱ���0  
  int  k4=1;//����4  str2  ͬ��  
  int  k5=1;//����5  ����С����ɷ�¼��  ==1ʱ����  !=1  �����С���㱻����  
  JButton  jicunqi;  //�Ĵ���  ��¼  �Ƿ��������·��ż�  
  Vector  vt=new  Vector(20,10);  
  
  JFrame  frame=new  JFrame("sunshine---������");  
  JTextField  jg_TextField=new  JTextField(jg,20);//20��  
  JButton  clear_Button=new  JButton("���");  
  JButton  button0=new  JButton("0");  
  JButton  button1=new  JButton("1");  
  JButton  button2=new  JButton("2");  
  JButton  button3=new  JButton("3");  
  JButton  button4=new  JButton("4");  
  JButton  button5=new  JButton("5");  
  JButton  button6=new  JButton("6");  
  JButton  button7=new  JButton("7");  
  JButton  button8=new  JButton("8");  
  JButton  button9=new  JButton("9");  
  JButton  button_Dian=new  JButton(".");  
  JButton  button_jia=new  JButton("+");  
  JButton  button_jian=new  JButton("-");  
  JButton  button_cheng=new  JButton("*");  
  JButton  button_chu=new  JButton("/");  
  JButton  button_dy=new  JButton("=");  
  ////////////////////////////////////////////////////////////////////////  
  public  static  void  main(String[]  args)  
  {  
    closeForm = true;
    CMath c = new CMath();  
  }  
  
  public static void Calcu()  
  {  
    CMath c = new CMath();  
  }  
  
  
  /////////////////////////////////////////////////////////////////////////  
  CMath()  
  {  
  button0.setMnemonic(KeyEvent.VK_0);//��Ч��  
  //����  ��Ч��  ��,  
  
  jg_TextField.setHorizontalAlignment(JTextField.RIGHT  );//�ı���  �Ҷ���  
  
  JPanel  pan=new  JPanel();  
  pan.setLayout(new  GridLayout(4,4,5,5));//��������  �߾�Ϊ5����  
  pan.add(button7);  
  pan.add(button8);  
  pan.add(button9);  
  pan.add(button_chu);  
  pan.add(button4);  
  pan.add(button5);  
  pan.add(button6);  
  pan.add(button_cheng);  
  pan.add(button1);  
  pan.add(button2);  
  pan.add(button3);  
  pan.add(button_jian);  
  pan.add(button0);  
  pan.add(button_Dian);  
  pan.add(button_dy);  
  pan.add(button_jia);  
  pan.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));//pan����ı߾�  
  
  JPanel  pan2=new  JPanel();  
  pan2.add(jg_TextField);  
  
  
  JPanel  pan3=new  JPanel();  //ΪʲôҪ  ���һ���أ�  ��Ϊ�Ҳ�������  ��ť�Ĵ�С  
  pan3.setLayout(new  FlowLayout());  
  pan3.add(clear_Button);  
  //clear_Button.setSize(10,10);//�������㰴ť�Ĵ�С  ���  ����ʹ  ����  
  
  frame.setLocation(300,  200);  //������  ������λ��  
  frame.setResizable(false);  //���ܵ���С  
  frame.getContentPane().setLayout(new  BorderLayout());  
  frame.getContentPane().add(pan2,BorderLayout.NORTH);  
  frame.getContentPane().add(pan,BorderLayout.CENTER);  
  frame.getContentPane().add(pan3,BorderLayout.SOUTH);  
  
  
  frame.pack();  
  frame.setVisible(true);  
  
  
  //������  �ؼ�  ��  ����  
  //�������¼�����  ��  ��  
  
  //---------------  ��  ��  ��  ----------------  
  class  JianTing  implements  ActionListener  
  {  
  public  void  actionPerformed(ActionEvent  e)  
  {  
  String  ss=((JButton)e.getSource()).getText();  
  jicunqi=(JButton)e.getSource();  
  vt.add(jicunqi);  
  if  (k1==1)  
  {  
  if(k3==1)  
  {  
  str1="";  
  k5=1;//��ԭ����k5״̬  
  }  
  str1=str1+ss;  
  //k2=1;  
  k3=k3+1;  
  //System.out.println(str1);  
  jg_TextField.setText(str1);//��ʾ  
  
  }  
  else  if(k1==2)  
  {  
  if  (k4==1)  
  {  
  str2="";  
  k5=1;  //��ԭ����k5״̬  
  }  
  str2=str2+ss;  
  //k2=2;  
  k4=k4+1;  
  ///////////////����////////////////  
  jg_TextField.setText(str2);  
  }  
  
  
  
  
  }  
  }  
  
  //������������������  �ţ���������������������  
  class  JianTing_fh  implements  ActionListener  
  {  
  public  void  actionPerformed(ActionEvent  e)  
  {  
  String  ss2=((JButton)e.getSource()).getText();  
  jicunqi=(JButton)e.getSource();  
  vt.add(jicunqi);  
  
  if(k2==1)  
  {  
  k1=2;//����  k1  Ϊ1ʱ,����1д  Ϊ2ʱ������2д  
  k5=1;  
  fh=ss2;  
  k2=k2+1;//�����ż��Ĵ���  
  }  
  else  
  {  
  int  a=vt.size();  
  JButton  c=(JButton)vt.get(a-2);  
  
  if(!(c.getText().equals("+"))&&!(c.getText().equals("-"))&&!(c.getText().equals("*"))&&!(c.getText().equals("/")))  
  //if(!(vt.get(a-2).getText().equals("-"))||!(vt.get(a-2).getText().equals("+"))||!(vt.get(a-2).getText().equals("*"))||!(vt.get(a-2).getText().equals("/")))  
  {  yuns();  
  str1=jg;  
  k1=2;//����  k1  Ϊ1ʱ,����1д  Ϊ2ʱ������2д  
  k5=1;  
  k4=1;  
  fh=ss2;  
  }  k2=k2+1;  
  
  }  
  
  }  
  }  
  
  //���������������������������������  
  class  JianTing_clear  implements  ActionListener  
  {  
  public  void  actionPerformed(ActionEvent  e)  
  {  
  jicunqi=(JButton)e.getSource();  
  vt.add(jicunqi);  
  k5=1;  
  k2=1;  
  k1=1;  
  k3=1;  
  k4=1;  
  str1="0";  
  str2="0";  
  fh="";  
  jg="";  
  jg_TextField.setText(jg);  
  vt.clear();  
  sysArea.setText("");
  }  
  }  
  
  //----------------��  ��  ---------------------  
  class  JianTing_dy  implements  ActionListener  
  {  
  public  void  actionPerformed(ActionEvent  e)  
  {  
  
  jicunqi=(JButton)e.getSource();  
  vt.add(jicunqi);  
  yuns();  
  k1=1;  //��ԭ����k1״̬  
  //str1=jg;  
  k2=1;  
  k3=1;//��ԭ����k3״̬  
  k4=1;  //��ԭ����k4״̬  
  
  str1=jg;  //Ϊ7+5=12  +5=17  ���ּ�����׼��  
  }  
  }  
  //----------------С����  ---------------------  
  class  JianTing_xiaos  implements  ActionListener  
  {  
  public  void  actionPerformed(ActionEvent  e)  
  {  
  jicunqi=(JButton)e.getSource();  
  vt.add(jicunqi);  
  if(k5==1)  
  {  
  String  ss2=((JButton)e.getSource()).getText();  
  if  (k1==1)  
  {  
  if(k3==1)  
  {  
  str1="";  
  k5=1;  //��ԭ����k5״̬  
  }  
  str1=str1+ss2;  
  //k2=1;  
  k3=k3+1;  
  //System.out.println(str1);  
  jg_TextField.setText(str1);//��ʾ  
  
  
  }  
  else  if(k1==2)  
  {  
  if  (k4==1)  
  {  
  str2="";  
  k5=1;  //��ԭ����k5״̬  
  }  
  str2=str2+ss2;  
  //k2=2;  
  k4=k4+1;  
  ///////////////����////////////////  
  jg_TextField.setText(str2);  
  }  
  }  
  
  k5=k5+1;  //  
  }  
  }  
  
  
  //ע��  ������  
  JianTing_dy  jt_dy=new  JianTing_dy();  
  JianTing  jt=  new  JianTing();//�������ּ�  
  JianTing_fh  jt_fh=  new  JianTing_fh();//��  ����  �ż�  
  JianTing_clear  jt_c=new  JianTing_clear();  //�����  
  JianTing_xiaos  jt_xs=new  JianTing_xiaos();//  С����  ��  
  
  
  button7.addActionListener(jt);  
  button8.addActionListener(jt);  
  button9.addActionListener(jt);  
  button_chu.addActionListener(jt_fh);  
  button4.addActionListener(jt);  
  button5.addActionListener(jt);  
  button6.addActionListener(jt);  
  button_cheng.addActionListener(jt_fh);  
  button1.addActionListener(jt);  
  button2.addActionListener(jt);  
  button3.addActionListener(jt);  
  button_jian.addActionListener(jt_fh);  
  button0.addActionListener(jt);  
  button_Dian.addActionListener(jt_xs);  
  button_dy.addActionListener(jt_dy);  
  button_jia.addActionListener(jt_fh);  
  clear_Button.addActionListener(jt_c);  
  
  //�ر��¼��������  
  frame.addWindowListener(new  WindowAdapter()  
  {  
  public  void  windowClosing(WindowEvent  e)  
  {  
    if(closeForm){
      System.exit(0); 
    } else {
    	frame.dispose(); 
    }
    //System.exit(0);  
  }  
  });  
  
  
  }  
  
  //---------------��  ��------------------  
  public  void  yuns()  
  {  
  double  a2;//������1  
  double  b2;//������2  
  String  c=fh;//  �����  
  double  jg2=0  ;//���  
  
  if  (c.equals(""))  
  {  
  //System.out.println("�����������");  
  jg_TextField.setText("�����������");  
  
  }  
  else  
  {  
  
  sysArea.append("str1:"+str1+sysLine);//System.out.println("str1:"+str1);//����ʱ  ʹ  ��  
  sysArea.append("str2:"+str2+sysLine);//System.out.println("str2:"+str2);//����ʱ  ʹ  ��  
  sysArea.append("�����:"+fh+sysLine);//System.out.println("�����:"+fh);//����ʱ  ʹ  ��  
  if  (str1.equals("."))  //�ַ���  "."  ת����double������ʱ  �����  �����ֹ�ת  
  str1="0.0";  
  if  (str2.equals("."))  
  str2="0.0";  
  a2=Double.valueOf(str1).doubleValue();  
  b2=Double.valueOf(str2).doubleValue();  
  
  sysArea.append("double�͵�a2��"+a2+sysLine);//System.out.println("double�͵�a2��"+a2);  //����ʱ  ʹ  ��  
  sysArea.append("double�͵�b2��"+b2+sysLine);//System.out.println("double�͵�b2��"+b2);  //����ʱ  ʹ  ��  
  
  
  if  (c.equals("+"))  
  {  
  jg2=a2+b2;  
  }  
  if  (c.equals("-"))  
  {  
  jg2=a2-b2;  
  }  
  if  (c.equals("*"))  
  {  
  jg2=a2*b2;  
  }  
  if  (c.equals("/"))  
  {  
  if(b2==0)  
  {  
  jg2=0;//0000000000000  by  0  cu!  
  }  
  else  
  {  
  jg2=a2/b2;  
  }  
  
  
  }  
  
  sysArea.append("double��a2"+fh+"b2�����"+jg2+sysLine); //System.out.println("double��a2"+fh+"b2�����"+jg2);  
  
  sysArea.append(sysLine);//System.out.println();  
  jg=((new  Double(jg2)).toString());  
  
  jg_TextField.setText(jg);  
  }  
  }  
}