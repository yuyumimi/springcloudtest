package com.yuyu.CW;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@XmlRootElement(name = "DATASET")
public class DATASET {

  
  private HEADINFO headinfo;
  
  private List<D> data;

  /**
   * @return the headinfo
   */
  public HEADINFO getHeadinfo() {
    return headinfo;
  }
  @JsonProperty("HEADINFO")
  @XmlElement(name = "HEADINFO")
  public void setHeadinfo(HEADINFO headinfo) {
    this.headinfo = headinfo;
  }

  public List<D> getData() {
    return data;
  }
  @JsonProperty("DATA")
  @XmlElementWrapper(name = "DATA")  
  @XmlElement(name = "D")
  public void setData(List<D> data) {
    this.data = data;
  }

}
