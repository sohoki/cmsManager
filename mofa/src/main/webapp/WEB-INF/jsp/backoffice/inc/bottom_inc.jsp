<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
 <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    <div id="agentState" class="needpopup" style="display:none">
        <div class="popHead">
            <h2>단말기 상태</h2>
        </div>
        <div id="chart_div" style="width:700px; height:400px"></div>
    </div>
    <script src="/js/needpopup.js"></script> 
    <script src="/js/jquery-ui.js"></script>
     
<div id="footer">
    <div class="contents">
        <span><spring:message code="URL.buttomInfo" /></span>
        <div class="clear"></div>
    </div>
</div>