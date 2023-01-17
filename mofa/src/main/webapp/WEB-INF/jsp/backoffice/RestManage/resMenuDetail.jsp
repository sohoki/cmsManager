<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title><spring:message code="URL.TITLE" /></title>
    <!--css-->
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    
    
    <link href="/css/rest/reset.css" rel="stylesheet" />
    <link href="/css/rest/paragraph.css" rel="stylesheet" />
    <script type="text/javascript"> 
    
    setInterval("dpTime()",1000); 
    function dpTime(){ 
    	var now = new Date(); 
    	hours = now.getHours(); 
    	minutes = now.getMinutes(); 
    	seconds = now.getSeconds(); 
    	if (hours > 12){ 
    		hours -= 12; ampm = "오후 "; 
    	}else{ ampm = "오전 "; 
    	} 
    	if (hours < 10){ hours = "0" + hours; } 
    	if (minutes < 10){ minutes = "0" + minutes; } 
    	if (seconds < 10){ seconds = "0" + seconds; } 
    	
    	$("#menu_time").html(ampm + hours + ":" + minutes + ":" + seconds);
    	
    	var timeInfo = ampm + hours;
    	if (timeInfo == "오후 01" && $("#searchTime").val() == "M"){
    		location.reload();
    	}else if (timeInfo == "오전 08" && $("#searchTime").val() == "F"){
    		location.reload();
    	}
    }  
    function fn_sendStar(){
    	for (var i =1; i < 6; i++ ){
    		$("#star"+i).attr("class", "");
    	}
    	var param;
    	
    	if ($('#startCount').val() == "1"){
    		param = {  'menuSeq' : $('#menuSeq').val(),   'menuStep01Value' : $('#startCount').val()};
    	}else if ($('#startCount').val() == "2"){
    		param = {  'menuSeq' : $('#menuSeq').val(),   'menuStep02Value' : $('#startCount').val()};
    	}else if ($('#startCount').val() == "3"){
    		param = {  'menuSeq' : $('#menuSeq').val(),   'menuStep03Value' : $('#startCount').val()};
    	}else if ($('#startCount').val() == "4"){
    		param = {  'menuSeq' : $('#menuSeq').val(),   'menuStep04Value' : $('#startCount').val()};
    	}else if ($('#startCount').val() == "5"){
    		param = {  'menuSeq' : $('#menuSeq').val(),   'menuStep05Value' : $('#startCount').val()};
    	}else {
    		alert("별점을 체크 하지 않았습니다.");
    		return;
    	}
    	
    	url= "/backoffice/RestManage/resStarUpdate.do";
		uniAjax(url, param, 
   			function(result) {
				       if (result.status == "LOGIN FAIL"){
				    	   alert(result.meesage);
						   location.href="/backoffice/login.do";
					   }else if (result.status == "SUCCESS"){
						    //여기 부분 수정 어떻게 할지 추후 생각
						   $("#startCount").val(""); 
						   $("#s05").text(result.menuInfo.menuStep05Value);
						   $("#s04").text(result.menuInfo.menuStep04Value);
						   $("#s03").text(result.menuInfo.menuStep03Value);
						   $("#s02").text(result.menuInfo.menuStep02Value);
						   $("#s01").text(result.menuInfo.menuStep01Value);
					   }
				    },
				    function(request){
					    alert("Error:" +request.status );	       						
				    }    		
        );
    	
    }
    function fn_sendStarCount(code){
    	$("#startCount").val("");
    	for (var i =1; i < 6; i++ ){
    		$("#star"+i).attr("class", "");
    	}
    	for (var i =1; i < (parseInt(code) + 1); i++ ){
    		$("#star"+i).attr("class", "active");
    	}
    	$("#startCount").val(code);
    	
    }
    </script>
    
	<style>
		.middleFood .imgArea{background-image:url("<c:url value='/upload/${restInfo.menuImg}' />");}
	</style>
</head>
<body>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/RestManage/restPageMian.do">
<input type="hidden" id="searchTime" name="searchTime" value="${regist.searchTime }">

 <!--//상단영역-->
    <header>
        <div class="content">
            <h1 class="floatL">
                <img src="/images/ssglogo.png" alt="ssg logo" />
            </h1>
            <div class="floatR">
                 <span class="day" id="menu_day">${menu_day }</span><span class="time"  id="menu_time">14:11</span>
            </div>
            <div class="clear"></div>
        </div>
    </header>
    <!--상단영역//-->
    <!--//콘텐츠영역-->
    <c:forEach items="${resultList }" var="restInfo" varStatus="status">
	<input type="hidden" id="menuSeq" value="${restInfo.menuSeq}"/>
	<input type="hidden" id="startCount" />
    <div class="content inArea">
        <!--//중식영역-->
        <div class="floatL middleFood">
            <div class="imgArea">
                <!--이미지가 들어가는 영역입니다 css에서 background로 넣었습니다.-->

            </div>
            <div class="textArea">
                <h4>${restInfo.menuGubunTxt }</h4>
                <span>${restInfo.menuInfo }</span>
            </div>
            <hr />
            <h5 class="floatL">만족도 선택</h5>
            <!--만족도 조사 선택할 때 acitve 추가-->
            <ul class="score floatR">
                <li id="star1" onClick="fn_sendStarCount('1')"></li>
                <li id="star2" onClick="fn_sendStarCount('2')"></li>
                <li id="star3" onClick="fn_sendStarCount('3')"></li>
                <li id="star4" onClick="fn_sendStarCount('4')"></li>
                <li id="star5" onClick="fn_sendStarCount('5')"></li>
            </ul>
            <div class="clear"></div>
            <!--button 터치시 active 가 추가 됩니다.-->
            <a href="#" onClick="fn_sendStar()" class="button">확인</a>
        </div>
        <!--중식영역//-->
        <!--//만족도 조사 결과 영역-->
        <div class="floatR width50">
            <!--<h4>${restInfo.menuGubunTxt }</h4>--><h4>메뉴 만족도 조사</h4>
            <ol>
                <li>
                    <div><span>5</span>매우만족</div><hr /><div id="s05">${restInfo.menuStep05Value }</div>
                </li>
                <li>
                    <div><span>4</span>만족</div><hr /><div id="s04">${restInfo.menuStep04Value }</div>
                </li>
                <li>
                    <div><span>3</span>보통</div><hr /><div id="s03">${restInfo.menuStep03Value }</div>
                </li>
                <li>
                    <div><span>2</span>불만족</div><hr /><div id="s02">${restInfo.menuStep02Value }</div>
                </li>
                <li>
                    <div><span>1</span>매우불만족</div><hr /><div id="s01">${restInfo.menuStep01Value }</div>
                </li>
            </ol>
        </div>
        <!--//만족도 조사 결과 영역-->
        <div class="clear"></div>
    </div>
    <!--콘텐츠영역//-->
    </c:forEach>
    <!--//하단영역-->
    <footer>
        <div class="content">
            <div class="floatL">
                <div class="noticeArea">
                    <span class="orangeFont">공지사항</span>
                    <span>
                    <marquee width="1200">
                    <c:forEach items="${noteList }" var="noteInfo" varStatus="status">
                    ${noteInfo.noteTitle }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    </c:forEach>
                    </marquee>
                    </span>
                </div>
            </div>
            <div class="floatR">
                <h3>
                    <img src="/images/neologo.png" alt="neo003 logo" />
                </h3>
            </div>
            <div class="clear"></div>
        </div>
    </footer>
    <!--하단영역//-->
 </form:form>
</body>
</html>