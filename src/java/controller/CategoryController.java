/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dao.CategoryDao;
import model.Dto.CategoryDto;

/**
 *
 * @author Trần Minh Tuấn
 */
@WebServlet(name = "CategoryController", urlPatterns = {"/category"})
public class CategoryController extends HttpServlet {

    private CategoryDao categoryDao = new CategoryDao();

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
                CategoryList(request, response);
                break;
            case "delete":
                deleteCategory(request, response);
                break;
            case "add":
                CategoryAddForm(request, response);
                break;
            case "update":
                CategoryUpdateForm(request, response);
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
                AddCategory(request, response);
                break;
            case "update":
                updateCategory(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "error_page.jsp");
        }
    }

    public void CategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> list = categoryDao.listAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("list_categories.jsp").forward(request, response);
    }

    public void CategoryAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("new_categories.jsp").forward(request, response);
    }

    public void AddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cateName = request.getParameter("categoryName");
        String memo = request.getParameter("memo");

        CategoryDto cate = new CategoryDto(cateName, memo);

        int result = categoryDao.insertRec(cate);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
        }
    }

    public void CategoryUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Id
        String id = request.getParameter("category");
        CategoryDto category = categoryDao.getById(Integer.parseInt(id));
        request.setAttribute("category", category);
        request.getRequestDispatcher("update_category.jsp").forward(request, response);
    }

    public void updateCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDto cate = new CategoryDto();

        String id = request.getParameter("category");
        cate = categoryDao.getById(Integer.parseInt(id));

        cate.setCategoryName(request.getParameter("categoryName").trim());
        cate.setMemo(request.getParameter("memo"));

        int result = categoryDao.updateRec(cate);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
        }

    }

    public void deleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("category");
        CategoryDto cate = new CategoryDto();
        cate = categoryDao.getById(Integer.parseInt(id));

        int result = categoryDao.deleteRec(cate);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=category");
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
