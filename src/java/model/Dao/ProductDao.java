/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.Accessible;
import model.Dto.AccountDto;
import model.Dto.CategoryDto;
import model.Dto.ProductDto;
import utils.ConnectDB;

/**
 *
 * @author Trần Minh Tuấn
 */
public class ProductDao implements Accessible<ProductDto> {

    @Override
    public int insertRec(ProductDto obj) {
        int result = 0;

        String sql = "INSERT INTO products "
                + "(productId, productName, productImage, brief, postedDate, typeId, account, unit, price, discount) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, obj.getProductId());
            ps.setString(2, obj.getProductName());
            ps.setString(3, obj.getProductImage());
            ps.setString(4, obj.getBrief());
            ps.setDate(5, obj.getPostedDate());

            
            ps.setInt(6, obj.getType().getTypeId());     
            ps.setString(7, obj.getAccount().getAccount()); 

            ps.setString(8, obj.getUnit());
            ps.setInt(9, obj.getPrice());
            ps.setInt(10, obj.getDiscount());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int updateRec(ProductDto pro) {
        int result = 0;

        String sql = "UPDATE products SET "
                + "productName = ?, "
                + "productImage = ?, "
                + "brief = ?, "
                + "typeId = ?, "
                + "account = ?, "
                + "unit = ?, "
                + "price = ?, "
                + "discount = ? "
                + "WHERE productId = ?";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, pro.getProductName());
            ps.setString(2, pro.getProductImage());
            ps.setString(3, pro.getBrief());
            ps.setInt(4, pro.getType().getTypeId());
            ps.setString(5, pro.getAccount().getAccount());
            ps.setString(6, pro.getUnit());
            ps.setInt(7, pro.getPrice());
            ps.setInt(8, pro.getDiscount());
            ps.setString(9, pro.getProductId());

            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public int deleteRec(ProductDto pro) {
        int result = 0;

        String sql = "DELETE FROM products WHERE productId = ?";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, pro.getProductId());
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public ProductDto getObjectById(String id) {
        ProductDto product = null;

        String sql = "SELECT p.productId, p.productName, p.productImage, p.brief, p.postedDate, "
                + "p.unit, p.price, p.discount, "
                + "c.typeId, c.categoryName, c.memo, "
                + "a.account, a.pass, a.lastName, a.firstName, "
                + "a.birthday, a.gender, a.phone, a.isUse, a.roleInSystem "
                + "FROM products p "
                + "JOIN categories c ON p.typeId = c.typeId "
                + "JOIN accounts a ON p.account = a.account "
                + "WHERE p.productId = ?";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                // Category
                CategoryDto category = new CategoryDto(
                        rs.getInt("typeId"),
                        rs.getString("categoryName"),
                        rs.getString("memo")
                );

                // Account 
                AccountDto acc = new AccountDto(
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getDate("birthday"),
                        rs.getBoolean("gender"),
                        rs.getString("phone"),
                        rs.getBoolean("isUse"),
                        rs.getInt("roleInSystem")
                );

                // Product
                product = new ProductDto(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        category,
                        acc,
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<ProductDto> listAll() {
        List<ProductDto> list = new ArrayList<>();

        String sql = "SELECT p.productId, p.productName, p.productImage, p.brief, p.postedDate, "
                + "p.unit, p.price, p.discount, "
                + "c.typeId, c.categoryName, c.memo, "
                + "a.account, a.pass, a.lastName, a.firstName, "
                + "a.birthday, a.gender, a.phone, a.isUse, a.roleInSystem "
                + "FROM products p "
                + "JOIN categories c ON p.typeId = c.typeId "
                + "JOIN accounts a ON p.account = a.account";

        try ( Connection cn = new ConnectDB().getConnection();  PreparedStatement ps = cn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                CategoryDto category = new CategoryDto(
                        rs.getInt("typeId"),
                        rs.getString("categoryName"),
                        rs.getString("memo")
                );

                AccountDto acc = new AccountDto(
                        rs.getString("account"),
                        rs.getString("pass"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getDate("birthday"),
                        rs.getBoolean("gender"),
                        rs.getString("phone"),
                        rs.getBoolean("isUse"),
                        rs.getInt("roleInSystem")
                );

                ProductDto product = new ProductDto(
                        rs.getString("productId"),
                        rs.getString("productName"),
                        rs.getString("productImage"),
                        rs.getString("brief"),
                        rs.getDate("postedDate"),
                        category,
                        acc,
                        rs.getString("unit"),
                        rs.getInt("price"),
                        rs.getInt("discount")
                );

                list.add(product);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
