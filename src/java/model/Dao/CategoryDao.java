/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accessible;
import model.Dto.CategoryDto;
import utils.ConnectDB;

/**
 *
 * @author Trần Minh Tuấn
 */
public class CategoryDao implements Accessible<CategoryDto> {

    @Override
    public int insertRec(CategoryDto category) {
        int result = 0;
        String sql = "INSERT INTO categories(categoryName,memo)"
                + " VALUES(?,?)";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, category.getCategoryName());
            ps.setString(2, category.getMemo());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(CategoryDto cate) {
        int result = 0;
        String sql = "update categories set categoryName = ?,"
                + "memo = ? where typeId = ?";
        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setString(1, cate.getCategoryName());
            ps.setString(2, cate.getMemo());
            ps.setInt(3, cate.getTypeId());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(CategoryDto cate) {
        int result = 0;
        String sql = "delete from categories where typeId = ?";
        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql);) {
            ps.setInt(1, cate.getTypeId());

            result = ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public CategoryDto getObjectById(String categoryName) {
        CategoryDto category = null;

        String sql = "SELECT typeId, categoryName, memo FROM categories WHERE categoryName = ?";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, categoryName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = new CategoryDto(
                        rs.getInt("typeId"),
                        rs.getString("categoryName"),
                        rs.getString("memo")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public List<CategoryDto> listAll() {
        List<CategoryDto> listCategory = new ArrayList<>();

        String sql = "SELECT typeId, categoryName, memo FROM categories";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                CategoryDto category = new CategoryDto(
                        rs.getInt("typeId"),
                        rs.getString("categoryName"),
                        rs.getString("memo")
                );
                listCategory.add(category);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return listCategory;
    }


    public CategoryDto getById(int id) {
        CategoryDto category = null;

        String sql = "SELECT typeId, categoryName, memo FROM categories WHERE typeId=?";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                category = new CategoryDto(
                        rs.getInt("typeId"),
                        rs.getString("categoryName"),
                        rs.getString("memo")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return category;
    }
}
