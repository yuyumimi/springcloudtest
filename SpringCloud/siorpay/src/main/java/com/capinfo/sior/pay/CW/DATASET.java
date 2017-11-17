package com.capinfo.sior.pay.CW;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "DATASET")
public class DATASET {

    private HEADINFO headInfo;

    private List<D> data;
    @XmlElement(name="HEADINFO")
    public HEADINFO getHeadInfo() {
        return headInfo;
    }

    public void setHeadInfo(HEADINFO headInfo) {
        this.headInfo = headInfo;
    }

    @XmlElementWrapper(name = "DATA")
    @XmlElement(name="D")
   public List<D> getData() {
        return data;
    }

    public void setData(List<D> data) {
        this.data = data;
    }
}
