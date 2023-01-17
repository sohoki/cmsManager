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
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <script type="text/javascript" src="/js/cms_report.js"></script>
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
<form:form name="regist" commandName="regist" method="post" >
<c:import url="/backoffice/inc/top_inc.do" />
<input type="hidden" name="pageIndex" id="pageIndex" value="${searchVO.pageIndex }">
<input type="hidden" name="mode" id="mode" >
<input type="hidden" name="reportSeq" id="reportSeq" >
<input type="hidden" name="reportSeqDel" id="reportSeqDel" >
<div class="Aconbox">
        <div class="rightT">
            <div class="Smain">
                <div class="Swrap Stitle">
                    <div class="infomenuA">
                        <img src="/images/home.png" alt="homeicon" />
                        <span> ▶ </span>
                        <spring:message code="menu.menu02" />
                        <span> ▶ </span>
                        <strong><spring:message code="menu.menu02_1" /></strong>
                    </div>
                </div>
            </div>
            
            <div class="Swrap Asearch">
                <div class="Atitle"><spring:message code="page.common.pageCnt" arguments="${totalCnt}"/></div>
                <section class="Bclear">
                    <select name="pageUnit" id="pageUnit" class="ssg_select">
                        <option value="10" <c:if test="${searchVO.pageUnit == '10' })"> selected="selected" </c:if>>10개씩 보기</option>
                        <option value="20" <c:if test="${searchVO.pageUnit == '20' })"> selected="selected" </c:if>>20개씩 보기</option>
                        <option value="30" <c:if test="${searchVO.pageUnit == '30' })"> selected="selected" </c:if>>30개씩 보기</option>
                        <option value="40" <c:if test="${searchVO.pageUnit == '40' })"> selected="selected" </c:if>>40개씩 보기</option>
                        <option value="50" <c:if test="${searchVO.pageUnit == '50' })"> selected="selected" </c:if>>50개씩 보기</option>
                    </select>
                    <select name="searchCondition"  id="searchCondition" class="ssg_select">
								<option value=""><spring:message code="combobox.text" /></option>
								<option value="reportTitle" <c:if test="${searchVO.searchCondition == 'reportTitle' }"> selected="selected" </c:if>>콘텐츠명</option>
								<option value="reportDc" <c:if test="${searchVO.searchCondition == 'reportDc' }"> selected="selected" </c:if>>콘텐츠 내용</option>
					</select>
					<input class="nameB ssg_input" type="text" name="searchKeyword" id="searchKeyword" placeholder="이름" value="${searchVO.searchKeyword}">                    
                    <a href="javascript:search_form();"><span class="redBtn"><spring:message code="button.inquire" /></span></a>
                    
                    <div class="rightB">
                        <a href="javascript:fn_ViewPage('Pop','0')" ><span class="deepBtn"><spring:message code="page.button.create" /></span></a>
                        <a href="#" data-needpopup-show="#report_pop" onClick="fn_ViewPage('Ins','0')"><span class="deepBtn"><spring:message code="page.button.url.create" /></span></a>
                        <a href="javascript:fn_ViewPage('Htm','0')" ><span class="deepBtn"><spring:message code="page.button.popup.create" /></span></a>
                        <a href="javascript:del_check()" ><span class="deepBtn"><spring:message code="button.delete" /></span></a>
                    </div>
                </section>
            </div>

            <div class="Swrap tableArea">
                <table class="margin-top30 backTable" id="tb_reportInfo">
                    <thead>
                        <tr>
                            <th><input type="checkbox" id="all_check" name="all_check" onClick="javascript:fn_allCheck()"></th>
                            <th><spring:message code="page.pagePreView" /></th>
                            <th><spring:message code="page.Title" /></th>
                            <th><spring:message code="page.Detail" /></th>
                            <th><spring:message code="page.url" /></th>
                            <th><spring:message code="common.UseYn.title" /></th>
                            
                        </tr>
                    </thead>
                    <tbody>
                     <%-- <c:forEach items="${resultList }" var="pageInfoInfo" varStatus="status">
                        <tr>
                            <td><input type="checkbox" name="reportCheck" value="${pageInfoInfo.reportSeq}"></td>
                            <td>
                            <c:choose>
						                <c:when test="${pageInfoInfo.reportGubun eq 'reportGubun_3' or pageInfoInfo.reportGubun eq 'reportGubun_4'}">
						                  <a href="#" onClick="view_Con('${fn:split(pageInfoInfo.reportPreview,',')[0]}','${fn:split(pageInfoInfo.reportPreview,',')[1]}', '${fn:split(pageInfoInfo.reportPreview,',')[2]}');">
										  <c:choose>
											  <c:when test="${fn:split(pageInfoInfo.reportPreview,',')[1] eq '' ||  fn:split(pageInfoInfo.reportPreview,',')[1] eq null }">
								               <img src="/images/no_image.png" style="width:80px; height:80px;"'>
								              </c:when>
								              <c:otherwise>
								                <img src='<c:url value='${ pageInfoInfo.reportUrl }'/>' style="width:160px;height:auto;">
								                <br>
								                 WIDTH: ${fn:split(pageInfoInfo.reportPreview,',')[1]} HEIGHT:  ${fn:split(pageInfoInfo.reportPreview,',')[2]} 
								                <c:if test="${pageInfoInfo.reportGubun eq 'reportGubun_4'}">
								                <br> PLAYTIME:${fn:split(pageInfoInfo.reportPreview,',')[3]}초
								                </c:if>
								              </c:otherwise>
							              </c:choose>
							              </a>
										  
						                </c:when>
						        		<c:otherwise>
						        		   <c:choose>
								        		  <c:when test="${pageInfoInfo.reportPreview ne '' &&  pageInfoInfo.reportPreview ne null }">
								        		       <a href="javascript:fn_PageView('${ pageInfoInfo.reportSeq }','${ pageInfoInfo.reportUrl }')"><img src="<c:url value='/upload/PreView/${ pageInfoInfo.reportPreview }'/>" style="width:160px;"></a>
								        		  </c:when>
								        		  <c:otherwise>
								        		        <a href="javascript:fn_PageView('${ pageInfoInfo.reportSeq }','${ pageInfoInfo.reportUrl }')"><img src="/images/url.png" style="width:80px; height:80px;"'></a>
								        		  </c:otherwise>
				                            </c:choose>
						        		 
						        		</c:otherwise>					        							        
						    </c:choose>
                            </td>
                            <td>
	                            <c:choose>
		                            <c:when test="${pageInfoInfo.reportGubun eq 'reportGubun_3' or pageInfoInfo.reportGubun eq 'reportGubun_4'}">
		                              ${ pageInfoInfo.reportTitle }
		                            </c:when>
		                            <c:otherwise>
		                              <a href="#" onClick="fn_ViewPage('Edt','${ pageInfoInfo.reportSeq }')" data-needpopup-show="#report_pop" class="underline" >
	                                  ${ pageInfoInfo.reportTitle }
	                                  </a>
		                            </c:otherwise>
	                            </c:choose>
                            </td>
                            <td>
                               <c:choose>
		                            <c:when test="${pageInfoInfo.reportGubun eq 'reportGubun_3' or pageInfoInfo.reportGubun eq 'reportGubun_4'}">
		                              ${ pageInfoInfo.reportTitle }
		                            </c:when>
		                            <c:otherwise>
		                              <a href="#" onClick="fn_ViewPage('Edt','${ pageInfoInfo.reportSeq }')" data-needpopup-show="#report_pop" class="underline" >
	                                  ${ pageInfoInfo.reportDc }
	                                  </a>
		                            </c:otherwise>
	                            </c:choose>
                            </td>
                            <td>${ pageInfoInfo.reportUrl }</td>
                            <td>${ pageInfoInfo.reportUseYn }</td>
                        </tr>
                      </c:forEach>       --%>                  
                    </tbody>
                </table>
            </div>
            <div class="new_pagenum">
                <div class="new_pager" id="ReportPage">
                </div>
            </div>
        </div>
    </div>

    <div id='report_pop' class="needpopup">  
        <!-- popheader-->                        
        <div class="popHead">
            <h2>URL등록</h2>
        </div>
        <!-- pop contents-->   
        <div class="popCon">
            <!--// 팝업 필드박스-->
            <div class="pop_box100">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.Title" /> <span class="join_id_comment joinSubTxt"></span></p>
                   <input type="text" name="reportTitle" id="reportTitle" class="input_noti" value="${regist.reportTitle }" size="100" maxlength="200"/>
                </div>                
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.Detail" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input  path="reportDc" size="50" maxlength="200" id="reportDc" class="input_noti" value="${regist.reportDc }" />
                </div>                
            </div>
            <!--// 팝업 필드박스-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.url" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:input  path="reportUrl" size="50" maxlength="200" id="reportUrl" class="input_noti" value="${regist.reportUrl }" />
                </div>   
            </div>
            <!--팝업 필드박스 //-->
            <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="page.urlGubun" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <form:select path="reportGubun" id="reportGubun" class="popSel" title="소속">
						     <form:option value="" label="--선택하세요--"/>
	                         <form:options items="${selectCodeDM}" itemValue="code" itemLabel="codeNm"/>
				    </form:select>	
                </div>                
            </div>
             <div class="pop_box50">
                <div class="padding15">
                    <p class="pop_tit">*<spring:message code="common.UseYn.title" /> <span class="join_id_comment joinSubTxt"></span></p>
                    <input type="radio" name="reportUseYn" id="reportUseYn_Y" value="Y" <c:if test="${regist.reportUseYn == 'Y' }"> checked </c:if>checked/>
                    <label for ="reportUseYn_Y"><spring:message code="button.use" /></label>
					<input type="radio" name="reportUseYn" id="reportUseYn_N" value="N" <c:if test="${regist.reportUseYn == 'N' }"> checked </c:if>/>
					<label for="reportUseYn_N"><spring:message code="button.nouse"/></label>
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

<script src="/js/jquery-ui.js"></script>
<script type="text/javascript">
   $(document).ready(function() { 
				$("#searchReportGubun").removeAttr("multiple");		   
				// report 쿼리 정리 하기
				report_List();
	});
    //여기 구문 다시 한번더 체크 
    function del_check(){
    	var reportSeq = ckeckboxValue('<spring:message code="common.radionCheck.msg" />',"reportCheck");
    	if (reportSeq == false){
    		return;
    	}
    	fn_uniDel("/backoffice/contentManage/reportDelete.do"
				  , "reportSeqDel="+ reportSeq
		          , "/backoffice/contentManage/displayList.do");	
    	
    	
    	/* 
    	if (confirm('<spring:message code="common.delete.msg" />')== true){
	    	apiExecute(
					"POST", 
					"/backoffice/contentManage/reportDelete.do",
					{
						reportSeqDel : reportSeq
					},
					null,				
					function(result) {				
						if (result != null) {
							if (result.status == "SUCCESS"){
								alert('<spring:message code="success.common.delete" />');
							}else {
								alert('<spring:message code="fail.common.delete" />');
							}					
						}else {
						    alert('<spring:message code="fail.common.delete" />');						  
						}
						report_List();			
					},
					null,
					null
				);	
	    }else {
	    	return;
	    } 	 */
    }
    function fn_CheckForm(){
    	   if (any_empt_line_id("reportTitle", '<spring:message code="page.report.alert01" />') == false) return;
		   if (any_empt_line_id("reportUrl", '<spring:message code="page.report.alert02" />') == false) return;
		   //여기 부분에 ajax 로 할지 정리 하기 
		   var param = {
              'reportSeq' : $("#reportSeq").val(),
              'reportTitle' : $("#reportTitle").val(),
              'reportDc' : $("#reportDc").val(),
              'reportUrl' : $("#reportUrl").val(),
              'reportUseYn' :  fn_emptyReplace($('input[name="reportUseYn"]:checked').val(),"Y"),
              'reportGubun' : $("#reportGubun").val(),
              'mode' : $("#mode").val()
          };
		  uniAjax("/backoffice/contentManage/pageInfoUpdate.do", param, 
        			function(result) {
				    	alert(result.message);
						if (result.status == "SUCCESS"){
							needPopup.hide();
							report_List();
						}else if (result.status == "LOGIN FAIL"){
							document.location.href="/backoffice/login.do";
		                }
				    },
				    function(request){
					    alert("Error:" +request.status );	       						
				    }    		
        	 );
		   return;
     }
    
    //페이지 등록 
	function fn_ViewPage(mode, reporSeq){
		  $('#mode').val(mode);
		  $('#reportSeq').val(reporSeq);
		  
		  if (mode == "Ins"){
			  $("#btnUpdate").text('<spring:message code="button.create" />');
			  AtenCommon.uniElementValue("reportTitle,reportDc,reportUrl", "");
			  $("select[id='reportGubun'] option[value='reportGubun_3']").remove();
			  $("select[id='reportGubun'] option[value='reportGubun_4']").remove();
			  $(".popHead > h2").html("URL 등록");
		  }else if (mode == "Htm"){
			  //pop업 설정 
			  var url = "/backoffice/contentManage/createPage.do";
			  window.open(url, 'url','menubar=no,width="+screen.width+",height="+screen.height+",toolbar=no,scrollbars=no');
		  }else if (mode == "Pop"){
			  //pop업 설정 
			  var url = "/backoffice/contentManage/fileIupload.do";
			  window.open(url, 'url','menubar=no,width=870,height=500,toolbar=no,scrollbars=yes');
		  }else {
			 //ajax 설정 
			  url= "/backoffice/contentManage/pageInfoView.do";
			  var param = {
		                'mode' : $('#mode').val(),
		                'reportSeq' : $('#reportSeq').val()
		      };
			  uniAjax(url, param, 
	     			function(result) {
					       if (result.status == "LOGIN FAIL"){
					    	   alert(result.meesage);
	  						   location.href="/backoffice/login.do";
	  					   }else if (result.status == "SUCCESS"){
                                  $(".popHead > h2").html("URL 수정");
		                          $("#btnUpdate").text('수정');
	  						    //여기 부분 수정 어떻게 할지 추후 생각
	  						    var obj = result.reportInfo;
	  						    $("#reportTitle").val(obj.reportTitle); 
	 		            	    $("#reportDc").val(obj.reportDc);
	 		            	    $("#reportUrl").val(obj.reportUrl);
	 		            	    $("#reportGubun").val(obj.reportGubun);
	 		            	    $('input:radio[name=reportUseYn]:radio[value=' + obj.reportUseYn + ']').prop("checked", true);	 
	  					   }
					    },
					    function(request){
						    alert("Error:" +request.status );	       						
					    }    		
	          );
				  
		  }
		  		  
	  }
	  function search_form(){	
		  $("#pageIndex").val("1");
		  report_List();
	  }
	  function fn_goPage(pageNo) {
		    $("#pageIndex").val(pageNo);
		    report_List();
	  }
	  function report_List(){
		   var params = { pageUnit : $("#pageUnit").val(),
							    searchCondition : $("#searchCondition").val(),
							    searchKeyword : $("#searchKeyword").val(),
							    pageIndex : $("#pageIndex").val()
		   };
		   uniAjax("/backoffice/contentManage/pageReportInfoList.do", params, 
	   			function(result) {
				    	if (result.status == "SUCCESS"){
					       $("#tb_reportInfo > tbody").empty();
					      
					       var setHtml = "";
					       var reportView ;
					       
					       $(".Atitle").html("");
					       $(".Atitle").html("◇ 총 " + result.resultList.length + "건의 게시물이 있습니다.");
					       
						   
					       for (var i = 0; i < result.resultList.length; i++){
							   var obj = result.resultList[i];	
							   setHtml += "<tr><td><input type='checkbox' name='reportCheck' value='"+ obj.reportSeq+"'></td><td>";
							   
							   
							  if (obj.reportGubun == "reportGubun_3" ||  obj.reportGubun == "reportGubun_4"){
								   
								   
								   reportView = obj.reportPreview.split(",");
								   setHtml +=  "<a href='#' onClick='view_Con( &#39;"+reportView[0] +"&#39; ,  &#39;"+reportView[1] +"&#39; , &#39;"+reportView[2] +"&#39;)'>"; 
								   if (reportView[0] != "" &&  reportView[0] != "null"){
									   setHtml += "<img src='"+ obj.reportUrl  + "' style='width:160px;height:auto;'>";
									   setHtml += "WIDTH: " +reportView[1] +" HEIGHT: " +reportView[2] +"";
									   if (obj.reportGubun == "reportGubun_4")
										   setHtml += "<br> PLAYTIME:" + reportView[3] +"초";
								   }else {
									   setHtml +=  "<img src='/images/no_image.png' style='width:80px; height:80px;'>";   
								   }
							   }else{
								   setHtml += "<a href='javascript:fn_PageView(&#39;" + obj.reportSeq +"&#39;,&#39;" + obj.reportUrl + "&#39;)'>";
								   if (obj.reportPreview != "" &&   obj.reportPreview != "null"){
									   setHtml += "<img src='/upload/PreView/"+ obj.reportPreview +"'  style='width:160px;'>"; 
								   }else {
									   setHtml += "<img src='/images/url.png' style='width:80px; height:80px;'>" 
								   }
								   setHtml += "</a>"
							   }
							   setHtml += "</td>";
							   if (obj.reportGubun == "reportGubun_3" ||  obj.reportGubun == "reportGubun_4"){
								   setHtml += "<td>"+ obj.reportTitle +"</td>";
								   setHtml += "<td>"+ obj.reportTitle +"</td>";
							   }else {
								   setHtml += "<td><a href='#' onClick='fn_ViewPage(&#39;Edt&#39;,&#39;"+  obj.reportSeq +"&#39;)' data-needpopup-show='#report_pop' class='underline'>"+ obj.reportTitle +"</a></td>";
								   setHtml += "<td><a href='#' onClick='fn_ViewPage(&#39;Edt&#39;,&#39;"+  obj.reportSeq +"&#39;)' data-needpopup-show='#report_pop' class='underline'>"+ obj.reportDc +"</a></td>";
							   }
							   setHtml += "<td>"+ obj.reportUrl +"</td>";
							   setHtml += "<td>"+ obj.reportUseYn +"</td>";
							   setHtml += "</tr>";
						   }
						   $("#tb_reportInfo >  tbody:last").append(setHtml);	 
						   //페이지 리스트
						   var pageObj = result.paginationInfo;						
	    	      			var pageHtml = ajaxPaging(pageObj.currentPageNo, pageObj.firstPageNo, pageObj.recordCountPerPage, 
	    	      						                  pageObj.firstPageNoOnPageList, pageObj.lastPageNoOnPageList, 
	    	      						                  pageObj.totalRecordCount, pageObj.pageSize, "fn_goPage");
	    	      			$("#ReportPage").html(pageHtml+"<div class='clear'></div>");	
	    	      			//페이지 리스트 끝 부분 
						}else if (result.status == "LOGIN FAIL"){
							document.location.href="/backoffice/login.do";
		                }else {
		                	alert(result.message);
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