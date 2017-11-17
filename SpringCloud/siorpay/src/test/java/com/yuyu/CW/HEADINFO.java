package com.yuyu.CW;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HEADINFO {
  @JsonProperty("CALLSTATUS")
  private String callstatus;
  @JsonProperty("ERRMSG")
  private String errmsg;

  /**
   * @return the callstatus
   */
  public String getCallstatus() {
    return callstatus;
  }
  @XmlElement(name="CALLSTATUS")
  public void setCallstatus(String callstatus) {
    this.callstatus = callstatus;
  }

  /**
   * @return the errmsg
   */
  public String getErrmsg() {
    return errmsg;
  }
  @XmlElement(name="ERRMSG")
  public void setErrmsg(String errmsg) {
    this.errmsg = errmsg;
  }

}
