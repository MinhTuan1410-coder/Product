/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Accessible;
import model.Dto.AccountDto;
import utils.ConnectDB;

/**
 *
 * @author Trần Minh Tuấn
 */
public class AccountDao implements Accessible<AccountDto> {

    @Override
    public int insertRec(AccountDto acc) {
        int result = 0;

        String sql = "INSERT INTO accounts "
                + "(account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setString(1, acc.getAccount());
            ptm.setString(2, acc.getPass());
            ptm.setString(3, acc.getLastName());
            ptm.setString(4, acc.getFirstName());
            ptm.setDate(5, acc.getBirthday()); 
            ptm.setBoolean(6, acc.isGender());
            ptm.setString(7, acc.getPhone());
            ptm.setBoolean(8, acc.isIsUse());
            ptm.setInt(9, acc.getRoleInSystem());

            result = ptm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int updateRec(AccountDto acc) {
        int result = 0;
        String sql = "UPDATE accounts "
                + "SET pass = ?, "
                + "lastName = ?, "
                + "firstName = ?, "
                + "birthday = ?, "
                + "gender = ?, "
                + "phone = ?, "
                + "isUse = ?, "
                + "roleInSystem = ? "
                + "WHERE account = ?";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setString(1, acc.getPass());
            ptm.setString(2, acc.getLastName());
            ptm.setString(3, acc.getFirstName());
            ptm.setDate(4, acc.getBirthday()); 
            ptm.setBoolean(5, acc.isGender());
            ptm.setString(6, acc.getPhone());
            ptm.setBoolean(7, acc.isIsUse());
            ptm.setInt(8, acc.getRoleInSystem());
            ptm.setString(9, acc.getAccount());

            result = ptm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int deleteRec(AccountDto acc) {
        String sql = "DELETE FROM accounts WHERE account = ?";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setString(1, acc.getAccount());
            return ptm.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public AccountDto getObjectById(String accountName) {
        AccountDto acc = null;
        String sql = "SELECT * FROM accounts WHERE account = ?";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setString(1, accountName);

            try ( ResultSet rs = ptm.executeQuery()) {
                if (rs.next()) {
                    acc = new AccountDto(
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
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return acc;
    }

    @Override
    public List<AccountDto> listAll() {
        List<AccountDto> listAcc = new ArrayList<>();
        String sql = "SELECT * FROM accounts";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql);  ResultSet rs = ptm.executeQuery()) {

            while (rs.next()) {
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
                listAcc.add(acc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return listAcc;
    }

    public AccountDto checkLogin(String accountName, String password) {
        AccountDto account = null;

        String sql = "SELECT account, pass, lastName, firstName, birthday, gender, phone, isUse, roleInSystem "
                + "FROM accounts "
                + "WHERE account = ? AND pass = ? AND isUse = 1";

        try ( Connection conn = new ConnectDB().getConnection();  PreparedStatement ptm = conn.prepareStatement(sql)) {

            ptm.setString(1, accountName);
            ptm.setString(2, password);

            try ( ResultSet rs = ptm.executeQuery()) {
                if (rs.next()) {
                    account = new AccountDto(
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
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(AccountDao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return account;
    }

}
