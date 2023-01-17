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
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script src="/js/popup.js"></script>
    
    <!-- TOAST UI Grid End-->
</head>
<body>
<div id="wrapper">	
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/schInfoList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="schCode" id="schCode" >

<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu02" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu02_4" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                    
						    
                    <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="schTitle" <c:if test="${searchVO.searchCondition == 'schTitle' }"> selected="selected" </c:if>>스케줄명</option>
								<option value=schMessage <c:if test="${searchVO.searchCondition == 'schMessage' }"> selected="selected" </c:if>>메세지내용</option>
					</select>
					<input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">                  
                    <a href="javascript:search_form();"><span class="redBtn">
                    <spring:message code="button.inquire" />
                    </span></a>
                    <div class="rightB">
                        <a href="javascript:view_sch('Ins','0')" >
                        <span class="deepBtn">
                        <spring:message code="button.create" />
                        </span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <div id="grid"></div>
                
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="common.Number.title" /></th>
                            <th><spring:message code="page.schMessage.title" /></th>
                            <th><spring:message code="page.schMessage.strDay" /></th>
                            <th><spring:message code="page.schMessage.endDay" /></th>
                            <th><spring:message code="page.schMessage.schTime" /></th>
                            <th><spring:message code="page.schMessage.fontSize" /></th>   
                            <th><spring:message code="common.UseYn.title" /></th>                            
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${resultList }" var="schInfo" varStatus="status">
                        <tr>
                            <td><c:out value="${ totalCnt - (((searchVO.pageIndex - 1) * searchVO.pageSize) + status.count) + 1}"/></td>
                            <td><a href="javascript:view_sch('Edt','${ schInfo.schCode }')" class="underline" >${ schInfo.schTitle }</a></td>
                            <td><a href="javascript:view_sch('Edt','${ schInfo.schCode }')" class="underline" >${schInfo.schEndDay}</a></td>
                            <td><a href="javascript:view_sch('Edt','${ schInfo.schCode }')" class="underline" >${schInfo.schStartDay}</a></td>
                            <td>${schInfo.schStartTime}~${schInfo.schEndTime}</td>
                            <td>${schInfo.schFonttype }</td> 
                            <td>${schInfo.schUseYn }</td>                            
                            <td><a href="javascript:del_check('${ schInfo.schCode }');" class="grayBtn">삭제</a></td>
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
<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
</div>
<script type="text/javascript">
   $(document).ready(function() { 
							   
	});
    //컬럼 값 변경 
    function createColumns(localeCode) {
        var columnData = columnMap[localeCode];
        var columns = [];
        _.each(columnData, function(title, name) {
            columns.push({
                title: title,
                name: name
            });
        });

        return columns;
    }
    function del_check(code){
         alert(code);
         $(":hidden[name=pageIndex]").val('1');
    	 fn_uniDelJSON("/backoffice/contentManage/schInfoDelete.do"
   					  , {schCode : code}
   			          , "/backoffice/contentManage/schInfoList.do");
    }
    function linkPage(pageNo) {
        $(":hidden[name=pageIndex]").val(pageNo);
        $("form[name=regist]").attr("action", "/backoffice/contentManage/schInfoList.do").submit();	
    }
    function view_sch(code, code1){
        $('#mode').val(code);
        $('#schCode').val(code1);
        $("form[name=regist]").attr("action", "/backoffice/contentManage/schInfoView.do").submit();
    }
    function search_form(){		  
        $("form[name=regist]").attr("action", "/backoffice/contentManage/schInfoList.do").submit();		  
    }
</script>
</body>
</html>