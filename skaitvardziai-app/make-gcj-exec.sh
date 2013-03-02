#! /bin/bash
mvn clean package assembly:assembly

echo Executing gcj
time gcj --main=eu.vytenis.skaitvardziai.app.main.Main -o target/skaitvardziai target/skaitvardziai-*-jar-with-dependencies.jar
