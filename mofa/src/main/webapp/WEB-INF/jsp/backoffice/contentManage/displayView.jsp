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
    <script type="text/javascript" src="/js/cms_display.js"></script>
    <script type="text/javascript" src="/js/cms_report.js"></script>
    <script src="/js/popup.js"></script>
    
    <link rel="stylesheet" href="/css/needpopup.css">
    
</head>
<body>
<div id="wrapper">	
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/displayList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
<input type="hidden" name="mode" id="mode" value="${regist.mode}">
<input type="hidden" name="searchCondition" id="searchCondition" value="${search.searchCondition}">
<input type="hidden" name="searchKeyword" id="searchKeyword" value="${search.searchKeyword}">

<input type="hidden" name="subpageIndex" id="subpageIndex" >
<input type="hidden" name="agentPageIndex" id="agentPageIndex" >

<input type="hidden" id="displaySeq" name="displaySeq"  value="${regist.displaySeq}"/>
<input type="hidden" id="detailSeq" name="detailSeq" />
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶  </span>
                        <span><spring:message code="menu.menu02" /></span>
                        <span> ▶  </span>
                        <strong><span><spring:message code="menu.menu02_2" /></span></strong>
                    </div>
                </div>
            </div>
             
            
            <div class="Swrap tableArea">
                <div class="code_width">
                
                    <div class="Swrap Asearch"> 
                    <select name="subsearchCondition"  id="subsearchCondition">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="reportTitle" <c:if test="${reportVO.searchCondition == 'reportTitle' }"> selected="selected" </c:if>>
								<spring:message code="page.Title" />
								</option>
								<option value="reportDc" <c:if test="${reportVO.searchCondition == 'reportDc' }"> selected="selected" </c:if>>
								<spring:message code="page.Detail" />
								</option>
					</select>
					<input class="nameB" type="text" name="subsearchKeyword" id="subsearchKeyword" placeholder="검색" value="${reportVO.searchKeyword }">   
					<a href="javascript:fn_searchForm();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
					    <div class="rightB">
	                         <a href="#" onclick="fn_ViewPage('Pop','0')"  data-needpopup-show="#content_pop"><span class="deepBtn"><spring:message code="page.button.create" /></span></a>     
	                    </div>
					</div>
                    
                    <table class="margin-top30 backTable" id="tb_reportList"  style="overflow-y:auto; overflow-x:hidden; float:left;  height:500px;display:block; ">
                        <thead>
                            <tr>
                                <th><spring:message code="page.pagePreView" /></th>
                                <th><spring:message code="page.Title" /></th>
                                <th><spring:message code="page.Detail" /></th>
                                <th><spring:message code="button.create" /></th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table> 
                    <div class="new_pagenum margin-top30">
                        <div class="new_pager"  id="divPageLeftUi">
                          <%-- <ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="linkPage" /> --%>
                        </div>
                    </div>   
                </div>                            
                <div class="code_width" style="padding-top:28px;" >                
                    <span id="detailTable" ></span>
                    <div class='conBoxHeader' id='contentBoxHeader'>
                       <table class='margin-top30 backTable'>
                          <thead>
                             <tr>
                             <th colspan='2' style='width:100px'><spring:message code="common.Number.title" /> </th>
                             <th style='width:150px'><spring:message code="page.pagePreView" /></th>
                             <th style='width:100px'>화면</th>
                             <th style='width:100px'><spring:message code="page.display.playTotalCnt" /></th>
                             <th colspan='2' style='width:10px;'>정렬순서</th>
                             <th><spring:message code="button.delete" /></th>
                             </tr>
                          </thead>
                        </table>
                        <div class="content scrollStyle1 ui-sortable" id="playContent" style="overflow-y:auto; width:auto; height:500px;"></div>
                   </div>
                </div> 
                <div class="clear"></div> 
            </div>
            <!--  전광판 일때 -->
            <div class="Swrap tableArea viewArea">
                <div class="view_contents">
                <table class="pop_table thStyle">
	                <tbody>
	                    <tr>
	                        <th><spring:message code="page.display.title" /></th>
	                        <td>
		                        <span class="sp_label">${regist.displayTitle}
		                        
		                        </span>
		                        <span class="sp_input">
		                        <form:input path="displayTitle" id="displayTitle" size="30"/>
		                        </span>
	                        </td>
	                        <th><spring:message code="page.display.displayRemarak" /></th>
	                        <td style="text-align:left">
		                        <span class="sp_label">${regist.displayRemark}</span>
		                        <span class="sp_input">
		                        <form:input path="displayRemark" id="displayRemark"  size="30"/>
		                        </span>
	                        </td>
	                    </tr>	                    
	                    <tr>
	                        <th><spring:message code="page.display.displayUseYn" /></th>
	                        <td>
	                       
						    <input type="radio" name="displayUseYn" id="displayUseYn_Y" value="Y" <c:if test="${regist.displayUseYn == 'Y' }"> checked </c:if> />
                            <label for="displayUseYn_Y"><spring:message code="button.use" /></label>
					        <input type="radio" name="displayUseYn" id="displayUseYn_N" value="N" <c:if test="${regist.displayUseYn == 'N' }"> checked </c:if> />
					        <label for="displayUseYn_N"><spring:message code="button.nouse" /></label>
						    
	                        </td>
	                        <th><spring:message code="page.display.playTotalCnt" /></th>
	                        <td><span id='sp_displayTimeInfo'>${regist.displayTotalTime}<spring:message code="page.agent.seconds" /></span></td>
	                    </tr>
	                    <c:choose>
                        <c:when test="${regist.adminLevel eq 'ROLE_ADMIN'}">
	                    <tr>
	                        <th>*<spring:message code="page.emp.userAuth" /></th>
	                        <td>
	                            <span class="sp_label">${regist.deptLevel}</span>
		                        <span class="sp_input">
			                         <form:select path="adminLevel" id="adminLevel" title="소속" onClick="javascript:fn_ComboView()">
										         <form:option value="" label='--선택하세요--'/>
						                         <form:options items="${selectState}" itemValue="authorCode" itemLabel="authorNm"/>
								     </form:select>	
		                        </span>
	                        
	                        
	                        </td>
	                        <th><spring:message code="page.emp.part" /></th>
	                        <td style="text-align:left">
	                            <span class="sp_label">${regist.partNm}</span>
		                        <span class="sp_input">
			                        <form:select path="partId" id="partId" title="소속">
										         <form:option value="" label='--선택하세요--'/>
						                         <form:options items="${selectGroup}" itemValue="partId" itemLabel="partNm"/>
								    </form:select>
		                        </span>
	                        </td>
	                    </tr>
	                    </c:when>
                        <c:otherwise> 
                            <input type="hidden" id="adminLevel" value="${regist.adminLevel}"/>
                            <input type="hidden" id="partId" value="${regist.partId}"/>
                        </c:otherwise>
                        </c:choose>
	                    <tr>
	                       <th><spring:message code="page.display.displayGubun" /></th>
	                        <td>
		                        <span class="sp_label">${regist.displayGubunTxt }
		                        
		                        </span>
		                        <span class="sp_input">
		                        <form:select path="displayGubun" id="displayGubun" title="화면 구분" >
								     <option value=""><spring:message code="combobox.text" /></option>
			                         <form:options items="${selectDisplayCombo}" itemValue="code" itemLabel="codeNm"/>
								</form:select>
								</span>
	                        </td>
	                        
	                        <c:choose>
	                               <c:when test="${regist.displayGubun eq 'DispalyGubun_2'}">
				                        <th><spring:message code="page.display.disContent" /></th>
				                        <td>
				                          <a href="javascript:fn_preView();" class="redBtn" id="btnSendUpdate"><spring:message code="page.display.disFile" /></a>
				                          <a href="javascript:fn_ScheduleView();" class="redBtn" id="btnSendUpdate"><spring:message code="page.display.disContent" /></a>
				                        </td>
						           </c:when>
						           <c:otherwise>
						               <th><spring:message code="page.display.agentSend" /></th>
				                       <td>
				                          <a href="javascript:fn_sendAgent();" class="redBtn" id="btnSendUpdate"><spring:message code="page.display.agentSend" /></a>
				                          <a href="#"  onClick="fn_disPlayView()"  data-needpopup-show="#agent_pop" class="redBtn" id="btnSendUpdate"><spring:message code="page.display.agentView" /></a>
				                       </td>
						           </c:otherwise>
	                         </c:choose>
	                    </tr>
	                   
	                    <tr id="tr_content">
	                       <th><spring:message code="page.display.nextSeq" /></th>
	                        <td>
		                        <span class="sp_label">${regist.displayNextSeqTxt }</span>
		                        <span class="sp_input">
		                        <form:select path="displayNextseq" id="displayNextseq" title="소속">
									     <option value=""><spring:message code="combobox.text" /></option>
				                         <form:options items="${selectDisplayNextCombo}" itemValue="displaySeq" itemLabel="displayTitle"/>
							    </form:select>
							    </span>
	                        </td>
	                       <th><spring:message code="page.display.disWH" /></th>
	                       <td>
	                          <span class="sp_label"><spring:message code="page.display.Width" /> : ${regist.displayWidth } <spring:message code="page.display.Height" /> : ${regist.displayHeight }</span>
	                          <span class="sp_input">
	                          <spring:message code="page.display.Width" /> : <form:input  path="displayWidth" size="10" maxlength="10" id="displayWidth"   value="${regist.displayWidth }" onkeypress="only_num();" />
                    	      <spring:message code="page.display.Height" /> : <form:input  path="displayHeight" size="10" maxlength="200" id="displayHeight"   value="${regist.displayHeight }" onkeypress="only_num();" />
                    	      </span>
	                       </td>
	                    </tr> 
	                </tbody>
	            </table>
	            </div>
            </div>
            <!--  전광판 일때 -->
            <div class="footerBtn">
	            <a href="javascript:fn_Edit();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
	            <a href="javascript:del_form();" class="redBtn"><spring:message code="button.delete" /></a>
	            <a href="javascript:fn_list()" class="deepBtn"><spring:message code="button.list" /></a>
	        </div>
        </div>
    </div>

<c:import url="/backoffice/inc/bottom_inc.do" />
</form:form>
</div>




<div id='content_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>콘텐츠 등록</h2>
        </div>
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">* 콘텐츠 구분 
                    <select id="cb_contentGubun"  onChange="fn_ConView()" style="width:200px;">
                        <option value="con">콘텐츠 등록</option>
                        <option value="url">페이지 등록</option>
                        <option value="edt">화면 등록</option>
                    </select>
                    </p>
                </div>                
            </div>
            
            <div class="pop_box100" id="dv_Con">
                <div class="padding15">
                    콘텐츠 등록
                </div>
             </div>
             <div class="pop_box100" id="dv_Url">
                <div class="padding15">
                    url 등록
                </div>
             </div>
         </div>        
</div>


	
<input type="hidden" name="leftPageIndex" id="leftPageIndex" >
<input type="hidden" name="rightPageIndex" id="rightPageIndex" >
 <div id='agent_pop' class="needpopup"><!--data-needpopup-options="custom"-->
	        <div class="popHead">
	            <h2><spring:message code="page.center.agentInfoState" /></h2>
	            
	            
	        </div>
	        <div class="Swrap tableArea popCon">
	                <div class="code_width">
	                    <table class="margin-top30 backTable" id="tb_leftAgent">
	                        <thead>
	                            <tr>
	                                <th><spring:message code="page.agent.TitleNm" /></th>
	                                <th><spring:message code="button.create" /></th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        </tbody>
	                    </table> 
	                    <div class="new_pagenum margin-top30" id="leftPage">
	                    </div>  
	                </div>                            
	                <div class="code_width" style="vertical-align: top;">                
	                    
	                    <table class="margin-top30 backTable" id="tb_RightAgent">
	                        <thead>
	                            <tr>
	                                <th><spring:message code="page.agent.TitleNm" /></th>
	                                <th><spring:message code="button.delete" /></th>
	                            </tr>
	                        </thead>
	                        <tbody >
	                        </tbody>
	                    </table>
	                     <div class="new_pagenum margin-top30" id="rightPage">
	                    </div>  
	                </div> 
	                <div class="clear"></div> 
	            </div>
</div>
	
<script type="text/javascript">
	function fn_disPlayView(){
		var params = {
				displayGubun : "${regist.displayGubun}" , //$("#displayGubun").val(),
				displaySeq : "${regist.displaySeq}",
				leftPageIndex :   fn_EmptyCheck($("#leftPageIndex").val(),"1") ,
				rightPageIndex :   fn_EmptyCheck($("#rightPageIndex").val(),"1") ,
				agentNm : ""				
		}
		uniAjaxXform("/backoffice/basicManage/AgentInfoListAjax.do", params, 
	   			function(result) {
		    	            if (result.status == "SUCCESS"){
		    	            	 if (result.disStateList != null ){
		    	            		//초기화 
	    	            		     $("#tb_leftAgent > tbody").empty(); 
	    	            		     $("#tb_RightAgent > tbody").empty(); 
	    	            		   
	    	            		     
	    	            		     var setHtml = "";
	  	    	            		 for (var i = 0; i < result.disStateList.length ; i++){
	  	    	            			var obj = result.disStateList[i];
	  	    	            			
	  	    	            			if (obj.play_gubun == "OT"){
	  	    	            				setHtml = "<tr><td>"+  obj.agent_nm +"</td>"
		    	      					        + "<td><a href='#' onclick='fn_updateAgent(&#39;Edt&#39; , &#39;"+obj.agent_code +"&#39;)' class='grayBtn'>등록</a></td>";
		    	      					    
		    	      					    $("#tb_leftAgent >  tbody:last").append(setHtml);	
	  	    	            			}else {
	  	    	            				setHtml = "<tr><td>"+ obj.agent_nm  +"</td>"
		    	      					        + "<td><a href='#' onclick='fn_updateAgent(&#39;Del&#39; , &#39;"+ obj.agent_code +"&#39;)' class='grayBtn'>취소</a></td>";
	  	    	            				$("#tb_RightAgent >  tbody:last").append(setHtml);	
	  	    	            			}
	  	    	      				}
	    	      		    	    //동적 페이징 
	  	    	            		var pageObj = result.leftPaginationInfo;						
		  	    	      			var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, 
		  	    	      						                  pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, 
		  	    	      						                  pageObj.totalRecordCount, pageObj.pageSize, "ajaxAgentLeftPageChange");
		  	    	      			$("#leftPage").html(pageHtml+"<div class='clear'></div>");	
		  	    	      				
		  	    	      			var pageRightObj = result.rightPaginationInfo;						
	  	    	      				pageHtml = ajaxPaging(pageRightObj.currentPageNo, pageRightObj.firstPageNo, pageRightObj.recordCountPerPage, 
	  	    	      					                                   pageRightObj.firstPageNoOnPageList  , pageRightObj.lastPageNoOnPageList, 
	  	    	      					                                   pageRightObj.totalRecordCount, pageRightObj.pageSize, "ajaxRightPageChange");
	  	    	      				$("#rightPage").html(pageHtml+"<div class='clear'></div>");	
	    	            		
					    		 }
							}else {
								alert(result.message );	
							}
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	    ); 
		
	}
	function ajaxAgentLeftPageChange(page){
		$("#leftPageIndex").val(page);
		fn_disPlayView();
	}
	function ajaxRightPageChange(page){
		$("#rightPageIndex").val(page);
		fn_disPlayView();
	}
	function fn_updateAgent(mode, agentCode){
		var params = {mode : mode, displaySeq: "${regist.displaySeq}" , agentCode: agentCode};
		
		uniAjax("/backoffice/basicManage/AgentUpdateStateAjax.do", params, 
				function(result) {
						if (result.status == "SUCCESS"){
							fn_disPlayView("2");
						}else if (result.status == "LOGIN FAIL"){
							alert(result.message);
							location.href="/backoffice/login.do";
						}else {
							alert(result.message);
						}
				    },
				    function(request){
					    alert("Error:" +request.status );	       						
				    }    		
		   ); 
	}
	function fn_agentViewPop(){
		  /* 
		   uniAjax("/backoffice/contentManage/agentGroupInfo.do", {'displaySeq':$("#displaySeq").val(), 'pageIndex':  fn_emptyReplace($("#agentPageIndex").val(),"1")}, 
				function(result) {
						if (result.status == "SUCCESS"){
							fn_agentState(result, conschCode);
						}else if (result.status == "LOGIN FAIL"){
							alert(result.message);
							location.href="/backoffice/login.do";
						}else {
							alert(result.message);
						}
				    },
				    function(request){
					    alert("Error:" +request.status );	       						
				    }    		
		   ); */
	}
    function fn_ConView(){
    	$('table:contains("tb_")').hide();
    	$("#tb_"+$("#cb_contentGubun").val()).show();
    }
	function fn_list(){
		//속성 제거 이후 넘기기
		var params = {
				pageIndex : $("#pageIndex").val(),
				searchCondition : $("#searchCondition").val(),
				searchKeyword : $("#searchKeyword").val()
		}
		fn_doPost("regist", params , "/backoffice/contentManage/displayList.do");
		
	}
	function fn_ViewPage(mode, seq){
		$("div[name^='dv_']").hide();
	}
	function fn_ScheduleView(){
		var url = "/backoffice/contentManage/conPopSchView.do?displaySeq="+$("#displaySeq").val();//;?report_seq="+seq;
		window.open(url, 'url','menubar=no,width=700,height=600,toolbar=no,scrollbars=no');
	}
	
	function fn_searchForm() {
		$("#subpageIndex").val("1");
		fn_PageLiftView();
	}
	function fn_PageLiftView(){
		var params = {
				displayGubun : $("#displayGubun").val(),
				pageIndex :   fn_EmptyCheck($("#subpageIndex").val(),"1") ,
				searchCondition : $("#subsearchCondition").val(),
				searchKeyword : $("#subsearchKeyword").val()				
		}
		uniAjaxXform("/backoffice/contentManage/ajaxDisplayView.do", params, 
       			function(result) {
  	    	            if (result.status == "SUCCESS"){
  				    		
  				    		 if (result.resultList != null ){
  				    			$( '#tb_reportList > tbody').empty();
  				    			didLeftHtml = "";
  				    			for (var i=0; i<result.resultList.length; i++) {
  				    				var obj = result.resultList[i];
  				    				didLeftHtml += "<tr><td>";
  				    				if (obj.reportGubun == "reportGubun_3" || obj.reportGubun == "reportGubun_4"){
  				    					 var jbSplit = obj.reportPreview.split(',');
 				    					 if (jbSplit[1] == "" || jbSplit[1] == "undefined"){
 				    						didLeftHtml += "<a href='#' onClick='view_Con(&#39;"+jbSplit[0]+"&#39;, &#39;"+jbSplit[1]+"&#39; , &#39;"+jbSplit[2]+"&#39;)'';>";
	  				    					didLeftHtml += "<img src='/images/no_image.png' style='width:50px; height:50px;'></a>";
 				    					 }else {
 				    						 console.log(obj.reportUrl);
	  				    					didLeftHtml += "<img src="+  obj.reportUrl +"  style='width:160px;height:auto;'>";
	  				    			     }
  				    				}else {
  				    					//var jbSplit = obj.reportPreview.split(',');
				    					 if (obj.reportPreview != "" || obj.reportPreview != null){
				    						didLeftHtml += "<img src='/upload/PreView/"+obj.reportPreview +"' style='width:160px;'>";
				    					 }else {
	  				    					didLeftHtml += "<a href='#' onClick=fn_PageView(&#39;"+ obj.reportSeq +"&#39;,&#39;"+ obj.reportUrl +"&#39;)'><img src='/images/url.png' style='width:50px; height:50px;'></a>";
	  				    			     }
  				    				}
  				    			    didLeftHtml += "</td>";
  				    				didLeftHtml += "<td onClick='codeUpdate(&#39;" + obj.reportSeq +"&#39;);'>"+ obj.reportTitle + "</td>";
  				    				didLeftHtml += "<td onClick='codeUpdate(&#39;" + obj.reportSeq +"&#39;);'>"+ obj.reportDc + "</td>";
  				    				didLeftHtml += "<td><a href='#' onClick='codeUpdate(&#39;"+ obj.reportSeq+"&#39;);' class='grayBtn'><spring:message code='button.create' /></a>";
  				    				didLeftHtml += "</td></tr>";
  				    				
  				    			}
  				    			$("#tb_reportList> tbody").append(didLeftHtml);
  				    			//$("#divPageLeftUi").html()
  				    			
  				    			var pageObj = result.paginationInfo;						
  								var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, 
  											                  pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, 
  											                  pageObj.totalRecordCount, pageObj.pageSize, "ajaxLeftPageChange");
  								$("#divPageLeftUi").html(pageHtml+"<div class='clear'></div>");	
  				    		 }
  	    	            	
  						}else {
  							
  						}
  				    },
  				    function(request){
  					    alert("Error:" +request.status );	       						
  				    }    		
        ); 
	}
	function ajaxLeftPageChange(page){
		$("#subpageIndex").val(page);
		fn_PageLiftView();
	}
	// 여기 부분 수정 하기 끝 부분
	function fn_preView(){
		var PageWidth = "${regist.displayWidth }";
		var PageHeight = "${regist.displayHeight }";
		var url = "/backoffice/contentManage/contentPreview.do?displaySeq="+$("#displaySeq").val();	      	
		window.open(url,"{regist.displayTitle } 미리보기", "width="+ PageWidth +",height="+PageHeight+",top=0,left=0,scrollbars=auto")	;      						
		
			    	
	}
	
	function fn_Edit(){
		 
		 if ($("#btnUpdate").text()  == "입력") {
			 if (any_empt_line_id('displayTitle', '<spring:message code="page.display.alert01" />') == false) return;
		    	
		    	var params = { mode:$("#mode").val(), 
		    			       displaySeq : $("#displaySeq").val(),
		    			       displayTitle : $("#displayTitle").val(),
		    			       displayRemark : $("#displayRemark").val(),
		    			       displayGubun : $("#displayGubun").val(),
		    			       displayNextSeq : $("#displayNextSeq").val(),
		    			       displayWidth : $("#displayWidth").val(),
		    			       displayHeight : $("#displayHeight").val(),
		    			       displayUseYn : $('input[name="displayUseYn"]:checked').val(),
		    			       adminLevel : $("#adminLevel").val(),
		    			       partId : fn_emptyReplace($("#partId").val(), "0")
		    	              };
			     uniAjax("/backoffice/contentManage/displayBasicUpdate.do", params, 
		       			function(result) {
		  	    	            alert(result.message);
		  				    	if (result.status == "SUCCESS"){
		  				    		 location.reload();
		  						}
		  				    },
		  				    function(request){
		  					    alert("Error:" +request.status );	       						
		  				    }    		
		        ); 
		 }
		 if ($("#displayGubun").val() == "DispalyGubun_2"){
			 $("#tr_content").show();
		 }else {
			 $("#tr_content").hide();
		 }
		 if ($("#mode").val() == "Edt"){
			    $("#mode").val('Txt');
			    $(".sp_label").show();
			    $(".sp_input").hide();
		 		$("#btnUpdate").text("수정");
		 		
		 }else{
			    $("#mode").val('Edt');
			    $(".sp_label").hide();
			    $(".sp_input").show();
		    	$("#btnUpdate").text("입력");	   	    
		 }
	}
	$(document).ready(function() {   
		if ($("#mode").val() == ""){
			$("#mode").val("Edt");
		}
		fn_Edit();
		fn_PageLiftView();
		detailList();
	});	
	function fn_sendAgent(){
		 var params = { displaySeq : $("#displaySeq").val() };
		 uniAjax("/backoffice/contentManage/disPlayUpdate.do", params, 
	       			function(result) {
			                alert(result.message);
	  	    	            if (result.status == "SUCCESS"){
	  				    		//detailList();
	  						}else if (result.status == "LOGIN FAIL"){
	  							location.href="/backoffice/login.do";
	  						}
	  				    },
	  				    function(request){
	  					    alert("Error:" +request.status );	       						
	  				    }    		
	        ); 
		
	}
	function codeUpdate(code){
		if (confirm('<spring:message code="common.regist.msg" />')== true){
	    	 var params = { displaySeq : $("#displaySeq").val(), reportSeq : code};
			 uniAjax("/backoffice/contentManage/detailUpdate.do", params, 
		       			function(result) {
		  	    	            alert(result.message);
		  				    	if (result.status == "SUCCESS"){
		  				    		detailList();
		  						}
		  				    },
		  				    function(request){
		  					    alert("Error:" +request.status );	       						
		  				    }    		
		        ); 
	    }else {
	    	return;
	    } 	
	}
	//정렬순서변경
	function order_code(code, code1){
		//400 error 발생
        var params = { detailSeq : code, detailOrder : code1 , displaySeq : $("#displaySeq").val() };
		uniAjax("/backoffice/contentManage/detailOrder.do", params, 
       			function(result) {
  				    	if (result.status == "SUCCESS"){
  				    		detailList();
  						}else if (result.status == "LOGIN FAIL"){
  							location.href="/backoffice/login.do";
  						}else{
  							alert(result.message);
  						}
  				    },
  				    function(request){
  					    alert("Error:" +request.status );	       						
  				    }    		
        );
	}
	
	//시간 수정 
	function fn_TimeChange(disSeq){
		//숫자 유효성 검사 확인 하기 
		if (disSeq != ""){
			
			if (isNumeric($("#time_"+disSeq).val(), "") == false ){
				alert('<spring:message code="page.error.message1" />');    
				$("#time_"+disSeq).val().replace(/[^0-9]/g,'');
				$("#time_"+disSeq).val( $("#time_"+disSeq).val().replace(/[^0-9]/g,'') );
				$("#time_"+disSeq).focus(); 
				return; 
			} 
			var params = {
					displaySeq : $("#displaySeq").val(),
					detailSeq : disSeq,
					detailTime : $("#time_"+disSeq).val()
			};
			uniAjax("/backoffice/contentManage/detailTimeUpdate.do", params, 
	       			function(result) {
	  	    	            alert(result.message);
	  				    	if (result.status == "SUCCESS"){
	  				    		$("#reportTitleTxt").html("");
								$("#detailSeq").val("");
								$("#detailTime").val("");
								$("#sp_displayTimeInfo").html(result.totalTime+"초");
								 
								//시간 변경값 대체 하기 
	  						}else if (result.status == "LOGIN FAIL"){
	  							location.href="/backoffice/login.do";
	  						}
	  				    },
	  				    function(request){
	  					    alert("Error:" +request.status );	       						
	  				    }    		
	        );
		}else {
			alert('<spring:message code="page.display.alert02" />');
		}
		
	}	
	// 우측 전광판 전송할 화면
	function detailList(){
		apiExecute(
				"POST", 
				"/backoffice/contentManage/displayDetailInfo.do",
				{
					displaySeq : $("#displaySeq").val()
				},
				null,				
				function(result) {	
					     //우측 화면 리스트 
					    didDetailHtml = "";
					    if (result != null) {
 							if (result.detailInfo != null) {
 								for (var i=0; i<result.detailInfo.length; i++) {
 									var obj = result.detailInfo[i];
 									
 									didDetailHtml += "<div id='listItem_"+i+"' class='listDiv'>";
 									didDetailHtml += "<table border='0'><tr><td><a class='dragHandle'></a></td><td class='orderSeq'>"+(i+1)+"</td><td>";
 									
 									if (fn_trim(obj.report_gubun) == "reportGubun_3" || fn_trim(obj.report_gubun) == "reportGubun_4"){
 										didDetailHtml += "<img class='listImg' src='"+ obj.report_url+"' style='width:100px;height:auto;' id='file_"+obj.detail_seq+"'>";
 									}else {
 										if (obj.report_preview != "" &&   obj.report_preview != "null"){
 											didDetailHtml +="<img class='listImg' src='/upload/PreView/"+ obj.report_preview +"' style='width:100px; height:50px;' id='file_"+obj.detail_seq+"'>";
 										}else {
 											didDetailHtml +=  "<img class='listImg' src='/images/url.png' style='width:50px; height:50px;' id='file_"+obj.detail_seq+"'>";
 										}
 									}
 									didDetailHtml += "</td><td>"+ obj.report_title  +"</td>";
 									if (fn_trim(obj.report_gubun) == "reportGubun_4"){
 										didDetailHtml += "<td>"+obj.detail_time+"초</td>";
 									}else {
 										didDetailHtml += "<td><input type='text' id='time_"+obj.detail_seq+"' value='"+ obj.detail_time  +"' size='3'>"
 										didDetailHtml += "초 <a href='javascript:fn_TimeChange("+obj.detail_seq+");' class='redBtn' style='padding: 5px 5px;'>적용</a></td>";
 									}
 									if (i == 0){
 										didDetailHtml += "<td style='width:5px'>&nbsp;</td>";
 										didDetailHtml += "<td style='width:5px'><a href='javascript:order_code(&#39;"+ obj.detail_seq  +"&#39;,&#39;DN&#39;)' class='grayBtn' style='width:10px;'>▼</a></td>";	
 									}else if (  (i+1) == result.detailInfo.length){
 										didDetailHtml += "<td style='width:5px'><a href='javascript:order_code(&#39;"+ obj.detail_seq  +"&#39;,&#39;UP&#39;)' class='grayBtn' style='width:10px;'>▲</a></td>";
 										didDetailHtml += "<td style='width:5px'>&nbsp;</td>";
 									}else { 										
 										didDetailHtml += "<td style='width:5px'><a href='javascript:order_code(&#39;"+ obj.detail_seq  +"&#39;,&#39;UP&#39;)' class='grayBtn' style='width:10px;'>▲</a></td>";
 	 									didDetailHtml += "<td style='width:5px'><a href='javascript:order_code(&#39;"+ obj.detail_seq  +"&#39;,&#39;DN&#39;)' class='grayBtn' style='width:10px;'>▼</a></td>";
 									}
 									didDetailHtml += "<td><a href='javascript:fn_delCode(&#39;"+ obj.display_seq  +"&#39;,&#39;"+ obj.detail_seq  +"&#39;)' class='grayBtn'>삭제</a></td></tr>";
 									didDetailHtml +="</table></div>"
 								}	  
 								
 							}    
 							
 						$("#playContent").html(didDetailHtml);
   						}	
						if (result.totTime != null){
							$("#sp_displayTimeInfo").html(result.totTime+"초");		
						}
				},
				null,
				null
			);	
	}
	$(".content").sortable({
		revert:true,
		placeholder:"block-placeholder",
		handle:".dragHandle",
		update: function (event, ui){
			ui.item.addClass("block");
			ui.item.attr("style", "opacity:70");
		},
		stop: function(event, ui){
			$(".content div").each(function (i, item){
				if (i ==0){
					if ($(this).attr("class") != "conBoxHeader"){
						
					}
				}
			})
			item_order();
		}
	})
	function item_order(){
        var length = $("#playContent").find('.listImg').length;   
        var disInfos = new Array();
        
		for (i=0; i < length ; i++ ){	 		
			var disInfo = new Object();
			var img_id =$("#playContent").find('.listImg').eq(i).attr('id').replace('file_','');
			disInfo.displaySeq = $("#displaySeq").val();
			disInfo.detailSeq = img_id;
			disInfo.detailSort = (i+1);
			disInfos.push(disInfo); 
		}	
		//정렬정리
		uniAjax("/backoffice/contentManage/detailOrderUpdatePage.do", disInfos, 
       			function(result) {
  	    	            alert(result.message);
  				    	if (result.status == "SUCCESS"){
  				    		detailList();
  						}else if (result.status == "LOGIN FAIL"){
  							location.href="/backoffice/login.do";
  						}
  				    },
  				    function(request){
  					    alert("Error:" +request.status );	       						
  				    }    		
        ); 
	}

	function fn_orderUpdate(fileSeq, orders){
		alert(fileSeq +":"+ orders);
	}
	function check_form(){
		   $("form[name=regist]").attr("action", "/backoffice/contentManage/displayDetail.do").submit();
	}
	function fn_delCode(displaySeq, detailSeq){
		if (confirm('<spring:message code="common.delete.msg" />')== true){
			var params  = { displaySeq : displaySeq, detailSeq : detailSeq };
			uniAjax("/backoffice/contentManage/detailDelete.do", params, 
	       			function(result) {
	  	    	            alert(result.message);
	  				    	if (result.status == "SUCCESS"){
	  				    		detailList();
	  						}else if (result.status == "LOGIN FAIL"){
	  							location.href="/backoffice/login.do";
	  						}
	  				    },
	  				    function(request){
	  					    alert("Error:" +request.status );	       						
	  				    }    		
	        ); 
			
	    }else {
	    	return;
	    } 	
	}
	function del_form(){
        $("#displayTitle").remove();
    	$("#displayRemark").remove();
    	$("#displayGubun").remove();
    	$("#displayWidth").remove();
    	$("#displayHeight").remove();
    	$("#displayNextseq").remove();
    	$("#adminLevel").remove();
    	$("#partId").remove(); 
    	fn_uniDel("/backoffice/contentManage/displayDelete.do"
				  , "displaySeq="+  $("#displaySeq").val()
		          , "/backoffice/contentManage/displayList.do");	
    	
    	
	     	   
	}	
</script>  
</body>
</html>