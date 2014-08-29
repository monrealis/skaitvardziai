#!/bin/bash
set -e

mvn package

if [ ! -x target/skaiciai ] ; then
	echo Executing gcj
	gcj --main=eu.vytenis.skaitvardziai.app.main.Main -o target/skaiciai target/skaitvardziai-*-jar-with-dependencies.jar
fi
