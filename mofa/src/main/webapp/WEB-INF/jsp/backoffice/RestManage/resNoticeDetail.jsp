<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page import ="egovframework.com.cmm.AdminLoginVO" %>
<% AdminLoginVO loginVO = (AdminLoginVO)session.getAttribute("AdminLoginVO"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
</head>
<body>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/RestManage/restPageInfoList.do">
<div id="wrapper">	
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="searchCondition" id="searchCondition" value="${searchVO.searchCondition }">
<input type="hidden" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword }">

<input type="hidden" name="mode" id="mode"  value="${searchVO.mode }">
<input type="hidden" name="noteSeq" id="noteSeq"  value="${searchVO.noteSeq }">


<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <span><spring:message code="menu.menu05_1" /></span>
                    </div>
                </div>
            </div>
             <div class="Swrap tableArea viewArea">
                <div class="view_contents">
                <table class="pop_table thStyle">
                <tbody>
                    <tr>
                        <th>공지글 </th>
                        <td style="text-align:left" colspan="3">
                        <form:input path="noteTitle" id="noteTitle" class="input_noti" size="100" value="${registInfo.noteTitle }" />                        
                        </td>
                    </tr>
                    <tr>
                        <th>공지 시작일</th>
                        <td style="text-align:left">
                            <form:input  path="noteStartdate" size="10" maxlength="10" id="noteStartdate" value="${registInfo.noteStartdate }" />
                        </td>
                        <th>공지 종료일</th>
                        <td style="text-align:left">
                             <form:input  path="noteEnddate" size="10" maxlength="10" id="noteEnddate" value="${registInfo.noteEnddate }"/>	
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
</div>
<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
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
		    $("#noteStartdate").datepicker(clareCalendar);
		    $("#noteEnddate").datepicker(clareCalendar);
		    $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
		    $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
	  });	  
	  function check_form(){
		   if (any_empt_line_id("noteTitle", '제목을 입력해 주세요') == false) return;
		   if (any_empt_line_id("noteStartdate", '공지 시작일을 입력해 주세요.') == false) return;
		   if (any_empt_line_id("noteEnddate", '공지 종료을 입력해 주세요.') == false) return;
		   
		   var params = $("#regist").serializeObject(); 
		   uniAjax("/backoffice/RestManage/restNoticeUpdate.do", params, 
	     			function(result) {
			                alert(result.message);
					    	if (result.status == "SUCCESS"){
								$("form[name=regist]").attr("action", "/backoffice/RestManage/restNoticeList.do").submit();
							}else if (result.status == "LOGIN FAIL"){
	  							location.href="/backoffice/login.do";
	  						} else {
	  							
	  						}
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	      );
		   
	  }
	  //센터 삭제 
	  function del_form(){
		  if (confirm('<spring:message code="common.delete.msg" />')== true){
			  document.regist.action = "/backoffice/basicManage/centerDeletel.do";
			  document.regist.encoding = "application/x-www-form-urlencoded"; 
			  document.regist.submit();  			  
		  }		  	  
	  }
	</script> 
</body>
</html>