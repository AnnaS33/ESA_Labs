<?xml version="1.0" ?>
<xsl:stylesheet
        xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
        version="3.0"
>
    <xsl:output method="html" indent="yes" media-type="text/html" encoding="UTF-8" />
    <xsl:template match="/">
        <html>
            <head>
                <title>API XSLT</title>
            </head>
            <body>
                <h1>Test XSLT Transformation</h1>
                <xsl:apply-templates />
            </body>
            <style>
                table{
                border: 1px solid blue;
                }
                th{
                border: 1px black;
                padding: 0;
                margin: 0;
                }
                td {
                border: 1px solid black;
                }
            </style>
        </html>
    </xsl:template>
    <xsl:template match="ArrayList">
        <h2>Managers</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>FullName</th>
                <th>CostOfServices</th>
                <th>Experience</th>
                <th>Phone</th>
                <th>Email</th>
            </tr>
            <xsl:for-each select="item">
                <tr>
                    <td><xsl:value-of select="id"/></td>
                    <td><xsl:value-of select="fullName"/></td>
                    <td><xsl:value-of select="costOfServices"/></td>
                    <td><xsl:value-of select="experience"/></td>
                    <td><xsl:value-of select="phone"/></td>
                    <td><xsl:value-of select="email"/></td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>