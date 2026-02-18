<%-- 
    Document   : access_denied
    Created on : Feb 12, 2026, 2:33:20 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet"
              href="${pageContext.request.contextPath}/styleCss/access_denied.css"/>

        <title>Access</title>
    </head>
    <body>
        <div class="denied-container">
            <!-- Icon -->
            <div class="denied-icon">🚫</div>

            <!-- Title -->
            <h1 class="denied-title">Access Denied</h1>
            <h2 class="denied-subtitle">You don't have permission</h2>

            <!-- Message -->
            <p class="denied-message">
                Sorry, you are not authorized to access this page. 
                This area is restricted to users with specific permissions only.
            </p>

            <!-- Details -->

            <!-- Action Buttons -->
            <div class="denied-buttons">

                <a href="Main?action=home&type=home" class="denied-btn denied-btn-secondary">
                    🏠 Go to Homepage
                </a>
            </div>

            <!-- Footer -->
            <div class="denied-footer">
                <p class="denied-footer-text">
                    If you believe this is a mistake, please contact 
                    <a href="#" class="denied-contact-link">system administrator</a> 
                    for assistance.
                </p>
            </div>
        </div>
    </body>
</html>
