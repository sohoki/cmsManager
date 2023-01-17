<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404 Error</title>
</head>
<body>
  <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
        <tr>
            <td width="100%" height="100%" align="center" valign="middle" style="padding-top: 150px;">
            code : ${requestScope['javax.servlet.error.status_code']}<br/>
            exception type : ${requestScope['javax.servlet.error.exception_type']}<br/>
            message : ${requestScope['javax.servlet.error.message']}<br/>
            exception : ${requestScope['javax.servlet.error.exception']}<br/>
            request uri : ${requestScope['javax.servlet.error.request_uri']}<br/>
            servlet name : ${requestScope['javax.servlet.error.servlet_name']}<br/>
            </td>
        </tr>
    </table>
</body>
</html>