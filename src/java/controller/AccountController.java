/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dao.AccountDao;
import model.Dto.AccountDto;

/**
 *
 * @author Trần Minh Tuấn
 */
@WebServlet(name = "AccountController", urlPatterns = {"/account"})
public class AccountController extends HttpServlet {

    private AccountDao accountDao = new AccountDao();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "list":
                accountList(request, response);
                break;
            case "delete":
                deleteAccount(request, response);
                break;
            case "add":
                AccountAddForm(request, response);
                break;
            case "update":
                AccountUpdateForm(request, response);
                break;
            case "use":
                accountUsing(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "error_page.jsp");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                addAccount(request, response);
                break;
            case "update":
                updateAccount(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/views/unsupported-feature.jsp");
        }
    }

    public void accountList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        List<AccountDto> list = accountDao.listAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("list_accounts.jsp").forward(request, response);
    }

    public void deleteAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");

        if (account != null) {
            AccountDto acc = accountDao.getObjectById(account);
            if (acc != null) {
                accountDao.deleteRec(acc);
            }
        }

        response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
    }

    public void updateAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        // param
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

        // date
        Date birthday = null;
        String birthdayStr = request.getParameter("birthday");
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            birthday = Date.valueOf(birthdayStr); // yyyy-MM-dd
        }

        // gender and using
        boolean gender = "1".equals(request.getParameter("gender")); // 1=Male
        boolean isUse = "1".equals(request.getParameter("isUse"));  // 1=Active

        // role in system
        int role = Integer.parseInt(request.getParameter("roleInSystem"));

        // sto
        AccountDto acc = new AccountDto(
                account,
                password,
                lastName,
                firstName,
                birthday,
                gender,
                phone,
                isUse,
                role
        );

        // update
        AccountDao dao = new AccountDao();
        int result = dao.updateRec(acc);

        if (result > 0) {
            // Redirect để tránh submit lại form khi refresh
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        }

    }

    public void AccountUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");
        AccountDto acc = accountDao.getObjectById(account);

        request.setAttribute("account", acc);
        request.getRequestDispatcher("update_account.jsp").forward(request, response);
    }

    public void AccountAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("new_account.jsp").forward(request, response);
    }

    public void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");

       
        Date birthday = null;
        String birthdayStr = request.getParameter("birthday");
        if (birthdayStr != null && !birthdayStr.isEmpty()) {
            birthday = Date.valueOf(birthdayStr); // yyyy-MM-dd
        }

       
        boolean gender = "1".equals(request.getParameter("gender")); 
        boolean isUse = "1".equals(request.getParameter("isUse"));  

        
        int role = Integer.parseInt(request.getParameter("roleInSystem"));

        
        AccountDto acc = new AccountDto(
                account,
                password,
                lastName,
                firstName,
                birthday,
                gender,
                phone,
                isUse,
                role
        );

        
        AccountDao dao = new AccountDao();
        int result = dao.insertRec(acc);

        if (result > 0) {
            // Redirect để tránh submit lại form khi refresh
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        }
    }

    public void accountUsing(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String account = request.getParameter("account");

        AccountDto acc = new AccountDto();
        acc = accountDao.getObjectById(account);

        if (acc.isIsUse()) {
            acc.setIsUse(false);
        } else {
            acc.setIsUse(true);
        }
        int result = accountDao.updateRec(acc);
        if (result > 0) {
            // Redirect để tránh submit lại form khi refresh
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=account");
        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
