JFLAGS = -g 
JC = javac 
JVM = java 
FILE = "config.txt" 
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS)$*.java

CLASSES = \
	AePlayWave.java\
	Central.java\
	Door.java\
	MagneticSensor.java\
	Sensor.java\
	Siren.java\
	Stage2.java\
	State.java\
	SwitchState.java\
	Window.java

MAIN = Stage2

default:classes

classes:$(CLASSES:.java=.class)

run:$(MAIN).class
	$(JVM) $(MAIN) $(FILE)

clean:
	$(RM) *.class
