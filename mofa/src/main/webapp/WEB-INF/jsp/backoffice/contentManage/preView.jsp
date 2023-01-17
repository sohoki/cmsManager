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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><spring:message code="URL.TITLE" /></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">    
    <link href="/css/reset.css" rel="stylesheet" />
    <link href="/css/global.css" rel="stylesheet" />
    <link href="/css/page.css" rel="stylesheet" />
    <script type="text/javascript" src="/js/jquery-1.12.3.min.js"></script>
    <script type="text/javascript" src="/js/html2canvas.min.js"></script>
    <script type="text/javascript" src="/js/common.js"></script>
    <!-- <script type="text/javascript" src="/js/jquery.ajax-cross-origin.min.js"></script> -->
    <script src="/js/popup.js"></script>
    
</head>
<body>
<form name="printPreviewForm" id="printPreviewForm">
<div style="width:100%; height:1080px;border:none;" id="frame_url"></div>
     <input type="hidden" id="imgData" name="imgData"></input>
     <input type="hidden" id="url" value="${reportInfo.reportUrl}"/>
	 <input type="hidden" id="reportGubun" value="${reportInfo.reportGubun}"/>
	 <input type="hidden" id="reportPreview" name="reportPreview" value="${reportInfo.reportPreview}"/>
	 <input type="hidden" id="reportSeq" name="reportSeq"  value="${reportInfo.reportSeq}">
	 <div id="contents" style="display:hidden" ></div>
</form>
 <script type="text/javascript">
    
 $(document).ready(function() { 
  	
 	 var previewUrl = ($("#reportGubun").val() == "reportGubun_1" ) ? "http://<%= request.getServerName() + ":" + request.getServerPort() %>"+ $("#url").val() : $("#url").val();
 	 var reportSeq = $("#reportSeq").val();
 	 
 	 
 	 
 	 $.ajax({
          url : previewUrl,
          //crossOrigin: true ,
          type : 'post',
          success: function(data) {
        	  $('#frame_url').html(data);
             
              
              html2canvas($("#frame_url"), {
  				    background :'#FFFFFF',
                     onrendered: function(canvas) {
                      if (typeof FlashCanvas != "undefined") {
                          FlashCanvas.initElement(canvas);
                      }
                      $("#imgData").val(canvas.toDataURL("image/jpeg", 1));
                      
                       var params = { imgData : $("#imgData").val(),
                      		reportPreview : $("#reportPreview").val(),
                      		reportSeq : reportSeq
                      };
                      apiExecute(
            				  "POST", 
            				  "/backoffice/contentManage/reportUpload.do",
    	       					params,
    	       					null,				
    	       					function(result) {							
    	       						if (result != null) {	   
    	       							alert(result.message);
  	  	       					    self.close();
  	                    		    opener.document.reload();
    	       						}
    	       					},
    	       					function(request){
    	       						alert(request.status );	       						
    	       					},
    	       					null
    	     				);   
                      } 
                   }); 
          },
              error: function() {
              $('#include_page').text('페이지 점검중 입니다.');
          }
      }); 
 	 
 	
	});
    
    </script>
</body>
</html>