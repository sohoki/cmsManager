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
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
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
	                        <td colspan="3">
	                        <span class="lable">${regist.reportTitle}</span>
	                        <span class="type">
	                        <input type="text" name="reportTitle" id="reportTitle" value="${regist.reportTitle }" size="100" maxlength="200"/>
	                        </span>
	                        </td>
	                        
	                    </tr>
	                    <tr>
	                        <th><spring:message code="page.Detail" /></th>
	                        <td style="text-align:left"  colspan="3">
	                        <span class="lable">${regist.reportDc }</span>
	                        <span class="type">
	                        <form:input  path="reportDc" size="100" maxlength="200" id="reportDc"   value="${regist.reportDc }" />
	                        </span>
	                        </td>
	                    </tr>	                    
	                    <tr>
		                    <th><spring:message code="page.url" /></th>
		                    <td>
		                    <span class="lable">
		                    ${regist.reportUrl }
		                    <a href="javascript:fn_pagePreview()">[<spring:message code="button.preview" />]</a>
		                    </span>
		                    <span class="type">
	                        <form:input  path="reportUrl" size="100" maxlength="200" id="reportUrl"   value="${regist.reportUrl }" />
	                        </span>
		                    </td>	                        
	                    </tr>
	                    <tr>
	                        <th><spring:message code="page.pageGubun" /></th>
	                        <td style="text-align:left">
	                        <span class="lable">${regist.reportGubun }</span>
	                        
	                        <span class="type">
	                        <form:select path="reportGubun" id="reportGubun" title="소속">
										     <form:option value="" label="--선택하세요--"/>
					                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
							</form:select>	
	                        </span>
	                        </td>
	                        <th><spring:message code="common.UseYn.title" /></th>
	                        <td style="text-align:left">
	                        <span class="lable">${regist.reportUseYn }</span>
	                        <span class="type">
	                        <input type="radio" name="reportUseYn" value="Y" <c:if test="${regist.reportUseYn == 'Y' }"> checked </c:if> /><label>사용</label>
							<input type="radio" name="reportUseYn" value="N" <c:if test="${regist.reportUseYn == 'N' }"> checked </c:if> /><label>사용안함</label>	
	                        </span>
	                        
	                        </td>
	                    </tr>	                    	                   
	                </tbody>
	            </table>
            </div>
            </div>
            <div class="footerBtn">
	            <a href="javascript:fn_Edit();" class="redBtn" id="btnUpdate">
	            <spring:message code="button.update" />
	            </a>
	            <a href="javascript:del_form();" class="redBtn" id="btnDel"><spring:message code="button.delete" /></a>
	            <a href="javascript:linkPage()" class="deepBtn"><spring:message code="button.list" /></a>
	        </div>
        </div>
    </div>

<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
	
<script type="text/javascript">
	function linkPage() {
		$("form[name=regist]").attr("action", "/backoffice/contentManage/pageInfoList.do").submit();
	}	
	$(document).ready(function() {     		
		$("#mode").val("Edt");
		fn_Edit();	
	});	
	function fn_pagePreview(){
		var url = "${regist.reportUrl }";
		window.open(url, 'url','menubar=no,width=923,height=700,toolbar=no,scrollbars=yes');
	}
	function fn_Edit(){
 	   
 	   $("#sp_MessageView").html("");  
		if ($("#mode").val() == "Edt" && $("#btnUpdate").text() == "입력"){
			   
			   if (any_empt_line_id("reportTitle", '<spring:message code="page.report.alert01" />') == false) return;
			   if (any_empt_line_id("reportUrl", '<spring:message code="page.report.alert02" />') == false) return;
		       
			   var params = $("#regist").serializeObject(); 
			   uniAjax("/backoffice/contentManage/pageInfoUpdate.do", param, 
	          			function(result) {
					    	alert(result.message);
							if (result.status == "SUCCESS"){
								$("#reportSeq").val(result.regist.reportSeq);
								$("#mode").val("Edt");
								$("form[name=regist]").attr("action", "/backoffice/contentManage/pageInfoList.do").submit();
							}
	  				    },
	  				    function(request){
	  					    alert("Error:" +request.status );	       						
	  				    }    		
	           );	
		}
		if ($("#mode").val() == "Edt"){
			$(".lable").show();
			$(".type").hide();
			$("#btnUpdate").text("수정");
			$("#btnDel").text("삭제");
			$("#mode").val("Pre");
		}else {
			
			$(".lable").hide();
			$(".type").show();
			$("#btnUpdate").text("입력");
			$("#btnDel").text("취소");
			$("#mode").val("Edt");
		}
	}
	function del_form(){
	    if (confirm('<spring:message code="common.delete.msg" />')== true){
	    	apiExecute(
					"POST", 
					"/backoffice/contentManage/pageInfoDelete.do",
					{
						reportSeq : $("#reportSeq").val()
					},
					null,				
					function(result) {				
						if (result != null) {
							if (result == "T"){
								alert('<spring:message code="success.common.delete" />');
							}else {
								alert('<spring:message code="fail.common.delete" />');
							}					
						}else {
							alert('<spring:message code="fail.common.delete" />');						  
						}
						$("form[name=regist]").attr("action", "/backoffice/contentManage/pageInfoList.do").submit();					
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