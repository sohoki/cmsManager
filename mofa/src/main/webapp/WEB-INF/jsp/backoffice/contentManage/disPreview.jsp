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
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-2.2.4.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript">
		    jQuery( function() {
				$('#display_View').load( function(){
				    bodyheight = window.innerWidth ? window.innerHeight : document.body.clientHeight;
				    $("#display_View").attr('style', "height:" + bodyheight+"px;");
			    });
			});
    </script>
</head>
<body>
<form:form name="regist" commandName="regist" method="post" >
<input type="hidden" name="detailSeq_N" id="detailSeq_N" value="${regist.detailSeq_N}">
<input type="hidden" name="totalCnt" id="totalCnt" value="${totCnt -1}">
<input type="hidden" name="playTime" id="playTime">
<input type="hidden" name="displaySeq" id="displaySeq" value="${regist.displaySeq }">
<input type="hidden" name="nowPlay" id="nowPlay">
<iframe name="display_View" id="display_View" width="100%" height="100%" marginheight="0" marginwidth="0" frameborder="0" scrolling="auto" >
</iframe>
<c:forEach items="${resultDetailList }" var="detailInfo" varStatus="status">
   <input type="hidden" name="hid_Detail" id="hid_Detail${detailInfo.detail_sort}" value="${detailInfo.report_url}|${detailInfo.detail_time}|${detailInfo.detail_seq}">
</c:forEach>
</form:form>
<script type="text/javascript">
	var preTime = null;
	 var bodyheight = 0;
	$(document).ready(function() {     
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
		var url_info = document.getElementsByName("hid_Detail")[parseInt( $("#nowPlay").val())].value;
		var detailInfo = url_info.split("|");
		$("#detailSeq_N").val(detailInfo[2]);
		$("#playTime").val(detailInfo[1]);
		$("#display_View").attr('src', detailInfo[0]);
		var nowPlay = (parseInt($("#nowPlay").val()) + 1);
		
		$("#nowPlay").val(nowPlay);
		
		preTime = setTimeout(function(){
			fn_nextView();
		}, parseInt($("#playTime").val()) * 1000 );
	}
	function fn_nextView(){
		
		if ($("#nowPlay").val() > $("#totalCnt").val()){
			$("#nowPlay").val("0");
		}	
		for (var i =0; i < document.getElementsByName("hid_Detail").length; i++){
			var detailInfo = document.getElementsByName("hid_Detail")[i].value.split("|");
			if ($("#nowPlay").val() == i){
				$("#detailSeq_N").val(detailInfo[2]);
			}
	    }
		location.href="/backoffice/contentManage/disPreview.do?displaySeq="+ $("#displaySeq").val()+"&detailSeq_N="+$("#detailSeq_N").val();
	}
	function setFrameSize(){
	    var bodyheight = 0;
	    if(window.innerWidth){    // IE가 아닐 때
	        bodyheight = window.innerHeight;
	    }else{
	        bodyheight = document.body.clientHeight;
	    }
	    document.getElementById("display_View").height = bodyheight;
	}
</script>
</body>
</html>