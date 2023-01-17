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
    <link rel="stylesheet" type="text/css" href="/css/needpopup.css">
</head>
<body>
<div id="wrapper">
<noscript>자바스크립트를 지원하지 않는 브라우저에서는 일부 기능을 사용하실 수 없습니다.</noscript>
<form:form name="regist" commandName="regist" method="post" action="/backoffice/basicManage/empList.do">	   	  
<c:import url="/backoffice/inc/top_inc.do" />	
 
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu04" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu04_1" /></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                   <select name="searchCondition" id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="adminId" <c:if test="${searchVO.searchCondition == 'adminId' }"> selected="selected" </c:if>>아이디</option>
								<option value="adminName" <c:if test="${searchVO.searchCondition == 'adminName' }"> selected="selected" </c:if>>이름</option>
							</select>	
                    <input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" placeholder="이름" value="${searchVO.searchKeyword }">
                    <a href="javascript:search_form()"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    <div class="rightB">
                        <a href="#"  onclick="fn_MBER('Ins','0')" data-needpopup-show="#user_popInfo"  ><span class="deepBtn"><spring:message code="button.create" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="common.Number.title" /></th>
                            <th><spring:message code="page.emp.usrid" /></th>
                            <th><spring:message code="page.emp.title" /></th>
                            <th><spring:message code="page.emp.part" /></th>
                            <th><spring:message code="page.emp.usertel" /></th>
                            <th><spring:message code="page.emp.userEmail" /></th>
                            <th><spring:message code="page.emp.passLock" /></th>
                            <th><spring:message code="page.emp.userReg" /></th>
                            <th><spring:message code="button.use" /></th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                       <c:forEach items="${resultList }" var="userinfo" varStatus="status">
                        <tr>
                            <td><c:out value="${(searchVO.pageIndex - 1) * searchVO.pageSize + status.count}"/></td>
                            <td><a href="#" onclick="fn_MBER('Edt','${ userinfo.adminId }')" class="underline" data-needpopup-show="#user_popInfo">${ userinfo.adminId }</a></td>
                            <td>${ userinfo.adminName }(${ userinfo.empNo })</td>
                            <td>${ userinfo.partNm }</td>
                            <td>${ userinfo.adminTel }</td>
                            <td>${ userinfo.adminEmail }</td>
                            <td>${ userinfo.lockYn }</td>
                            <td>${ userinfo.regDate }</td>
                            <td>${ userinfo.useYn }</td>
                            <td><a href="javascript:del_check('${ userinfo.adminId }');" class="grayBtn"><spring:message code="button.delete" /></a></td>
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
   


<div id='user_popInfo' class="needpopup optionPop">
        <!-- popheader-->                        
        <div class="popHead">
            <h2>사용자 관리</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.emp.usrid" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="text" name="adminId" style="width:40%" maxlength="20" id="adminId" class="ssg_input"/>
                    <span id="uniCheck" ></span>
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.emp.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                     <input type="text" name="adminName" style="width:40%" maxlength="20" id="adminName" class="ssg_input"/> 
                     <input type="text" name="empNo" style="width:40%" maxlength="20" id="empNo" class="ssg_input"/>
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.emp.usertel" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="text" name="adminTel" size="15" maxlength="25" id="adminTel" class="ssg_input"/> 
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.emp.userEmail" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="text" name="adminEmail" size="30" maxlength="125" id="adminEmail" class="ssg_input"/> 
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.emp.password" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="password" name="adminPassword" size="20" maxlength="20"  id="adminPassword"  class="ssg_input" title='<spring:message code="page.emp.password"/>'/>
                </div>                
            </div>
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit"><spring:message code="page.emp.passwordCk" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="password" name="adminPassword2" id="adminPassword2" class="ssg_input" title='<spring:message code="page.emp.passwordCk"/>' type="password" size="20" maxlength="20" />
                </div>                
            </div>
<%--             <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                   <p class="pop_tit"><spring:message code="page.emp.userReg" /> <span class="join_id_comment joinSubTxt"></span></p>
                   <span id="reg_date"></span>
                </div>   
            </div> --%>

            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.emp.userAuth" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="adminLevel" id="adminLevel" class="ssg_select" title="소속" onClick="javascript:fn_ComboView()">
						         <form:option value="" label='--선택하세요--'/>
		                         <form:options items="${selectState}" itemValue="authorCode" itemLabel="authorNm"/>
				    </form:select>
                </div>   
            </div>
            
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.emp.part" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="partId" id="partId" class="ssg_select" title="소속">
						         <form:option value="" label='--선택하세요--'/>
		                         <form:options items="${selectGroup}" itemValue="partId" itemLabel="partNm"/>
				   </form:select>
                </div>                
            </div>
                        
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="common.UseYn.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="radio" name="useYn" value="Y"  id="useYn_Y"/><label for="useYn_Y"><spring:message code="button.use" /></label>
					<input type="radio" name="useYn" value="N"  id="useYn_N"/><label for="useYn_N"><spring:message code="button.nouse" /></label>
                </div>                
            </div>
            <div class="clear"></div>   
        </div>
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span>
            <a href="javascript:check_form();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
        	<div class="clear"></div>
        </div>
    </div>
 <c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
</div>    	
<script type="text/javascript">
	function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);		
		$("form[name=regist]").attr("action","/backoffice/basicManage/empList.do").submit();
	}
	function fn_MBER(mode, adminId){
		$("#mode").val(mode);
		 if ($("#mode").val() == "Ins"){   
		 		$("form[name=regist]").append("<input type='hidden'  id='idCheck' name='idCheck' />");	
		 		$("#adminId").val("");
		 		$("#uniCheck").html('<a href="javascript:check_id();" class="deepBtn"><spring:message code="page.emp.userCheck" /></a>');
		 		$("#btnUpdate").text('<spring:message code="button.create" />');
		 		$("#partId").hide();
		    }	else {
		    	
		    	  url= "/backoffice/basicManage/managerCheck.do";
				  var param = {
			                'mode' : $('#mode').val(),
			                'adminId' :adminId
			      };
				  uniAjax(url, param, 
		     			function(result) {
						       if (result.status == "LOGIN FAIL"){
						    	   alert(result.meesage);
		  						   location.href="/backoffice/login.do";
		  					   }else if (result.status == "SUCCESS"){
		  						    //여기 부분 수정 어떻게 할지 추후 생각
		  						    var obj = result.userInfo;
		  						    $("#adminId").val(adminId);
		  						    $("#adminName").val(obj.adminName);
			  						$("#empNo").val(obj.empNo);
			  						$("#adminTel").val(obj.adminTel);
			  						$("#adminEmail").val(obj.adminEmail);
			  						$("#reg_date").html(obj.regDate);
			  						$('input:radio[name=useYn]:input[value=' + obj.useYn + ']').attr("checked", true);	 
			  						$("#adminLevel").val(obj.adminLevel);
			  						$("#partId").val(obj.partId);
			  						fn_ComboView();
			  						$("#btnUpdate").text('<spring:message code="button.update" />');
			  				   	    $("#uniCheck").html("");
			  				   	    $("#reg_date").html($("#regDate").val());
			  				   	    $("input[name=adminId]").attr("readOnly", true);
		  					   }
						    },
						    function(request){
							    alert("Error:" +request.status );	       						
						    }    		
		          );
		    	
		    	
		    	
		    	
		    }
	}
	function del_check(code){
		fn_uniDelJSON("/backoffice/basicManage/managerDelete.do"
				  , {adminId : code}
		          , "/backoffice/basicManage/empList.do");		
	}  
	function check_id(){	  
	    if ( $("#adminId").val()!= ""   ){	  
	    	var params = {adminId : $("#adminId").val() };
	    	uniAjax("/backoffice/basicManage/IdCheck.do", params, 
	       			function(result) {
  				    		if (result == "0"){
  								alert('<spring:message code="page.emp.message13" />');
  								$("#idCheck").val("Y");							
  							}else {
  								alert('<spring:message code="page.emp.message14" />');
  								$("#idCheck").val("N");
  							}
	  				    },
	  				    function(request){
	  					    alert("Error:" +request.status );	       						
	  				    }    		
	        );
	    }else {
	    	alert ('<spring:message code="page.emp.message05" />');
	    	$("#adminId").focus();
	    	return;
	    }
	}
	function check_form(){	  
		   if ($("#mode").val() == "Ins"){
			   if ($("#idCheck").val() == "N"){
				   alert('<spring:message code="page.emp.message01" />');
				   return ;			   
			   }			   
			   if(!chkPwd( $.trim($('#adminPassword').val()))){ 
				   alert('<spring:message code="page.emp.message02" />');    
				   $('#adminPassword').val('');
				   $('#adminPassword').focus(); return ;
				   }
			   if ( $.trim($('#adminPassword').val()) !=   $.trim($('#adminPassword2').val())  ){
				   alert('<spring:message code="page.emp.message03" />')
				   return;
			   }
		   }   	
		   if (any_empt_line_id('adminLevel', '<spring:message code="page.emp.message04" />') == false) return;	  
		   var params = {  adminId : $("#adminId").val(),
								 adminName : $("#adminName").val(),
								 empNo : $("#empNo").val(),
								 adminTel : $("#adminTel").val(),
								 adminEmail : $("#adminEmail").val(),
								 adminLevel : $("#adminLevel").val(),
								 useYn :  fn_emptyReplace($('input[name="useYn"]:checked').val(),"Y"),
								 partId : $("#partId").val(),
								 adminPassword : $("#adminPassword").val(),
								 mode : $("#mode").val()
		    };
		   uniAjax("/backoffice/basicManage/managerUpdate.do", params, 
	     			function(result) {
			                alert(result.message);
					    	if (result.status == "SUCCESS"){
								document.location.reload();
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
    </script>
</body>
</html>