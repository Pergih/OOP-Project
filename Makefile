# Compilador
JAVAC = javac
JAVA = java

# Diretorias
SRC = src
BIN = bin

# Ficheiros
SOURCES = $(wildcard $(SRC)/*.java)
CLASSES = $(patsubst $(SRC)/%.java,$(BIN)/%.class,$(SOURCES))

# Default
all: compile run

# Compilar para Bin
$(BIN)/%.class: $(SRC)/%.java
	@mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $<

#compile
compile:
	$(JAVAC) -d $(BIN) $(SOURCES)

# Correr o programa
run: all
	$(JAVA) -cp $(BIN) Main

# Remover os ficheiros
clean:
	rm -rf $(BIN)

# Phony
.PHONY: all run clean
