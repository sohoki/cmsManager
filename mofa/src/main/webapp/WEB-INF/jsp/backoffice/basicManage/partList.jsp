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
    <link rel="stylesheet" href="/css/needpopup.min.css" />
    <link rel="stylesheet" type="text/css" href="/css/needpopup.css">
</head>
<body>
<div id="wrapper">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/basicManage/partList.do">	   	  
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" id="mode">
<input type="hidden" id="partId">
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu03" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu03_1" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                   <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="partId" <c:if test="${regist.searchCondition == 'partId' }"> selected="selected" </c:if>>부서아이디</option>
								<option value="partNm" <c:if test="${regist.searchCondition == 'partNm' }"> selected="selected" </c:if>>부서명</option>
							</select>	
                    <input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" placeholder="이름" value="${regist.searchKeyword }">
                    <a href="javascript:search_form()"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    <div class="rightB">
                        <a href="#"  data-needpopup-show="#groupPop" onclick="javascript:fn_View('Ins','0');" ><span class="deepBtn"><spring:message code="button.create" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="page.group.groupId" /></th>
                            <th style="width:300px;"><spring:message code="page.group.groupNm" /></th>
                            <th><spring:message code="page.group.groupCreaetTime" /></th>
                            <th><spring:message code="page.group.parentGroupId" /></th>
                            <th><spring:message code="page.group.groupUseYn" /></th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach items="${resultList }" var="partInfo" varStatus="status">
                        <tr>
                            <td>${ partInfo.partId }</td>
                            <td style="text-align:left">
                                <pre><a href="#" onclick="javascript:fn_View('Edt','${ partInfo.partId }')" class="underline" data-needpopup-show="#groupPop">${ partInfo.partNm }</a></pre>
                            </td>
                            <td>${ partInfo.partCreateDe }</td>
                            <td>${ partInfo.parentPartId }</td>
                            <td>${ partInfo.partUseyn }</td>
                            <td><a href="javascript:del_check('${ partInfo.partId }');" class="grayBtn"><spring:message code="button.delete" /></a></td>
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
    
    
    <div id='groupPop' class="needpopup optionPop">
        <div class="popHead">
            <h2>부서 관리</h2>
        </div>
        <div class="popCon">
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*부서명</p>
                    <input type="text" class="width100 input_noti" id="partNm" name="partNm">
                </div>
            </div>
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*사용유무</p>
                    <input type="radio" name="partUseyn" id="partUseyn_Y" value="Y">
                    <label for="partUseyn_Y">사용</label>
                    <input type="radio" name="partUseyn" id="partUseyn_N" value="N">
                    <label for="partUseyn_N">사용안함</label>
                </div>
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*상위부서코드</p>
                    <form:select path="parentPartId" id="parentPartId" class="popSel" title="소속">
                                 <option value=""><spring:message code="combobox.text" /></option>
                                 <form:options items="${selectGroupCombo}" itemValue="partId" itemLabel="partNm"/>
                            </form:select>
                </div>
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*정렬순서</p>
                    <form:select path="partOrder" id="partOrder" class="popSel" title="소속">
                                 <option value=""><spring:message code="combobox.text" /></option>
                                 <c:forEach var="item" varStatus="i" begin="1" end="10" step="1">
                                   <option value="${item}">
                                    <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
                                   </option>
                                 </c:forEach>
                    </form:select>
                </div>
            </div>
            <div class="clear"></div>
        </div>
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span>
            <a href="javascript:fn_checkForm();" class="redBtn" id="btnUpdate">등록</a>
            <div class="clear"></div>
        </div>
    </div>
    
    <div id='okLogin' class="needpopup close_black">
        <div class="check_pop">
            <p><span id="sp_message"></span></p>
        </div>
    </div>
    </div>
    <c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>    
</div>

<script type="text/javascript">
	function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").attr("action","/backoffice/basicManage/partList.do").submit();
	}
	function search_form(){
		linkPage("1");
	}
	$(document).ready(function() {   
		if ("${status}" != ""){
			if ("${status}" == "SUCCESS") {
				alert('<spring:message code="success.common.update" />');	
			}else{
				alert('<spring:message code="fail.common.msg" />');	
			}						
		}		
		
	});	
	function del_check(code){
		fn_uniDel("/sohoki/util/UnideleteParam.do"
				  , {tableId: "tb_partinfo", conField: "PART_ID", value : code }
		          , "/backoffice/basicManage/partList.do");				
	}  
	
	function fn_View(mode, partId){
	   $("#mode").val(mode);
	   if (mode=="Edt"){
		   $("#partId").val(partId);
		   var param = {
	                'partId' : partId,
	                'mode' : mode
	            };
		   var parentPartId = "";
           $.ajax({
	            url: "/backoffice/basicManage/partDetail.do",
	            type : "POST",
	            contentType : "application/json; charset=utf-8",
	            data : JSON.stringify(param),
	            success : function(obj) {
	            	
	            	
	            	$("#partNm").val(obj.partNm); 
	            	$("#parentPartId").val(obj.parentPartId);
	            	$("#partOrder").val(obj.partOrder);
	            	$('input:radio[name=partUseyn]:input[value=' + obj.partUseyn + ']').attr("checked", true);	            	
	            },
	            error: function (jqXHR, textStatus, errorThrown){
		            alert(textStatus + ":" + errorThrown);
	            }
	            
           });
         
           $("#btn_Update").text('<spring:message code="button.update" />');
	   }else{
		   AtenCommon.uniElementValue("partNm,parentPartId,partOrder", "");
		   $("#btn_Update").text('<spring:message code="button.update" />');
	   }	
	}
	function fn_checkForm(){
		if (any_empt_line_id('partNm', '<spring:message code="page.group.alert01" />') == false) return;
		if (any_empt_line_id('parentPartId', '<spring:message code="page.group.alert02" />') == false) return;
		var param = {
                'partId' : $("#partId").val(),
                'partNm' : $("#partNm").val(),
                'partUseyn' :  $('input[name="partUseyn"]:checked').val(),
                'parentPartId' : $("#parentPartId").val(),
                'partOrder' : $("#partOrder").val(),
                'mode' : $("#mode").val()
            };
            $.ajax({
                type : 'POST',
                url : '/backoffice/basicManage/partUpdate.do',
                contentType : "application/json; charset=utf-8",
                beforeSend:function(jxFax, settings){
    	        	jxFax.setRequestHeader('AJAX', true);
    	        },
                data : JSON.stringify(param),
                success : function(response) {
                   if (response > 0 ){
                	   alert("정상적으로 등록 되었습니다");
                   }else {
                	   alert("등록중 문제가 발생 하였습니다");
                   }
                  // location.reload();
                },
                error : function(e) {
                    alert("ERROR : " + e.statusText);
                }
            });
    }

    </script>
</body>
</html>
