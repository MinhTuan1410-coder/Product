/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dto;

/**
 *
 * @author Trần Minh Tuấn
 */
public class CategoryDto {
    private int typeId;
    private String categoryName;
    private String memo;

    public CategoryDto() {
    }

    public CategoryDto(int typeId, String categoryName, String memo) {
        this.typeId = typeId;
        this.categoryName = categoryName;
        this.memo = memo;
    }

    public CategoryDto(String categoryName, String memo) {
        this.categoryName = categoryName;
        this.memo = memo;
    }
    

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Category{" + "typeId=" + typeId + ", categoryName=" + categoryName + ", memo=" + memo + '}';
    }
    
}
