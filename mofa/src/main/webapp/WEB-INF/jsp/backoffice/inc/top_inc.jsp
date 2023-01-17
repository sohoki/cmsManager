<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>  
<%@ page import ="egovframework.com.cmm.AdminLoginVO" %>
<%
AdminLoginVO loginVO = (AdminLoginVO)session.getAttribute("AdminLoginVO");


if(loginVO == null ){ %>
	<script type="text/javascript">
	location.href="/backoffice/login.do";
	</script>
<%  }else{ %> 
    <header class="Mwrap">
        <section>
            <div class="subwidth">
                <h1><a href="/backoffice/basicManage/dashboardInfo.do"><img src="/images/logo.png" alt="logo" /></a></h1>
                <nav id="topMenu">
                    <ul>
					    <li class="dropdown">
                            <a href="/backoffice/basicManage/dashboardInfo.do" class="dropbtn">
                            <spring:message code="menu.menu01" />
                            </a>
                            <ul class="dropdown-content sedrop">
                                <li><a href="/backoffice/basicManage/dashboardInfo.do"><spring:message code="menu.menu01_1" /></a></li>
                                <li><a href="/backoffice/basicManage/AgentInfoList.do"><spring:message code="menu.menu01_2" /></a></li>
                            </ul>
                        </li>
                        <li class="dropdown">
                            <a href="/backoffice/contentManage/pageInfoList.do" class="dropbtn">
                            <spring:message code="menu.menu02" />
                            </a>
                            <ul class="dropdown-content sedrop">
                                <li><a href="/backoffice/contentManage/pageInfoList.do"><spring:message code="menu.menu02_1" /></a></li>
                                <li><a href="/backoffice/contentManage/displayList.do"><spring:message code="menu.menu02_2" /></a></li>
                                <li><a href="/backoffice/contentManage/conSchInfoList.do"><spring:message code="menu.menu02_3" /></a></li>
                                <li><a href="/backoffice/contentManage/schInfoList.do"><spring:message code="menu.menu02_4" /></a></li>
                            </ul>
                        </li>
                        
                        <li class="dropdown">
                            <a href="/backoffice/basicManage/centerList.do" class="dropbtn"><spring:message code="menu.menu03" /></a>
                            <ul class="dropdown-content sedrop">
                                <!--<% if (loginVO.getAdminLevel().equals("ROLE_ADMIN")){ %>-->
                                <li><a href="/backoffice/basicManage/partList.do"><spring:message code="menu.menu03_1" /></a></li>
                                <!--<% } %>-->
                                <li><a href="/backoffice/basicManage/centerList.do"><spring:message code="menu.menu03_2" /></a></li>                                
                            </ul>
                        </li>
					   
                        <li class="dropdown">
                            <a href="/backoffice/basicManage/empList.do" class="dropbtn">
                            <spring:message code="menu.menu04" />
                            </a>
                            <ul class="dropdown-content sedrop">
                                <li><a href="/backoffice/basicManage/empList.do"><spring:message code="menu.menu04_1" /></a></li>       
                                <li><a href="/backoffice/basicManage/codeList.do"><spring:message code="menu.menu04_2" /></a></li>
                                <li><a href="/backoffice/operManage/xmlList.do"><spring:message code="menu.menu04_3" /></a></li> 
                                
                            </ul>
                        </li>
                        <li class="dropdown">
								<a href="#" class="dropbtn"><spring:message code="menu.menu05" /></a>
								<ul class="dropdown-content sedrop">
									<li><a href="/backoffice/loginfo/loginList.do"><spring:message code="menu.menu05_1" /></a></li> 
                                    <li><a href="/backoffice/loginfo/SyslogList.do"><spring:message code="menu.menu05_2" /></a></li> 
                                    <li><a href="/backoffice/operManage/xmlList.do"><spring:message code="menu.menu05_3" /></a></li> 
								</ul>
				        </li>
				
                    </ul>
                </nav>
                <p>                                   
                    <a href="<c:url value='/backoffice/actionLogout.do'/>"><spring:message code="button.logout" /></a>
                </p>
            </div>
        </section>
    </header>
        <% } %>	    
