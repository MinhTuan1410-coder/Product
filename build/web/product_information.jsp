<%-- 
    Document   : product_information
    Created on : Feb 17, 2026, 3:57:14 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styleCss/information.css"/>
        <title>Product</title>
    </head>
    <body>
        <%@include file="nav.jspf"%>
        <div class="pd-container">

            <!-- Main Product Card -->
            <div class="pd-main-card">
                <!-- Image Section -->
                <div class="pd-image-section">
                    <div class="pd-main-image" id="mainImage">
                        <img src="${pageContext.request.contextPath}${requestScope.product.productImage}" />
                    </div>
                </div>

                <!-- Info Section -->
                <div class="pd-info-section">
                    <!-- Category -->
                    <div class="pd-category-tag">📂 ${requestScope.product.type.categoryName}</div>

                    <!-- Product Name -->
                    <div>
                        <h1 class="pd-product-name">${requestScope.product.productName}</h1>
                        <div class="pd-product-id">Product ID: ${requestScope.product.productId}</div>
                    </div>

                    <!-- Price -->
                    <div class="pd-price-section">
                        <div class="pd-price-row">
                            <span class="pd-price-current">${requestScope.product.getFinalPrice()}đ</span>
                            <span class="pd-price-original">${requestScope.product.price}đ</span>
                            <span class="pd-discount-badge">${requestScope.product.discount}%</span>
                        </div>
                    </div>

                    <!-- Meta Grid -->
                    <div class="pd-meta-grid">
                        <div class="pd-meta-item">
                            <div class="pd-meta-label">Unit</div>
                            <div class="pd-meta-value">📦 ${requestScope.product.unit}</div>
                        </div>
                        <div class="pd-meta-item">
                            <div class="pd-meta-label">Owner</div>
                            <div class="pd-meta-value">👤 ${sessionScope.user.account}</div>
                        </div>
                        <div class="pd-meta-item">
                            <div class="pd-meta-label">Posted Date</div>
                            <div class="pd-meta-value">📅 ${requestScope.product.postedDate}</div>
                        </div>
                    </div>

                </div>
            </div>

            <!-- Admin Actions -->
            <div class="pd-admin-actions">
                <span class="pd-admin-label">⚙️ Admin Actions</span>
                <div class="pd-admin-btns">
                    <a href="Main?action=list&type=product" class="pd-admin-btn pd-admin-btn-back">
                        ← Back to List
                    </a>
                </div>
            </div>

            <!-- Description Card -->
            <div class="pd-desc-card">
                <div class="pd-section-title">
                    📝 Product Description
                </div>
                <p class="pd-desc-text">${requestScope.product.brief}</p>
            </div>
        </div>
    </body>
</html>
