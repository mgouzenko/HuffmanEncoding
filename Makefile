Problem1: 
	javac *.java structures/*.java

.PHONY: clean
clean: 
	rm -f *.class Problem1

.PHONY: test
test: Problem1.class
	java Problem1 test.txt
