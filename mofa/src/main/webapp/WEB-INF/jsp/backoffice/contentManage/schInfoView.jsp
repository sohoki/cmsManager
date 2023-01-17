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
    <title><spring:message code="URL.TITLE" /></title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <link href="/css/jquery-ui-modify.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/jquery-ui.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script src="/js/popup.js"></script>
</head>
<body>
<div id="wrapper">	
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/displayList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
<input type="hidden" name="mode" id="mode" value="${regist.mode }">
<form:hidden path="pageSize" />
<form:hidden path="searchCondition" />
<form:hidden path="searchKeyword" />
<form:hidden path="schCode" />
<input type="hidden" name="agentCodeLst" id="agentCodeLst">
<input type="hidden" name="schSendGubun" id="schSendGubun" value="FileGubun_1">

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
            <div class="Swrap tableArea viewArea">
                <div class="view_contents">
                <table class="pop_table thStyle">
	                <tbody>
	                    <tr>
	                        <th><spring:message code="page.schMessage.title" /></th>
	                        <td colspan="4">
	                        <span class="lable">${registinfo.schTitle }</span>
	                        <span class="type">
	                        <form:input  path="schTitle" size="40" maxlength="100" id="schTitle"   value="${registinfo.schTitle }" />
	                        </span>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th><spring:message code="page.schMessage.schDay" /></th>
	                        <td class="tableauto">
	                        <span class="lable">
	                        ${registinfo.schStartDay } ~  ${registinfo.schEndDay } 
	                        </span>
	                        <span class="type">
	                        <form:input  path="schStartDay" size="10" maxlength="20" id="schStartDay"   value="${registinfo.schStartDay }" /> ~
	                        <form:input  path="schEndDay" size="10" maxlength="20" id="schEndDay"   value="${registinfo.schEndDay }" />
	                        </span>
	                        </td>
                           
                            <th>송출시간</th>
                            <td class="tableauto">
                            <span class="lable">
                            ${registinfo.schStartTime } ~ ${registinfo.schEndTime }
                            </span>
                            <span class="type">
                            <select id="schStartTime1" name="schStartTime1"style="width:50px !important;">
						      <option value="0">00</option>
						      <c:forEach var="item" varStatus="i" begin="1" end="24" step="1">
						       <option value="${item}" <c:if test="${item == registinfo.schStartTime1}">selected="selected"</c:if>>
						        <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
						       </option>
						      </c:forEach>
						     </select><spring:message code="common.hour.time.msg" />&nbsp; 
						     <select id="schStartTime2" name="schStartTime2"style="width:50px !important;">
						      <option value="0">00</option>
						      <c:forEach var="item" varStatus="i" begin="5" end="55" step="5">
						       <option value="${item}" <c:if test="${item == registinfo.schStartTime2}">selected="selected"</c:if>>
						        <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
						       </option>
						      </c:forEach>
						     </select><spring:message code="common.minute.time.msg" /> ~ 
						     <select id="schEndTime1" name="schEndTime1" style="width:50px !important;">
						      <option value="0">00</option>
						      <c:forEach var="item" varStatus="i" begin="1" end="24" step="1">
						       <option value="${item}" <c:if test="${item == registinfo.schEndTime1}">selected="selected"</c:if>>
						        <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
						       </option>
						      </c:forEach>
						     </select><spring:message code="common.hour.time.msg" />&nbsp; 
						     <select id="schEndTime2" name="schEndTime2" style="width:50px !important;">
						      <option value="0">00</option>
						      <c:forEach var="item" varStatus="i" begin="5" end="55" step="5">
						       <option value="${item}" <c:if test="${item == registinfo.schEndTime2}">selected="selected"</c:if>>
						        <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
						       </option>
						      </c:forEach>
						     </select><spring:message code="common.minute.time.msg" />
						     </span>
                            </td>                 
	                    </tr>
	                    <tr>
                            <th><spring:message code="page.schMessage.fontSize" /></th>
	                        <td>
	                        <span class="lable">${ registinfo.schFonttype}</span>
	                        <span class="type">
	                        <select id="schFonttype" name="schFonttype">
						      <c:forEach var="item" varStatus="i" begin="1" end="10" step="1">
						       <option value="${item}" <c:if test="${item == registinfo.schFonttype}">selected="selected"</c:if>>
						        <c:if test="${item < 10}">0</c:if><c:out value="${item}" />
						       </option>
						      </c:forEach>
						     </select> 
	                        </span>
	                        </td>   
	                        <th><spring:message code="common.UseYn.title" /></th>
	                        <td style="text-align:left">
	                        <input type="radio" name="schUseYn" value="Y" <c:if test="${registinfo.schUseYn == 'Y' }"> checked </c:if> />
	                        <label><spring:message code="button.use" /></label>
							<input type="radio" name="schUseYn" value="N" <c:if test="${registinfo.schUseYn == 'N' }"> checked </c:if> />
							<label><spring:message code="button.nouse" /></label>	
	                        </td>
	                        <!--<th><spring:message code="page.schMessage.schGubun" /></th>
	                        <td style="text-align:left" >
	                        <span class="lable">${ registinfo.schSendGubunTxt}</span>
	                        <span class="type">
	                        <form:select path="schSendGubun" id="schSendGubun" title="소속" onChange="javascript:fn_ComboChange();">
										     <form:option value="" label="--선택하세요--"/>
					                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
						    </form:select>	
	                        </span>
	                        </td>-->	
	                    </tr>   
	                    <tr>
	                       <th><spring:message code="page.schMessage.sendContent" /></th>
	                       <td class="auto100" colspan="3">
	                       <span class="lable">
	                       ${registinfo.schMessage }
	                       ${registinfo.schFileNm }
	                       </span>	 
	                       <span class="type">
	                         <input name="schFileImage" id="schFileImage" type="file"  size="20" style="display:none"/>
	                         <form:input  path="schMessage" size="200" maxlength="200" id="schMessage" class="width100"  value="${registinfo.schMessage }" style="display:none"/>
	                       </span>                        
	                       </td>
	                    </tr>  	                    
	                </tbody>
	            </table>
	            </div>
	            <div id="didplayAgent">
	            </div>
            </div>
            <div class="footerBtn">
	            <a href="javascript:fn_Edit();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
	            <a href="javascript:fn_delCheck();" class="redBtn" id="btnDel"><spring:message code="button.delete" /></a>
	            <a href="javascript:linkPage()" class="deepBtn"><spring:message code="button.list" /></a>
	        </div>
        </div>
    </div>

<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
	</div>
<script type="text/javascript">
	function linkPage() {
		$("form[name=regist]").attr("action", "/backoffice/contentManage/schInfoList.do").submit();
	}	
	function fn_delCheck(){
		if ($("#btnDel").text() == "<spring:message code='button.reset' />"){
            location.reload();
   		}else{
   			fn_uniDelJSON("/backoffice/contentManage/schInfoDelete.do"
   					  , {schCode : $("#schCode").val()}
   			          , "/backoffice/contentManage/schInfoList.do");
   		}	   
	}
    $(document).ready(function() { 
		if ($("#mode").val() == "Ins"){
			$("#btnDel").text("<spring:message code='button.reset' />");
		}
		fn_Edit();
	});
	function fn_Edit(){
		
		if ($("#btnUpdate").text() == "<spring:message code='button.create' />" && $("#btnDel").text() == "<spring:message code='button.reset' />" && ( $("#mode").val() == "Edt" ||  $("#mode").val() == "Ins"  )){
			  
			   if (any_empt_line_id("schTitle", "<spring:message code='page.schMessage.alert01' />") == false) return;
			   if (any_empt_line_id("schStartDay", "<spring:message code='page.schMessage.alert02' />") == false) return;
			   if (any_empt_line_id("schEndDay", "<spring:message code='page.schMessage.alert03' />") == false) return;
			   if ( fn_calValidate ('schStartDay', 'schEndDay', '<spring:message code="page.consch.alert08" />') ==  false) return;
			   
			   if (any_empt_line_id("schStartTime1", "<spring:message code='page.schMessage.alert04' />") == false) return;
			   if (any_empt_line_id("schStartTime2", "<spring:message code='page.schMessage.alert04' />") == false) return;
			   if (any_empt_line_id("schEndTime1", "<spring:message code='page.schMessage.alert05' />") == false) return;
			   if (any_empt_line_id("schEndTime2", "<spring:message code='page.schMessage.alert05' />") == false) return;
			 
			   
			   var params = $("#regist").serializeObject(); 
			   if ($("#schFileImage").val() != "" ){
				   var form = $('#schFileImage')[0];
			       var fileInfo = new FormData(form);
			       params.push({schFileImage : fileInfo});  
			   }
			  
			   uniAjax("/backoffice/contentManage/schInfoUpdate.do", params, 
   		     			function(result) {
   				               alert(result.message);
   						       if (result.status == "LOGIN FAIL"){
   		  							location.href="/backoffice/login.do";
   		  					   }else if (result.status == "SUCCESS"){
   		  						    //여기 부분 수정 어떻게 할지 추후 생각
   		  						    if (result.regist.schCode != ""){
	   		  						    $("#schCode").val(result.regist.schCode);
		   								$("#mode").val("Edt");
		   								$("form[name=regist]").attr("action", "/backoffice/contentManage/schInfoView.do").submit();
   		  						    }
   		  					   }
   						    },
   						    function(request){
   							    alert("Error:" +request.status );	       						
   						    }    		
   		      );	
			
			
		}
		 
		
		if ($("#mode").val() == "Edt" ){
   			$(".lable").show();
   			$(".type").hide();
   			$("#btnUpdate").text("<spring:message code='button.update' />");
   			$("#btnDel").text("<spring:message code='button.delete' />");
   			$("#mode").val("Pre");
   			$("#sp_MessageResult").text("<spring:message code='page.schMessage.agentView' />").prop("href", "javascript:fn_agentView()");
   			fn_agentView();
   		}else if ($("#mode").val() == "Ins") {
   			$("#didplayAgent").html("");
   			$(".lable").hide();
   			$(".type").show();
   			$("#schMessage").show();
   			$("#btnUpdate").text("<spring:message code='button.create' />");
   			$("#btnDel").hide();   			
            $("input:radio[name='schUseYn']:radio[value='Y']").attr("checked",true);
   			$("#sp_MessageResult").text("<spring:message code='page.schMessage.sendContent' />").prop("href", "#");
   		}else {
   			$("#didplayAgent").html("");
   			$(".lable").hide();
   			$(".type").show();
   			$("#btnUpdate").text("<spring:message code='button.create' />");
   			$("#btnDel").text("<spring:message code='button.reset' />");
   			$("#sp_MessageResult").text("<spring:message code='page.schMessage.sendContent' />").prop("href", "#");
   			$("#mode").val("Edt");
   			//추후 이미지 적용 될때 사용
   			//fn_ComboChange();
   			$("#schFileImage").attr("style","display:none");
			$("#schMessage").attr("style","display:block");
   		}
	}
	function fn_ComboChange(){
		if ($("#schSendGubun").val() == "MessageGubun_1") {
			$("#schFileImage").attr("style","display:block");
			$("#schMessage").attr("style","display:none");
		}else {
			$("#schFileImage").attr("style","display:none");
			$("#schMessage").attr("style","display:block");
		}
    }
	function fn_agentView(){
		apiExecute(
				"POST", 
				"/backoffice/schAgentList.do",
				{
					schCode : $("#schCode").val()
				},
				null,				
				function(result) {				
					if (result != null) {
						didDetailHtm = "<div class='code_text margin-top10'>";
						didDetailHtm += "<table class='margin-top30 backTable'><thead><tr><th colspan='3'>연동단말기</th><th style='width:150px'>";
					    didDetailHtm += "<span class='alltext'>전체선택</span> <input type='checkbox' id='all_check' name='all_check' onClick='javascript:fn_allCheck()'></tr></thead><tbody><tr>";
						
						if (result.schState != null) {
								var a = 0;	
								for (var i=0; i<result.schState.length; i++) {
									var obj = result.schState[i];
								    if (a == 4 ){
								    	didDetailHtm += "</tr><tr>";
								    	a=0;
								    }
								    didDetailHtm += "<td style='width:300px;'><input type='checkbox' name='schCheck' value="+ obj.agent_code+"";
								    if (obj.sch_code != null && obj.sch_code != ""){
								    	didDetailHtm += " checked"; 
								    }
								    didDetailHtm += ">"+obj.agent_nm+"("+obj.center_nm+":"+obj.agent_floor+"층)</td>";
								    a = (parseInt(a) + 1);
								}	
								if (a<5){
									for ( var b=a; b<4; b++){
										didDetailHtm +="<td>&nbsp;</td>"
									}
								}
								
						}	
						didDetailHtm += "</tr><tr colspan='4' style='text-align:center'><a href='javascript:fn_schAgentSend();' class='redBtn' >적용</a></td></tr>";
						didDetailHtm += "</tbody></table>";
						didDetailHtm += "</div>";
						;
						$("#didplayAgent").html(didDetailHtm);
						
					}else {
					    alert("error.");						  
					}					
				},
				null,
				null
			);	
	}
	function fn_schAgentSend(){
		var agentCode = ckeckboxValueEmpty('schCheck');
		fn_uniUpdate("/backoffice/schAgentUpdate.do", {agentCodes: agentCode, schCode : $("#schCode").val()}, null );
	}
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        yearSuffix: '년'            
    });

    $(function () {
        $("#schStartDay").datepicker({
            onSelect: function (dateText, inst) {
              //오늘 날짜 이전 날짜를 선택 하셔
            	if (today_check( $("#schStartDay").val().replace(/-/gi, "")) == false){
            		alert("날짜 형식이  아니거나 이전 날짜를 선택 하셨습니다");
            		$("#schStartDay").val("");
            	}else {
            		$("#schStartDay").val($("#schStartDay").val().replace(/-/gi, ""));
            	}
            }
        });
        $("#schEndDay").datepicker({
            onSelect: function (dateText, inst) {
            	if ( day_Check (  $("#schStartDay").val().replace(/-/gi, ""), $("#schEndDay").val().replace(/-/gi, "")) == false){
            		alert("시작일 보다 종료일이 이전 날짜 일수 없습니다.");
            		$("#schEndDay").val("");
            	}else {
            		$("#schEndDay").val($("#schEndDay").val().replace(/-/gi, ""));
            	}
            }
        });
    });
	
</script>  
</body>
</html>