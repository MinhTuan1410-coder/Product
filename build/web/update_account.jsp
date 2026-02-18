<%-- 
    Document   : update_account.jsp
    Created on : Feb 8, 2026
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account</title>
        <link rel="stylesheet" href="styleCss/update.css"/>
    </head>
    <body>

        <%@include file="nav.jspf"%>

        <div class="container">

            <div class="form-header">
                <h1>Update Information</h1> 
            </div>

            <form id="accountForm"
                  class="form-content"
                  method="post"
                  action="Main">

                <div class="form-section">
                    <div class="section-title">
                        <span class="section-icon">👤</span>
                        Account Information
                    </div>

                    <div class="form-grid">

                        <!-- ACCOUNT -->
                        <div class="form-group">
                            <label class="form-label">
                                Account (Username) <span class="required">*</span>
                            </label>
                            <input type="text"
                                   class="form-input"
                                   name="account"
                                   value="${account.account}"
                                   readonly>
                        </div>

                        <!-- PASSWORD -->
                        <div class="form-group">
                            <label class="form-label">
                                Password <span class="required">*</span>
                            </label>
                            <input type="password"
                                   class="form-input"
                                   name="password"
                                   placeholder="Enter new password"
                                   maxlength="20"
                                   value="${account.pass}"
                                   required>
                        </div>

                        <!-- FIRST NAME -->
                        <div class="form-group">
                            <label class="form-label">
                                First Name <span class="required">*</span>
                            </label>
                            <input type="text"
                                   class="form-input"
                                   name="firstName"
                                   maxlength="30"
                                   value="${account.firstName}"
                                   required>
                        </div>

                        <!-- LAST NAME -->
                        <div class="form-group">
                            <label class="form-label">Last Name</label>
                            <input type="text"
                                   class="form-input"
                                   name="lastName"
                                   maxlength="50"
                                   value="${account.lastName}">
                        </div>

                        <!-- BIRTHDAY -->
                        <div class="form-group">
                            <label class="form-label">Birthday</label>
                            <input type="date"
                                   class="form-input"
                                   name="birthday"
                                   value="<fmt:formatDate value='${account.birthday}' pattern='yyyy-MM-dd'/>">
                        </div>

                        <!-- PHONE -->
                        <div class="form-group">
                            <label class="form-label">Phone Number</label>
                            <input type="tel"
                                   class="form-input"
                                   name="phone"
                                   maxlength="20"
                                   value="${account.phone}">
                        </div>

                        <!-- GENDER: 1 = MALE, 0 = FEMALE -->
                        <div class="form-group">
                            <label class="form-label">Gender</label>
                            <div class="radio-group">
                                <label class="radio-label">
                                    <input type="radio"
                                           name="gender"
                                           value="1"
                                           ${account.gender ? "checked" : ""}>
                                    Male
                                </label>
                                <label class="radio-label">
                                    <input type="radio"
                                           name="gender"
                                           value="0"
                                           ${!account.gender ? "checked" : ""}>
                                    Female
                                </label>
                            </div>
                        </div>

                        <!-- STATUS: 1 = ACTIVE, 0 = BLOCK -->
                        <div class="form-group">
                            <label class="form-label">Account Status</label>
                            <div class="radio-group">
                                <label class="radio-label">
                                    <input type="radio"
                                           name="isUse"
                                           value="1"
                                           ${account.isUse ? "checked" : ""}>
                                    Active
                                </label>
                                <label class="radio-label">
                                    <input type="radio"
                                           name="isUse"
                                           value="0"
                                           ${!account.isUse ? "checked" : ""}>
                                    Blocked
                                </label>
                            </div>
                        </div>

                        <!-- ROLE -->
                        <div class="form-group">
                            <label class="form-label">Role in System</label>
                            <select class="form-select" name="roleInSystem">
                                <option value="0"
                                        ${account.roleInSystem == 0 ? "selected" : ""}>
                                    Staff
                                </option>
                                <option value="1"
                                        ${account.roleInSystem == 1 ? "selected" : ""}>
                                    Admin
                                </option>
                                <option value="2"
                                        ${account.roleInSystem == 2 ? "selected" : ""}>
                                    Manager
                                </option>
                            </select>
                        </div>

                    </div>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn btn-primary">
                        Update Account
                    </button>
                    <button type="reset" class="btn btn-secondary">
                        Reset Form
                    </button>
                </div>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="type" value="account">
            </form>

        </div>
    </body>
</html>
