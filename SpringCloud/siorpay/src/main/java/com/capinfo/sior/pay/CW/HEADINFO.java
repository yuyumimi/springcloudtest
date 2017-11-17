package com.capinfo.sior.pay.CW;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class HEADINFO {

    private String callStatus;
    private String errmsg;
    @XmlElement(name="CALLSTATUS")
    public String getCallStatus() {
        return callStatus;
    }

    public void setCallStatus(String callStatus) {
        this.callStatus = callStatus;
    }
    @XmlElement(name="ERRMSG")
    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
