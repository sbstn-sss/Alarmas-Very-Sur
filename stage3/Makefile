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
	Persona.java\
	Pir.java\
	Position.java\
	Sensor.java\
	Siren.java\
	Stage3.java\
	State.java\
	SwitchState.java\
	Window.java

MAIN = Stage3 

default:classes

classes:$(CLASSES:.java=.class)

run:$(MAIN).class
	$(JVM)$(MAIN)$(FILE)

clean:
	$(RM) *.class
