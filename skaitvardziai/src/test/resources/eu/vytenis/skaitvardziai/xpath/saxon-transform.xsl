<?xml version="1.0" encoding="UTF-8" ?>


<!-- 
	Parametrų reikšmės:
	Linksnis: V, K, N, G, In, Vt, S
	Skaičius: SV, SD
	Giminė: VG, MG
	Poskyris: PP, PD, PK, PT, PKl
	
	Parametrai jungiami kableliu.

 -->
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sk="java:eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctions">
	<xsl:output method="text" />

	<xsl:template match="/">
	
		<xsl:value-of select="sk:sveikasis(12)" />
		<xsl:text>: dvylika;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(15, '')" />
		<xsl:text>: penkiolika;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(26, 'K')" />
		<xsl:text>: dvidešimt šešių;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(37, 'MG')" />
		<xsl:text>: trisdešimt septynios;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(38, 'MG,K')" />
		<xsl:text>: trisdešimt aštuonių;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(39, 'K,MG')" />
		<xsl:text>: trisdešimt devynių;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(100, 'SD,I')" />
		<xsl:text>: šimtais;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(2, 'PK,N')" />
		<xsl:text>: dvejetui;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(2, 'PD,N,MG')" />
		<xsl:text>: dvejoms;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(1, 2)"  />
		<xsl:text>: viena antroji;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(2, 3)"/>
		<xsl:text>: dvi trečiosios;</xsl:text>
	</xsl:template>
</xsl:stylesheet>
