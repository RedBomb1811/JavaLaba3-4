<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="yes"/>
    <xsl:template match="/">
        <html>
            <body>
                <table border="1">
                    <thead>
                        <tr>
                            <th>Name</th>
                            <th>Power</th>
                            <th>Voltage</th>
                        </tr>
                    </thead>
                    <xsl:for-each select="listOfElectricAppliances/electricAppliance">
                        <tr>
                            <xsl:call-template name="PrintElectricAppliance"/>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>

    <xsl:template name="PrintElectricAppliance">
        <td>
            <xsl:value-of select="name"/>
        </td>
        <td>
            <xsl:value-of select="powet"/>
        </td>
        <td>
            <xsl:value-of select="voltage"/>
        </td>
    </xsl:template>

</xsl:stylesheet>