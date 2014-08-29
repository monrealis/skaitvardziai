#! /bin/bash
mvn clean package

echo Executing gcj
time gcj --main=eu.vytenis.skaitvardziai.app.main.Main -o target/skaiciai target/skaitvardziai-*-jar-with-dependencies.jar
