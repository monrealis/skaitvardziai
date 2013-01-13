# Lietuviškų skaičių konvertavimo į skaitvardžius biblioteka

JAVA biblioteka, pagal perduotus skaičius įvairiomis gramatinėmis formomis lietuvių kalba
grąžinanti skaitvardžius.

## Funkcijos
Biblioteka gali būti naudojama per:
* JAVA API
* Egzistuojančių bibliotekų išplėtimus
    * XSLT procesorių

## Pavyzdžiai
Pavyzdžių formatas: *įvestis: išvestis*
* 1: vienas
* 222, kilmininkas: dviejų šimtų dvidešimt dviejų
* 2, dauginis, mot. g., naudininkas: dvejoms
* 4, kuopinis, naudininkas: ketvertui
* 2/3, galininkas: dvi trečiąsias
* 5, įvardžiuotinis, įnagininkas: penktuoju


## Progresas

Realizuotas šis skaičių konvertavimo į skaitvardžius funkcionalumas:
* Kiekiniai skaitvardžiai
   * Pagrindiniai (visi linksniai, vyr. g. ir mot. g., gerai ištestuota iki 1000)
   * Dauginiai (visi linksniai, vyr. g. ir mot. g., gerai ištestuota iki 1000)
   * Kuopiniai skaitvardžiai (visi linksniai, ištestuoti visi - nuo 1 iki 10)
   * Trupmeniniai (visi linksniai, kol kas dar nepakankamai ištestuota)
* Kelintiniai skaitvardžiai (visi linksniai, vyr. g. ir mot. g., įvardžiuotiniai ir neįvardžiuotiniai, gerai ištestuota iki 1000)

Realizuotos XPath funkcijos XSLT procesoriams:
* Saxon
* Xalan
* Standartiniams OpenJDK ir Oracle JRE XLST procesoriams