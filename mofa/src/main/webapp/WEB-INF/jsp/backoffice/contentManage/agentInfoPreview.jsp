<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1">  
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/calendar.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/jscript" src="/SE/js/HuskyEZCreator.js" ></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script src="/js/popup.js"></script>
    
</head>
<body>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/basicManage/seatList.do">
<input type="hidden" name="detailSeq_N" id="detailSeq_N" value="${regist.detailSeq_N}">
<input type="hidden" name="agentCode" id="agentCode" value="${regist.agentCode}">
<input type="hidden" name="totalCnt" id="totalCnt" value="${totCnt -1}">
<input type="hidden" name="playTime" id="playTime">
<input type="hidden" name="nowPlay" id="nowPlay">
<input type="hidden" name="hideGubun" id="hideGubun">
<!-- <iframe name="ifm_displayView1" id="ifm_displayView1" width="100%" height="100%" style="z-index:10" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto">
</iframe>
<iframe name="ifm_displayView2" id="ifm_displayView2" width="100%" height="100%" style="z-index:11" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto">
</iframe> -->
<c:forEach items="${resultDetailList }" var="detailInfo" varStatus="status">
   <input type="hidden" name="hid_Detail" id="hid_Detail${detailInfo.detail_sort}" value="${detailInfo.report_url}|${detailInfo.detail_time}|${detailInfo.detail_seq}">
</c:forEach>


<script type="text/javascript">
var preTime = null;
$(document).ready(function() {    
	$("#ifm_displayView2").hide();
	fn_preView();
});	
function fn_preView(){
	if ($("#detailSeq_N").val() == ""){	
		$("#nowPlay").val("0");
	}else {
		for (var i =0; i < document.getElementsByName("hid_Detail").length; i++){
				var detailInfo = document.getElementsByName("hid_Detail")[i].value.split("|");
				if ($("#detailSeq_N").val() == detailInfo[2]){
					$("#nowPlay").val(i);
				}
		}				
	}
	console.log("fn_preView()");
	ifrmaeView();
	//여기 구문이 중복임 
	
}
function fn_nextView(){
	//여기 부분에 값 확인 하기 
	
	console.log("fn_nextView()");
	if ($("#nowPlay").val() > $("#totalCnt").val()){
		$("#nowPlay").val("0");
	}	
	for (var i =0; i < document.getElementsByName("hid_Detail").length; i++){
		var detailInfo = document.getElementsByName("hid_Detail")[i].value.split("|");
		if ($("#nowPlay").val() == i){
			$("#detailSeq_N").val(detailInfo[2]);
		}
    }
	$("#agentCode").val($("#agentCode").val());
	$("#detailSeq_N").val($("#detailSeq_N").val());
	fn_preView();
	
}
function ifrmaeView(){
	if ($("#hideGubun").val() == "") $("#hideGubun").val("1");
	console.log("ifrmaeView start");
	preTime = null;
	var url_info = document.getElementsByName("hid_Detail")[parseInt($("#nowPlay").val())].value;
	var detailInfo = url_info.split("|");
	$("#detailSeq_N").val(detailInfo[2]);
	$("#playTime").val(detailInfo[1]);
	
	$("#nowPlay").val(parseInt($("#nowPlay").val()) + 1);

	

    var hdie_div = $("#hideGubun").val() == "1" ? "2" : "1";
	$("#ifm_displayView"+hdie_div).css("position","absolute").css("z-index","21");
	$("#ifm_displayView"+hdie_div).fadeTo(1000, 0.1, function (){
		$("#ifm_displayView"+hdie_div).remove();
	});

	console.log("ifm_displayView fadeto");

	var new_frame = $("<iframe name='ifm_displayView"+$("#hideGubun").val()+"' id='ifm_displayView"+$("#hideGubun").val()+"' width='100%' height='100%' style='z-index:10;position:absolute;' marginheight='0' marginwidth='0' frameborder='0' scrolling='auto' />");
	new_frame.appendTo('body');
	setFrameSize("ifm_displayView"+$("#hideGubun").val());
	$("#ifm_displayView"+$("#hideGubun").val()).attr('src', detailInfo[0]);



	if ($("#hideGubun").val() == "1"){
		$("#hideGubun").val("2");
		$(function(){
		    $('#ifm_displayView1').load(function(){
				console.log("ifm_displayView1 load");
				$("#ifm_displayView1").show();
				console.log("ifrmae1");
			});
		  });
	}else {
		$("#hideGubun").val("1");
		$(function(){
			$('#ifm_displayView2').load(function(){
				console.log("ifm_displayView2 load");
				$("#ifm_displayView2").show();
				console.log("ifrmae2");
			});
		});
	}
	//alert("playTime:"+$("#playTime").val());
	preTime = setTimeout(function(){
		fn_nextView();
	}, parseInt($("#playTime").val()) * 1000 );
	console.log("ifrmaeView end");
}

function setFrameSize(ifrmaeId){
    var bodyheight = 0;
    if(window.innerWidth){    // IE가 아닐 때
        bodyheight = window.innerHeight;
    }else{
        bodyheight = document.body.clientHeight;
    }
    document.getElementById(ifrmaeId).height = bodyheight;
}
</script>
</form:form>
</body>
</html>