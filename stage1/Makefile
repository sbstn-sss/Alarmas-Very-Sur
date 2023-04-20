JFLAGS = -g 
JC = javac 
JVM = java 
FILE = "config.txt" 
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS)$*.java

CLASSES = \
	Door.java\
	MagneticSensor.java\
	Sensor.java\
	Stage1.java\
	State.java\
	SwitchState.java\
	Window.java

MAIN = Stage1

default:classes

classes:$(CLASSES:.java=.class)

run:$(MAIN).class
	$(JVM) $(MAIN) $(FILE)

clean:
	$(RM) *.class
