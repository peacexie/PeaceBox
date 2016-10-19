

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