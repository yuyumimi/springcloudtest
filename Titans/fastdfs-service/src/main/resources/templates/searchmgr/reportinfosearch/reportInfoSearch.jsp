<!DOCTYPE html>
<html  xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>北京市社会保险网上服务平台  查询管理</title>
    <link th:href="@{css/base.css}" rel="stylesheet"/>
    <link th:href="@{css/reportInfoSearch.css}" rel="stylesheet"/>
</head>
<body>
<jsp:include page="${path }/page/header.jsp"></jsp:include>
<div id="wrap">
    <jsp:include page="${path }/page/searchmgr/searchmgrTop.jsp"></jsp:include>
    <h2 class="title1">
        申报信息状态查询
        
        <a href="javascript:;" class="button1 faq"><span>操作说明</span></a>
    </h2>
    <div class="screen">
        <h3>
            <strong>时间：</strong>
            <input type="text" id="start" class="date">
            <span>-</span>
            <input type="text" id="end" class="date">
            <i>今天</i>
            <a href="javascript:;">高级筛选</a>
        </h3>
        <ul class="fuzzy">
            <li id ='1'>
                <strong>时间：</strong>
                <a href="javascript:;" data-type="" class="all check">全部</a>
                <a href="javascript:;" data-type="1">1个月</a>
                <a href="javascript:;" data-type="3">3个月</a>
                <a href="javascript:;" data-type="6">6个月</a>
                <a href="javascript:;" data-type="12">1年</a>
                <input type="hidden" name="selectedDate" id="selectedDate" />
            </li>
            <li id ='2'>
                <strong>分类：</strong>
                <a href="javascript:;" data-type="" class="all check">全部</a>
                <a href="javascript:;" data-type="DATA_NORMAL_CUT">普通减员</a>
                <a href="javascript:;" data-type="DATA_CHANGE_HOS">定点医疗机构变更</a>
                <a href="javascript:;" data-type="DATA_MONTHLY_PAY">职工上年月均工资收入申报</a>
                <a href="javascript:;" data-type="DATA_ENTINFO_UPDATE">单位基本信息变更</a>
                <a href="javascript:;" data-type="DATA_INDINFO_UPDATE">个人基本信息变更</a>
                <a href="javascript:;" data-type="DATA_IND_ADD">转入人员增加</a>
                <input type="hidden" name="dataType" id="dataType" />
            </li>
            <li id ='3'>
                <strong>状态：</strong>
                <a href="javascript:;" data-type="" class="all check">全部</a>
                <a href="javascript:;" data-type="0">未提交</a>
                <a href="javascript:;" data-type="1">处理中</a>
                <a href="javascript:;" data-type="2">处理结束</a>
                <input type="hidden" name="reportStatus" id="reportStatus" />
            </li>
        </ul>
        <form class="advanced">
            <ul>
                <li>
                    <label>
                        <span>申报流水号：</span>
                        <input type="text" id="reportId" name="reportId" placeholder="请输入申报流水号">
                    </label>
                </li>
                <li>
                    <label>
                        <span>身份证号：</span>
                        <input type="text" id="idCard" name="idCard" placeholder="请输入合法的18位身份证号">
                    </label>
                </li>
                <li>
                    <label>
                        <span>姓名：</span>
                        <input type="text" id="personName" name="personName" placeholder="请输入姓名">
                    </label>
                </li>
            </ul>
            <label class="button1"><input type="button" value="查  询"></label>
        </form>
        <input type="hidden" name="selectedTyep" id="selectedTyep" />
    </div>
    <div class="list">
        <ul class="list_head">
            <li>姓名</li>
            <li>身份证号</li>
            <li>网上申报流水号</li>
            <li>办理状态</li>
            <li>申报业务类型</li>
            <li>业务状态</li>
            <li>申报时间</li>
            <li>操作</li>
        </ul>
        <ul class="content">
            
        </ul>
    </div>
    <div class="error" style="display: none;">
    	<img src="<%=path%>/images/warning.png">
    	<p>抱歉，系统未搜索到您查询的信息！</p>
    </div>
    <div class="page"></div>
</div>
<script type="text/javascript" src="<%=path%>/js/ajaxutil.js"></script>
<script type="text/javascript" src="<%=path %>/js/date.js"></script>
<!--[if lt IE 8]>
<script type="text/javascript" src="<%=path %>/js/json2.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=path %>/js/reportInfoSearch.js"></script>
</body>
</html>