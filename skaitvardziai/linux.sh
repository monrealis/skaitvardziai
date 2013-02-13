#! /bin/bash
mvn clean package assembly:assembly
gcj --main=eu.vytenis.skaitvardziai.app.Main -o target/skaitvardziai target/skaitvardziai-*-jar-with-dependencies.jar
