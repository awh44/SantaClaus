.PHONY: run clean

run: Main.class
	java Main

Main.class: Main.java Santa.java Reindeer.java Elf.java Sleigh.java SantaHelper.java 
	javac Main.java Santa.java Reindeer.java Elf.java Sleigh.java SantaHelper.java

clean:
	rm *.class
