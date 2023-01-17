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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/needpopup.css">
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script src="/js/popup.js"></script>    
</head>
<body>
<div id="wrapper">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/loginfo/loginList.do">	   	  
<c:import url="/backoffice/inc/top_inc.do" />	
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu05" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu05_1" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                   <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="userId" <c:if test="${searchVO.searchCondition == 'userId' }"> selected="selected" </c:if>>
								<spring:message code="page.login.loginId" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchCondition == 'userNm' }"> selected="selected" </c:if>>
								<spring:message code="page.login.loginNm" />
								</option>
					</select>
					<input type="text" class="nameB ssg_input" name="searchStartdday"  maxlength="10" id="searchStartdday"  value="${searchVO.searchStartdday }"/>
	                <input type="text" class="nameB ssg_input" name="searchEndday"  maxlength="10" id="searchEndday"  value="${searchVO.searchEndday }"/>		
                    <input type="text" class="nameB ssg_input" name="searchKeyword" id="searchKeyword" placeholder="이름" value="${searchVO.searchKeyword }">
                    <a href="javascript:search_form()"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="page.login.loginId" /></th>
                            <th><spring:message code="page.login.loginNm" /></th>
                            <th><spring:message code="page.login.loginPartNm" /></th>
                            <th><spring:message code="page.login.loginIp" /></th>
                            <th><spring:message code="page.login.loginMth" /></th>
                            <th><spring:message code="page.login.loginTime" /></th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach items="${resultList }" var="logininfo" varStatus="status">
                        <tr>
                            <td>
							 <a href="javascript:fn_Center('Edt','${logininfo.log_id }')">
								${logininfo.conect_id }
							 </a>
							</td>
							<td>
							 <a href="javascript:fn_Center('Edt','${logininfo.log_id }')">
								${logininfo.admin_name }
							 </a>
							</td>
							<td><a href="javascript:fn_Center('AGT','${logininfo.log_id }')">${logininfo.part_nm }</a></td>
							<td>${logininfo.conect_ip }</td>
							<td>${logininfo.conect_mthdtxt }</td>
							<td>${logininfo.creat_dt }</td>
                        </tr>
                       </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="new_pagenum">
               <div class='new_pager'>
                  <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
               </div> 
            </div>
        </div>
    </div>
    <c:import url="/backoffice/inc/bottom_inc.do" />    
    </form:form>    
</div>
<script type="text/javascript">
	$(function () {
	    var clareCalendar = {
	        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
	        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
	        weekHeader: 'Wk',
	        dateFormat: 'yymmdd', //형식(20120303)
	        autoSize: false, //오토리사이즈(body등 상위태그의 설정에 따른다)
	        changeMonth: true, //월변경가능
	        changeYear: true, //년변경가능
	        showMonthAfterYear: true, //년 뒤에 월 표시
	        buttonImageOnly: true, //이미지표시
	        buttonText: '달력선택', //버튼 텍스트 표시
			buttonImage: '/images/calendar.gif', //이미지주소
	        showOn: "both", //엘리먼트와 이미지 동시 사용(both,button)
	        yearRange: '1970:2099' //1990년부터 2020년까지
	    };	
	    $("#searchStartdday").datepicker(clareCalendar);
	    $("#searchEndday").datepicker(clareCalendar);
	    $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
	    $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
	});	
	function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").attr("action","/backoffice/loginfo/loginList.do").submit();
	}
	//조건 검색
	function search_form(){
		$(":hidden[name=pageIndex]").val("1");		
		$("form[name=regist]").attr("action","/backoffice/loginfo/loginList.do").submit();
	}
    </script>
</body>
</html>