all: src
	mkdir -p bin
	javac -d bin -cp src:lib/* src/fr/main/Main.java

clean:
	rm -r -f bin/*

run: bin
	java -cp bin:lib/* -Djava.library.path=natives fr.main.Main
