<%-- 
    Document   : update_product
    Created on : Feb 11, 2026, 3:32:28 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="styleCss/addProduct.css"/>
        <title>Product</title>
    </head>
    <body>
        <%@include file="nav.jspf"%>
        <!-- Navigation Bar -->


        <!-- Product Form -->
        <div class="product-container">
            <!-- Page Header -->
            <div class="product-page-header">
                <h1 class="product-page-title">Update Product</h1>
                <p class="product-page-subtitle">Manage your product information</p>
            </div>

            <form action="Main" method="post">
                <!-- Basic Information Section -->
                <div class="product-form-card">
                    <div class="product-section-header">
                        <span class="product-section-icon">📦</span>
                        <span>Basic Information</span>
                    </div>

                    <div class="product-form-grid">
                        <!-- Product ID -->
                        <div class="product-form-field">
                            <label class="product-field-label">
                                Product ID <span class="product-field-required">*</span>
                            </label>
                            <input 
                                type="text" 
                                class="product-input" 
                                placeholder="e.g., SHG2303MRA"
                                name="productId"
                                maxlength="10"
                                value="${product.productId}"
                                readonly
                                />
                            <span class="product-helper-text">Maximum 10 characters</span>
                        </div>

                        <!-- Category -->
                        <div class="product-form-field">
                            <label class="product-field-label">
                                Category <span class="product-field-required">*</span>
                            </label>
                            <select class="product-select" name="categoryId">
                                <c:forEach items="${categories}" var="i">
                                    <option value="${i.typeId}"
                                            ${i.typeId == product.type.typeId ? "selected" : ""}>
                                        ${i.categoryName}
                                    </option>
                                </c:forEach>
                            </select>

                        </div>

                        <!-- Product Name -->
                        <div class="product-form-field span-2">
                            <label class="product-field-label">
                                Product Name <span class="product-field-required">*</span>
                            </label>
                            <input 
                                type="text" 
                                class="product-input" 
                                name="productName"
                                value="${product.productName}"
                                placeholder="Enter product name"
                                />
                            <span class="product-helper-text">Maximum 500 characters</span>
                        </div>

                        <!-- Unit -->
                        <div class="product-form-field">
                            <label class="product-field-label">Unit</label>
                            <input 
                                type="text" 
                                class="product-input" 
                                name="productUnit"
                                placeholder="e.g., pcs, box, set"
                                value="${product.unit}"
                                />
                            <span class="product-helper-text">Default: Cái</span>
                        </div>

                        <!-- Account (Owner) -->
                        <div class="product-form-field">
                            <label class="product-field-label">
                                Account (Owner) <span class="product-field-required">*</span>
                            </label>
                            <input 
                                type="text" 
                                class="product-input" 
                                name="accountName"
                                value="${sessionScope.user.account}"
                                readonly
                                />
                        </div>
                    </div>
                </div>

                <!-- Price & Discount Section -->
                <div class="product-form-card">
                    <div class="product-section-header">
                        <span class="product-section-icon">💰</span>
                        <span>Pricing Information</span>
                    </div>

                    <div class="product-form-grid">
                        <!-- Price -->
                        <div class="product-form-field">
                            <label class="product-field-label">Price (VND)</label>
                            <input 
                                type="number" 
                                class="product-input product-number-input" 
                                name="productPrice"
                                placeholder="0"
                                value="${product.price}"
                                />
                            <span class="product-helper-text">Enter price in VND</span>
                        </div>

                        <!-- Discount -->
                        <div class="product-form-field">
                            <label class="product-field-label">Discount (%)</label>
                            <input 
                                type="number" 
                                class="product-input product-number-input" 
                                placeholder="0"
                                name="productDiscount"
                                min="0"
                                max="100"
                                value="${product.discount}"
                                />
                            <span class="product-helper-text">0-100%</span>
                        </div>
                    </div>

                    <div class="product-info-box">
                        <div class="product-info-box-title">💡 Pricing Tips</div>
                        <div class="product-info-box-text">
                            Set competitive prices and attractive discounts to boost sales. 
                            Final price will be calculated as: Price - (Price × Discount%)
                        </div>
                    </div>
                </div>

                <!-- Product Details Section -->
                <div class="product-form-card">
                    <div class="product-section-header">
                        <span class="product-section-icon">📝</span>
                        <span>Product Details</span>
                    </div>

                    <div class="product-form-grid single-col">
                        <!-- Product Image -->
                        <div class="product-form-field">
                            <label class="product-field-label">Product Image</label>

                            <span class="product-helper-text"> enter image path manually:</span>
                            <input 
                                type="text" 
                                class="product-input" 
                                name="productImage"
                                placeholder="/images/sanPham/filename.jpg"
                                style="margin-top: 10px;"
                                value="${product.productImage}"
                                />

                            <!-- Image Preview -->
                            <div class="product-image-preview" id="imagePreview" style="display: none;">
                                <div class="product-preview-placeholder">📦</div>
                            </div>
                        </div>

                        <!-- Brief Description -->
                        <div class="product-form-field">
                            <label class="product-field-label">Brief Description</label>
                            <textarea 
                                class="product-textarea" 
                                name="productBrief"
                                placeholder="Enter product description and features..."

                                >${product.brief}</textarea>
                            <span class="product-helper-text">Maximum 2000 characters</span>
                        </div>
                    </div>
                </div>

                <!-- Action Buttons -->
                <div class="product-btn-group">
                    <button type="submit" class="product-btn product-btn-submit">
                        ➕ Update Product
                    </button>
                    <button type="reset" class="product-btn product-btn-reset">
                        🔄 Reset Form
                    </button>
                </div>
                <input type="hidden" name="action" value="update"/>
                <input type="hidden" name="type" value="product"/>

            </form>
        </div>
    </body>
</html>
