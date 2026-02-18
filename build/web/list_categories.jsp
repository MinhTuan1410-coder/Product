<%-- 
    Document   : list_categories
    Created on : Feb 5, 2026, 8:12:17 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
        <link rel="stylesheet" href="styleCss/table.css"/>
    </head>
    <body>
        <%@include file="nav.jspf"%>

        <section>
            <div>
                <h2>List all categories</h2>
                <table>
                    <tr>
                        <th>Type Id: </th>
                        <th>Name</th>
                        <th>memo</th>
                        
                        <!--Admin and Manager-->
                            <c:if test="${sessionScope.user.roleInSystem == 1 || sessionScope.user.roleInSystem == 2}">
                            <th>Tool</th>
                            </c:if>


                    </tr>
                    <c:forEach var="i" items="${requestScope.list}">
                        <tr>
                            <td>${i.typeId}</td>
                            <td>${i.categoryName}</td>
                            <td>${i.memo}</td>
                            
                            <!--Admin and Manger can use tool to manage it-->
                            <c:if test="${sessionScope.user.roleInSystem == 1 || sessionScope.user.roleInSystem == 2}">
                                <td class="action-btns">


                                    <c:url var="urlUpdate" value="Main">
                                        <c:param name="category" value="${i.typeId}"/>
                                        <c:param name="action" value="update"/>
                                        <c:param name="type" value="category"/>
                                    </c:url>
                                    <a href="${urlUpdate}" class="btn btn-edit">Update</a>



                                    <c:url var="urlDelete" value="Main">
                                        <c:param name="category" value="${i.typeId}"/>
                                        <c:param name="action" value="delete"/>
                                        <c:param name="type" value="category"/>
                                    </c:url>
                                    <a href="${urlDelete}"
                                       class="btn btn-delete"
                                       onclick="return confirm('Delete this category?')">
                                        Delete
                                    </a>
                                </td>
                            </c:if>
                        </tr>                        
                    </c:forEach>                    
                </table>
            </div>
        </section>
    </body>
</html>
