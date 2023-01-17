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
    <link href="/css/reset.css" rel="stylesheet" />
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
<input type="hidden" name="menuSeq" id="menuSeq" >


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
                    <form:input  path="searchStartDay" size="10" maxlength="10" id="searchStartDay"  />~
	                <form:input  path="searchEndDay" size="10" maxlength="10" id="searchEndDay"  />	  
	                <form:select path="searchMenugubun" id="searchMenugubun" title="소속">
							     <option value=""><spring:message code="combobox.text" /></option>
		                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
					</form:select> 	    
                    <select name="searchCondition"  id="searchCondition">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="menuInfo" <c:if test="${searchVO.searchCondition == 'menuInfo' }"> selected="selected" </c:if>><spring:message code="page.rest.title" /></option>
								<option value="centerNm" <c:if test="${searchVO.searchCondition == 'centerNm' }"> selected="selected" </c:if>><spring:message code="page.center.centerNm" /></option>
								
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
                            <th><spring:message code="page.rest.restImg" /></th>
                            <th><spring:message code="page.center.centerNm" /></th>
                            <th><spring:message code="page.rest.date" /></th>
                            <th><spring:message code="page.rest.title" /></th>
                            <th><spring:message code="page.rest.menuGubun" /></th>
                            <th><spring:message code="page.rest.grad01" /></th>
                            <th><spring:message code="page.rest.grad02" /></th>
                            <th><spring:message code="page.rest.grad03" /></th>
                            <th><spring:message code="page.rest.grad04" /></th>
                            <th><spring:message code="page.rest.grad05" /></th>
                            <th><spring:message code="page.rest.useYn" /></th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${resultList }" var="restInfo" varStatus="status">
                        <tr>
                            <td>
                             <a href="javascript:fn_menuInfo('Edt','${restInfo.menuSeq }')">
								<c:choose>
						                <c:when test="${restInfo.menuImg eq ''}">
						                  <img id="atchFileId" name="atchFileId" src="/images/no_image.png"  width="100" height="60"/>
						                </c:when>
						        		<c:otherwise>
						        		  <img src="<c:url value='/upload/${restInfo.menuImg}' />" alt="${restInfo.menuInfo }" width="150px;" />
						        		</c:otherwise>					        							        
						        </c:choose>
							 </a>
                            </td>
                            <td>${restInfo.centerNm }</td>
                            <td>${restInfo.menuDate }</td>
                            <td><a href="#" onclick="fn_menuInfo('Edt','${ restInfo.menuSeq }')">${ restInfo.menuInfo}</a></td>
                            
                            <td>${restInfo.menuGubunTxt }</td>
                            <td>${restInfo.menuStep01Value }</td>
                            <td>${restInfo.menuStep02Value }</td>
                            <td>${restInfo.menuStep03Value }</td>
                            <td>${restInfo.menuStep04Value }</td>
                            <td>${restInfo.menuStep05Value }</td>
                            <td>${restInfo.menuUseyn }</td>
                            <td><a href="javascript:del_check('${ restInfo.menuSeq }');" class="grayBtn">
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
    
<c:import url="/backoffice/inc/bottom_inc.do" />

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
	    $("#searchStartDay").datepicker(clareCalendar);
	    $("#searchEndDay").datepicker(clareCalendar);
	    $("#menuDate_P").datepicker(clareCalendar);
	    $("img.ui-datepicker-trigger").attr("style", "margin-left:3px; vertical-align:middle; cursor:pointer;"); //이미지버튼 style적용
	    $("#ui-datepicker-div").hide(); //자동으로 생성되는 div객체 숨김
	});	
   function fn_menuInfo(mode, menuSeq){
		$("#menuSeq").val(menuSeq);
		$("#mode").val(mode)
		//url = (mode == "Ins") ? "/backoffice/basicManage/centerDetail.do" : "/backoffice/basicManage/centerView.do";
		
		$("form[name=regist]").attr("action","/backoffice/RestManage/restDetail.do").submit(); 
   }
   function del_check(code){	
    	fn_uniDelJSON("/backoffice/RestManage/restPageInfoDelete.do"
				  , {menuSeq : code}
		          , "/backoffice/RestManage/restPageInfoList.do");	
   }  
   /* function fn_CheckForm(){
	   if (any_empt_line_id("menuInfo_P", '<spring:message code="page.rest..alert01" />') == false) return;
	   if (any_empt_line_id("menuGubun_P", '<spring:message code="page.rest..alert02" />') == false) return;
	   if (any_empt_line_id("centerId_P", '<spring:message code="page.rest..alert03" />') == false) return;
	   if (any_empt_line_id("menuDate_P", '<spring:message code="page.rest..alert04" />') == false) return;
	   
	   $("#menuInfo").val($("#menuInfo_P").val());
	   $("#menuGubun").val($("#menuGubun_P").val());
	   $("#centerId").val($("#centerId_P").val());
	   $("#menuDate").val($("#menuDate_P").val());
	   
	   $("#menuUseyn").val("Y");
	   
	   alert($("#menuImg_P").val());
	   
	   $("#menuImg").val($("#menuImg_P").val());
	   
	   alert($("#menuImg").val());
	   
	  
	  
	   document.regist.encoding = "multipart/form-data";
	   $("form[name=regist]").attr("action", "/backoffice/basicManage/restPageInfoUpdate.do").submit();
	  
	   
   } */
   function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);	
		$("form[name=regist]").attr("action","/backoffice/RestManage/restPageInfoList.do").submit();
   }

   function search_form(){	
		  $(":hidden[name=pageIndex]").val("1");	
		  $("form[name=regist]").attr("action", "/backoffice/RestManage/restPageInfoList.do").submit();		  
   }
</script>

</form:form>
</body>
</html>