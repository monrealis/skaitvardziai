# Skaičių konvertavimo į lietuvių kalbos skaitvardžius biblioteka

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

Programa veikia stabiliai ir artimiausiu metu dirbti prie jos neplanuojama. Kaip programą galima panaudoti,
galima rasti jUnit testuose. Apie rastas klaidas prašome informuoti. Taip pat informuokite,
jei turite idėjų, kaip biblioteką galima vystyti.


## Vykdomosios programos (veikiančios per komandinę eilutę) instaliavimas

sudo yum install git maven gcc-java  # Jei Fedora
sudo apt-get install git maven gcj-jdk # Jei Ubuntu
git clone https://github.com/monrealis/skaitvardziai
cd skaitvardziai
./install-to-linux.sh
skaiciai 125

## Licencija

Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)
