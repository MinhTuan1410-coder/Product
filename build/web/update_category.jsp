<%-- 
    Document   : update_category
    Created on : Feb 8, 2026, 8:47:56 PM
    Author     : Trần Minh Tuấn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category</title>
        <link rel="stylesheet" href="styleCss/category.css"/>
    </head>
    <body>
        <%@include file="nav.jspf"%>
        <!-- Update/Add Form -->
        <div class="form-section">
            <div class="section-title">
                <span class="section-icon">✏️</span>
                <span id="formModeTitle">Update Category</span>
            </div>

            <form id="categoryForm" action="Main" method="post">



                <div class="form-group">
                    <label class="form-label">Category ID</label>
                    <input 
                        type="text" 
                        class="form-input" 
                        id="displayCategoryId"
                        value="${category.typeId}"
                        disabled
                        >
                    <span class="helper-text">Auto-generated on creation</span>
                </div>


                <div class="form-group">
                    <label class="form-label">
                        Category Name <span class="required">*</span>
                    </label>

                    <input 
                        type="text" 
                        class="form-input" 
                        id="categoryName"
                        name="categoryName" 
                        placeholder="Enter category name (e.g., Dụng cụ nhà bếp)"
                        maxlength="88"
                        value="${category.categoryName}"
                        required
                        >
                    <span class="helper-text">Maximum 88 characters</span>
                </div>

                <div class="form-group">
                    <label class="form-label">Memo/Notes</label>
                    <textarea 
                        class="form-textarea" 
                        id="categoryMemo"
                        name="memo" 
                        placeholder="Enter additional notes or description about this category..."
                        >${category.memo}</textarea>
                    <span class="helper-text">Optional: Add any additional information</span>
                </div>

                <div class="button-group">
                    <button type="submit" class="btn btn-primary" id="submitBtn">
                        <span id="submitBtnText">Update Category</span>
                    </button>

                </div>
                <input type="hidden" name="action" value="update">
                <input type="hidden" name="type" value="category">
                <input type="hidden" name="category" value="${requestScope.category.typeId}">
            </form>
        </div>
    </body>
</html>
