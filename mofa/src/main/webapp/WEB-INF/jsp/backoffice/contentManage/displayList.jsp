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
    <link rel="stylesheet" href="/css/needpopup.min.css" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/cms_display.js"></script>
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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/displayList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="displaySeq" id="displaySeq" >

<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu02" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu02_2" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                    <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value="0" <c:if test="${searchVO.searchCondition == '' }"> selected="selected" </c:if>><spring:message code="combobox.text" /></option>
								<option value="displayTitle" <c:if test="${searchVO.searchCondition == 'displayTitle' }"> selected="selected" </c:if>>
								<spring:message code="page.display.displayNm" />
								</option>
								<option value="displayRemark" <c:if test="${searchVO.searchCondition == 'displayRemark' }"> selected="selected" </c:if>>
								<spring:message code="page.display.displayRemarak" />
								</option>
					</select>
					<input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">                    
                    <a href="javascript:linkPage(1);">
                    <span class="redBtn">
                    <spring:message code="button.inquire" />
                    </span>
                    </a>
                    <div class="rightB">
                        <a href="#"  data-needpopup-show="#displayPop" onClick="javascript:fn_ViewDis('Ins','0')" >
                        <span class="deepBtn">
                        <spring:message code="button.create" />
                        </span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="common.Number.title" /></th>
                            <th><spring:message code="page.display.title" /></th>
                            <th><spring:message code="page.display.agentCnt" /></th>
                            <th><spring:message code="page.display.contentCnt" /></th>
                            <th><spring:message code="page.display.playTotalCnt" /></th>
                            <th><spring:message code="common.UseYn.title" /></th>
                            <th><spring:message code="button.preview" /></th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${resultList }" var="displayInfo" varStatus="status">
                        <tr>
                            <td><c:out value="${ totalCnt - (((searchVO.pageIndex - 1) * searchVO.pageSize) + status.count) + 1}"/></td>
                            <td><a href="javascript:fn_ViewDis('Edt','${ displayInfo.display_seq }')" class="underline" >${ displayInfo.display_title }</a></td>
                            <td><a href="javascript:fn_agentList('${ displayInfo.display_seq }')" class="underline">${displayInfo.reporttitle }</a></td>
                            <td><a href="#"  onClick="fn_disPlayView('${ displayInfo.display_seq }')"  data-needpopup-show="#agent_pop" >  ${displayInfo.displaypagecnt } </a></td>
                            <td>${displayInfo.displaytotaltime }초</td>
                            <td>${displayInfo.displayuseyn }</td>
                            <td><a href="javascript:fn_agentPreView('${ displayInfo.display_seq }','${ displayInfo.display_title }','${ displayInfo.display_gubun }')"  class="grayBtn">미리보기</a></td>                            
                            <td><a href="javascript:fn_delCheck('${ displayInfo.display_seq }');" class="grayBtn">삭제</a></td>
                        </tr>
                      </c:forEach>                        
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
    
    
    <div id='displayPop' class="needpopup">
        <div class="popHead">
            <h2>화면구성 등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.display.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input path="displayTitle" id="displayTitle" class="input_noti" size="100"/>
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.display.displayRemarak" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input  path="displayRemark" size="100" maxlength="200" id="displayRemark" class="input_noti" value="${regist.displayRemark }" />
                </div>                
            </div>
            
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.display.displayGubun" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="displayGubun" id="displayGubun" class="popSel" title="소속" onclick='fn_disGubun()'>
						     <option value=""><spring:message code="combobox.text" /></option>
	                         <form:options items="${selectDisplayCombo}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>	
				        
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.common.UseYn" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="radio" name="displayUseYn" id="displayUseYn_Y" value="Y" <c:if test="${regist.displayUseYn == 'Y' }"> checked </c:if> />
                    <label for="displayUseYn_Y"><spring:message code="button.use" /></label>
					<input type="radio" name="displayUseYn" id="displayUseYn_N" value="N" <c:if test="${regist.displayUseYn == 'N' }"> checked </c:if> />
					<label for="displayUseYn_N"><spring:message code="button.nouse" /></label>
                </div>                
            </div>
            <!--  권한 관련 내용 추가 -->
            <c:choose>
            <c:when test="${userInfo.adminLevel eq 'ROLE_ADMIN'}">
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.emp.userAuth" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="adminLevel" id="adminLevel" class="popSel" title="소속" onClick="javascript:fn_ComboView()">
						<form:option value="" label='--선택하세요--'/>
						<form:options items="${selectState}" itemValue="authorCode" itemLabel="authorNm"/>
				    </form:select>	
                </div>                
            </div>
            
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"> <spring:message code="page.emp.part" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="partId" id="partId" class="popSel" title="소속">
						<form:option value="" label='--선택하세요--'/>
						<form:options items="${selectGroup}" itemValue="partId" itemLabel="partNm"/>
				    </form:select>
                </div>                
            </div>
            </c:when>
            <c:otherwise> 
                <form:hidden path="adminLevel" id="adminLevel" />
                <form:hidden path="partId" id="partId" />
            </c:otherwise>
            </c:choose>

            <!--  권한 관련 내용 추가  -->
            <div class="pop_box50" id="divNext">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.display.nextSeq" /> <span class="join_id_comment joinSubTxt"></span></p>
				    <form:select path="displayNextseq" id="displayNextseq" class="popSel" title="소속">
						<option value=""><spring:message code="combobox.text" /></option>
						<form:options items="${selectDisplayNextCombo}" itemValue="displaySeq" itemLabel="displayTitle"/>
				    </form:select>	
                </div>                
            </div>
            <div class="pop_box50" id="divWH">
                <div class="padding15">
                    	<p class="pop_tit">*<spring:message code="page.display.disWH" /> <span class="join_id_comment joinSubTxt"></span></p>
				      	가로 : <form:input  path="displayWidth" size="5" maxlength="10" id="displayWidth" class="inputhalf30 input_noti" value="${regist.displayWidth }" onkeypress="only_num();" />
                    	세로 : <form:input  path="displayHeight" size="5" maxlength="200" id="displayHeight" class="inputhalf30 input_noti" value="${regist.displayHeight }" onkeypress="only_num();" />
                </div>                
            </div>            
        </div>
        <div class="clear"></div>
         <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후 클릭해주세요.</span>
            <a href="javascript:fn_checkForm();" class="redBtn" id="btnUpdate"><spring:message code="button.create" /></a>    
        </div>   
    </div> 
</form:form>
<c:import url="/backoffice/inc/bottom_inc.do" />
</div>


<script type="text/javascript">
    function fn_delCheck(code){
    	fn_uniDel("/backoffice/contentManage/displayDelete.do"
				  , "displaySeq="+ code
		          , "/backoffice/contentManage/displayList.do");	
    }
    function fn_agentList(code){
		var params = {
				pageIndex : "1",
				searchCondition : "disSeq",
				searchKeyword : code
		};
		fn_doPost("regist", params , "/backoffice/basicManage/AgentInfoList.do");
    }
    function linkPage(pageNo) {
    	$("#pageIndex").val(pageNo);
		var params = { pageIndex : $("#pageIndex").val(),
							searchCondition : $("#searchCondition").val(),
							searchKeyword : $("#searchKeyword").val()};
		fn_doPost("regist", params , "/backoffice/contentManage/displayList.do");
	}
</script>

</body>
</html>