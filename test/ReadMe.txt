

A3. DOS命令：
javac -classpath bin -d bin src/begin/*.java
javac -classpath bin -d bin src/module/*.java
javac -classpath bin -d bin src/module/puzzler/*.java
javac -classpath bin -d bin src/module/tools/*.java
javac -classpath bin -d bin src/panels/*.java
javac -classpath bin -d bin src/sframe/*.java
java -classpath bin begin.PeaceBox
java -classpath bin/ module.tools.Coder
java -classpath bin/ module.puzzler.CMath
A2. DOS命令：
javac -d bin src/*.java
java -classpath bin/ main.PeaceBox
javac -classpath bin -d bin src/*.java
javac -classpath bin -sourcepath src -d bin src/*.java
A1. DOS命令：
javac -d ../bin/ *.java
javac -d ../bin/ PeaceBox.java
java -classpath ../bin/ main.PeaceBox
------------------------------------------------

B2. 常用代码：
if(str.equals("peace")) ...
int i = 50;
String s = Integer.toString(i); 
int n = s.length();
int j = Integer.valueOf(s);
------------------------------------------------
C2. 数独输入示例：
 000 004 076 
 801 000 030 
 046 003 000 

 000 020 701 
 100 706 005 
 507 030 000 

 000 900 810 
 050 000 204 
 910 800 000 
 用0占位，忽略空格和空白行
------------------------------------------------
C2. 超级素数规则：
//规则1: abcdef六位数素数; abc,def组成2个3位数素数; ab,cd,ef组成3个2位数素数。
//规则2: abcdef六位数素数; a,ab,abc,abcd,abcde组成5个素数; 
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