<%-- 
    Document   : new_account
    Created on : Feb 8, 2026, 2:02:06 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
        <link rel="stylesheet" href="styleCss/update.css"/>
        <title>Account</title>
    </head>
    <body>

        <%@include file="nav.jspf"%>

        <div class="container">

            <div class="form-header">
                <h1>Add New Information</h1> 
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
                                   >
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

                                   required>
                        </div>

                        <!-- LAST NAME -->
                        <div class="form-group">
                            <label class="form-label">Last Name</label>
                            <input type="text"
                                   class="form-input"
                                   name="lastName"
                                   maxlength="50"
                                   >
                        </div>

                        <!-- BIRTHDAY -->
                        <div class="form-group">
                            <label class="form-label">Birthday</label>
                            <input type="date"
                                   class="form-input"
                                   name="birthday"
                                   >
                        </div>

                        <!-- PHONE -->
                        <div class="form-group">
                            <label class="form-label">Phone Number</label>
                            <input type="tel"
                                   class="form-input"
                                   name="phone"
                                   maxlength="20"
                                   >
                        </div>

                        <div class="form-group">
                            <label class="form-label">Gender</label>
                            <div class="radio-group">
                                <label class="radio-label">
                                    <input type="radio" name="gender" value="1" checked>
                                    Male
                                </label>
                                <label class="radio-label">
                                    <input type="radio" name="gender" value="0">
                                    Female
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Account Status</label>
                            <div class="radio-group">
                                <label class="radio-label">
                                    <input type="radio" name="isUse" value="1">
                                    Active
                                </label>
                                <label class="radio-label">
                                    <input type="radio" name="isUse" value="0" checked>
                                    Blocked
                                </label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="form-label">Role in System</label>
                            <select class="form-select" name="roleInSystem">
                                <option value="0">Staff</option>
                                <option value="1">Admin</option>
                                <option value="2">Manager</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn btn-primary">New Account</button>
                    <button type="reset" class="btn btn-secondary">Reset Form</button>
                </div>

                <input type="hidden" name="action" value="add">
                <input type="hidden" name="type" value="account">
            </form>

        </div>
    </body>
</html>
