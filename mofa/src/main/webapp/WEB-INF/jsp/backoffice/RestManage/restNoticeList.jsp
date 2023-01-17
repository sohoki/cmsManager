<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page import ="egovframework.com.cmm.AdminLoginVO" %>
<% AdminLoginVO loginVO = (AdminLoginVO)session.getAttribute("AdminLoginVO"); %>
<!DOCTYPE html">
<html>
<head>
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/rest/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
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
<form:form name="regist" commandName="regist" method="post" action="/backoffice/RestManage/restPageInfoList.do">
<div id="wrapper">	
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="noteSeq" id="noteSeq" >


<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <span><spring:message code="menu.menu05" /></span>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                  <select name="searchCondition"  id="searchCondition">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="noteTitle" <c:if test="${searchVO.searchCondition == 'noteTitle' }"> selected="selected" </c:if>>제목</option>
								<option value="noteContent" <c:if test="${searchVO.searchCondition == 'noteContent' }"> selected="selected" </c:if>>내용</option>
								
					</select>
					<input class="nameB" type="text" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">                    
                    <a href="javascript:search_form();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    <div class="rightB">
                        <a href="#" onclick="fn_menuInfo('Ins','0')"><span class="deepBtn"><spring:message code="button.create" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>공지글</th>
                            <th>등록일</th>
                            <th>계시일자</th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${resultList }" var="restInfo" varStatus="status">
                        <tr>
                            <td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
                            <td><a href="#" onclick="fn_menuInfo('Edt','${ restInfo.noteSeq }')">${restInfo.noteTitle }</a></td>
                            <td>${restInfo.lastUpdtPnttm }</td>
                            <td>${restInfo.noteStartdate }~${restInfo.noteEnddate }</td>
                            <td><a href="javascript:del_check('${ restInfo.noteSeq }');" class="grayBtn">
                                <spring:message code="button.delete" />
                                </a>
                            </td>
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
 </div>   
<c:import url="/backoffice/inc/bottom_inc.do" />
<script type="text/javascript">
   function fn_menuInfo(mode, noteSeq){
		$("#noteSeq").val(noteSeq);
		$("#mode").val(mode);
		$("form[name=regist]").attr("action","/backoffice/RestManage/restNoticeDetail.do").submit(); 
   }
   function del_check(code){	
    	fn_uniDelJSON("/backoffice/RestManage/restNoticeDelete.do"
				  , {noteSeq : code}
		          , "/backoffice/RestManage/restNoticeList.do");	
   }  
   function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);	
		$("form[name=regist]").attr("action","/backoffice/RestManage/restNoticeList.do").submit();
   }
   function search_form(){	
		  $(":hidden[name=pageIndex]").val("1");	
		  $("form[name=regist]").attr("action", "/backoffice/RestManage/restNoticeList.do").submit();		  
   }
</script>

</form:form>
</body>
</html>