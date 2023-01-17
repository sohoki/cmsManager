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
    <link rel="stylesheet" type="text/css" href="/css/calendar.css">
    <script type="text/javascript" src="/js/new_calendar.js"></script>
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/jscript" src="/SE/js/HuskyEZCreator.js" ></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script src="/js/popup.js"></script>
</head>
<body>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/pageInfoList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
<input type="hidden" name="mode" id="mode" value="${regist.mode }">
<form:hidden path="pageSize" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="reportSeq" />

<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <span><spring:message code="menu.menu01" /></span>
                        <span> ▶ </span>
                        <strong><span><spring:message code="menu.menu01_1" /></span></strong>
                    </div>
                </div>
            </div>

            
            <div class="Swrap tableArea viewArea">
                <div class="view_contents">
                <table class="pop_table thStyle">
	                <tbody>
	                    <tr>
	                        <th><spring:message code="page.Title" /></th>
	                        <td colspan="3"><input type="text" name="reportTitle" id="reportTitle" value="${regist.reportTitle }" size="100" maxlength="200"/></td>
	                        
	                    </tr>
	                    <tr>
	                        <th><spring:message code="page.Detail" /></th>
	                        <td style="text-align:left"  colspan="3">
	                        <form:input  path="reportDc" size="100" maxlength="200" id="reportDc"   value="${regist.reportDc }" />
	                        </td>                   
	                    </tr>	                    
	                    <tr>
		                    <th><spring:message code="page.url" /></th>
		                    <td  colspan="3">
		                    <form:input  path="reportUrl" size="100" maxlength="200" id="reportUrl"   value="${regist.reportUrl }" />
		                    </td>
	                    </tr>
	                    <tr>
	                        <th><spring:message code="page.pageGubun" /></th>
		                    <td>
		                            <form:select path="reportGubun" id="reportGubun" title="소속">
										     <form:option value="" label="--선택하세요--"/>
					                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
								    </form:select>	
		                    </td>
	                        <th><spring:message code="common.UseYn.title" /></th>
	                        <td style="text-align:left">
	                        <input type="radio" name="reportUseYn" value="Y" <c:if test="${regist.reportUseYn == 'Y' }"> checked </c:if> /><label>사용</label>
							<input type="radio" name="reportUseYn" value="N" <c:if test="${regist.reportUseYn == 'N' }"> checked </c:if> /><label>사용안함</label>	                            
	                        </td>
	                    </tr>
	                </tbody>
	            </table>
            </div>
            </div>
            <div class="footerBtn">
	            <a href="javascript:check_form();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
	            <a href="javascript:linkPage()" class="deepBtn"><spring:message code="button.list" /></a>
	        </div>
        </div>
    </div>

<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
	
<script type="text/javascript">
	function linkPage() {
		$(":hidden[name=pageIndex]").val($("#pageIndex").val());
		$("form[name=regist]").attr("action", "/backoffice/contentManage/pageInfoList.do").submit();
	}	
	$(document).ready(function() {     
		if ("${status}" != ""){
			if ("${status}" == "SUCCESS") {
				alert('<spring:message code="success.common.update" />');  
				opener.document.location.reload();
			}else{
				alert('<spring:message code="fail.request.msg" />');
			}						
		}		
	    if ($("#mode").val() == "Ins"){
	 		$("#btnUpdate").text('<spring:message code="button.create" />');
	    }	else {
	    	$("#btnUpdate").text('<spring:message code="button.update" />');	   	    
	    }
	});	
	function check_form(){
		   if (any_empt_line_id("reportTitle", '<spring:message code="page.report.alert01" />') == false) return;
		   if (any_empt_line_id("reportUrl", '<spring:message code="page.report.alert02" />') == false) return;
		   //여기 부분에 ajax 로 할지 정리 하기 
		   var param = {
                'reportSeq' : $("#reportSeq").val(),
                'reportTitle' : $("#reportTitle").val(),
                'reportDc' : $("#reportDc").val(),
                'reportUrl' : $("#reportUrl").val(),
                'reportUseYn' :  $('input[name="reportUseYn"]:checked').val(),
                'reportGubun' : $("#reportGubun").val(),
                'mode' : $("#mode").val()
            };
		    uniAjax("/backoffice/contentManage/pageInfoUpdate.do", param, 
          			function(result) {
				    	alert(result.message);
						if (result.status == "SUCCESS"){
							$("#reportSeq").val(result.regist.reportSeq);
							$("#mode").val("Edt");
							$("form[name=regist]").attr("action", "/backoffice/contentManage/pageInfoView.do").submit();
						}
  				    },
  				    function(request){
  					    alert("Error:" +request.status );	       						
  				    }    		
          	 );
		   return;
	}
	function del_form(){
		
	    if (confirm("삭제 하시겠습니까?")== true){
	    	apiExecute(
					"POST", 
					"/backoffice/basicManage/pageInfoDelete.do",
					{
						reportSeq : $("#reportSeq").val()
					},
					null,				
					function(result) {				
						if (result != null) {
							if (result == "T"){
								alert("정상적으로 삭제되었습니다.");
							}else {
								alert("삭제시 문제가 생겼습니다.");
							}					
						}else {
						    alert("삭제시 문제가 생겼습니다1.");						  
						}
						$("form[name=regist]").attr("action", "/backoffice/basicManage/pageInfoList.do").submit();					
					},
					null,
					null
				);	
	    }else {
	    	return;
	    } 	   
	}	
</script>  
</body>
</html>