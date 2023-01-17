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
    	} else { 
    		ampm = "오전 "; 
    	} 
    	if (hours < 10){ hours = "0" + hours; } 
    	if (minutes < 10){ minutes = "0" + minutes; } 
    	if (seconds < 10){ seconds = "0" + seconds; } 
    	//document.getElementById("dpTime").innerHTML = ampm + hours + ":" + minutes + ":" + seconds; 
    	$("#menu_time").html(ampm + hours + ":" + minutes + ":" + seconds);
    }  
    </script>
		<style>
	            <c:choose>
		                <c:when test="${restInfo.menuImg eq ''}">
		                 .middleFood .imgArea{background-image:url(/images/ex01.png);}
		                </c:when>
		        		<c:otherwise>
		        		   .middleFood .imgArea{background-image:url("<c:url value='/upload/${restInfo.menuImg}' />");}
		        		</c:otherwise>					        							        
		        </c:choose>
		       	<c:choose>
		                <c:when test="${restInfo.menuImg eq ''}">
		                 .nightFood .imgArea{background-image:url(/images/ex01.png);}
		                </c:when>
		        		<c:otherwise>
		        		   .nightFood .imgArea{background-image:url("<c:url value='/upload/${restInfo.menuImg}' />");}
		        		</c:otherwise>					        							        
		        </c:choose>
             </style>
</head>
<body>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/RestManage/restPageMian.do">

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
    <div class="content outArea">
        <!--//중식영역-->
        <c:if test="${menuCnt ne '0'}">
        
        
        <c:forEach items="${resultList }" var="restInfo" varStatus="status">
        <c:if test="${status.count eq '1' }">
        <div class="floatL middleFood">
         <div class="imgArea">

            </div>
            <div class="textArea">
                <h4>${restInfo.menuGubunTxt }</h4>
            </div>
            <div class="textArea">
                <pre style="line-height: 1.5em">${restInfo.menuInfo }</pre>
            </div>
        </div>
        </c:if>
        
        <c:if test="${status.count ne '1' }" >
        <div class="floatR nightFood">
         <div class="imgArea">

            </div>
            <div class="textArea" style="float:left">
                <h4>${restInfo.menuGubunTxt }</h4>
            </div>
            <div class="textArea">
               <pre style="line-height: 1.5em">${restInfo.menuInfo }</pre>
            </div>
        </div>
        </c:if>
           
        <!--중식영역//-->
        </c:forEach>
        </c:if>
        <c:if test="${menuCnt eq '0'} }">
        <div class="content">        
          <p class="noticeText">내일은 중식과 석식이 없습니다<br />오늘도 좋은 하루 되세요</p>
        </div>
        </c:if>
       
        <!--//석식영역-->
        <div class="clear"></div>
    </div>
    <!--콘텐츠영역//-->
    <!--//하단영역-->
    <footer>
        <div class="content">
            <div class="floatL">
                <div class="noticeArea">
                    <span class="orangeFont">공지사항</span><span>
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