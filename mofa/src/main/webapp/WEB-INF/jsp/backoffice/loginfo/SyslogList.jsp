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
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/needpopup.css">
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js" ></script>
    <script type="text/javascript" src="/js/jquery-ui.js" ></script>
    <script type="text/javascript" src="/js/common.js" ></script>
    <script src="/js/popup.js"></script>    
	<link rel="stylesheet" href="/css/needpopup.css">
	<style>
		.joinSubTxt{
			float:right;
		} 
		.id-pw-warning{
			font-weight: bold;
			color : #EA4335;
		}
		.id-pw-posible{
			font-weight: bold;
			color : #34A853;
		}
		.join_pop_main_subTxt{
			float:left;
			font-weight: bold;
		}
		.pw_pop_main_subTxt{
			float:left;
			font-weight: bold;
		}		
	</style>
</head>
<body>
<div id="wrapper">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/loginfo/SyslogList.do">	
<c:import url="/backoffice/inc/top_inc.do" />	
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" id="hid_resultInfo" />
<input type="hidden" id="hid_resultGubun" />
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu05" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu05_2" /></strong>
                    </div>
                </div>
            </div>
              
            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
					<select name="searchProcessGubun"  id="searchProcessGubun" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="S" <c:if test="${searchVO.searchProcessGubun == 'S' }"> selected="selected" </c:if>>
								<spring:message code="page.syslog.searchTxt_1" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchProcessGubun == 'I' }"> selected="selected" </c:if>>
								<spring:message code="page.syslog.searchTxt_2" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchProcessGubun == 'U' }"> selected="selected" </c:if>>
								<spring:message code="page.syslog.searchTxt_3" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchProcessGubun == 'D' }"> selected="selected" </c:if>>
								<spring:message code="page.syslog.searchTxt_4" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchProcessGubun == 'E' }"> selected="selected" </c:if>>
								<spring:message code="page.syslog.searchTxt_5" />
								</option>
					</select>
					<input type="text" class="nameB ssg_input" name="searchStartdday"  maxlength="10" id="searchStartdday"  value="${searchVO.searchStartdday }"/>
	                <input type="text" class="nameB ssg_input" name="searchEndday"  maxlength="10" id="searchEndday"  value="${searchVO.searchEndday }"/>		
	                <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="userId" <c:if test="${searchVO.searchCondition == 'userId' }"> selected="selected" </c:if>>
								<spring:message code="page.login.loginId" />
								</option>
								<option value="userNm" <c:if test="${searchVO.searchCondition == 'userNm' }"> selected="selected" </c:if>>
								<spring:message code="page.login.loginNm" />
								</option>
					</select>
                    <input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" placeholder="이름" value="${searchVO.searchKeyword }">
                    <a href="javascript:search_form()"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                </section>
            </div>
           
            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="page.syslog.methodNm" /></th>
                            <th><spring:message code="page.syslog.srvcNm" /></th>
                            <th><spring:message code="page.syslog.processSeCode" /></th>
                            <th><spring:message code="page.syslog.errorCode" /></th>
                            <th><spring:message code="page.syslog.rqesterIp" /></th>
                            <th><spring:message code="page.syslog.rqesterId" /></th>
                            <th><spring:message code="page.login.loginNm" /></th>
                            <th><spring:message code="page.login.loginPartNm" /></th>
                            <th><spring:message code="page.syslog.occrrncDe" /></th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach items="${resultList }" var="sysLoginfo" varStatus="status">
                        <tr>
                            <td>
							 <a href="#" onclick="fn_logView('${sysLoginfo.requst_id }')" data-needpopup-show="#sysinfo_pop">${sysLoginfo.method_nm }</a>
							</td>
							<td>
							 <a href="#" onclick="fn_logView('${sysLoginfo.requst_id }')"  data-needpopup-show="#sysinfo_pop">${sysLoginfo.method_nm }</a>
							</td>
							<td>${sysLoginfo.process_se_code_txt }</td>
							<td>${sysLoginfo.error_code }</td>
							<td>${sysLoginfo.rqester_ip }</td>
							<td>${sysLoginfo.rqester_id }</td>
							<td>${sysLoginfo.admin_name }</td>
							<td>${sysLoginfo.part_nm }</td>
							<td>${sysLoginfo.occrrnc_de }</td>
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

<div id='sysinfo_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2><spring:message code="page.syslog.title" /></h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.requstId" /> <span id="sysInfo_idTxt"></span></p>
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.methodNm" /> <span id="sysInfo_Method"></span></p>
                </div>   
            </div>
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.srvcNm" /> <span id="sysInfo_Service"></span></p>
                </div>                
            </div>
            
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.processSeCode" /> <span id="sysInfo_ProcessSe"></span></p>
                    	
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.processTime" /> <span id="sysInfo_ProcessTime"></span></p>
                </div>                
            </div>
            
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.rqesterIp" /> <span id="sysInfo_requestIp"></span></p>
                    	
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.rqesterId" /> <span id="sysInfo_requestId"></span></p>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.occrrncDe" /> <span id="sysInfo_occTime"></span></p>
                    	
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.syslog.errorCode" /> <span id="sysInfo_errorCode"></span></p>
                </div>                
            </div>
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">
                     <span id="sys_ParamInfo"></span>
                    </p>
                    <div style="overflow-y:auto; width:auto; height:30px;">
                    <span id="sysInfo_ServiceParam" ></span>
                    </div>
                </div>                
            </div>
           
            <div class="pop_box100" id="pop_resultDiv">
                <div class="padding15">
                    <p class="pop_tit">
                     <span id="sys_ResultInfo"></span>
                     </p>
                     <span id="sysInfo_methodParam"></span>
                </div>                
            </div>
            
            <div class="clear"></div>   
        </div>
        <div class="pop_footer">
            <a href="#" onclick="fn_needHid()"  class="redBtn" id="btnUpdate"><spring:message code="button.close" /></a>
        	<div class="clear"></div>
        </div>
    </div>
    
 <!--popup-->
    <script>  
        needPopup.config.custom = {
            'removerPlace': 'outside',
            'closeOnOutside': false,
            onShow: function() {
                console.log('needPopup is shown');
            },
            onHide: function() {
                console.log('needPopup is hidden');
            }
        };
        needPopup.init();
 </script>   
<script type="text/javascript" src="<c:url value="/js/calender.js" />" ></script> 	
<script type="text/javascript">	
	//코드 확인
	$(function () {
		CalenderFnc.prototype.getSet('searchStartdday','searchEndday');
	});	
	function fn_logView(requstId){
		//var param = "requestId=" + requstId;
		$("#hid_resultInfo").val("");
		
		var data = {'requstId': requstId};
		uniAjax("/backoffice/loginfo/SyslogInfo.do",  data, 
	        			function(result) {
		    	            if (result.status == "SUCCESS"){
		    	            	var obj = result.sysInfo;
							   	$("#sysInfo_idTxt").html(obj.REQUST_ID);
							   	$("#sysInfo_Method").html(obj.METHOD_NM);
							   	$("#sysInfo_Service").html(obj.SRVC_NM);
							   	$("#sysInfo_ProcessSe").html(obj.PROCESS_SE_CODE_TXT);
							   	$("#sysInfo_ProcessTime").html(obj.PROCESS_TIME);
							   	$("#sysInfo_requestIp").html(obj.RQESTER_IP);
							   	$("#sysInfo_requestId").html(obj.RQESTER_ID);
							   	$("#sysInfo_occTime").html(obj.OCCRRNC_DE);
							   	$("#sysInfo_errorCode").html(obj.ERROR_CODE);
							   	
							   	if (fn_trim(obj.PROCESS_SE_CODE) == "E"){
							   		$("#sys_ParamInfo").html('<spring:message code="page.syslog.sqlParam_e" />');
							   		$("#sys_ResultInfo").html('<spring:message code="page.syslog.methodResult_e" /> [상세보기]');
							   		$("#hid_resultGubun").val('E');
							   	}else{
							   		$("#sys_ParamInfo").html('<spring:message code="page.syslog.sqlParam" />');
							   		$("#sys_ResultInfo").html('<spring:message code="page.syslog.methodResult" /> [상세보기]');
							   		$("#hid_resultGubun").val('P');
							   	}
							   	if (fn_trim(obj.PROCESS_SE_CODE) == "E" || fn_trim(obj.PROCESS_SE_CODE) == "S"){
							   		$("#pop_resultDiv").show();
							   	}else {
							   		$("#pop_resultDiv").hide();
							   	}
							    
							    
							   	$("#sysInfo_ServiceParam").html(obj.SQL_PARAM);
							   	//$("#sysInfo_methodParam").html('[<spring:message code="button.detailInfo" />]');
							   	$("#hid_resultInfo").val(obj.METHOD_RESULT);
							    $("#sys_ResultInfo").attr("onclick", "fn_detail('S')")
							   	
							}
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	        	 );
			   return;
		
	}
	function fn_detail(ViewGubun){
		if (ViewGubun == "S" && $("#hid_resultInfo").val() != ""){
			$("#sys_ResultInfo").html('[ 상세 보기 닫기]');
			$("#sysInfo_methodParam").wrap("<div style='overflow-x:hidden; overflow-y:auto; width:auto; height:30px;'></div>");
			$("#sysInfo_methodParam").html($("#hid_resultInfo").val());
			$("#sys_ResultInfo").attr("onclick", "fn_detail('D')");
		}else {
			
			if ($("#hid_resultGubun").val() == "E"){
		   		$("#sys_ResultInfo").html('<spring:message code="page.syslog.methodResult_e" /> [상세보기]');
		   	}else{
		   		$("#sys_ResultInfo").html('<spring:message code="page.syslog.methodResult" /> [상세보기]');
		   	}
			$("#sysInfo_methodParam").unwrap();
			$("#sysInfo_methodParam").html('');
			$("#sys_ResultInfo").attr("onclick", "fn_detail('S')");
		}
		
		
	}
	function linkPage(pageNo) {
		AtenCommon.prototype.goLingPage(pageNo, "regist", "/backoffice/loginfo/SyslogList.do");
	}
	//조건 검색
	function search_form(){
		AtenCommon.prototype.goLingPage("1", "regist", "/backoffice/loginfo/SyslogList.do");
	}
	
    </script>
    
</body>
</html>