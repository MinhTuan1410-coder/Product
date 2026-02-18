<%-- 
    Document   : list_accounts
    Created on : Feb 5, 2026, 6:24:50 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accounts</title>
        <link rel="stylesheet" href="styleCss/table.css"/>
    </head>
    <body>
        <%@include file="nav.jspf"%>

        <section>
            <div>
                <h2>List of account in system</h2>
                <table>
                    <tr>
                        <th>Account</th>
                        <th>Full name</th>
                        <th>Birth day</th>
                        <th>Gender</th>
                        <th>Phone</th>
                        <th>Role in system</th>
                        <th>Action</th>
                    </tr>
                    <c:forEach var="i" items="${requestScope.list}">
                        <tr>
                            <td>${i.account}</td>
                            <td>${i.lastName} ${i.firstName}</td>
                            <td>${i.birthday}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${i.gender}">Male</c:when>
                                    <c:otherwise>Female</c:otherwise>
                                </c:choose>
                            </td>

                            <td>${i.phone}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${i.roleInSystem == 1}">Admin</c:when>
                                    <c:when test="${i.roleInSystem == 2}">Manager</c:when>
                                    <c:otherwise>Staff</c:otherwise>
                                </c:choose>
                            </td>

                            <td class="action-btns">

                                <c:url var="urlUpdate" value="Main">
                                    <c:param name="action" value="update"/>
                                    <c:param name="account" value="${i.account}"/>
                                    <c:param name="type" value="account"/>
                                </c:url>

                                <a href="${urlUpdate}" class="btn btn-edit">Update</a>




                                <c:url var="urlUsing" value="Main">
                                    <c:param name="action" value="use"/>
                                    <c:param name="account" value="${i.account}"/>
                                    <c:param name="type" value="account"/>
                                </c:url>

                                <c:choose>
                                    <c:when test="${i.isUse}">
                                        <a href="${urlUsing}" class="btn btn-deactive">Deactive</a>
                                    </c:when>
                                    <c:otherwise>
                                        <a href="${urlUsing}" class="btn btn-active">Active</a>
                                    </c:otherwise>
                                </c:choose>




                                <c:url var="urlDelete" value="Main">
                                    <c:param name="action" value="delete"/>
                                    <c:param name="account" value="${i.account}"/>
                                    <c:param name="type" value="account"/>
                                </c:url>
                                <a href="${urlDelete}"
                                   class="btn btn-delete"
                                   onclick="return confirm('Delete this account?')">
                                    Delete
                                </a>

                            </td>

                        </tr>                        
                    </c:forEach>                    
                </table>
            </div>
        </section>

    </body>
</html>
