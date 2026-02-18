<%-- 
    Document   : login
    Created on : Feb 4, 2026, 2:07:52 PM
    Author     : Trần Minh Tuấn
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link rel="stylesheet" href="<c:url value='/styleCss/login.css'/>">
    </head>
    <body>
        <!--your code here-->
        <div class="login-container">
            <img src="${pageContext.request.contextPath}/images/Capy.png" alt=""/>
            <div class="login-header">
                <h1>Login</h1>
                <p>Welcome back!</p>
            </div>

            <form action="Login" id="loginForm" method="post">
                <div class="form-group">
                    <label>Account</label>
                    <input 
                        type="text" 
                        name="account" 
                        placeholder="Enter your account"
                        required
                        >
                </div>

                <div class="form-group">
                    <label>Password</label>
                    <input 
                        type="password" 
                        name="password" 
                        placeholder="Enter your password"
                        required
                        >
                </div>



                <button type="submit" class="login-button">Login</button>

                <c:if test="${not empty errorUser}">
                    <div style="margin-top: 15px; padding: 12px 15px; background-color: #ffe6ea;
                         border-left: 5px solid #e74c3c;color: #c0392b;
                         font-size: 14px;
                         border-radius: 6px;
                         text-align: center; ">
                        ${errorUser}
                    </div>
                </c:if>
            </form>
        </div>
    </body>
</html>
