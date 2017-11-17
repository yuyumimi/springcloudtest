package com.capinfo.sior.pay;

import com.capinfo.sior.pay.CMBC.TestCMBCInterface;
import com.capinfo.sior.pay.CW.TestCWInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private PayService payService;

    @Autowired
    private TestCMBCInterface cmbcInterface;

    @Autowired
    private TestCWInterface cwInterface;

    @RequestMapping(value="/testPay",method = RequestMethod.GET,produces="application/json")
    public String testPay(){
        return this.payService.pay();
    }

    @RequestMapping(value="/testPay_xml",method = RequestMethod.GET,produces="application/xml")
    public String testPay2(){
        return this.payService.pay2();
    }
    @RequestMapping(value="/test/pubkey",method = RequestMethod.GET,produces="application/json")
    public String getPubkey(){
        return this.cmbcInterface.getPubkey();
    }

        @RequestMapping(value="/testcw_xml",method = RequestMethod.GET,produces="application/xml")
    public String testCW(){
        return this.cwInterface.getXML();
    }


}
