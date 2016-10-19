package sframe;
import begin.*;

//import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
//import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import java.io.PrintWriter;
import java.net.*;

public class MEdit {  
  
  JTextField jFind=new JTextField();//���ҶԻ����ı���
  JTextField jRep=new JTextField();//�滻�ı���2
  int A=0,B=0,C=0,D=0;//Ϊ���ҹ��ܶ���ȫ�ֱ���
  
  JTextArea sysEditor = PeaceBox.sysEditor;
  Properties sysLTab = PeaceBox.sysLTab; //sysLTab.getProperty("xxName","defValue")
  String sysLine = System.getProperty("line.separator");
  
  public void setMenu(JMenuBar sysMenu) 
  { 
    JMenu m = new JMenu(sysLTab.getProperty("edSubj","Edit(E)")); sysMenu.add(m);  m.setMnemonic('E'); 
    
    JMenuItem mFind  = new JMenuItem(sysLTab.getProperty("edIFind","Find ..."));   m.add(mFind); 
    JMenuItem mRep   = new JMenuItem(sysLTab.getProperty("edIRep","Replace ...")); m.add(mRep); 
    m.addSeparator();
    JMenuItem mCopy  = new JMenuItem(sysLTab.getProperty("edICopy","Copy"));       m.add(mCopy); 
    JMenuItem mCut   = new JMenuItem(sysLTab.getProperty("edICut","Cut"));         m.add(mCut);
    JMenuItem mPaste = new JMenuItem(sysLTab.getProperty("edIPaste","Paste"));     m.add(mPaste); 
    m.addSeparator();
    JMenuItem mSAll  = new JMenuItem(sysLTab.getProperty("edISAll","Select All"));     m.add(mSAll);
    JMenuItem mTime  = new JMenuItem(sysLTab.getProperty("edITime","Insert Time")); m.add(mTime);
    
    mFind.addActionListener(new aFind());
    mRep.addActionListener(new aRep());
    mCopy.addActionListener(new aCopy());
    mCut.addActionListener(new aCut());
    mPaste.addActionListener(new aPaste());
    mSAll.addActionListener(new aSAll());
    mTime.addActionListener(new aTime());
    
  } 
  
  public void editFindDialog() 
  {
    A=1; B=1;
    final JDialog fi=new JDialog();//�½��Ի���
    fi.setTitle(sysLTab.getProperty("edDSubj","Find"));
    fi.setBounds(150,150,330,150);
    fi.setVisible(true);
    fi.setLayout(null);
    fi.setResizable(false);
    fi.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JLabel jlb1,jlb2;
    jlb1=new JLabel(sysLTab.getProperty("edDCont","Find(N):")); fi.add(jlb1); jlb1.setBounds(10,10,90,20);
    jlb2=new JLabel(sysLTab.getProperty("edDDir","Order:"));    fi.add(jlb2); jlb2.setBounds(10,45,60,20);
    fi.add(jFind); jFind.setBounds(100,10,200,20);
    jFind.addKeyListener(new KeyAdapter(){//ģ��windows����Alt+Nѡ���ı�
     public void keyPressed(KeyEvent e){
       if(e.getModifiers()==InputEvent.ALT_MASK&&e.getKeyCode()==KeyEvent.VK_N)
         jFind.selectAll();
     }
    });
    final JButton fi_ne=new JButton(sysLTab.getProperty("edDNext","Next(F3)"));               fi.add(fi_ne); fi_ne.setBounds(50,80,90,25);
          JButton can=new JButton(sysLTab.getProperty("edDCancel","Cancel"));                 fi.add(can); can.setBounds(200,80,90,25);
          JRadioButton up=new JRadioButton(sysLTab.getProperty("edDUp","Up(U)"));            up.setMnemonic('U');
    final JRadioButton down=new JRadioButton(sysLTab.getProperty("edDDown","Down(D)"),true); down.setMnemonic('D');
    up.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent arg0) {
       fi_ne.setText(sysLTab.getProperty("edDPrev","Prev(F3)"));
       A=2;//��ʼ�������Ե��ò��ҷ���
     }    
    });
    down.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent arg0) {
       fi_ne.setText(sysLTab.getProperty("edDNext","Next(F3)"));
       A=1;//��ʼ�������Ե��ò��ҷ���
     }  
    });
    ButtonGroup bg=new ButtonGroup();
    bg.add(up); bg.add(down);
    fi.add(up); up.setBounds(50,45,75,20);
    fi.add(down); down.setBounds(125,45,75,20);
    final JCheckBox jcb=new JCheckBox(sysLTab.getProperty("edDCase","Match case(C)")); jcb.setMnemonic('C');
    jcb.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
       if(jcb.isSelected()==true)//��ʼ�������Ե��ò��ҷ���
         B=0;
       else
         B=1;
     }    
    });
    fi.add(jcb); jcb.setBounds(195,45,110,20);
    fi_ne.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
       editFind();//���ò��ҷ���
     }    
    });
    can.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent arg0) {
       fi.dispose();
     }    
    });
  }
  
  public void editFind() 
  {
    String sTmp=new String(sysEditor.getText());//��ʱ�洢�����ı�
    String sRet=jFind.getText();//�����ı�������
    Matcher m; Pattern p;
    if(A==1){//������·���ѡ��
      String s1=sTmp.substring(sysEditor.getCaretPosition());//ȡ�ӹ��λ�õ��ı�ĩβ���Ӵ�
      if(sRet!=null){//����������ݲ�Ϊ��
        p=Pattern.compile(sRet);//����ģʽĬ��Ϊ���ִ�Сд
        if(B==1) p=Pattern.compile(sRet,Pattern.CASE_INSENSITIVE);//�趨���Դ�Сд��ģʽ
        m=p.matcher(s1);
        if(m.find()){
          sysEditor.select(m.start()+sysEditor.getCaretPosition(), m.end()+sysEditor.getCaretPosition()); 
          jFind.setText(sRet);
        } else {
          JOptionPane.showMessageDialog(null,sysLTab.getProperty("edDNoFind","Not Found"),sysLTab.getProperty("edDNoSubj","Info"),JOptionPane.WARNING_MESSAGE);
        } // m.find()
      } // sRet!=null 
    } else if(A==2) { //���ϲ���
      String s2=sTmp.substring(0,sysEditor.getCaretPosition()-sRet.length());//�����Ӵ�Ϊ�ı���ʼ�����λ�ü�Ҫ�����ַ�������
      if(D==0) s2=sTmp.substring(0,sysEditor.getCaretPosition());//�����һ�ν������ϲ���ʱ�򲻼�Ҫ�����ַ�������
      if(sRet!=null){
        p=Pattern.compile(sRet);
        if(B==1) p=Pattern.compile(sRet,Pattern.CASE_INSENSITIVE);
        m=p.matcher(s2);
        int Y=0;//����������Կ��Ƶ�����
        while(m.find()){
          sysEditor.select(m.start(), m.end());
          jFind.setText(sRet);
          D=1; Y=1;
        } // while
        if(Y==0) JOptionPane.showMessageDialog(null,sysLTab.getProperty("edDNoFind","Not Found"),sysLTab.getProperty("edDNoSubj","Info"),JOptionPane.WARNING_MESSAGE);       
      } // sRet!=null
    } // A==2) //���ϲ���
  }
  
  public void editRep()
  {
    A=1; B=1; C=0;
    final JDialog re=new JDialog();//�½��Ի������ò���
    re.setTitle(sysLTab.getProperty("edDRepSubj","Replace"));
    re.setBounds(150,150,330,200);
    re.setVisible(true);
    re.setLayout(null);
    re.setResizable(false);
    re.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    JLabel jlb1,jlb2;
    jlb1=new JLabel(sysLTab.getProperty("edDCont","Find(N)")); re.add(jlb1); jlb1.setBounds(10,10,100,20);
    jlb2=new JLabel(sysLTab.getProperty("edDRepCont","Replace"));   re.add(jlb2); jlb2.setBounds(10,45,100,20);
    jFind.addKeyListener(new KeyAdapter(){//ģ��windows����Alt+Nѡ���ı�
     public void keyPressed(KeyEvent e){
       if(e.getModifiers()==InputEvent.ALT_MASK&&e.getKeyCode()==KeyEvent.VK_N)
         jFind.selectAll();
     }
    });
    re.add(jFind);
    jFind.setBounds(100,10,200,20);
    jRep.addKeyListener(new KeyAdapter(){//ģ��windows����Alt+Pѡ���ı�
     public void keyPressed(KeyEvent e){
       if(e.getModifiers()==InputEvent.ALT_MASK&&e.getKeyCode()==KeyEvent.VK_P)
         jRep.selectAll();
     }
    });
    re.add(jRep); jRep.setBounds(100,45,200,20);
    final JCheckBox jcb=new JCheckBox(sysLTab.getProperty("edDCase","Match case(C)")); jcb.setMnemonic('C');
    jcb.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
      if(jcb.isSelected()==true)
       B=0;
      else
       B=1;
     }    
    });
    re.add(jcb); jcb.setBounds(20,85,110,20);
    final JButton fi_ne=new JButton(sysLTab.getProperty("edDNext","Next(F3)"));
    fi_ne.setMnemonic('F');
    fi_ne.addActionListener(new ActionListener(){
     public void actionPerformed(ActionEvent e) {
      A=1;
      editFind();//���ò��ҷ���
     }
    });
    re.add(fi_ne); fi_ne.setBounds(170,80,130,25);
    JButton rep=new JButton(sysLTab.getProperty("edDRepNext","Replace(R)")); rep.setMnemonic('R');
    rep.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        StringBuffer srb=new StringBuffer(sysEditor.getText());//��ʱ�洢�����ı�
        String jtfs1=jFind.getText();//�����ı�������
        String jtfs2=jRep.getText();
        Matcher m; Pattern p;
        String s1=srb.substring(sysEditor.getCaretPosition());//ȡ�ӹ��λ�õ��ı�ĩβ���Ӵ�
        if(jtfs1!=null&&jtfs2!=null){//����������ݲ�Ϊ��
          p=Pattern.compile(jtfs1);//����ģʽĬ��Ϊ���ִ�Сд
          if(B==1) p=Pattern.compile(jtfs1,Pattern.CASE_INSENSITIVE);//�趨���Դ�Сд��ģʽ
          m=p.matcher(s1);
          if(m.find()) {
            if(C==0){
              sysEditor.select(m.start()+sysEditor.getCaretPosition(), m.end()+sysEditor.getCaretPosition()); 
              jFind.setText(jtfs1);
              C++;
            }
            C++;
          } else{
            A=3;
          }
          if(sysEditor.getSelectedText()!=null){
            if(C>2){
              int start = sysEditor.getSelectionStart(); //�õ�Ҫɾ�����ַ�������ʼλ��
              int len = sysEditor.getSelectedText().length(); //�õ�Ҫɾ�����ַ����ĳ���
              StringBuffer srb1=new StringBuffer(sysEditor.getText());
              srb1.delete(start,start+len);//ɾ��ѡ���ı�
              sysEditor.setText(srb1.toString());//�����ı�����ԭ�ı�
              sysEditor.insert(jtfs2,start);//����Ҫ�滻�ɵ��ı�
              if(A!=3) { //�����ƥ�������򲻽���ѡ��
                sysEditor.setCaretPosition(m.start()+jtfs2.length());
              }
               editFind();
            }
          } 
          if(A==3) JOptionPane.showMessageDialog(null,sysLTab.getProperty("edDNoFind","Not Found"),sysLTab.getProperty("edDNoSubj","Info"),JOptionPane.WARNING_MESSAGE);
        }
      }
    });
    re.add(rep); rep.setBounds(10,130,90,25);
    JButton repal=new JButton(sysLTab.getProperty("edDRepAll","All(R)")); repal.setMnemonic('R');
    repal.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        String srb=new String(sysEditor.getText());//��ʱ�洢�����ı�
        String jtfs1=jFind.getText();//�����ı�������
        String jtfs2=jRep.getText();
        Matcher m;
        Pattern p;
        if(jtfs1!=null&&jtfs2!=null){
          p=Pattern.compile(jtfs1);//����ģʽĬ��Ϊ���ִ�Сд
          if(B==1) p=Pattern.compile(jtfs1,Pattern.CASE_INSENSITIVE);//�趨���Դ�Сд��ģʽ
          m=p.matcher(srb);
          if(m.find()){
            String temp=m.replaceAll(jtfs2);
            sysEditor.setText(temp.toString());
          }
        }
      }
    });
    re.add(repal); repal.setBounds(110,130,90,25);
    JButton can=new JButton(sysLTab.getProperty("edDCancel","Cancel"));
    can.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent e){
        re.dispose();
      }
    });
    re.add(can); can.setBounds(210,130,90,25);
  }
  
  class aFind implements ActionListener
  {
    public void actionPerformed(ActionEvent evt) 
    { editFindDialog(); } 
  } // ����f21Find
  class aRep implements ActionListener 
  { 
    public void actionPerformed(ActionEvent evt) 
    { editRep(); } 
  } // �滻f22Rep
  
  class aCopy implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { sysEditor.copy(); }
  } // ����m23Copy  
  class aCut implements ActionListener 
  {
    public void actionPerformed(ActionEvent e)
    { sysEditor.cut(); }
  } // ����m24Cut  
  class aPaste implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    { sysEditor.paste(); }
  } // ճ��m25Paste

  class aSAll implements ActionListener
  {
    public void actionPerformed(ActionEvent e) 
    { 
      sysEditor.setSelectionStart(0); 
      sysEditor.setSelectionEnd(sysEditor.getText().length());  
    } 
  } // ȫѡm28SAll 
  
  class aTime implements ActionListener
  {
    public void actionPerformed(ActionEvent e)
    {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
      sysEditor.append(sysLine+sdf.format(new Date()));
    }
  } // ����ʱ��m29Time
  
}


