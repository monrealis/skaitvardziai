# Skaičių konvertavimo į lietuvių kalbos skaitvardžius biblioteka

[![Build Status](https://travis-ci.org/monrealis/skaitvardziai.svg?branch=master)](https://travis-ci.org/monrealis/skaitvardziai)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/eu.vytenis.skaitvardziai/skaitvardziai-core/badge.svg)](http://search.maven.org/#artifactdetails|eu.vytenis.skaitvardziai|skaitvardziai-core|1.1|jar)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)


JAVA biblioteka, kuri pagal perduotus skaičius įvairiomis gramatinėmis formomis lietuvių kalba
suformuoja skaitvardžius.

## Sąsaja
Biblioteka gali būti naudojama:
* Per JAVA API
* Kaip programa, paleidžiama per komandinę eilutę (kaip vykdomasis JAR arba su GCJ sukompiliuota programa)
* Kaip XSLT procesorių XPath funkcijų išplėtimas (Xalan, Saxon, OpenJDK JRE, Oracle JRE)

## Pavyzdžiai
Pavyzdžių formatas: *įvestis: išvestis*
* 1: vienas
* 222, kilmininkas: dviejų šimtų dvidešimt dviejų
* 2, dauginis, mot. g., naudininkas: dvejoms
* 4, kuopinis, naudininkas: ketvertui
* 2/3, galininkas: dvi trečiąsias
* 5, įvardžiuotinis, įnagininkas: penktuoju


## Progresas

Programa veikia stabiliai. Kaip programą galima panaudoti,
galima rasti jUnit testuose. Apie rastas klaidas prašome informuoti. Taip pat informuokite,
jei turite idėjų, kaip biblioteką galima vystyti.


## Vykdomosios programos (veikiančios per komandinę eilutę) instaliavimas

git clone https://github.com/monrealis/skaitvardziai
cd skaitvardziai
./install-to-linux.sh
skaiciai 125

## Licencija

Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)

## Naudojimas su Maven

Programą galima atsisiųsti iš centrinės Apache Maven repozitorijos.
```xml
<dependency>
	<groupId>eu.vytenis.skaitvardziai</groupId>
	<artifactId>skaitvardziai-core</artifactId>
	<version>1.1</version>
</dependency>
```

```java
new SveikasisSkaicius(10).toString();
new Trupmena(123456789, 987654321).toString();
```

## Naudojimas per komandinę eilutę

```shell
skaiciai --help
seq 10 | skaiciai
seq 10 | xargs -i echo {}/{} | skaiciai -f I
```
    