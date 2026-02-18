<%-- 
    Document   : error_page
    Created on : Feb 12, 2026, 2:20:44 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/styleCss/error.css"/>

        <title>Error Page</title>
    </head>
    <body>
        <div class="bg-decoration"></div>
        <div class="bg-decoration"></div>
        <div class="bg-decoration"></div>

        <div class="error-container">
            <div class="error-404">404</div>
            <div class="error-emoji">😕</div>
            <h1 class="error-title">Oops! Page Not Found</h1>
            <p class="error-message">
                The page you are looking for might have been removed, 
                had its name changed, or is temporarily unavailable.
            </p>

            <div class="error-buttons">
                <a href="Main?action=home&type=home" class="error-btn error-btn-home">
                    🏠 Go to Homepage
                </a>
            </div>
        </div>

    </body>
</html>
