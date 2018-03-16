/**
 * Created by WINTAN on 2016/10/21.
 */
$(function () {
    $(".disable").click(function () {
        $.warning("抱歉，功能开发中暂时无法使用！");
    });

    $(".quick .add").click(function () {
    	 $.warning("抱歉，功能开发中暂时无法使用！");
//        var _this = $(this);
//        _this.addClass("hover");
//        data1 = "<select><option value='1'>7</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>";
//        data2 = "<select><option value='1'>7</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>";
//        data3 = "<select><option value='1'>7</option><option value='2'>2</option><option value='3'>3</option><option value='4'>4</option><option value='5'>5</option></select>";
//        quick(data1, data2, data3);
//        $(".popup h2 a").on("click", function () {
//            _this.removeClass("hover");
//        });
    });
    $(".reportCounts ").loading();
    var isFirstLogin = $("#isFirstLogin").val();
    if(isFirstLogin && isFirstLogin == '1'){
    	first();
    }
    //<div>
	//    <p>您单位有<span>1</span>人<a href="">&lt;定点医疗信息变更&gt;</a>信息填写不符合制卡要求、有<span>1</span>批次<span>1</span>人的<a href="">&lt;定点医疗信息变更&gt;</a>业务审核失败。具体情况请详见“申报信息状态查询”。</p>
	//</div>
    $(".reportCounts").show(function(){
    	 //首页温馨提示的查询方法
    	var url =path +"/main/ajaxCounts";
    	 $.ajaxse(
    			 url,
    			 null,
    			 function(data){//success方法
    			 	showContentFunc(data);
    			 }, 
    			 function(){//error方法
    			    //alert("出错了");
    		     }
    	 );
    });
    IEPoint();//IE8以下兼容性提示
   //notice();
});
function showContentFunc(data){
	var divCon = $("<div/>");
	var hCon = $("<h3><span>申报记录</span></h3>");
	var pCon = $("<p/>").append("您单位本月已完成<span>"+data+"</span>次申报，查看详情请点击<a href='"+path+"/page/searchmgr/reportinfosearch/reportInfoSearch.jsp'>&lt;申报状态查询&gt;</a>");
	divCon.append(hCon);
	divCon.append(pCon);
	$(".reportCounts ").loadingClose();
	$(".reportCounts ").append(divCon);
}
function quick(data1, data2, data3) {
    var add = $("<div/>").addClass("content add");
    var form = $("<form/>");
    var ul = $("<ul/>").addClass("ul");
    var quick = $("<li/>").addClass("li");
    var business = $("<li/>").addClass("li business");
    quick.append("<span>快捷列表：</span>");
    var quick_div = $("<div/>").addClass("select div");
    quick_div.append(data1);
    quick_div.append("<div></div>");
    quick.append(quick_div);
    ul.append(quick);
    business.append("<span>业务列表：</span>");
    var select1 = $("<div></div>").addClass("select select1 div");
    select1.append(data2);
    select1.append("<div></div>");
    var select2 = $("<div></div>").addClass("select select2 div");
    select2.append(data3);
    select2.append("<div></div>");
    business.append(select1);
    business.append(select2);
    ul.append(business);
    form.append(ul);
    var button = $("<input>").prop("type","button").val("确认添加").addClass("button1");
    form.append(button);
    add.append(form);
    $.popup({
        title: "编辑快捷入口",
        object: add
    });
    $(".popup .add").toSelect();
}

function notice() {
    $.iframeOpen({
        title: "平台协议",
        src: path +"/notice.jsp"
    });
    var width = 664;
    var height = 370;
    $(".getResult iframe").css({
        width: width+"px",
        height: height+"px"
    });
    $(".getResult").css({
    	width: width+"px",
        "margin-top": -(height + 50) / 2 + "px",
        "margin-left": -width / 2 + "px"
    }).find("h2 a").hide();
}

function iframeOpenClose() {
    $(".getResult").find("h2 a").click();
}

function IEPoint() {
    if(IE() == 10){
        return false;
    }
    var point = $("<div/>").prop("id", "point");
    var bj = $("<div/>").addClass("bj");
    var content = $("<p/>").text("温馨提示：检测到您的浏览器版本过低，如果页面显示不正常，建议您将浏览器升级到最新版本！");
    var button = $("<a/>").text("我知道了").prop("href", "javascript:;");
    content.append(button);
    point.append(bj);
    point.append(content);
    $("body").append(point);
    button.click(function () {
        point.remove();
    });
}

function first() {
    var body = $("body");
    body.on("mousewheel", function (e) {
        e.preventDefault();
    });
    var tiyan = $("<div/>").addClass("tiyan");
    var tiyan_div = $("<div/>");
    var tiyan_button = $("<a/>").prop("href", "javascript:;");
    tiyan_div.append(tiyan_button);
    tiyan.append(tiyan_div);
    body.append(tiyan);
    tiyan_button.click(function(){
    	tiyan.remove();
    	var zhezhao = $("<div/>").addClass("zhezhao");
        body.append(zhezhao);
        var main_1 = $("<div/>").addClass("main_1");
        body.append(main_1);
        var main_1_img = $("<img>").prop("src", path +"/images/main_first_1.png").addClass("main_1_img");
        var main_1_next = $("<a/>").addClass("first_next main_1_next").prop("href", "javascript:;");
        main_1.append(main_1_img);
        main_1.append(main_1_next);
        main_1_next.click(function () {
            main_1_img.remove();
            main_1_next.remove();
            var main_2_img = $("<img>").prop("src", path +"/images/main_first_2.png").addClass("main_2_img");
            var main_2_next = $("<a/>").addClass("first_next main_2_next").prop("href", "javascript:;");
            main_1.append(main_2_img);
            main_1.append(main_2_next);
            main_2_next.click(function () {
                main_2_img.remove();
                main_2_next.remove();
                var main_3_img = $("<img>").prop("src", path +"/images/main_first_3.png").addClass("main_3_img");
                var main_3_next = $("<a/>").addClass("first_next main_3_next").prop("href", "javascript:;");
                main_1.append(main_3_img);
                main_1.append(main_3_next);
                main_3_next.click(function () {
                    main_3_img.remove();
                    main_3_next.remove();
                    main_1.addClass("main_2");
                    var cut_first_1 = $("<img>").prop("src", path +"/images/cut_first_1.png").addClass("cut_first_1");
                    var cut_first_next = $("<a/>").addClass("first_next cut_first_next").prop("href", "javascript:;");
                    main_1.append(cut_first_1);
                    main_1.append(cut_first_next);
                    var cut = $("<div/>").addClass("cut");
                    var nav = $("<img>").prop("src", path +"/images/first_menu.png").addClass("first_menu");
                    var cut_content = $("<img>").prop("src", path +"/images/cut_content.png").addClass("cut_content");
                    cut.append(nav);
                    cut.append(cut_content);
                    body.append(cut);
                    cut_first_next.click(function () {
                        cut_first_1.remove();
                        cut_first_next.remove();
                        main_1.removeClass("main_2");
                        var cut_first_2 = $("<img>").prop("src", path +"/images/cut_first_2.png").addClass("cut_first_2");
                        var lijitiyan = $("<a/>").addClass("lijitiyan").prop("href", "javascript:;");
                        main_1.append(cut_first_2);
                        main_1.append(lijitiyan);
                        lijitiyan.click(function () {
                            main_1.remove();
                            cut.remove();
                            zhezhao.remove();
                        });
                    });
                });
            });
        });
    });
}