package begin;

import module.*;
import panels.*;
import sframe.*;

//import java.lang.*;
import java.io.*;
import java.text.*;
import java.util.*;
//import java.util.Hashtable.*;
//import java.sql.*;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

//import java.net.*; 

public class PeaceBox extends JFrame {

    public static Properties sysConfig = Info.getConfigs("data\\PeaceBox.ini","");
    public static String sysLang = sysConfig.getProperty("cfgLang","English.ini");
    public static String sysCSet = sysConfig.getProperty("cfgCSet","ISO-8859-1");
    public static Properties sysLTab = Info.getConfigs("img\\lang\\"+sysLang,sysCSet);
    
    public static String sysBasePath = Info.getBasePath();
    public static String sysLine = System.getProperty("line.separator");
    public static Font sysFont = new Font(sysConfig.getProperty("cfgFont","Courier"),0,12);
    
    public static JTextArea sysEditor = new JTextArea("");
    public static JLabel labState = new JLabel(sysBasePath);  
    public static JLabel labInfo = new JLabel(sysLTab.getProperty("boxPos","Pos")+":0,0 "+sysLTab.getProperty("boxSize","Size")+":0B");          
    public static JLabel labPeace = new JLabel(sysLTab.getProperty("boxAdv","Adver... --- Peace")); //
    
    public static JPanel pSide = new JPanel(new FlowLayout()); 
    public static ToolBar tooBar = new ToolBar(); 
     
  public PeaceBox(String title)
  {
    super(title); //setTitle("Peace Box");
  	sysEditor.setFont(sysFont); sysEditor.setMargin(new Insets(1,3,1,1));
    setIconImage(Toolkit.getDefaultToolkit().getImage(sysBasePath+"img\\logo\\icon.jpg")); 
    setSize(720,560); setLocation(120,120);
    addWindowListener(new actClosing()); //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
    
    //setLayout(new BorderLayout(0,0)); //(默认)东西南北方位布局
    JPanel pTop = new JPanel(new GridLayout(2,1,0,0)); 
    JPanel pTool = new JPanel(new FlowLayout(FlowLayout.RIGHT,2,1)); 
    //JPanel pSide = new JPanel(new FlowLayout());  
    JPanel pState = new JPanel(new GridLayout(1,2,0,0)); 
    JPanel pStat2 = new JPanel(new GridLayout(1,2,0,0));
    JPanel pStat3 = new JPanel(new FlowLayout(FlowLayout.RIGHT,0,0));
    
    JMenuBar sysMenu = new JMenuBar(); 
    tooBar.setTools(pTool);
    JScrollPane pScroll = new JScrollPane(sysEditor);
    sysEditor.addCaretListener(new updEditor());
    
    // ===== 布局设置 === Layout ....
    add(pTop,"North"); 
    pTop.add(sysMenu);
    pTop.add(pTool); 
    
    add(pScroll,"Center"); add(pSide,"West"); 
    SideBar sideBar = new SideBar(); sideBar.setSide();

    add(pState,"South");   
    pState.add(labState); labState.setFont(sysFont);
    pState.add(pStat2); 
    pStat2.add(labInfo);  labInfo.setFont(sysFont); 
    pStat2.add(pStat3); 
    pStat3.add(labPeace); labPeace.setFont(sysFont);      
        
     
    // ===== 菜单添加 === 
    MFile mFile = new MFile(); mFile.setMenu(sysMenu); 
    MEdit mEdit = new MEdit(); mEdit.setMenu(sysMenu);
        
    MMods mTool = new MMods(); mTool.setTool(sysMenu);
    MMods mPuzz = new MMods(); mPuzz.setPuzz(sysMenu);
    
    MSett mSett = new MSett(); mSett.setMenu(sysMenu);
    MHelp mHelp = new MHelp(); mHelp.setMenu(sysMenu);
    
  }
  
  public static void main(String[] args) 
  {
    Enumeration keys = UIManager.getDefaults().keys();
    while (keys.hasMoreElements()) {
      Object key = keys.nextElement();
      Object value = UIManager.get(key);
      if (value instanceof javax.swing.plaf.FontUIResource) {
        UIManager.put(key, sysFont);
      }
    } 
    PeaceBox pBox = new PeaceBox(sysLTab.getProperty("boxTitle","Peace Box"));
    pBox.setVisible(true); 
  }

  public static void actExit() 
  {
    // Save Prop Items
    //int i = JOptionPane.showConfirmDialog(null,"确认要关闭系统[PeaceBox]?","系统提示",JOptionPane.YES_NO_OPTION);
    //if(i==0) System.exit(0);   
    //try {
      MSett.saveProp();
    //} catch(Exception ex) {   
	    //Func.doException(ex, sysLTab.getProperty("actExit","(saveProp)"));
	    System.exit(0);
    //}
  }

  private class actClosing extends WindowAdapter {
    public void windowClosing(WindowEvent wevent){ 
    	actExit();
    } 
  } // 退出Exit

 	class updEditor implements CaretListener
	{
    public void caretUpdate(CaretEvent e1) {
      try {
        int row = sysEditor.getLineOfOffset(e1.getDot());
        int col = e1.getDot() - sysEditor.getLineStartOffset(row);
        int len = sysEditor.getText().length();
        labInfo.setText(sysLTab.getProperty("boxPos","Pos")+":"+(row+1)+","+col+" "+sysLTab.getProperty("boxSize","Size")+":"+len+"B"); 
      } catch (Exception e2) {
        e2.printStackTrace(); 
      }
    } 
	} //www.cppblog.com/biao/archive/2009/05/27/85854.html
 
/*
    Component[] comp = this.getComponents(); 
    for(Component cp:comp){ 
      cp.setFont(sysFont); 
    } 
*/ 

}


