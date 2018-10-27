package com.skyobserver.model.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MarketingAirline {

    @JacksonXmlProperty(localName = "Code", isAttribute = true)
    private String code;

    @JacksonXmlProperty(localName = "CodeContext", isAttribute = true)
    private String codeContext;

    @JacksonXmlProperty(localName = "CompanyShortName", isAttribute = true)
    private String companyShortName;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeContext() {
        return codeContext;
    }

    public void setCodeContext(String codeContext) {
        this.codeContext = codeContext;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    @Override
    public String toString() {
        return "MarketingAirline{" +
                "code='" + code + '\'' +
                ", codeContext='" + codeContext + '\'' +
                ", companyShortName='" + companyShortName + '\'' +
                '}';
    }
}
