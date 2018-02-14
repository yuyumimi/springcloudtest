/**
 * Created by WINTAN on 2016/10/21.
 */
(function () {
    $.popup = function (obj, callback){
    	if($("#cover").length>0){
    		$("#cover").remove();
    	}
    	if($(".popup").length>0){
    		$(".popup").remove();
    	}
        var body = $("body");
        var cover = $("<div/>").prop("id", "cover");
        body.append(cover);
        cover.fadeIn();
        var pop = $("<div/>").addClass("popup");
        var pop_h = $("<h2/>");
        pop_h.html("<span>"+obj.title+"</span>");
        var close = $("<a/>");
        close.prop("href","javascript:;");
        pop_h.append(close);
        pop.append(pop_h);
        pop.append(obj.object);
        body.append(pop);
        pop.css("margin-top", -pop.height() / 2 + "px");
        pop.fadeIn(300);
        close.off("click").on("click",function () {
            pop.fadeOut(300,function () {
                $(this).remove();
            });
            cover.fadeOut(300,function () {
                $(this).remove();
            });
            if(callback!= null || callback != undefined){
            	callback();
            }
        });
    };

    $.loading = function (){
        var loading = $("<div/>").addClass("content loading");
        var animate = $("<div/>").addClass("animate");
        var left = $("<div/>").addClass("left").append("<div/>");
        var right = $("<div/>").addClass("right").append("<div/>");
        if(IE() <= 9){
            left.children().css("border-color", "#0b9cd2");
            right.children().css("border-color", "#0b9cd2");
        }
        var img = $("<img/>").prop("src", path+"/images/logo2.png").addClass("logo");
        var text = "数据正在加载中";
        var tishi = $("<p/>").text(text);
        animate.append(left);
        animate.append(right);
        animate.append(img);
        loading.append(animate);
        loading.append(tishi);
        $.popup({
            title: "系统提示",
            object: loading
        });
        loading.siblings("h2").find("a").hide();
        loading.parent().css("margin-top", "-183px");
        var t = 0;
        window.setInterval(function () {
            if(t == 6){
                t = 0;
                tishi.text(text);
                return false;
            }
            t++;
            tishi.append(".");
        },500);
    };

    $.loadingClose = function () {
        var loading = $(".popup .loading");
        if(loading.length > 0){
            loading.prev().find("a").click();
        }
    };

    $.fn.loading = function (width) {
        $(this).css("position", "relative");
        var loading = $("<div/>").addClass("partLoad");
        var animate = $("<div/>").addClass("animate");
        var left = $("<div/>").addClass("left").append("<div/>");
        var right = $("<div/>").addClass("right").append("<div/>");
        if(IE() <= 9){
            left.children().css("border-color", "#0b9cd2");
            right.children().css("border-color", "#0b9cd2");
        }
        var img = $("<div/>").addClass("logo");
        var text = "数据正在加载中";
        var tishi = $("<p/>").text(text);
        animate.append(left);
        animate.append(right);
        animate.append(img);
        loading.append(animate);
        loading.append(tishi);
        $(this).append(loading);
        if(width){
            var border_width = width / 2;
            animate.css({
                "width": width + "px",
                "height": width + "px"
            });
            animate.children("div:lt(2)").css({
                "width": border_width + "px",
                "height": width + "px"
            }).children().css("border-width", border_width + "px");
        }
        var t = 0;
        window.setInterval(function () {
            if(t == 6){
                t = 0;
                tishi.text(text);
                return false;
            }
            t++;
            tishi.append(".");
        },500);
    };

    $.fn.loadingClose = function () {
        $(this).find(".partLoad").remove();
    };

    $.warning = function (text, callback) {
        var warning = $("<div/>").addClass("content warning");
        var div = $("<div/>");
        var img = $("<img/>").prop("src", path+"/images/warning.png");
        var p = $("<p/>").html(text);
        div.append(img);
        div.append(p);
        warning.append(div);
        $.popup({
            title: "系统提示",
            object: warning
        }, callback);
        warning.parent().css("margin-top", "-200px");
    };

    $.success = function (text) {
        var warning = $("<div/>").addClass("content warning");
        var div = $("<div/>");
        var img = $("<img/>").prop("src", path+"/images/dispose_success.png");
        var p = $("<p/>").text(text);
        div.append(img);
        div.append(p);
        warning.append(div);
        $.popup({
            title: "系统提示",
            object: warning
        });
    };
    var zIndex = 1;
    $.fn.toSelect = function () {
        var _this = $(this);
        var select = _this.find("select");
        var document_height = $("body").height();
        var select_top = 0;
        zIndex = select.length;
        select.each(function () {
            $(this).siblings("span,div,ul,input").remove();
            if(IE() <= 7){
                $(this).parent().css("z-index", zIndex);
            }
            zIndex--;
            var html = "";
            select_top = $(this).parent().offset().top;
            if(document_height - select_top < 250){
                html = "<div class='list up' style='display: none;'>";
            } else {
                html = "<div class='list down' style='display: none;'>";
            }
            html += "<ul>";
            $(this).find("option").each(function () {
                html += "<li><input type='hidden' value='"+$(this).val()+"'>"+$(this).text()+"</li>";
            });
            html += "</ul>";
            html += "</div>";
            var select_val = $(this).val();
            if(select_val == ""){
                $(this).after("<span>"+$(this).find("option:selected").text()+"</span>"+html);
            } else {
                $(this).after("<span class='click'>"+$(this).find("option:selected").text()+"</span>"+html);
            }
            var height = $(this).siblings(".list").find("ul li").length * 35;
            if(height > 200){
                var list = $(this).siblings(".list");
                list.append("<div><div></div></div>");
            }
        });
        var list = select.siblings(".list");
        var length = 0;
        function handleMouseheel(event){
            if(IE() > 8) {
                event.preventDefault();
            } else {
                window.event.returnValue = false;
            }
            event = EventUtil.getEvent(event);
            var delta = EventUtil.getWheelDelta(event);
            var div = list.children("div");
            var ul = list.find("ul");
            var ul_height = length * 35;
            var top = parseInt(ul.css("margin-top"));
            top = (top > 0 ? 0 : top) + delta;
            top = top > 0 ? 0 : -top + 200 > ul_height ? -(ul_height - 200) : top;
            var hidden_height = ul_height - 200;
            var scroll_height = 100;
            var scroll_top = -scroll_height * (top / hidden_height);
            ul.css("margin-top", top + "px");
            div.find("div").css("margin-top", scroll_top);
        }
        var startY, endY, mouse = false, scroll_div_top;
        list.hover(function () {
            var div = $(this).find("div");
            if(div.length > 0){
               EventUtil.addHandler(document,"mousewheel",handleMouseheel);
               EventUtil.addHandler(document,"DOMMouseScroll",handleMouseheel);
               div.find("div").mousedown(function (e) {
                   startY = e.clientY;
                   mouse = true;
               });
           }
        }, function () {
            EventUtil.removeHandler(document, "mousewheel",handleMouseheel);
            EventUtil.removeHandler(document, "DOMMouseScroll",handleMouseheel);
        });
        $(window).mouseup(function () {
            mouse = false;
        });
        $(window).mousemove(function (e) {
            if(mouse){
                endY = e.clientY;
                scroll_div_top = scroll_div_top + (endY - startY);
                startY = endY;
                scroll_div_top = scroll_div_top < 0 ? 0 : scroll_div_top > 100 ? 100 : scroll_div_top;
                list.children("div").find("div").css("margin-top", scroll_div_top + "px");
                list.find("ul").css("margin-top", -(length * 35 - 200) * (scroll_div_top / 100));
            }
        });
        select.siblings("span").click(function () {
            document_height = $("body").height();
            list = $(this).siblings(".list");
            select_top = $(this).parent().offset().top;
            if(document_height - select_top < list.height()){
                list.removeClass("down").addClass("up");
            } else {
                list.removeClass("up").addClass("down");
            }
            var parent = $(this).parent();
            if(list.find("div").length > 0){
                length = list.find("li").length;
                var div = list.children("div").find("div");
                scroll_div_top = div.position().top;
                scroll_div_top = scroll_div_top > 0 ? 0 : scroll_div_top;
            }
            $("body").find("select").siblings(".list").slideUp();
            if (list.css("display")=="none"){
                list.slideDown();
                parent.addClass("click");
            } else {
                list.slideUp();
                if(list.find("div").length > 0){
                    var div = list.children("div").find("div");
                    div.css("margin-top", 0);
                }
                var value = $(this).siblings("[type=hidden]").val();
                var val = $(this).siblings("select").val();
                if(value == val){
                    parent.removeClass("click");
                }
            }
        });
        select.siblings(".list").find("li").click(function () {
            var val = $(this).find("input").val();
            var span = $(this).parents(".list").siblings("span");
            span.text($(this).text());
            if(val!=""){
                span.addClass("click");
            } else {
                $(this).parents(".select").removeClass("click");
                span.removeClass("click");
            }
            $(this).parents(".list").slideUp();
            span.siblings("select").val(val);
        });
        $("body").click(function (e) {
            var select = $(".select");
            select.each(function () {
                var list = $(this).find(".list");
                if(list.css("display")=="block"){
                    var mouseLeft = e.pageX;
                    var mouseTop = e.pageY;
                    var left = list.offset().left,
                        right = list.width()+2+left,
                        top = list.offset().top,
                        bottom = 0;
                    if(list.hasClass("up")){
                        top += 2;
                        if(list.height() > 10){
                        	bottom = top + list.height()+2;
                        } else {
                        	bottom = top + $(this).height()+2;
                        }
                    }else if(list.hasClass("down")){
                        bottom = list.height()+2+top;
                        top -= $(this).height()+2;
                    }
                    if (mouseLeft < left || mouseLeft > right || mouseTop < top || mouseTop > bottom){
                    	list.slideUp();
                        var value = $(this).find("[type=hidden]").val();
                        var val = $(this).find("select").val();
                        if(value == val){
                            $(this).removeClass("click");
                        }
                    }
                }
            });
        });
    };

    $.iframeOpen=function (obj) {
        var body = $("body");
        var cover = $("<div/>").prop("id", "cover");
        body.append(cover);
        cover.fadeIn();
        var pop = $("<div/>").addClass("getResult");
        var pop_h = $("<h2/>");
        pop_h.html("<span>"+obj.title+"</span>");
        var close = $("<a/>");
        close.prop("href","javascript:;");
        pop_h.append(close);
        pop.append(pop_h);
        var iframe = $("<iframe/>").prop("src", obj.src);
        pop.append(iframe);
        body.append(pop);
        pop.css({
            "margin-top": -pop.height() / 2 + "px",
            "margin-left": -pop.width() / 2 + "px"
        });
        pop.fadeIn();
        close.off("click").on("click",function () {
            pop.fadeOut(function () {
                $(this).remove();
            });
            cover.fadeOut(function () {
                $(this).remove();
            });
        });
    };

    var ms = {
        init:function(obj,args){
            return (function(){
                ms.fillHtml(obj,args);
                ms.bindEvent(obj,args);
            })();
        },
        //填充html
        fillHtml:function(obj,args){
            return (function(){
                obj.empty();
                //上一页
                if(args.current > 1){
                    obj.append('<a href="javascript:;" class="prevPage">上一页</a>');
                }else{
                    obj.remove('.prevPage');
                    obj.append('<span class="disabled">上一页</span>');
                }
                //中间页码
                if(args.current != 1 && args.current >= 4 && args.pageCount != 4){
                    obj.append('<a href="javascript:;" class="tcdNumber">'+1+'</a>');
                }
                if(args.current-2 > 2 && args.current <= args.pageCount && args.pageCount > 5){
                    obj.append('<span>...</span>');
                }
                var start = args.current -2,end = args.current+2;
                if((start > 1 && args.current < 4)||args.current == 1){
                    end++;
                }
                if(args.current > args.pageCount-4 && args.current >= args.pageCount){
                    start--;
                }
                for (;start <= end; start++) {
                    if(start <= args.pageCount && start >= 1){
                        if(start != args.current){
                            obj.append('<a href="javascript:;" class="tcdNumber">'+ start +'</a>');
                        }else{
                            obj.append('<span class="current">'+ start +'</span>');
                        }
                    }
                }
                if(args.current + 2 < args.pageCount - 1 && args.current >= 1 && args.pageCount > 5){
                    obj.append('<span>...</span>');
                }
                if(args.current != args.pageCount && args.current < args.pageCount -2  && args.pageCount != 4){
                    obj.append('<a href="javascript:;" class="tcdNumber">'+args.pageCount+'</a>');
                }
                //下一页
                if(args.current < args.pageCount){
                    obj.append('<a href="javascript:;" class="nextPage">下一页</a>');
                }else{
                    obj.remove('.nextPage');
                    obj.append('<span class="disabled">下一页</span>');
                }
            })();
        },
        //绑定事件
        bindEvent:function(obj,args){
            return (function(){
            	obj.off("click");
                obj.on("click","a.tcdNumber",function(){
                    var current = parseInt($(this).text());
                    ms.fillHtml(obj,{"current":current,"pageCount":args.pageCount});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current);
                    }
                });
                //上一页
                obj.on("click","a.prevPage",function(){
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj,{"current":current-1,"pageCount":args.pageCount});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current-1);
                    }
                });
                //下一页
                obj.on("click","a.nextPage",function(){
                    var current = parseInt(obj.children("span.current").text());
                    ms.fillHtml(obj,{"current":current+1,"pageCount":args.pageCount});
                    if(typeof(args.backFn)=="function"){
                        args.backFn(current+1);
                    }
                });
            })();
        }
    };
    $.page = function(num1, num2){
    	if(num1 >= num2){
    		$(".page").hide();
    	}
    };
    $.fn.createPage = function(options){
        var args = $.extend({
            pageCount : 10,
            current : 1,
            backFn : function(p){
            	console.log(p)
            }
        },options);
        ms.init(this,args);
    };
    if(IE() <= 9){
        $("#header .nav").hover(function () {
            $(this).find("ul").css({"top":"45px", "_top": "65px"});
        },function () {
            $(this).find("ul").css({"top":" -420px", "_top": " -420px"});
        });
        $("#header li").hover(function () {
            $(this).css({"background-color":"#0075ff"}).children("a").css("color", "#fff");
            $(this).children("dl").css({"left":"265px"});
        },function () {
            $(this).css({"background-color":"#fff"}).children("a").css("color", "#007fd5");
            $(this).children("dl").css({"left":"-510px"});
        });
        $("#header li:last-child").height("72px");
    }
    $("#header dl a").hover(function () {
        var hr = $(this).parents("li").find("hr");
        hr.show();
        var span = $(this).find("span");
        var width = span.width();
        var bottom = span.position().top + 20;
        var left = span.position().left;
        hr.stop().animate({"width": width + "px", "left": left + "px", "top": bottom + "px"},50);
    }, function (e) {
        $(this).parents("li").find("hr").hide();
    });
})($);
$(function() {
	$(".message").click(function(){
    	$.iframeOpen({
            title: "使用须知",
            src: path +"/noticeForUse.jsp"
        });
    	var width = 664;
        var height = 320;
        $(".getResult iframe").css({
            width: width+"px",
            height: height+"px"
        });
        $(".getResult").css({
        	width: width+"px",
            "margin-top": -(height + 50) / 2 + "px",
            "margin-left": -width / 2 + "px"
        });
    });
	$("input").on("change", function(){
		var val = $(this).val();
		var trimVal = $.trim(val);
		if(val != trimVal){
			$(this).val(trimVal);
		}
	});
	$(".button .submit").click(function () {
        var _this = $(this);
        if(!_this.hasClass("click")){
            _this.addClass("click").find("a").html('<span>提交中</span>');
        }
        setTimeout(function () {
            _this.removeClass("click").find("a").html('<span>提<i></i>交</span>');
        }, 5000);
    });
	searchDBClass();
});

function searchDBClass(){
	if($(".searchDBClass").length <= 0){
		return false;
	}
	var url = path + "/main/searchDBTime";
    $.ajaxse(
            url,
            null,
            function (data) {//success方法
                $(".searchDBClass").append(data.rtnStr);
            },
            function (data) {//error方法
                //alert("111");
            }
    );
}

function overtime(url) {
    if($(".popup").length>0){
        $(".popup h2 a").click();
    }
    $.warning("抱歉，您长时间未操作系统已自动退出，请重新登录！");
    var button = $("<a/>").addClass("button1").html("戳我重新登录").prop("href", url);
    $(".warning").addClass("overtime").find("div").append(button).prev().find("a").hide();
}
function IE() {
    if(navigator.appName == "Microsoft Internet Explorer"){
        if(navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE6.0"){
            return 6;
        }
        if(navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE7.0"){
            return 7;
        }
        if(navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE8.0"){
            return 8;
        }
        if(navigator.appVersion .split(";")[1].replace(/[ ]/g,"")=="MSIE9.0"){
            return 9;
        }
        return 10;
    }
    return 10;
}
//身份证号码格式验证
function validateIdCard(idCard) {
    //15位和18位身份证号码的正则表达式  
    var regIdCard = /^(^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$)|(^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[Xx])$)$/;
    //如果通过该验证，说明身份证格式正确，但准确性还需计算  
    if (regIdCard.test(idCard)) {
        if (idCard.length == 18) {
            var idCardWi = new Array(7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2); //将前17位加权因子保存在数组里  
            var idCardY = new Array(1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2); //这是除以11后，可能产生的11位余数、验证码，也保存成数组  
            var idCardWiSum = 0; //用来保存前17位各自乖以加权因子后的总和  
            for (var i = 0; i < 17; i++) {
                idCardWiSum += idCard.substring(i, i + 1) * idCardWi[i];
            }
            var idCardMod = idCardWiSum % 11;//计算出校验码所在数组的位置  
            var idCardLast = idCard.substring(17);//得到最后一位身份证号码  
            //如果等于2，则说明校验码是10，身份证号码最后一位应该是X  
            if (idCardMod == 2) {
                if (idCardLast == "X" || idCardLast == "x") {
                    return true;
                    //alert("恭喜通过验证啦！");  
                } else {
                    return false;
                    //alert("身份证号码错误！");  
                }
            } else {
                //用计算出的验证码与最后一位身份证号码匹配，如果一致，说明通过，否则是无效的身份证号码  
                if (idCardLast == idCardY[idCardMod]) {
                    //alert("恭喜通过验证啦！");  
                    return true;
                } else {
                    return false;
                    //alert("身份证号码错误！");  
                }
            }
        }
    } else {
        //alert("身份证格式不正确!");  
        return false;
    }
}

var EventUtil = {
	    //视情况分别使用DOM0级方法、DOM2级方法或IE方法来添加事件
	    addHandler: function(element, type, handler){
	        if (element.addEventListener){
	            element.addEventListener(type, handler, false);
	        } else if (element.attachEvent){
	            element.attachEvent("on" + type, handler);
	        } else {
	            element["on" + type] = handler;
	        }
	    },
	    //返回对event的引用对象
	    getEvent: function(event){
	        return event ? event : window.event;
	    },
	    getWheelDelta: function(event){
	        if (event.wheelDelta){
	            return  event.wheelDelta;
	        } else {
	            return -event.detail * 40;
	        }
	    },
	    removeHandler: function (element, type, handler) {
	        if (element.addEventListener){
	            element.removeEventListener(type, handler, false);
	        } else if (element.attachEvent){
	            element.detachEvent("on" + type, handler);
	        } else {
	            element["on" + type] = null;
	        }
	    }
	};