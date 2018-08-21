JFLAGS = -g
JC = javac

.SUFFIXES: .java .class

java.class:
	$(JC) $(JFLAGS) *.java

CLASSES = CacheSimulator.java \
	Hex2Dec.java \
	test.java \
	L1Cache.java \
	L1Cache_2.java \
	L2Cache.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
