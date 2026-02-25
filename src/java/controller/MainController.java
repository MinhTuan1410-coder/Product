/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dto.AccountDto;
import model.Dto.Role;

/**
 *
 * @author Trần Minh Tuấn
 */
@WebServlet(name = "MainController", urlPatterns = {"/Main"})
public class MainController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        AccountDto account = (AccountDto) request.getSession().getAttribute("user");
        
        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        String type = request.getParameter("type");
        String action = request.getParameter("action");
        
        if (type == null) {
            response.sendRedirect("error_page.jsp");
            return;
        }
        
        if (action == null) {
            response.sendRedirect("error_page.jsp");
            return;
        }
        
        switch (type) {
            case "product":
                handleProduct(request, response, type, action);
                break;
            case "category":
                handleCategory(request, response, type, action);
                break;
            case "account":
                handleAccount(request, response, type, action);
                break;
            case "home":
                response.sendRedirect("home_page.jsp");
                break;
            default:
                response.sendRedirect("error_page.jsp");
        }
        
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
        processRequest(request, response);
        
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
        processRequest(request, response);
    }
    
    public void handleAccount(HttpServletRequest request, HttpServletResponse response, String type, String action)
            throws ServletException, IOException {
        AccountDto account = (AccountDto) request.getSession().getAttribute("user");
        
        if (canManageAccounts(account)) {
            request.setAttribute("type", type);
            request.setAttribute("action", action);
            request.getRequestDispatcher("account").forward(request, response);
        } else {
            response.sendRedirect("access_denied.jsp");
            return;
        }
        
    }
    
    public void handleCategory(HttpServletRequest request, HttpServletResponse response, String type, String action)
            throws ServletException, IOException {
        AccountDto account = (AccountDto) request.getSession().getAttribute("user");
        // Staff can using a list Function
        String notManager = "list";
        
        if (!canManageCategories(account) && !"list".equals(action)) {
            response.sendRedirect("access_denied.jsp");
            return;
        }
        
        request.getRequestDispatcher("category").forward(request, response);
    }
    
    public void handleProduct(HttpServletRequest request, HttpServletResponse response, String type, String action)
            throws ServletException, IOException {
        AccountDto account = (AccountDto) request.getSession().getAttribute("user");
        // Staff can using a list Function
        String notManager = "list";
        
        if (!canManageProducts(account) && !notManager.equals(action)) {
            response.sendRedirect("access_denied.jsp");
            return;
        }
        
        request.getRequestDispatcher("product").forward(request, response);
    }

    //check user can manage accounts
    private boolean canManageAccounts(AccountDto a) {
        return Role.isAdmin(a.getRoleInSystem());
    }

    //check user can manage products
    private boolean canManageProducts(AccountDto a) {
        return Role.isAdmin(a.getRoleInSystem()) || Role.isManager(a.getRoleInSystem());
    }

    //check user can manage categoriess
    private boolean canManageCategories(AccountDto a) {
        return Role.isAdmin(a.getRoleInSystem()) || Role.isManager(a.getRoleInSystem());
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
