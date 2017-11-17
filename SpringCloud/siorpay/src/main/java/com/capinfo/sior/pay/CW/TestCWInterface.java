package com.capinfo.sior.pay.CW;

import com.capinfo.sior.pay.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestCWInterface {

    public String getXML() {
        DATASET data=preData();

        return XMLUtil.parseToXML(data);
    }

    private DATASET preData(){
        DATASET dataSet=new DATASET();

        HEADINFO headinfo=new HEADINFO();

        headinfo.setCallStatus("1成功 0失败");
        headinfo.setErrmsg("0");
        dataSet.setHeadInfo(headinfo);

        D d1=new D();
        d1.setPSNSN("1");
        d1.setJE("1");
        d1.setND("1");
        d1.setSTATUS("1");
        D d2=new D();
        d2.setPSNSN("2");
        d2.setJE("2");
        d2.setND("2");
        d2.setSTATUS("2");

        List<D> data=new ArrayList<>();
        data.add(d1);
        data.add(d2);
        dataSet.setData(data);

    return dataSet;
    }

}
