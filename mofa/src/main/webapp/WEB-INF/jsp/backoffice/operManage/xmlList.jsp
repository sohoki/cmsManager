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
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link rel="stylesheet" href="/css/needpopup.css" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script src="/js/popup.js"></script>    
    <link rel="stylesheet" href="/css/needpopup.min.css" />
  
</head>
<body>
<div id="wrapper">	
<form:form name="regist" commandName="regist" method="post" action="/backoffice/operManage/xmlList.do">	    	   
	<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
	<input type="hidden" name="mode" id="mode" >
	<input type="hidden" name="xmlSeq" id="xmlSeq" >	    
	<c:import url="/backoffice/inc/top_inc.do" />
	<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu04" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu04_3" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                    <form:select path="seachWorkGubun" id="seachWorkGubun" class="ssg_select" title="전문구분">
							<option value=""><spring:message code="combobox.text" /></option>
							<form:options items="${selectWorkgubun}" itemValue="code" itemLabel="codeNm"/>
					</form:select>	
					<select name="searchCondition" id="searchCondition" class="ssg_select">
						<option value=""><spring:message code="combobox.text" /></option>
						<option value="xmlProcessName" <c:if test="${searchVO.searchCondition == 'xmlProcessName' }"> selected="selected" </c:if>><spring:message code="page.xml.processId" /></option>
						<option value="processRemark" <c:if test="${searchVO.searchCondition == 'processRemark' }"> selected="selected" </c:if>><spring:message code="page.xml.processRemark" /></option>
					</select>
					<input type="text" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}" class="nameB ssg_input">                    
                    <a href="javascript:search_form();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    <div class="rightB">
                        <a href="javascript:view_Xml('Ins','0')" ><span class="deepBtn"><spring:message code="button.create" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
					<thead>
						<tr>							
							<th><spring:message code="common.Number.title" /></th>
							<th><spring:message code="page.xml.sendGubun" /></th>
							<th><spring:message code="page.xml.processId" /></th>
							<th><spring:message code="page.xml.processJob" /></th>
							<th><spring:message code="page.xml.ViewSendJson" /></th>									
							<th><spring:message code="page.xml.ViewRecJson" /></th>
							<th><spring:message code="button.confirm" /></th>	
							<th><spring:message code="button.delete" /></th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${resultList }" var="xmlinfo" varStatus="status">
						<tr>
							<td><c:out value="${ totalCnt - (((searchVO.pageIndex - 1) * searchVO.pageSize) + status.count) + 1}"/></td>
							<td style="text-align:left"><A href="javascript:view_Xml( 'Viw', '${xmlinfo.xmlSeq  }' )">  ${xmlinfo.codeNm  }</A></td>
							<td style="text-align:left"><A href="javascript:view_Xml( 'Viw', '${xmlinfo.xmlSeq  }' )"  title="<c:out value="${  xmlinfo.xmlProcessName }" /> ">  <c:out value="${ fn:substring( xmlinfo.xmlProcessName, 0, 20) }" /> </A></td>
							<td style="text-align:left"><c:out value="${ fn:substring( xmlinfo.processRemark, 0, 15) }" /> </td>
							<td class="md_btn">
							<a href="#" onclick="javascript:open_JsonView('I','${xmlinfo.xmlSeq}')" data-needpopup-show="#groupPop" class="brownBtn">미리보기</a></td>							
							<td class="md_btn">
							<a href="#" onclick="javascript:open_JsonView('O','${xmlinfo.xmlSeq}')" data-needpopup-show="#groupPop" class="brownBtn" >미리보기</a></td>
							<td>
							<c:choose>
							   <c:when test="${xmlinfo.testOk eq 'Y' }"><spring:message code="button.confirm" /></c:when>
							   <c:otherwise><spring:message code="button.undefine" /></c:otherwise>
							</c:choose>		
							</td>
							<td class="md_btn"><a href="javascript:fn_del('${xmlinfo.xmlSeq  }')" class="delkey"><spring:message code="button.delete" /></a></td>
						</tr>			
						</c:forEach>
						<c:if test="${fn:length(resultList) == 0 }">
						<tr>
						  <td colspan="8"><spring:message code="page.xml.noRecorder" /></td>
						</tr>
						</c:if>
					</tbody>
				</table>
            </div>
            <div class="new_pagenum">
                <div class="new_pager">
                	<ol>
                		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage"  />
                   </ol>
                </div>
            </div>
        </div>
    </div>
	
	
	<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
    <div id='groupPop' class="needpopup">
        <div class="user_top">
            <p><span id="sp_title"></span></p>
        </div>
        <div class="user_info">
            <table class="user_noti">
                <tbody>
                    <tr>
                        <th>전문</th>
                        <td>
                         <span id="sp_json"></span>
                        </td>
                    </tr>
                </tbody>
            </table>
            <div class="clear"></div>
        </div>
    </div>
   
</div>
<script  type="text/javascript" src="/js/needpopup.min.js" ></script> 	
<script type="text/javascript">
     function view_Xml(code, code1){
    	  $('#mode').val(code);
 		  $('#xmlSeq').val(code1);
 		  $("form[name=regist]").attr("action", "/backoffice/operManage/xmlView.do").submit();			 
 	  }
     function fn_del(xmlSeq){
    		fn_uniDel("/sohoki/util/UnideleteParam.do"
  				  , {tableId: "tb_sendmessagetypr", conField: "XML_SEQ", value : xmlSeq }
  		          , "/backoffice/operManage/xmlList.do");	
     }
     //검색 
     function search_form(){
    	   $("#pageIndex").val("1");
    	   $("form[name=regist]").attr("action", "/backoffice/operManage/xmlList.do").submit();
     }   
  	 function linkPage(pageNo) {
 		$(":hidden[name=pageIndex]").val(pageNo);		
 		$("form[name=regist]").attr("action", "/backoffice/operManage/xmlList.do").submit();
 	 }	
     function open_JsonView(code, code1){
  	      
 		  var url = code == "I" ? "/backoffice/operManage/jsonAuthView.do?xmlSeq="+code1 : "/backoffice/operManage/jsonAuthReq.do?xmlSeq="+code1;
 		  var title = code == "I" ? '<spring:message code="page.xml.ViewSendJson" />' : '<spring:message code="page.xml.ViewRecJson" />';
 		 alert(code +":" + url);
 		  uniAjax(url, null, 
	     			function(result) {
					       if (result.status == "LOGIN FAIL"){
					    	   alert(result.meesage);
	  						   location.href="/backoffice/login.do";
	  					   }else if (result.status == "SUCCESS"){
	  						    //여기 부분 수정 어떻게 할지 추후 생각
	  						   $("#sp_title").html(title);
	 						   $("#sp_json").html(result.json_res);
	  					   }else {
	  						 $("#sp_title").html("애러");
	 						 $("#sp_json").html("");
	  					   }
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	      );
 		    	   
     }
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
</body>
</html>