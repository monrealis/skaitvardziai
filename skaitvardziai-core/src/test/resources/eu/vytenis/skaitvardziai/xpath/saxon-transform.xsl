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
	
		<xsl:value-of select="sk:sveikasis(1)" />
		<xsl:text>= vienas;</xsl:text>		
		<xsl:value-of select="sk:kiti(1, 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šuo;</xsl:text>		
		
		<xsl:value-of select="sk:sveikasis(-1)" />
		<xsl:text>= minus vienas;</xsl:text>		
		<xsl:value-of select="sk:kiti(-1, 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šuo;</xsl:text>			
		
		<xsl:value-of select="sk:sveikasis(12)" />
		<xsl:text>= dvylika;</xsl:text>		
		<xsl:value-of select="sk:kiti(12, 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šunų;</xsl:text>		
		
		<xsl:value-of select="sk:sveikasis(15, '')" />
		<xsl:text>= penkiolika;</xsl:text>
		<xsl:value-of select="sk:kiti(15, '', 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šunų;</xsl:text>		
		
		<xsl:value-of select="sk:sveikasis(26, 'K')" />
		<xsl:text>= dvidešimt šešių;</xsl:text>
		<xsl:value-of select="sk:kiti(26, 'K', 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šunų;</xsl:text>		
		
		<xsl:value-of select="sk:sveikasis(37, 'MG')" />
		<xsl:text>= trisdešimt septynios;</xsl:text>
		<xsl:value-of select="sk:kiti(37, '', 'katė', 'katės', 'kačių')" />
		<xsl:text>= katės;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(37, 'Vt')" />
		<xsl:text>= trisdešimt septyniuose;</xsl:text>		
		<xsl:value-of select="sk:sveikasis(37, 'vt')" />
		<xsl:text>= trisdešimt septyniuose;</xsl:text>
		<xsl:value-of select="sk:sveikasis(37, 'VT')" />
		<xsl:text>= trisdešimt septyniuose;</xsl:text>
		<xsl:value-of select="sk:kiti(37, 'Vt', 'šunyje', 'šunyse', 'šunų')" />
		<xsl:text>= šunyse;</xsl:text>
		<xsl:value-of select="sk:kiti(37, 'vt', 'šunyje', 'šunyse', 'šunų')" />
		<xsl:text>= šunyse;</xsl:text>
		<xsl:value-of select="sk:kiti(37, 'VT', 'šunyje', 'šunyse', 'šunų')" />
		<xsl:text>= šunyse;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(38, 'MG,K')" />
		<xsl:text>= trisdešimt aštuonių;</xsl:text>
		<xsl:value-of select="sk:kiti(38, 'MG,K', 'katės', 'kačių', '')" />
		<xsl:text>= kačių;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(39, 'K,MG')" />
		<xsl:text>= trisdešimt devynių;</xsl:text>
		<xsl:value-of select="sk:kiti(39, 'K,MG', 'katės', 'kačių', 'kačių')" />
		<xsl:text>= kačių;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(100, 'SD,I')" />
		<xsl:text>= šimtais;</xsl:text>
		<xsl:value-of select="sk:kiti(100, 'SD,I', 'šunimi', 'šunimis', 'šunų')" />
		<xsl:text>= šunų;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(2, 'PK,N')" />
		<xsl:text>= dvejetui;</xsl:text>
		<xsl:value-of select="sk:kiti(2, 'PK,N', 'šuniui', 'šunims', 'šunų')" />
		<xsl:text>= šunų;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(2, 'PD,N,MG')" />
		<xsl:text>= dvejoms;</xsl:text>
		<xsl:value-of select="sk:kiti(2, 'PD,N,MG', 'katei', 'katėms', 'kačių')" />
		<xsl:text>= katėms;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(102, 'Kl,MG')" />
		<xsl:text>= šimtas antra;</xsl:text>
		<xsl:value-of select="sk:kiti(102, 'Kl,MG', 'katė', 'katės', 'kačių')" />
		<xsl:text>= katė;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(1014, 'Kl,iv')" />
		<xsl:text>= tūkstantis keturioliktasis;</xsl:text>
		<xsl:value-of select="sk:kiti(1014, 'Kl,iv', 'šuo', 'šunys', 'šunų')" />
		<xsl:text>= šuo;</xsl:text>
		
		<xsl:value-of select="sk:sveikasis(102, 'Kl,MG')" />
		<xsl:text>= šimtas antra;</xsl:text>
		<xsl:value-of select="sk:kiti(102, 'Kl,MG', 'katė', 'katės', 'kačių')" />
		<xsl:text>= katė;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(1, 2)"  />
		<xsl:text>= viena antroji;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(-2, -3, 'N')"/>
		<xsl:text>= minus dviem minus trečiosioms;</xsl:text>
	</xsl:template>
</xsl:stylesheet>
