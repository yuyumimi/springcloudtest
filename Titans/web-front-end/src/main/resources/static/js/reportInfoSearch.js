/**
 * Created by WINTAN on 2016/11/1.
 */	
	
        	
$(function () {
	$(".menu > p a:first").addClass("select");
	var t = 0;
    var table_head_li = $(".list_head").find("li");
    table_head_li.first().css("width", "120px");
    table_head_li.eq(1).css("width", "191px");
    table_head_li.eq(2).css("width", "140px");
    table_head_li.eq(3).css("width", "94px");
    table_head_li.eq(4).css("width", "181px");
    table_head_li.eq(5).css("width", "100px");
    table_head_li.eq(6).css("width", "140px");
    table_head_li.eq(7).css({"width": "162px", "text-align": "center"});
    var content_li = $(".content li");
    content_li.each(function () {
        var p = $(this).find("p");
        p.first().css("width", "120px");
        p.eq(1).css("width", "191px");
        p.eq(2).css("width", "140px");
        p.eq(3).css("width", "94px");
        p.eq(4).css("width", "181px");
        p.eq(5).css("width", "100px");
        p.eq(6).css("width", "140px");
        p.eq(7).css("width", "162px");
    });

    	var start = {
            min: "2001-05-01",
            max: "now",
            choose: function (date) {
                $("#end").data("mindate", date);
            }
        };
        var end = {
            max: "now",
            min: "2001-05-01",
            choose: function (date) {
                $("#start").data("maxdate", date);
            }
        };
        $("#start").wdate(start);
        $("#end").wdate(end);
    $(".screen h3 i").click(function () {
        var date = new Date();
        var month = (date.getMonth() + 1) >= 10 ? (date.getMonth() + 1) : "0" + (date.getMonth() + 1);
        var day = date.getFullYear() + "-" + month + "-" + date.getDate();
        $(this).prev().val(day).prev().prev().val(day);
        searchReportInfo(getSearchJson(1, 1));
    });
    $(".screen h3 i").click();
    $(".screen h3 a").click(function () {
        var text = $(this).text();
        var advanced = $(".advanced");
        var fuzzy = $(".fuzzy");
        if (text == "高级筛选") {
            $("#start").val("");
            $("#end").val("");
            $(this).text("模糊筛选");
            advanced.fadeIn(500);
            fuzzy.fadeOut(500);
            if(t==2){
            	advanced.find("input:text").val("");
            }
        } else {
            $(this).text("高级筛选");
            fuzzy.fadeIn(500);
            advanced.fadeOut(500);
            if(t==1){
            	fuzzy.find("li").each(function(){
            		$(this).find(".all").addClass("check").siblings("a").removeClass("check").siblings("input:hidden").val("");
            	});
            }
        }
    });

    $(".advanced .button1 ").click(function () {
    	t = 1;
        var flag = true;
        $(".advanced").find("input:not(:button)").each(function () {
            var val = $.trim($(this).val());
            if(val!=""){
                flag = false;
                return false;
            }
        });
        if(flag){
            $.warning("请至少输入一项查询条件");
            return false;
        }
        searchReportInfo(getSearchJson(2, 1));
        $(".screen").removeClass("scroll").next().removeClass("scroll");
        $(window).scrollTop(0);
    });

    $(".fuzzy a").click(function () {
    	t = 2;
        $(this).addClass("check").siblings(".check").removeClass("check");
        var data = $(this).data("type");
        if ($(this).parent().attr("id") == "1") {
            $("#start").val("");
            $("#end").val("");
            $("#dateSelect").val(data);
        } else if ($(this).parent().attr("id") == "2") {
            $("#dataType").val(data);
        } else {
            $("#reportStatus").val(data);
        }
        searchReportInfo(getSearchJson(1, 1));
        $(".screen").removeClass("scroll").next().removeClass("scroll");
        $(window).scrollTop(0);
    });

    $(window).scroll(function () {
        var top = $(this).scrollTop();
        var screen = $(".screen");
        if (top > 300) {
            screen.addClass("scroll").next().addClass("scroll");
        } else {
            screen.removeClass("scroll").next().removeClass("scroll");
        }
    });

    //查看详情
    $(".list .content").on("click", ".detail", function () {
        var json = $(this).data("item");
        var postdatajson = {
        	'selectedPage': 1,
            'selectedPageSize': 5,
            'reportId': json.id,
            'dataType': json.dataType
        };

        var url = path + "/search/reportinfosearch/getDataDetail";
        var postdata = JSON.stringify(postdatajson);

        $.ajaxse(
            url,
            postdata,
            function (rtdata) {//success方法
            	if(json.dataType == "DATA_MONTHLY_PAY"){
            		//工资申报业务显示结构与其他申报不同，单独处理
            		var data = rtdata.dataDetail;
            		monthlyPayDetail(data[0].govFlag,rtdata,json.id,json.dataType);
            	}else{
            		var data = getDataDetailJsonByDataType(json.dataType, json, rtdata);
                    var html = "";
                    $.each(data, function (i, item) {
                        html += "<li><strong>" + i + "</strong>" + "<span title='" + item + "'>" + item + "</span></li>";
                    });
                    detail(html);
            	}
            },
            function () {//error方法
            }
        );
    });
    //处理结果按钮
    $(".list .content").on("click", ".result", function () {
    	var srcPath = "";
    	if($(this).hasClass("disable")){
    		return false;
    	}
        var data = $(this).data("item");
        var width = 666;
        var height = 351;
        if(data.dataType == "DATA_NORMAL_CUT"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_CutResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        }else if(data.dataType == "DATA_CHANGE_HOS"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_HospitalResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        }else if(data.dataType == "DATA_MONTHLY_PAY"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_MonthlyPayResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        	width = 900;
        	height = 400;
        }else if(data.dataType == "DATA_ENTINFO_UPDATE"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_EntInfoUpdateResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        }else if(data.dataType == "DATA_INDINFO_UPDATE"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_IndInfoUpdateResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        }else if(data.dataType == "DATA_IND_ADD"){
        	srcPath = path + "/page/searchmgr/reportinfosearch/reportInfoSearch_IndAddResult.jsp?json=" + encodeURI(encodeURI(JSON.stringify(data)));
        }
        
        $.iframeOpen({
            title: "申报处理结果",
            src: srcPath
        });
        $(".getResult iframe").css({
            width: width+"px",
            height: height+"px"
        });
        $(".getResult").css({
            "margin-top": -(height + 50) / 2 + "px",
            "margin-left": -width / 2 + "px"
        });
    });
    $(".import").click(function () {
        var list = $(".list");
        if (list.css("display") == "none") {
            $.warning("暂无数据<br>请选择状态或筛选条件后再导出Excel文件");
            return false;
        }
    });
    $(".faq").click(function() {
    	$.iframeOpen({
            title: "操作说明",
            src: path+"/page/searchmgr/reportinfosearch/reportInfoSearchFAQ.jsp"
        });
    	var width = 611;
        var height = 355;
        $(".getResult iframe").css({
            width: width+"px",
            height: height+"px"
        });
        $(".getResult").css({
            "margin-top": -(height + 50) / 2 + "px",
            "margin-left": -width / 2 + "px"
        });
	});
    
    
    
    var data = {
            "姓名：": "欧阳雨露",
            "性别：": "女",
            "民族：": "汉",
            "出生日期：": "2014-08-08",
            "公民身份证号码：": "110110201707071111",
            "联系人姓名：": "欧阳田",
            "img": path + "images/pic.jpg"
        };
        xincanbao(data);
});

function detail(html) {
    var det = $("<ul/>").addClass("content");
    det.append(html);
    $.popup({
        title: "申报查看详情",
        object: det
    });
    var popup = $(".popup");
    popup.addClass("popupDetail").css("margin-top", -popup.height() / 2 + "px");
}
// 根据申报类别生成“查看详情”json
function getDataDetailJsonByDataType(dataType, json, rtdata) {
    var data;
    if (dataType == 'DATA_CHANGE_HOS') {
        data = {
            "申报流水号：": json.id,
            "姓名：": json.personName,
            "身份证号：": json.idCard,
            "定点医疗机构1：": rtdata.dataDetail.hosp1Display,
            "定点医疗机构2：": rtdata.dataDetail.hosp2Display,
            "定点医疗机构3：": rtdata.dataDetail.hosp3Display,
            "定点医疗机构4：": rtdata.dataDetail.hosp4Display,
            "定点医疗机构5：": rtdata.dataDetail.hosp5Display,
            "创建时间：": rtdata.dataDetail.createTimeDisplay
        };
    } else if (dataType == 'DATA_NORMAL_CUT') {
        data = {
            "申报流水号：": json.id,
            "姓名：": json.personName,
            "身份证号：": json.idCard,
            "停止缴费险种：": rtdata.dataDetail.insuranceTypeDisplay,
            "四险缴费截止月份：": rtdata.dataDetail.fourStopdateDisplay,
            "停止缴费原因：": rtdata.dataDetail.chgReasonDisplay,
            "创建时间：": rtdata.dataDetail.createTimeDisplay
        };
    } else if (dataType == 'DATA_ENTINFO_UPDATE'){
    	data = {
    			"申报流水号：": json.id,
    	};
    	var obj = rtdata.dataDetail;
    	for(var i in obj){
    		data[obj[i].fieldName+"："] = obj[i].newValue;
    	}
    	
    } else if (dataType == 'DATA_INDINFO_UPDATE'){
    	data = {
    			"申报流水号：": json.id,
    	};
    	var obj = rtdata.dataDetail;
    	for(var i in obj){
    		data[obj[i].fieldName+"："] = obj[i].newValue;
    	}
    	
    } else if (dataType == 'DATA_IND_ADD'){
    	var obj = rtdata.dataDetail;
    	var psnOffiFlag = obj.psnOffiFlag;
    	var psnOffiFlagNew = obj.psnOffiFlagNew;
    	data = {
    		"姓名：": obj.name,	
    		"公民身份证号码：": obj.idCard,	
    		"缴费人员类别：": obj.feePsnType,	
    		"月均工资收入：": obj.avgSalary,	
    		"参加险种：": obj.insuranceType,	
    		//"医保个人缴费原因：": obj.insuranceType,	
    		"医疗参保人员类别：": obj.medPsnType,	
    		"增员原因：": obj.indAddReason,	
    		"公费医疗补充医疗保险补助标识：": obj.psnOffiFlagNew,	
    	};
    	if(psnOffiFlagNew != "" && psnOffiFlagNew !="0" && psnOffiFlagNew !=null){
    		data["公费医疗补充医疗保险补助缴费方式："] = obj.psnOffiFeeType;
    	}
    	if(psnOffiFlag==1){
    		data["用工形式："] = obj.employmentType;
    		data["退休时认定出生日期："] = obj.dateBirthRetire;	
		    data["参加机关养老保险时间："] = obj.govInstPensInsurDate;	
            data["职业年金虚账记账标识："] = obj.occuPensVirFlag==1?"虚账":"实账";
            data["机关事业养老工资："] = obj.govInstPensSalary;
            if(obj.salaryMap){
            	data["<li><strong>机关事业补缴工资：</strong>"] = showSalaryMap(obj.salaryMap);
            }
            data["特殊待遇标识："] = obj.specTreatFlag;
            data["2014年9月工资分类："] = obj.salaryGrade201409;
            data["2014年9月警衔："] = obj.fnlInfoCopRank201409;
            data["2014年9月岗位："] = obj.fnlInfoPost201409;
            data["2014年9月前工作年限："] = obj.yearWork201409;
    	}
    	var salaryGrade201409 = obj.salaryGrade201409;
    	showOptionBySalaryGrade(salaryGrade201409,data,obj);
    }
    return data;
}
function showSalaryMap(salaryMap){
	var html = "<span>";
	
    $.each(salaryMap, function (i, childitem) {
        html += "<i>" + i + "：</i>" + childitem + "<br>";
    });
    html += "</span></li>";
    return html;
    
}

function showOptionBySalaryGrade(salaryGrade201409,data,obj){
	if(salaryGrade201409== "" || salaryGrade201409 == null){
		return false;
	}
	if(salaryGrade201409 == "机关公务员"){
		var str = obj.dutyBeforeReform;
		//判断公务员职务是否为空   不为空拼接后展示
		if(obj.pubOfficPos){
			str+="、"+obj.pubOfficPos;
		}
		data["2014年9月享受工资职级待遇："] = str;
		data["2014年9月级别："] = obj.rankBeforeReform;
		data["2014年9月级别工资标准档次："] = obj.salGrdBeforeReform;
	}else if(salaryGrade201409 == "机关技术工人" || salaryGrade201409 == "机关普通工人"){
		data["2014年9月技术等级："] = obj.rankBeforeReform;
		data["2014年9月岗位工资标准："] = obj.salGrdBeforeReform;
	}else if(salaryGrade201409 == "事业管理人员" || salaryGrade201409 == "事业专业技术人员" || salaryGrade201409 == "事业工勤人员"){
		data["2014年9月岗位："] = obj.dutyBeforeReform;
		data["2014年9月薪级："] = obj.salGrdBeforeReform;
	}
}

//type 1为高级筛选  2为模糊筛选  selectedPage 第几页
function getSearchJson(type, selectedPage) {
    $("#selectedTyep").val(type);
    var json = {};
    if (type == 1) {
        json = {
            'startDate': $('#start').val(),
            'endDate': $('#end').val(),
            'dataType': $('#dataType').val(),
            'reportStatus': $('#reportStatus').val(),
            'selectedDate': $('#selectedDate').val(),
            'selectedPage': selectedPage,
            'selectedTyep': type
        };
    } else {
        json = {
            'idCard': $('#idCard').val(),
            'startDate': $('#start').val(),
            'endDate': $('#end').val(),
            'reportId': $('#reportId').val(),
            'personName': $('#personName').val(),
            'selectedPage': selectedPage,
            'selectedTyep': type
        };
    }
    var postdata = JSON.stringify(json);
    return postdata;
}

function searchReportInfo(postdata) {
    var url = path + "/search/reportinfosearch/search";
    $.ajaxse(
        url,
        postdata,
        function (data) {//success方法
            var infoReportList = data.infoReportList;
        	if (data.infoReportList!="") {
        		$(".list,.page").show();
        	}else{
        		$(".page").hide();
        	}
            var pageCount = data.pageCount;
            $(".page").off();// 有用吗？
            $(".page").createPage({
                pageCount: pageCount,
                current: 1,
                backFn: function (p) {
                    var pagedata = getSearchJson($("#selectedTyep").val(), p);
                    var pageurl = path + "/search/reportinfosearch/search";
                    $.ajaxse(
                        pageurl,
                        pagedata,
                        function (data) {//success方法
                            var infoReportList = data.infoReportList;
                            list(infoReportList);
                            $(window).scrollTop(0);
                        },
                        function () {//error方法
                        }
                    );
                }
            });
            list(infoReportList);
        },
        function () {//error方法
        }
    );
}
 function replaceNull(str)
 {
	if(str == null){
		return "--";
	}else{
		return str;
	}
 }
function list(data) {
    var html = '';
    $.each(data, function (i, item) {
        html += '<li>';
        html += '<p style="width:120px">' + replaceNull(item.personName) + '</p>';
        html += '<p style="width:191px">' + replaceNull(item.idCard) + '</p>';
        html += '<p style="width:140px">' + item.id + '</p>';
        var status = item.status;
        if (status == 0) {
            html += '<p class="save" style="width:94px"><strong>未提交</strong></p>';
        } else if (status == 1) {
            html += '<p class="doing" style="width:94px"><strong>处理中</strong></p>';
        } else if (status == 2) {
            html += '<p class="done" style="width:94px"><strong>处理结束</strong></p>';
        } else{
        	html += '<p class="done" style="width:94px">&nbsp</p>';
        }
        html += '<p style="width:181px"><span>' + item.dataTypeDisplay + '</span></p>';
        var reportFlag = item.reportFlag;
        if (reportFlag == 11) {
            html += '<p class="success" style="width:100px"><strong>导入成功</strong></p>';
        } else if (reportFlag == 10) {
            html += '<p class="fail" style="width:100px"><strong>导入不成功</strong></p>';
        } else if (reportFlag == 12) {
            html += '<p class="fail" style="width:100px"><strong>导入部分成功</strong></p>';
        } else {
            html += '<p class="fail" style="width:100px"><strong>&nbsp</strong></p>';
        }
        if (status == 0) {
        	html += '<p style="width:140px">&nbsp</p>';
        }else{
        	html += '<p style="width:140px">' + item.createDateDisplay + '</p>';
        }
        
        html += '<p style="width:162px">';
        html += '<a href="javascript:;" class="detail" data-item=\'' + JSON.stringify(item) + '\'>查看详情</a>';
        if (status == 0){
        	html += '<a href="javascript:;" class="result disable"  data-item=\'' + JSON.stringify(item) + '\'>处理结果</a>';
        } else {
        	html += '<a href="javascript:;" class="result"  data-item=\'' + JSON.stringify(item) + '\'>处理结果</a>';
        }
        html += '</p>';
        html += '</li>';
    });
    if(html == ""){
    	$(".error").show();
    	$(".list").hide();
    	$(".content").html("");
    } else {
    	$(".error").hide();
    	$(".content").html(html);
    }
}

/*var data = {
"申报流水号：": "201480624304918",
"邮政编码：":"100015",
"单位经办人姓名：":"欧阳雨露"
};
biangeng(data);*/
function biangeng(data) {
    var html = "";
    $.each(data, function (i, item) {
        html += "<li><strong>" + i + "</strong>" + "<span title='" + item + "'>" + item + "</span></li>";
    });
    var det = $("<ul/>").addClass("content biangeng");
    det.append(html);
    $.popup({
        title: "申报处理结果-单位信息变更",
        object: det
    });
    var popup = $(".popup");
    popup.addClass("popupDetail").css("margin-top", -popup.height() / 2 + "px");
}

function monthlyPayDetail(type, data, id, dataType) {
    var det = $("<div/>").addClass("content");
    var table = $("<table/>");
    var thead = $("<thead/>");
    if(type == "1"){
        thead.append($("<tr/>").append("<th>姓名</th><th>身份证号</th><th>网上申报机关事业<br>养老上年月均工资</th><th>网上申报职工上年月均工资</th>"));
    } else {
        thead.append($("<tr/>").append("<th>姓名</th><th>身份证号</th><th>网上申报职工上年月均工资</th>"));
    }
    table.append(thead);
    var tbody = $("<tbody/>");
    tbody.html(tPage(type, data.dataDetail));
    table.append(tbody);
    det.append(table);
    var page = $("<div/>").addClass("page");
    det.append(page);
    $.popup({
        title: "申报处理结果-职工上年月均工资申报",
        object: det
    });
    var popup = $(".popup");
    popup.addClass("popupTable").css("margin-top", -popup.height() / 2 + "px");
    page.createPage({
        pageCount:data.pageCount,
        current:1,
        backFn:function(p){
        	var json = $(this).data("item");
            var postdatajson = {
            	'selectedPage': p,
                'reportId': id,
                'dataType': dataType
            };
            var pageurl = path + "/search/reportinfosearch/getDataDetail";
            var postdata = JSON.stringify(postdatajson);
            $.ajaxse(
                pageurl,
                postdata,
                function (pageData) {//success方法
                	var data1 = pageData.dataDetail;
            		tbody.html(tPage(type, data1));
                },
                function () {//error方法
                }
            );
        }
    });
}

function tPage(type, data) {
    var html = "";
    if(type == 1){
        $.each(data, function (i, item) {
            html += "<tr><td>" + item["name"] + "</td>";
            html += "<td>" + item["idCard"] + "</td>";
            html += "<td><span>" + item["govSalary"] + "</span></td>";
            html += "<td><span>" + item["salary"] + "</span></td></tr>";
        });
    } else {
        $.each(data, function (i, item) {
            html += "<tr><td>" + item["name"] + "</td>";
            html += "<td>" + item["idCard"] + "</td>";
            html += "<td><span>" + item["salary"] + "</span></td></tr>";
        });
    }

    return html;
}

function xincanbao(data, name) {
    var html = "";
    $.each(data, function (i, item) {
        if(i == "img"){
            html += "<li class='img'><img src='" + data.img + "'></li>";
            return true;
        }
        if(i == "bujiaogongzi"){
            html += "<li><strong>" + data.bujiaogongzi.name + "</strong>" + "<span>";
            $.each(data.bujiaogongzi.data, function (i, childitem) {
                html += "<i>" + i + "</i>" + childitem + "<br>";
            });
            html += "</span></li>";
            return true;
        }
        html += "<li><strong>" + i + "</strong>" + "<span title='" + item + "'>" + item + "</span></li>";
    });
    var det = $("<ul/>").addClass("content");
    det.append(html);
    $.popup({
        title: name,
        object: det
    });
    var popup = $(".popup");
    popup.addClass("popupDetail").css("margin-top", -popup.height() / 2 + "px");
}