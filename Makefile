# Compilador
JAVAC = javac
JAVA = java

# Diretórios
SRC = src
BIN = bin
OUTPUT_FILE = saves/output.txt
INPUT_FILE = saves/scripts.txt

# Ficheiros
SOURCES = $(wildcard $(SRC)/*.java)
CLASSES = $(patsubst $(SRC)/%.java,$(BIN)/%.class,$(SOURCES))

# Default
all: compile

# Compilar para Bin
$(BIN)/%.class: $(SRC)/%.java
	@mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $<

# Compilar todos os ficheiros
compile:
	@echo "Compiling source files..."
	$(JAVAC) -d $(BIN) $(SOURCES)

# Só correr o programa manualmente (se quiser)
run:
	$(JAVA) -cp $(BIN) Main

# Script para correr com input
script: all scriptAux
	@echo "Running the program with input..."
	cat $(INPUT_FILE) | $(JAVA) -cp $(BIN) Main

scriptAux:
	@echo "Preparing the input file..."
	@cat $(INPUT_FILE)
	@echo "Input file copied to output file."

# Limpar bin
clean:
	rm -rf $(BIN)

# Phony
.PHONY: all run clean script scriptAux
