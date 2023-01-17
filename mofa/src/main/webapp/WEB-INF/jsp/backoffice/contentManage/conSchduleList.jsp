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
    <link href="/css/jquery-ui.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
   	<script src="/js/popup.js"></script>
    <link rel="stylesheet" href="/css/needpopup.css">
    <style type="text/css">
         #agent_pop{top: 40% !important;}
		 thead, tbody tr {
            display:table;
			width:100%;
			table-layout:fixed;
        }
    </style>
</head>
<body>
<div id="wrapper">	
<form:form name="regist" commandName="regist" method="post" action="/backoffice/contentManage/conSchInfoList.do">
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${regist.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="conschCode" id="conschCode" >
<input type="hidden" name="agentPageIndex" id="agentPageIndex">
<input type="hidden" name="adminLevel" id="adminLevel" value="${regist.adminLevel}">

<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <span><spring:message code="menu.menu02" /></span>
                        <span> ▶ </span>
                        <strong><span><spring:message code="menu.menu02_3" /></span></strong>
                    </div>
                </div>
            </div>

            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                    <select name="searchCondition"  id="searchCondition" class="ssg_select">
						<option value=""><spring:message code="combobox.text" /></option>
						<option value="conschTitle" <c:if test="${regist.searchCondition == 'conschTitle' }"> selected="selected" </c:if>><spring:message code="page.consch.title" /></option>
						<option value="displayTitle" <c:if test="${regist.searchCondition == 'displayTitle' }"> selected="selected" </c:if>><spring:message code="page.agent.displayNm" /></option>
					</select>
					<input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" value="${regist.searchKeyword}">                    
                    <a href="javascript:search_form();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    <div class="rightB">
                        <a href="#" onclick="fn_conSchPop('Ins','0')" data-needpopup-show="#conshch_pop"><span class="deepBtn"><spring:message code="button.create" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable">
                    <thead>
                        <tr>
                            <th><spring:message code="common.Number.title" /></th>
                            <th><spring:message code="page.consch.title" /></th>
                            <th><spring:message code="page.consch.scheduleDay" /></th>
                            <th><spring:message code="page.consch.displayTitle" /></th>
                            <th><spring:message code="page.consch.agentCnt" /></th>
                            <th><spring:message code="page.consch.partNm" /></th>
                            <th style="display:none"><spring:message code="page.consch.sinkGubun" /></th>
                            <th><spring:message code="common.UseYn.title" /></th>
                            <th><spring:message code="page.consch.conschSend" /></th>
                            <th><spring:message code="button.preview" /></th>
                            <th><spring:message code="button.delete" /></th>
                        </tr>
                    </thead>
                    <tbody>
                     <c:forEach items="${resultList }" var="conSchInfo" varStatus="status">
                        <tr>
                            <td><c:out value="${ totalCnt - (((searchVO.pageIndex - 1) * searchVO.pageSize) + status.count) + 1}"/></td>
                            <td><a href="#" data-needpopup-show="#conshch_pop" onclick="fn_conSchPop('Edt','${ conSchInfo.conschCode }')" class="underline">${ conSchInfo.conschTitle }</a></td>
                            <td><a href="#" data-needpopup-show="#conshch_pop" onclick="fn_conSchPop('Edt','${ conSchInfo.conschCode }')" class="underline">
                                ${ conSchInfo.conschStartday}~${ conSchInfo.conschEndday}
                                </a>
                            </td>
                            <td>${conSchInfo.displayTitle }</td>
                            <td><a href="#" onclick="fn_agentViewPop('${ conSchInfo.conschCode }')"  data-needpopup-show="#agent_pop" class="underline" >${ conSchInfo.conschAgentcnt }</a></td>
                            <td style="text-align:center">${conSchInfo.partNm }</td>
                            <td style="display:none">${conSchInfo.conschUnisinkgubunTxt }</td>
                            <td>${conSchInfo.conschUseyn }</td>
                            <td><a href="#" onclick="fn_sendSchedue('${ conSchInfo.conschCode }')" class="grayBtn"><spring:message code="page.consch.conschSend" /></a></td>
                            <td><a href="javascript:fn_disPreview('${ conSchInfo.displaySeq }','${ conSchInfo.displayTitle }')" class="grayBtn"><spring:message code="button.preview" /></a></td>                            
                            <td>
                                <a href="javascript:del_check('${ conSchInfo.conschCode }');" class="grayBtn"><spring:message code="button.delete" /></a>
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
    <div id='conshch_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2 id='consch_popTitle'><spring:message code="page.consch.subTitle" /></h2>
        </div>
        
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input path="conschTitle" size="80" maxlength="100" id="conschTitle" class="input_noti" />
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.scheduleDay" /> <span class="join_id_comment joinSubTxt"></span></p>
                     <form:input path="conschStartday" size="10" maxlength="10" id="conschStartday" class="width40 input_noti"/>~
	                 <form:input path="conschEndday" size="10" maxlength="10" id="conschEndday" class="width40 input_noti"/>	  
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.displayTitle" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="displaySeq" id="displaySeq" class="popSel" title="소속">
							     <option value=""><spring:message code="combobox.text" /></option>
		                         <form:options items="${selectDisplayCombo}" itemValue="displaySeq" itemLabel="displayTitle"/>
					</form:select>
                </div>                
            </div>
            
            <c:choose>
	            <c:when test="${regist.adminLevel eq 'ROLE_ADMIN'}">
	            <div class="pop_box50">
	                <div class="padding15">
	                    <p class="pop_tit">*<spring:message code="page.agent.partInfo" /> <span class="join_id_comment joinSubTxt"></span></p>
	                    <form:select path="partId" id="partId" class="popSel" title="소속">
								     <option value=""><spring:message code="combobox.text" /></option>
			                         <form:options items="${selectGroupCombo}" itemValue="partId" itemLabel="partNm"/>
						</form:select>
	                </div>   
	            </div>
	            </c:when>
	            <c:otherwise> 
	                <input type="hidden" id="partId" value="${regist.partId}" />
	            </c:otherwise>
            </c:choose>    
            
            <!--// 팝업 필드박스-->
            <div class="pop_box50" style="display:none">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.sinkGubun" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="conschUnisinkgubun" id="conschUnisinkgubun" class="popSel" title="소속" onChange="javascript:fn_schView()">
							     <option value=""><spring:message code="combobox.text" /></option>
		                         <form:options items="${selectConsch}" itemValue="code" itemLabel="codeNm"/>
					</form:select>
					<span id="sp_flooerInfo"></span>
                </div>   
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="common.UseYn.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="radio" name="conschUseyn" id="conschUseyn_Y" value="Y" checked <c:if test="${regist.conschUseyn == 'Y' }"> checked </c:if> />
                    <label for="conschUseyn_Y"><spring:message code="button.use" /></label>
					<input type="radio" name="conschUseyn" id="conschUseyn_N" value="N" <c:if test="${regist.conschUseyn == 'N' }"> checked </c:if> />
					<label for="conschUseyn_N"><spring:message code="button.nouse" /></label>
                </div>                
            </div>
            <!--  보여주기 안보여 주기 -->
            <div class="pop_box50" id="div01">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.sinkUniCode" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input path="conschUnisinkcode" size="10" id="conschUnisinkcode"/>
                </div>   
            </div>
             <div class="pop_box50" id="div02">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.consch.sinkOrder" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input path="conschUnisinkorder" size="10" id="conschUnisinkorder"/>
                </div>                
            </div>
            <!-- 보여주기 안보여 주기  -->
             
            <div class="clear"></div>   
        </div>
        <div class="pop_footer">
            <span id="join_confirm_comment" class="join_pop_main_subTxt">내용을 모두 입력후  클릭해주세요.</span>
            <a href="javascript:fn_CheckForm();" class="redBtn" id="btnUpdate"><spring:message code="button.update" /></a>
        	<div class="clear"></div>
        </div>
    </div>
    <div id='agent_pop' class="needpopup"><!--data-needpopup-options="custom"-->
        <div class="popHead">
            <h2><spring:message code="page.consch.AgentConnectState" /></h2>
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
                        <tbody style="overflow:auto;height:480px;display:block;">
                        </tbody>
                    </table>
                </div> 
                <div class="clear"></div> 
            </div>
		</div>
		<div class="clear"></div>   
	
		<script src="/js/jquery-ui.js"></script>
		<c:import url="/backoffice/inc/bottom_inc.do" />
		</form:form>
	</div>
<script type="text/javascript" src="<c:url value="/js/calender.js" />" ></script> 	
<script type="text/javascript">	
	//코드 확인
	$(function () {
		CalenderFnc.prototype.getSet('conschStartday','conschEndday');
	});	
    function del_check(code){	
    	// 연동 단말기 와 날짜 체크 해서 확인해 보기 
    	$(":hidden[name=pageIndex]").val($("#pageIndex").val());	
    	alert($("#pageIndex").val());
    	fn_uniDelJSON("/backoffice/contentManage/conSchDeleteInfo.do"
				  , {conschCode : code}
		          , "/backoffice/contentManage/conSchInfoList.do");	
   }
   function fn_schView(){
	   if ($("#conschUnisinkgubun").val() == "CONSCH_GUBUN_1"){
		   $("#div01").hide();
		   $("#div02").hide();
	   }else {
		   $("#div01").show();
		   $("#div02").show(); 
	   }
   }
   //콘텐츠 reset
   function fn_sendSchedue(conschCode){
	   uniAjax("/backoffice/contentManage/scheduleReset.do", {'conschCode':conschCode}, 
	   			function(result) {
						if (result.status == "SUCCESS"){
							alert(result.message);
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
   function fn_agentViewPop(conschCode){
	   $("#conschCode").val(conschCode);
	   uniAjax("/backoffice/contentManage/agentGroupInfo.do", {'conschCode':$("#conschCode").val(), 'pageIndex':  fn_emptyReplace($("#agentPageIndex").val(),"1")}, 
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
   	   );
   }
   function fn_updateAgent(mode, conschCode, code){
	   var params = (mode == "Ins") ? {'mode': mode, 'conschCode' : conschCode, 'agentCode': code, 'pageIndex':  fn_emptyReplace($("#agentPageIndex").val(),"1")} 
	                               :  {'mode': mode, 'conschCode' : conschCode, 'groupSeq': code, 'pageIndex':  fn_emptyReplace($("#agentPageIndex").val(),"1")};
	   
       uniAjax("/backoffice/contentManage/agentUpdateInfo.do", params, 
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
      	);
   }
   function ajaxPageChange(page){
	   fn_agentViewPop($("#conschCode").val());
   }
   function fn_agentState(result, conschCode){
	   if (result.resultList.length >= 0){
		   
		   $("#tb_leftAgent > tbody").empty(); 
		   var data = result.resultList;
		   if (data.length > 0 ){
		    	for (var i = 0; i < data.length; i++ ){
					setHtml = "<tr><td>"+ data[i].agentNm +"</td>"
					        + "<td><a href='#' onclick='fn_updateAgent(&#39;Ins&#39;,&#39;"+ conschCode +"&#39;,&#39;"+ data[i].agentCode +"&#39;)' class='grayBtn'>등록</a></td>";
					$("#tb_leftAgent >  tbody:last").append(setHtml);	
				}
		    	//동적 페이징 
		    	var pageObj = result.leftPaginationInfo;						
				var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, 
						                  pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, 
						                  pageObj.totalRecordCount, pageObj.pageSize, "ajaxPageChange");
				$("#leftPage").html(pageHtml+"<div class='clear'></div>");	
			}
	   }
       if (result.resultRight.length >= 0){
    	   $("#tb_RightAgent > tbody").empty(); 
		   var data = result.resultRight;
		   if (data.length > 0 ){
		    	for (var i = 0; i < data.length; i++ ){
					setHtml = "<tr><td>"+ data[i].agentNm +"</td>"
					        + "<td><a href='#' onclick='fn_updateAgent(&#39;Del&#39;,&#39;"+ conschCode +"&#39;,&#39;"+ data[i].groupSeq +"&#39;)' class='grayBtn'>삭제</a></td>";
					$("#tb_RightAgent >  tbody:last").append(setHtml);	
				}		   	
			}
	   }
	   
   }
   
   //에이전트 연동 팝업 관련 작업 
   function fn_disPreview(displaySeq, displaytitle){
	   var url = "/backoffice/contentManage/contentPreview.do?displaySeq="+displaySeq;	   
	   //추후 값 넣기 
		window.open(url, displaytitle+ " 미리보기", "width=1920,height=1080,top=0,left=0,scrollbars=auto")	;      
   }
   function fn_CheckForm(){
	   if (any_empt_line_id("conschTitle", '<spring:message code="page.consch.alert01" />') == false) return;
	   if (any_empt_line_id("conschStartday", '<spring:message code="page.consch.alert02" />') == false) return;
	   if (any_empt_line_id("conschEndday", '<spring:message code="page.consch.alert03" />') == false) return;
	   if ( fn_calValidate ('conschStartday', 'conschEndday', '<spring:message code="page.consch.alert08" />') ==  false) return;
	   //날짜 비교 사이트 
	   if (any_empt_line_id("displaySeq", '<spring:message code="page.consch.alert04" />') == false) return;
	   if (any_empt_line_id("conschUnisinkgubun", '<spring:message code="page.consch.alert05" />') == false) return;
	   //여기 부분에 ajax 로 할지 정리 하기 
	   var param = {
		  'conschCode' : $("#conschCode").val(),
          'conschTitle' : $("#conschTitle").val(),
          'conschStartday' : $("#conschStartday").val(),
          'conschEndday' : $("#conschEndday").val(),
          'displaySeq' : $("#displaySeq").val(),
          'partId' : fn_emptyReplace($("#partId").val(),"0"),
          'conschUnisinkgubun' : $("#conschUnisinkgubun").val(),
          'conschUseyn' :  fn_emptyReplace($('input[name="conschUseyn"]:checked').val(),"Y"),
          'conschUnisinkcode' : $("#conschUnisinkcode").val(),
          'conschUnisinkorder' : fn_emptyReplace($("#conschUnisinkorder").val(),"0"),
          'mode' : $("#mode").val()
      };
	  uniAjax("/backoffice/contentManage/conSchInfoUpdate.do", param, 
    			function(result) {
			    	alert(result.message);
					if (result.status == "SUCCESS"){
						document.location.reload();
					}
			    },
			    function(request){
				    alert("Error:" +request.status );	       						
			    }    		
    	 );
	  return;
   }
   function fn_conSchPop(mode, consch){
	   $("#mode").val(mode);
	   $("#conschCode").val(consch);
	   
	   if (mode == "Edt"){
		      //여기 부분 정리 헤서 보기 
              $("#consch_popTitle").html("스케줄 수정");
              $("#btnUpdate").text('<spring:message code="button.update" />');	
		      url= "/backoffice/contentManage/conSchInfoView.do";
			  var param = {
		                'conschCode' : consch
		      };
			  uniAjax(url, param, 
	     			function(result) {
					       if (result.status == "LOGIN FAIL"){
					    	   alert(result.message);
	  						   location.href="/backoffice/login.do";
	  					   }else if (result.status == "SUCCESS"){
	  						    //여기 부분 수정 어떻게 할지 추후 생각
	  						    var obj = result.conSchInfo;
	  						    $("#conschTitle").val(obj.conschTitle); 
	 		            	    $("#conschStartday").val(obj.conschStartday);
	 		            	    $("#conschEndday").val(obj.conschEndday);
	 		            	    $("#displaySeq").val(obj.displaySeq);
	 		            	    $("#partId").val(obj.partId);
                                $("#conschUnisinkgubun").val(obj.conschUnisinkgubun);
	 		            	    $("#conschUnisinkcode").val(obj.conschUnisinkcode);
	 		            	    $("#conschUnisinkorder").val(obj.conschUnisinkorder);
	 		            	    $('input:radio[name=conschUseyn]:radio[value=' + obj.conschUseyn + ']').prop("checked", true);
	 		            	    fn_schView();
	  					   }
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	          );
	   }else {
           $("#consch_popTitle").html("스케줄 등록");
           $("#btnUpdate").text('<spring:message code="button.create" />');
           $("#conschTitle").val(""); 
           $("#conschStartday").val("");
           $("#conschEndday").val("");
           $("#displaySeq").val("");
           
           if($("#adminLevel").val() == "ROLE_ADMIN"){
              $("#partId").val("");
           } else{
              $("#partId").val($("#partId").val());
           }     	   
		   
           $("#conschUnisinkgubun").val("CONSCH_GUBUN_1");
		   fn_schView();
	   }
   }
   function linkPage(pageNo) {
		$(":hidden[name=pageIndex]").val(pageNo);	
		$("form[name=regist]").attr("action","/backoffice/contentManage/conSchInfoList.do").submit();
   }
   function search_form(){
	   linkPage("1");
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
   
   $('.needpopup_remover').click(function(){
	   alert("확인");
   });
   

</script>
</body>
</html>