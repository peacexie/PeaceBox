

A3. DOS���
javac -classpath bin -d bin src/begin/*.java
javac -classpath bin -d bin src/module/*.java
javac -classpath bin -d bin src/module/puzzler/*.java
javac -classpath bin -d bin src/module/tools/*.java
javac -classpath bin -d bin src/panels/*.java
javac -classpath bin -d bin src/sframe/*.java
java -classpath bin begin.PeaceBox
java -classpath bin/ module.tools.Coder
java -classpath bin/ module.puzzler.CMath
A2. DOS���
javac -d bin src/*.java
java -classpath bin/ main.PeaceBox
javac -classpath bin -d bin src/*.java
javac -classpath bin -sourcepath src -d bin src/*.java
A1. DOS���
javac -d ../bin/ *.java
javac -d ../bin/ PeaceBox.java
java -classpath ../bin/ main.PeaceBox
------------------------------------------------

B2. ���ô��룺
if(str.equals("peace")) ...
int i = 50;
String s = Integer.toString(i); 
int n = s.length();
int j = Integer.valueOf(s);
------------------------------------------------
C2. ��������ʾ����
 000 004 076 
 801 000 030 
 046 003 000 

 000 020 701 
 100 706 005 
 507 030 000 

 000 900 810 
 050 000 204 
 910 800 000 
 ��0ռλ�����Կո�Ϳհ���
------------------------------------------------
C2. ������������
//����1: abcdef��λ������; abc,def���2��3λ������; ab,cd,ef���3��2λ��������
//����2: abcdef��λ������; a,ab,abc,abcd,abcde���5������; 
------------------------------------------------
Debug 3
@REM start javaw -jar jname.jar 
@REM java -classpath bin/ begin.PeaceBox
@REM javac -d bin src/*.java
@REM java -classpath bin/ begin.PeaceBox
@REM cmd
cd ../
javac -classpath bin -d bin src/begin/*.java
javac -classpath bin -d bin src/module/*.java
javac -classpath bin -d bin src/module/puzzler/*.java
javac -classpath bin -d bin src/module/tools/*.java
javac -classpath bin -d bin src/panels/*.java
javac -classpath bin -d bin src/sframe/*.java
java -classpath bin/ begin.PeaceBox
@cmd
------------------------------------------------