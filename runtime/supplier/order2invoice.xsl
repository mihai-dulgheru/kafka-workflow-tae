<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

	<!-- Transformarea elementului Order în Invoice -->
	<xsl:template match="Order">
		<Invoice>
			<!-- Copiază toate atributele elementului Order în elementul Invoice -->
			<xsl:copy-of select="@*" />
			<!-- Copiază toate elementele și conținutul elementului Order în
			elementul Invoice -->
			<xsl:copy-of select="node()" />
		</Invoice>
	</xsl:template>

	<!-- Template-ul de identitate - copiază toate celelalte elemente și
	atribute ca atare -->
	<xsl:template match="@* | node()">
		<xsl:copy>
			<xsl:apply-templates select="@* | node()" />
		</xsl:copy>
	</xsl:template>

</xsl:stylesheet>
