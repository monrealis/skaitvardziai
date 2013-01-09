<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:sk="java:eu.vytenis.skaitvardziai.xpath.SkaiciusXPathFunctions">
	<xsl:output method="text" />

	<xsl:template match="/">
		<xsl:value-of select="sk:sveikasis(15)" />
		<xsl:text>: penkiolika;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(1, 2)"  />
		<xsl:text>: viena antroji;</xsl:text>
		
		<xsl:value-of select="sk:trupmena(2, 3)"/>
		<xsl:text>: dvi trečiosios;</xsl:text>
	</xsl:template>
</xsl:stylesheet>
