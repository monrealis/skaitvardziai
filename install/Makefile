ULB=/usr/local/bin/
ULL=/usr/local/lib/
TARGET=../skaitvardziai-app/target/
CPBIN=cp -av skaiciai.sh $(ULB)skaiciai
CPLIB=cp -av $(TARGET)skaiciai.jar $(ULL)
RM=rm -f $(ULB)skaiciai $(ULL)/skaiciai.jar

compile:
	mvn -f .. install
	cp $(TARGET)skaitvardziai-*-jar-with-dependencies.jar $(TARGET)skaiciai.jar

install: compile install-cp test-install

install-cp:
	@if [ -w $(ULB) ] ; then \
		$(CPBIN) ; \
		$(CPLIB) ; \
	else \
		echo $(ULB) not writable, installing with sudo ; \
		sudo $(CPBIN) ; \
		sudo $(CPLIB) ; \
	fi

test-install:	
	@if [ `skaiciai 1` == "vienas" ] ; then \
		echo Test passed. Time to have fun. ; \
		echo -n Counting from -5 to 20: "" ; \
		seq -5 20 | skaiciai | paste -s -d, - | sed "s/,/, /g" ; \
		echo -n  Counting from -5/1 to 20/1: "" ; \
		seq -5 20 | xargs -i echo {}/1 | skaiciai | paste -s -d, - | sed "s/,/, /g" ; \
	else \
		echo Test failed ; \
		exit 1 ; \
	fi

clean:
	@if [ -w $(ULB) ] ; then \
		$(RM) ; \
	else \
		echo $(ULB)skaiciai not writable, removing with sudo ; \
		sudo $(RM) ; \
	fi
