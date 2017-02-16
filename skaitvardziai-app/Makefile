ULB=/usr/local/bin/
CP=cp -av target/skaiciai $(ULB)
RM=rm -f $(ULB)skaiciai

compile:
	mvn -f .. install
	gcj --main=eu.vytenis.skaitvardziai.app.main.Main -o target/skaiciai target/skaitvardziai-*-jar-with-dependencies.jar

install: compile install-cp test-install

install-cp:
	@if [ -w $(ULB) ] ; then \
		$(CP) ; \
	else \
		echo $(ULB) not writable, installing with sudo ; \
		sudo $(CP) ; \
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
