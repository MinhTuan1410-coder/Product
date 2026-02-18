<%-- 
    Document   : list_products
    Created on : Feb 5, 2026, 9:06:19 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product</title>
        <link rel="stylesheet" href="styleCss/productList.css"/>
    </head>
    <body>
        <%@include file="nav.jspf"%>

        <div class="product-list-container">
            <!-- Header -->
            <div class="product-list-header">
                <h1 class="product-list-title">Product Management</h1>
            </div>

            <!-- Filter Section -->
            <form action="Main">
                <div class="product-filter-section">
                    <!--filter-->
                    <div class="product-filter-group">
                        <label class="product-filter-label">Category:</label>
                        <select class="product-filter-select" name="category">
                            <option>All Categories</option>
                            <c:forEach items="${categories}" var="i">
                                <option value="${i.typeId}" >   
                                    ${i.categoryName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="product-filter-group">
                        <input class="product-btn-delete" type="submit" value="  Filter  ">
                        <input type="hidden" name="type" value="product">
                        <input type="hidden" name="action" value="filter">
                    </div>
                </div>
            </form>
            
            <!-- Product Table -->
            <div class="product-list-card">
                <table class="product-table">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Product ID</th>
                            <th>Product Name</th>
                            <th>Category</th>
                            <th>Price</th>
                            <th>Unit</th>
                            <th>Owner</th>
                                <c:if test="${sessionScope.user.roleInSystem == 1 || sessionScope.user.roleInSystem == 2}">
                                <th>Actions</th>
                                </c:if>
                        </tr>
                    </thead>
                    <tbody>
                        <!-- Product 1 -->
                        <c:forEach var="i" items="${requestScope.list}">
                            <tr>
                                <td>
                                    <div class="product-thumb-placeholder">
                                        <img src="${pageContext.request.contextPath}${i.productImage}" 
                                             class="product-thumb" />
                                    </div>
                                </td>

                                <td class="product-id-cell">${i.productId}</td>

                                <td class="product-name-cell">
                                    <a href="${pageContext.request.contextPath}/Main?action=detail&type=product&productId=${i.productId}"
                                       style="color:#333">
                                        ${i.productName}
                                    </a>
                                </td>

                                <td class="product-category-cell">${i.type.categoryName}</td>
                                <td class="product-price-cell">
                                    <div class="product-original-price">${i.price}</div>
                                    ${i.getFinalPrice()}
                                    <span class="product-discount-badge">${i.discount}%</span>
                                </td>
                                <td class="product-category-unit">${i.unit}</td>
                                <td class="account">${i.account.account}</td>

                                <c:if test="${sessionScope.user.roleInSystem == 1 || sessionScope.user.roleInSystem == 2}">
                                    <td>
                                        <div class="product-action-btns">
                                            <c:url var="urlDelete" value="Main">
                                                <c:param name="action" value="delete"/>
                                                <c:param name="product" value="${i.productId}"/>
                                                <c:param name="type" value="product"/>
                                            </c:url>
                                            <a href="${urlDelete}"
                                               class="product-btn-delete"
                                               onclick="return confirm('Delete this account?')">
                                                🗑️ Delete
                                            </a>

                                            <c:url var="urlUpdate" value="Main">
                                                <c:param name="action" value="update"/>
                                                <c:param name="product" value="${i.productId}"/>
                                                <c:param name="type" value="product"/>
                                            </c:url>

                                            <a href="${urlUpdate}" class="product-btn-edit">✏️ Update</a>


                                        </div>
                                    </td>
                                </c:if>
                            </tr>                            
                        </c:forEach>



                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
