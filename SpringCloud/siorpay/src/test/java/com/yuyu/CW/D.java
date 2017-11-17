package com.yuyu.CW;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

public class D {

    private String psnsn;
    private String nd;
    private String je;
    private String status;
    /**
     * @return the psnsn
     */
    public String getPsnsn() {
      return psnsn;
    }
    @JsonProperty("PSNSN")
    @XmlElement(name = "PSNSN")
    public void setPsnsn(String psnsn) {
      this.psnsn = psnsn;
    }
    /**
     * @return the nd
     */
    public String getNd() {
      return nd;
    }
    @JsonProperty("ND")
    @XmlElement(name = "ND")
    public void setNd(String nd) {
      this.nd = nd;
    }
    /**
     * @return the je
     */
    public String getJe() {
      return je;
    }
    @JsonProperty("JE")
    @XmlElement(name = "JE")
    public void setJe(String je) {
      this.je = je;
    }
    /**
     * @return the status
     */
    public String getStatus() {
      return status;
    }
    @JsonProperty("STATUS")
    @XmlElement(name = "STATUS")
    public void setStatus(String status) {
      this.status = status;
    }

}
