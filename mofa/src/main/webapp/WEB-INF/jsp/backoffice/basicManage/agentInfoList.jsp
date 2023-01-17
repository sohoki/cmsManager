<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<%@ page import ="egovframework.com.cmm.AdminLoginVO" %>
<% AdminLoginVO loginVO = (AdminLoginVO)session.getAttribute("AdminLoginVO"); %>
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
	    <script type="text/javascript" src="/js/cms_display.js"></script>
	    <script type="text/javascript" src="https://www.google.com/jsapi"></script>
	    <script type="text/javascript" src="/js/cms_agent.js"></script>
	
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
			<form:form name="regist" commandName="regist" method="post" action="/backoffice/basicManage/AgentInfoList.do">
				<c:import url="/backoffice/inc/top_inc.do" />
				<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
				<input type="hidden" name="mode" id="mode" >
				<input type="hidden" name="agentCode" id="agentCode" >
				<input type="hidden" name="adminLevel" id="adminLevel" value="<%= loginVO.getAdminLevel() %>">
	
				<div class="Aconbox">
	        		<div class="rightT">
	            		<div class="Smain">
	                		<div class="Swrap Stitle">
	                    		<div class="infomenuA">
	                        		<img src="/images/home.png" alt="homeicon" />
	                        		<span> ▶ </span>
	                        		<span><spring:message code="menu.menu01" /></span>
	                        		<span> ▶ </span>
	                        		<strong><span><spring:message code="menu.menu01_2" /></span></strong>
	                    		</div>
	                		</div>
	            		</div>
	            		<div class="Swrap Asearch">
	                		<div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
	                		<section class="Bclear">
		                    	<select name="pageUnit" id="pageUnit" class="ssg_select">
		                        	<c:forEach var="pageCnt" begin="10" end="50" step="10">
		                        		<option value="${pageCnt}" ${pageCnt== searchVO.pageUnit ? 'selected="selected"' : '' }>${pageCnt }개씩 보기 </option>
		                        	</c:forEach>
		                    	</select>
		                    	<c:if test="${page_Unit eq pageCnt})"> selected="selected" </c:if>
		                    
		                    	<% if (loginVO.getAdminLevel().equals("ROLE_ADMIN")){ %>
								<form:select path="searchPartid" id="searchPartid" class="ssg_select" title="소속" onChange="javascript:fn_centerView('searchPartid','searchCenterid', '')">
									<option value=""><spring:message code="combobox.text" /></option>
				   		        	<form:options items="${selectGroupCombo}" itemValue="partId" itemLabel="partNm"/>
								</form:select>
								<% } else { %>
		                       	<input type="hidden" id="searchPartid" value="${regist.partId}">
		                    	<% } %>
		                    
		                    
			                    <form:select path="searchCenterid" id="searchCenterid" class="ssg_select" title="소속" onChange="javascript:fn_FlooerView('searchCenterid', 'searchFloor', '')">
									<option value=""><spring:message code="combobox.text" /></option>
				                    <form:options items="${selectCenterCombo}" itemValue="centerId" itemLabel="centerNm"/>
								</form:select>
								<select id="searchFloor" name="searchFloor" class="ssg_select">
								
								</select>
								<form:select path="searchAgentgubun" id="searchAgentgubun" class="ssg_select" title="소속">
									<option value=""><spring:message code="combobox.text" /></option>
					                <form:options items="${selectAgentContentGubun}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
									    
			                    <select name="searchCondition"  class="ssg_select" id="searchCondition">
									<option value=""><spring:message code="combobox.text" /></option>
									<option value="agentNm" <c:if test="${searchVO.searchCondition == 'agentNm' }"> selected="selected" </c:if>><spring:message code="page.agent.TitleNm" /></option>
									<option value="disSeq" <c:if test="${searchVO.searchCondition == 'disSeq' }"> selected="selected" </c:if>><spring:message code="page.agent.disSeq" /></option>
									<option value="agentRemark" <c:if test="${searchVO.searchCondition == 'agentRemark' }"> selected="selected" </c:if>><spring:message code="page.agent.remark" /></option>
								</select>
								<input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" value="${searchVO.searchKeyword}">                    
			                    <a href="javascript:search_form();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
			                    <div class="rightB">
			                        <a href="javascript:fn_SendMsg('SP_AGENTREBOOT', 'agentInput', 'M')" class="grayBtn"><spring:message code="page.agent.sendBtn2" /></a>
			                        <!--<a href="javascript:fn_SendMsg('SP_REDOWN', 'agentInput', 'M')" class="grayBtn"><spring:message code="page.agent.sendBtn1" /></a>-->
			                        <a href="#" onclick="fn_agentPop('Ins','0')" data-needpopup-show="#agent_pop"><span class="deepBtn"><spring:message code="button.create" /></span></a>
			                    </div>
	                		</section>
	            		</div>
	
	            		<div class="Swrap tableArea">
	                		<table class="margin-top30 backTable">
	                    		<thead>
	                        		<tr>
			                            <th><input type="checkbox" id="all_check" name="all_check" onClick="fn_allCheck()"></th>
			                            <th><spring:message code="page.agent.state" /></th>
			                            <th><spring:message code="page.agent.TitleNm" /></th>
			                            <th><spring:message code="page.agent.agentCode" /></th>
			                            <th><spring:message code="page.agent.contentGubun" /></th>
			                            <th><spring:message code="page.center.centerNm" /></th>
			                            <th><spring:message code="page.agent.floor" /></th>
			                            <th><spring:message code="page.agent.remark" /></th>
			                            <th><spring:message code="page.agent.displayNm" /></th>
			                            <th><spring:message code="page.agent.startTime" /></th>
			                            <th><spring:message code="page.agent.osType" /></th>
			                            <th><spring:message code="common.UseYn.title" /></th>
			                            <th><spring:message code="button.preview" /></th>
			                            <th><spring:message code="button.delete" /></th>
	                        		</tr>
	                    		</thead>
	                    		<tbody>
	                     			<c:forEach items="${resultList }" var="agentInfo" varStatus="status">
		                        		<tr>
		                            		<td><input type="checkbox" name="agentInput" value="<c:out value="${agentInfo.agentCode}/${agentInfo.agentMac}"/>"></td>
			                            	<td>
			                                	<a href="#" onclick="fn_agentState('${ agentInfo.agentCode }')" data-needpopup-show="#agentState" class="underline" >
				                                	<c:choose>
											       		<c:when test="${agentInfo.agentStateInfo eq 'NORMAL' }">
												    		<img src="/images/on_icon.png" width="16px" height="16px" />
											       		</c:when>
											       		<c:when test="${agentInfo.agentStateInfo eq 'CAUTION' }">
													    	<img src="/images/caution_icon.png" width="16px" height="16px" />
											       		</c:when>
											       		<c:otherwise>
											   		    	<img src="/images/danger_icon.png" width="16px" height="16px" />
											       		</c:otherwise>
													</c:choose>
			                                	</a>
				                            	<a href="#" onclick="fn_agentState('${ agentInfo.agentCode }')" data-needpopup-show="#agentState" class="underline" ></a>
			                            	</td>
				                            <td><a href="#" onclick="fn_viewAgent('${ agentInfo.agentCode }')" class="underline">${ agentInfo.agentNm }</a></td>
				                            <td><a href="#" onclick="fn_agentPop('Edt','${ agentInfo.agentCode }')"  data-needpopup-show="#agent_pop" class="underline" >${ agentInfo.agentCode }</a></td>
				                            <td>${agentInfo.contentGubun }</td>
				                            <td>${agentInfo.centerNm }</td>
				                            <td>${agentInfo.agentFloor }<spring:message code="page.agent.floorTxt" /></td>
				                            <td>${agentInfo.agentRemark }</td>
				                            <td>${agentInfo.displayTitle }</td>
				                            <td>
				                            	<c:if test="${agentInfo.agentStarttime != '' }">
				                            		${fn:substring(agentInfo.agentStarttime,0,2)}:${fn:substring(agentInfo.agentStarttime,2,4)}~
				                            	</c:if>
				                            	<c:if test="${agentInfo.agentEndtime != '' }">
				                            		${fn:substring(agentInfo.agentEndtime,0,2)}:${fn:substring(agentInfo.agentEndtime,2,4)}
				                            	</c:if>
				                            </td>
				                            <td>
				                            	<c:choose>
											   		<c:when test="${agentInfo.agentGubun eq '안드로이드' }">
														<img src="/images/android_icon.png" width="16px" height="16px" />
											   		</c:when>
											   		<c:when test="${agentInfo.agentGubun eq '윈도우' }">
														<img src="/images/windows_icon.png" width="16px" height="16px" />
											   		</c:when>
											   	<c:otherwise>
											   		<img src="/images/ios_icon.png" width="16px" height="16px" />
											   	</c:otherwise>
												</c:choose>
				                            </td>
				                            <td>
				                            	<c:if test="${agentInfo.agentUseYn == 'Y' }"><img src="/images/on_icon.png" width="16px" height="16px" /></c:if> 
				                            	<c:if test="${agentInfo.agentUseYn == 'N' }"><img src="/images/danger_icon.png" width="16px" height="16px" /></c:if>
				                            </td>
				                            <td><a href="javascript:fn_agentPreView('${ agentInfo.displaySeq }','${ agentInfo.displayTitle }','${ agentInfo.contentGubun }')" class="grayBtn"><spring:message code="button.preview" /></a></td>                            
				                            <td><a href="javascript:del_check('${ agentInfo.agentCode }','${ agentInfo.agentNm }');" class="grayBtn">
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
		    	<div id='agent_pop' class="needpopup">  
			        <!-- popheader-->                        
			        <div class="popHead">
			            <h2>단말기 등록</h2>
			        </div>
			        <!-- pop contents-->   
			        <div class="popCon">
			            <!-- 단말기명 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="page.agent.TitleNm" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:input  path="agentNm" size="20" maxlength="100" id="agentNm" class="input_noti" value="${regist.agentNm }" />
			                </div>                
			            </div>
			            
			            <!-- 단말기 운영체제 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="page.agent.osType" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:select path="agentGubun" id="agentGubun" class="popSel" title="소속">
										     <option value=""><spring:message code="combobox.text" /></option>
					                         <form:options items="${selectAgentGubun}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
			                </div>                
			            </div>
			            
			            <!-- 단말기 비고 팝업 필드박스 -->
			            <div class="pop_box100">
			                <div class="padding15">
			                    <p class="pop_tit"><spring:message code="page.agent.remark" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:input  path="agentRemark" size="80" maxlength="120" id="agentRemark" class="input_noti" value="${regist.agentRemark }" />
			                </div>                
			            </div>
			            
			            <!-- 단말기 부서 팝업 필드박스 -->
			            <% if (loginVO.getAdminLevel().equals("ROLE_ADMIN")){ %>
			            <div class="pop_box50">
			            	<div class="padding15">
			            	    <p class="pop_tit">*<spring:message code="page.agent.partInfo" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:select path="partId" id="partId" class="popSel" title="소속" onChange="javascript:fn_centerView('partId','centerId', '')">
						     		<option value=""><spring:message code="combobox.text" /></option>
					                <form:options items="${selectGroupCombo}" itemValue="partId" itemLabel="partNm"/>
								</form:select>
			            	</div>   
			            </div>
			            <% } else { %>
			            	<input type="hidden" id="partId" value="${regist.partId}" />
			            <% } %>
			            
			            <!-- 단말기 지점 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="page.center.centerNm" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:select path="centerId" id="centerId" class="input_noti input_noti_x" title="소속" onChange="javascript:fn_FlooerView('centerId', 'agentFloor', '')">
									<option value=""><spring:message code="combobox.text" /></option>
					                <form:options items="${selectCenterCombo}" itemValue="centerId" itemLabel="centerNm"/>
								</form:select>
								<select id='agentFloor' name='agentFloor' class="input_noti input_noti_x" style="display:none;">
								
								</select>
			                </div>   
			            </div>
			            
			            <!-- 단말기 콘텐츠구분 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="page.agent.contentGubun" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:select path="agentContentgubun" id="agentContentgubun" class="popSel" title="소속" onChange="fn_displayView('')">
									<option value=""><spring:message code="combobox.text" /></option>
									<form:options items="${selectAgentContentGubun}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
			                </div>                
			            </div>
			            
			            <!-- 단말기 구동화면 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="page.agent.displayNm" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <form:select path="displaySeq" id="displaySeq" class="popSel" title="소속" style="display:none">
					                 <option value=""><spring:message code="combobox.text" /></option>
						             <form:options items="${selectCodeDM}" itemValue="displaySeq" itemLabel="displayTitle"/>
								</form:select>
			                </div>   
			            </div>
			            
						<!-- 단말기 운영시간 팝업 필드박스 -->
						<div class="pop_box50">
			                <div class="padding15">
			                   	<p class="pop_tit">*<spring:message code="page.agent.startTime" />~<spring:message code="page.agent.endTime" /> <span class="join_id_comment joinSubTxt"></span></p>
			                   	<select id="agentStarttime1" name="agentStarttime1" class="input_noti input_noti_x2" title="시간">
							   	</select>
							   	<select id="agentStarttime2" name="agentStarttime2" class="input_noti input_noti_x2" title="시간">
							   	</select>
								~
								<select id="agentEndtime1" name="agentEndtime2" class="input_noti input_noti_x2" title="시간">
								</select>
								<select id="agentEndtime2" name="agentEndtime2" class="input_noti input_noti_x2" title="시간">
								</select>
			                </div>   
			            </div>
			            
			            <!-- 단말기 사용유무 팝업 필드박스 -->
			            <div class="pop_box50">
			                <div class="padding15">
			                    <p class="pop_tit">*<spring:message code="common.UseYn.title" /> <span class="join_id_comment joinSubTxt"></span></p>
			                    <input type="radio" name="agentUseYn" id="agentUseYn_Y" value="Y" <c:if test="${regist.agentUseYn == 'Y' }"> checked </c:if> />
			                    <label for="agentUseYn_Y"><spring:message code="button.use" /></label>
								<input type="radio" name="agentUseYn" id="agentUseYn_N" value="N" <c:if test="${regist.agentUseYn == 'N' }"> checked </c:if> />
								<label for="agentUseYn_N"><spring:message code="button.nouse" /></label>
			                </div>                
			            </div>
			            <div class="clear"></div>   
					</div>
					<div class="pop_footer">
						<span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span>
						<a href="javascript:fn_CheckForm();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
						<div class="clear"></div>
					</div>
		    	</div>
				<c:import url="/backoffice/inc/bottom_inc.do" />
			</form:form>
		</div>
	
		<script type="text/javascript">
			var searchFloor = "${searchVO.searchFloor}";
			var searchCenter = "${searchVO.searchCenterid}";
			
			$(document).ready(function() {
				//등록/수정 팝업창 시간세팅
				timeSelSetting("agentStarttime1","hour");
				timeSelSetting("agentStarttime2","min");
				timeSelSetting("agentEndtime1","hour");
				timeSelSetting("agentEndtime2","min");
				
				if (searchFloor != ""){
					fn_FlooerView("searchCenterid", 'searchFloor', searchFloor);
				} else {
					$("#searchFloor").hide();
				}
				
				if (searchCenter != ""){
					fn_CenterView("searchPartid", 'searchCenterid', searchCenter);
				} else {
					$("#searchCenterid").hide();
				}
			});
		   
			function fn_agentPop(mode, code){
				$('#mode').val(mode);
				$('#agentCode').val(code);
				
				if (mode == "Edt"){
					$(".popHead > h2").html("단말기 수정");
					$("#btnUpdate").text('<spring:message code="button.update" />');	
					url= "/backoffice/basicManage/agentInfoDetail.do";
					var param = {
				                	'mode' : $('#mode').val(),
				                	'agentCode' : $('#agentCode').val()
				      			};
					uniAjax(
								url, 
								param, 
								function(result) {
									if(result.status == "LOGIN FAIL") {
								   		alert(result.meesage);
			  				   			location.href="/backoffice/login.do";
									} else if(result.status == "SUCCESS") {
			  				    		//여기 부분 수정 어떻게 할지 추후 생각
			  				    		var obj = result.agentInfo;
			  				    		$("#agentNm").val(obj.agentNm);
			  				    		$("#agentGubun").val(obj.agentGubun);
				  						$("#agentRemark").val(obj.agentRemark);
				  						$("#centerId").val(obj.centerId);
				  						$("#partId").val(obj.partId);
						      			$("#agentStarttime1").val(obj.agentStarttime.substring(0,2));
						      			$("#agentStarttime2").val(obj.agentStarttime.substring(2,4));
						      			$("#agentEndtime1").val(obj.agentEndtime.substring(0,2));
						      			$("#agentEndtime2").val(obj.agentEndtime.substring(2,4));
				  						$("#agentContentgubun").val(obj.agentContentgubun);
			 		        	    	$('input:radio[name=agentUseYn]:input[value=' + obj.agentUseYn + ']').attr("checked", true);	 
			 		        	    	fn_FlooerView("centerId", "agentFloor", "");
			 		        	    	fn_displayView(obj.displaySeq);
			  			   			}
								},
								function(request){
									alert("Error:" +request.status );	       						
								}    		
							);
				} else {
					$("#agentNm").val("");
					$("#agentGubun").val("");
					$("#agentRemark").val("");
					$("#centerId").val("");
					
					if($("#adminLevel").val() == "ROLE_ADMIN"){
					  $("#partId").val("");
					} else{
					  $("#partId").val($("#partId").val());
					}
					
					$("#agentStarttime1").val("");
					$("#agentStarttime2").val("");
					$("#agentEndtime1").val("");
					$("#agentEndtime2").val("");
					$("#agentContentgubun").val("");
					$("input:radio[name=agentUseYn]:input[value='Y']").attr("checked", true);
					$("#agentFloor").hide();
					$("#displaySeq").closest('.pop_box50').hide();
					$("#displaySeq").hide();
					$("#btnUpdate").text('<spring:message code="button.create" />');
					$(".popHead > h2").html("단말기 등록");
				}
		   	}
			
		   	function del_check(code){
		    	fn_uniDelJSON("/backoffice/basicManage/agentInfoDelete.do",
		    			{agentCode : code},
		    			"/backoffice/basicManage/AgentInfoList.do");	
			}
		
			function fn_displayView(selectVal){
				uniAjax
				(
					"/backoffice/contentManage/displayCombo.do?agentContentgubun="+$("#agentContentgubun").val(),
					{'agentContentgubun': $("#agentContentgubun").val()}, 
					function(result) {
						if (result.status == "SUCCESS") {
							if (result.disList.length> 0) {
								if($("#agentContentgubun").val() == "AGENT_CONTENT_1") {
									$("#displaySeq").closest('.pop_box50').show();
									$("#displaySeq").show();
								} else {
									$("#displaySeq").closest('.pop_box50').hide();
									$("#displaySeq").hide();
						    	}
						    			
		                		$("#displaySeq option").remove();
						    	$("#displaySeq").append("<option value=''>선택</option>");
						    	for (var i = 0; i <= result.disList.length; i ++){
						    		var obj = result.disList[i];
		                            if(selectVal == obj.displaySeq) {
						    			$("#displaySeq").append("<option value='"+obj.displaySeq+"' selected>"+obj.displayTitle+"</option>");
		                            } else {
		                            	$("#displaySeq").append("<option value='"+obj.displaySeq+"'>"+obj.displayTitle+"</option>");
		                            }
						    	}  
						    			
								if (selectval != ""){
									console.log("selectval:" + selectval);
						    		$("#displaySeq").val(selectval);
								}		    	
							}
						} else if (result.status == "LOGIN FAIL") {
							location.href="/backoffice/login.do";
						} else {
							alert(result.message);
						}
					},
					function(request){
						alert("Error:" +request.status );	       						
					}    		
		        );
			}
			
			function fn_centerView(selectId, tagId, selectVal){
				if($("#"+selectId).val() != false){
					uniAjax
					(
						"/backoffice/basicManage/centerCombo.do", 
						{'partId': $("#"+selectId).val()}, 
						function(result) {
							if (result.status == "SUCCESS") {
								var floorTag = selectId = "searchPartid" ? "searchFloor" : "agentFloor";
								$("#" + floorTag).val("");
								
								if (result.resultList.length > 0) {
									result.resultList["type"] = "center";
									result.resultList["selectVal"] = selectVal;
									fn_optionControl(tagId,result.resultList);
								} else {
									$("#" + tagId).hide();
									$("#" + floorTag).hide();
								}
							} else if (result.status == "LOGIN FAIL") {
								location.href="/backoffice/login.do";
							} else {
								alert(result.message);
							}
						},
						function(request){
							alert("Error : " + request.status);	       						
						}    		
			        );
				}
			}
			
			function fn_FlooerView(selectId, tagId, selectVal){
				if($("#centerId").val() != "" || $("#searchCenterid").val() != ""){
					if($("#searchCenterid").val() == ""){
		                $("#searchFloor").hide();
		            }else if ($("#centerId").val() == ""){
		                $("#agentFloor").hide();
		            }
					var params = (selectId == "centerId") ? { centerId : $("#centerId").val() }: { centerId : $("#searchCenterid").val() };
					uniAjax
					(
						"/backoffice/basicManage/centerInfo.do", 
						params, 
						function(result) {
							if (result.status == "SUCCESS"){
								if(result.regist.centerFloorTxt != "" && result.regist.centerFloorEndTxt != ""){
									var startFloor = result.regist.centerFloorTxt.replace(/층/gi,"");
									var endFloor = result.regist.centerFloorEndTxt.replace(/층/gi,"");
								}else if (result.regist.centerFloorTxt != "" && result.regist.centerFloorEndTxt == ""){
				  	           		var startFloor = result.regist.centerFloorTxt.replace(/층/gi,"");
				  	           		var endFloor = result.regist.centerFloorTxt.replace(/층/gi,"");
				  	           	}
								
								result.regist["type"] = "floor";
								result.regist["selectVal"] = selectVal;
								result.regist["startFloor"] = startFloor;
								result.regist["endFloor"] = endFloor;
								
								if(selectId == "centerId"){
									fn_optionControl("agentFloor", result.regist);
								} else {
									fn_optionControl("searchFloor", result.regist);
								} 
							} else if(result.status == "LOGIN FAIL"){
				  				location.href="/backoffice/login.do";
				  			} else {
				  				alert(result.message);
				  			}
						},
				  	    function(request){
				  			alert("Error:" +request.status );	       						
				  	    }    		
				    );
				}
			}
		    
		   	function fn_CheckForm(){
			   	if(any_empt_line_id("agentNm", '<spring:message code="page.agent.alert1" />') == false) return;
			   	if(any_empt_line_id("agentRemark", '<spring:message code="page.agent.alert2" />') == false) return;
			   	if(any_empt_line_id("partId", "부서를 입력하지 앟았습니다.") == false) return;
			   	if(any_empt_line_id("centerId", "지점을 입력하지 않았습니다..") == false) return;
			   	if(any_empt_line_id("agentContentgubun", "콘텐츠구분을 입력 하지 않았습니다.") == false) return;
			   	if(any_empt_line_id("agentStarttime1", "시작시간을 입력 하지 않았습니다.") == false) return;
			   	if(any_empt_line_id("agentStarttime2", "시작시간을 입력 하지 않았습니다.") == false) return;
			   	if(any_empt_line_id("agentEndtime1", "종료시간을 입력 하지 않았습니다.") == false) return;
			   	if(any_empt_line_id("agentEndtime2", "종료시간을 입력 하지 않았습니다.") == false) return;
			   
			   	if(parseInt($("#agentStarttime1").val() + $("#agentStarttime2").val()) > parseInt($("#agentEndtime1").val() + $("#agentEndtime2").val())) {
			    	alert("시작 시간이 종료시간보다 빠를수 없습니다.");
					return;
				}
			   
			   	var params = {  agentNm : $("#agentNm").val(),
							   	agentGubun : $("#agentGubun").val(),
							   	agentRemark : $("#agentRemark").val(),
							   	centerId : $("#centerId").val(),
							   	partId : $("#partId").val(),
							   	agentStarttime : $("#agentStarttime1").val() + $("#agentStarttime2").val(),
							   	agentEndtime : $("#agentEndtime1").val() + $("#agentEndtime2").val(),
							   	displaySeq : $("#displaySeq").val(),
							   	agentUseYn :  fn_emptyReplace($('input[name="agentUseYn"]:checked').val(),"Y"),
							   	agentContentgubun : $("#agentContentgubun").val(),
							   	mode : $("#mode").val(),
							   	agentFloor : $("#agentFloor").val(),
							   	agentCode : $("#agentCode").val()
					        };
				
			   	uniAjax
			   	(
					"/backoffice/basicManage/agentInfoUpdate.do", params, 
					function(result) {
						if (result.status == "SUCCESS") {
							location.reload();
						} else if (result.status == "LOGIN FAIL") {
		  					location.href="/backoffice/login.do";
		  				} else {
		  					alert(result.message);
		  				}
					},
					function(request){
						alert("Error:" +request.status );	       						
					}    		
		      	);
		   	}
		   
			function linkPage(pageNo) {
				$(":hidden[name=pageIndex]").val(pageNo);	
				$("form[name=regist]").attr("action","/backoffice/basicManage/AgentInfoList.do").submit();
			}
		   
			function fn_viewAgent(agentCode) {
				$('#mode').val("Edt");
				$('#agentCode').val(agentCode);
				$("form[name=regist]").attr("action", "/backoffice/basicManage/agentInfoView.do").submit();
		   	}
		   
			function search_form() {	
				$(":hidden[name=pageIndex]").val("1");	
				$("form[name=regist]").attr("action", "/backoffice/basicManage/AgentInfoList.do").submit();		  
			}
			
			function timeSelSetting(tagId,type) {
				var tag = $("#"+tagId);
				tag.append("<option value=''>선택</option>");
				if(type == "hour"){
					for(var i=1; i<24; i++){
						var hour = i < 10 ? "0"+i : i;
						tag.append("<option value='" + hour + "'>" + hour + "시</option>");
					}
				} else if(type == "min") {
					for(var i=0; i<60; i+=5){
						var min = i < 10 ? "0"+i : i;
						tag.append("<option value='" + min + "'>" + min + "분</option>");
					}					
				}
			}
			
		</script>
	</body>
</html>