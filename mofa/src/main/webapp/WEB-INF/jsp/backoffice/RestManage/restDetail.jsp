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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    
    <script src="/js/popup.js"></script>
</head>
<body>
<div id="wrapper">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/basicManage/centerUpdate.do">	   	  
	

<form:hidden path="menuSeq" id="menuSeq"/>
<form:hidden path="mode" id="mode"/>
<form:hidden path="pageIndex" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<c:import url="/backoffice/inc/top_inc.do" />

<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span>></span>
                        <span><spring:message code="menu.menu05" /></span>
                    </div>
                </div>
            </div>

            <div class="Swrap tableArea viewArea">
                <div class="view_contents">
                <table class="pop_table thStyle">
                <tbody>
                    <tr>
                        <th><spring:message code="page.rest.title" /> </th>
                        <td style="text-align:left">
                        <form:textarea cols="100" rows="10" path="menuInfo" size="50" maxlength="200" id="menuInfo"   value="${regist.menuInfo }" />
                        </td>
                        <th><spring:message code="page.rest.menuGubun" /></th>
                        <td style="text-align:left">
	                        <form:select path="menuGubun" id="menuGubun" title="소속" style="width:90%;">
								     <option value=""><spring:message code="combobox.text" /></option>
			                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
						    </form:select>
                        </td>
                    </tr>
                    <tr>
                        <th><spring:message code="page.rest.restImg" /></th>
                        <td style="text-align:left">
                            <input name="centerImg" id="centerImg" type="file"  size="20"/>
                        </td>
                        <th><spring:message code="page.rest.date" /> </th>
                        <td style="text-align:left">
                            <form:input  path="menuDate" size="10" maxlength="10" id="menuDate" style="width:80%" value="${regist.menuDate }" />
                        </td>
                    </tr>
                    <tr>
                        <th><spring:message code="page.center.centerNm" /></th>
                        <td style="text-align:left">
                            <form:select path="centerId" id="centerId" title="소속">
							     <option value=""><spring:message code="combobox.text" /></option>
		                         <form:options items="${selectCenterCombo}" itemValue="centerId" itemLabel="centerNm"/>
					        </form:select>
                        </td>
                        <th><spring:message code="common.UseYn.title" /></th>
                        <td style="text-align:left">
                            <input type="radio" name="menuUseyn" id="menuUseyn" value="Y" <c:if test="${regist.menuUseyn == 'Y' }"> checked </c:if> />
		                    <label><spring:message code="button.use" /></label>
							<input type="radio" name="menuUseyn" id="menuUseyn" value="N" <c:if test="${regist.menuUseyn == 'N' }"> checked </c:if> />
							<label><spring:message code="button.nouse" /></label>
                        </td>
                    </tr>
                </tbody>
            </table>
            </div>
            </div>
            <div class="footerBtn">
	            <a href="javascript:check_form();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
	            <c:if test="${regist.mode != 'Ins' }">
	            <a href="javascript:del_form();" class="redBtn"><spring:message code="button.delete" /></a>
	            </c:if>
	            <a href="fn_linkPage('/backoffice/RestManage/restPageInfoList.do', 'regist');" class="deepBtn"><spring:message code="button.list" /></a>
	        </div>
        </div>
    </div>
	<c:import url="/backoffice/inc/bottom_inc.do" />    
	</form:form>    	
</div>
  <script type="text/javascript">
		$(document).ready(function() {
			if ($("#mode").val() == "Ins"){   	  		    	
		 		$("#btnUpdate").text('<spring:message code="button.create" />');
		 		$("#sp_CenterInfo").html('<spring:message code="page.center.message" />');
		    }	else {		    	
		    	$("#btnUpdate").text('<spring:message code="button.update" />');
		    	$("#sp_CenterInfo").html($("#centerId").val());
		    }
			
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
		    $("#menuDate").datepicker(clareCalendar);
		    $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
		    $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
	  });	  
	  function check_form(){
		   if (any_empt_line_id("menuInfo", '<spring:message code="page.rest..alert01" />') == false) return;
		   if (any_empt_line_id("menuGubun", '<spring:message code="page.rest..alert02" />') == false) return;
		   if (any_empt_line_id("centerId", '<spring:message code="page.rest..alert03" />') == false) return;
		   if (any_empt_line_id("menuDate", '<spring:message code="page.rest..alert04" />') == false) return;
		   
		   document.regist.encoding = "multipart/form-data";
		   $("form[name=regist]").attr("action", "/backoffice/basicManage/restPageInfoUpdate.do").submit();
		   /* 
		   document.regist.encoding = "multipart/form-data";
		   $("form[name=regist]").attr("action", "/backoffice/basicManage/centerUpdate.do").submit(); */
		   
	  }
	  //센터 삭제 
	  function del_form(){
		  /* if (confirm('<spring:message code="common.delete.msg" />')== true){
			  document.regist.action = "/backoffice/basicManage/centerDeletel.do";
			  document.regist.encoding = "application/x-www-form-urlencoded"; 
			  document.regist.submit();  			  
		  }	 */
		  
		  fn_uniDelJSON("/backoffice/RestManage/restPageInfoDelete.do"
				  , {menuSeq : $("#menuSeq").val()}
		          , "/backoffice/RestManage/restPageInfoList.do");	
	  }
	</script> 
  
</body>
</html>