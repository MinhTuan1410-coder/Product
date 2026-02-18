/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Dao.CategoryDao;
import model.Dao.ProductDao;
import model.Dto.AccountDto;
import model.Dto.CategoryDto;
import model.Dto.ProductDto;

/**
 *
 * @author Trần Minh Tuấn
 */
@WebServlet(name = "ProductController", urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    private ProductDao productDao = new ProductDao();

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
                ProductList(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "add":
                ProductAddForm(request, response);
                break;
            case "update":
                ProductUpdateForm(request, response);
                break;
            case "detail":
                detailProduct(request, response);
                break;
            case "filter":
                filterProduct(request, response);

                break;

            default:
                response.sendRedirect(request.getContextPath() + "/error_page.jsp");
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
                AddProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;

            default:
                response.sendRedirect(request.getContextPath() + "/error_page.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public void ProductList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ProductDto> list = productDao.listAll();
        List<CategoryDto> categories = new CategoryDao().listAll();
        request.setAttribute("categories", categories);
        request.setAttribute("list", list);
        request.getRequestDispatcher("list_products.jsp").forward(request, response);
    }

    public void ProductAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryDto> categories = new CategoryDao().listAll();
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("add_new_product.jsp").forward(request, response);
    }

    public void AddProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String unit = request.getParameter("productUnit");
        String brief = request.getParameter("productBrief");

        int price = Integer.parseInt(request.getParameter("productPrice"));
        int discount = Integer.parseInt(request.getParameter("productDiscount"));

        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("user");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CategoryDto category = new CategoryDao().getById(categoryId);

        //images
        String manualPath = request.getParameter("productImage");

        String imagePath;

        imagePath = manualPath;

        // Dto
        ProductDto product = new ProductDto(
                productId,
                productName,
                imagePath,
                brief,
                Date.valueOf(LocalDate.now()),
                category,
                account,
                unit,
                price,
                discount
        );

        // chen
        int result = new ProductDao().insertRec(product);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        }
    }

    public void ProductUpdateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDto product = productDao.getObjectById(request.getParameter("product").trim());
        List<CategoryDto> categories = new CategoryDao().listAll();

        request.setAttribute("product", product);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("update_product.jsp").forward(request, response);
    }

    public void updateProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");

        String productId = request.getParameter("productId");
        String productName = request.getParameter("productName");
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String unit = request.getParameter("productUnit");
        String brief = request.getParameter("productBrief");

        int price = Integer.parseInt(request.getParameter("productPrice"));
        int discount = Integer.parseInt(request.getParameter("productDiscount"));

        HttpSession session = request.getSession();
        AccountDto account = (AccountDto) session.getAttribute("user");

        if (account == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        CategoryDto category = new CategoryDao().getById(categoryId);

        String manualPath = request.getParameter("productImage");

        String imagePath;

        imagePath = manualPath;

        ProductDto product = new ProductDto(
                productId,
                productName,
                imagePath,
                brief,
                Date.valueOf(LocalDate.now()),
                category,
                account,
                unit,
                price,
                discount
        );

        int result = new ProductDao().updateRec(product);

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        }

    }

    public void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("product");

        int result = productDao.deleteRec(productDao.getObjectById(id));

        if (result > 0) {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        } else {
            response.sendRedirect(request.getContextPath() + "/Main?action=list&type=product");
        }

    }

    public void detailProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String productId = request.getParameter("productId");
        ProductDto product = productDao.getObjectById(productId);

        request.setAttribute("product", product);
        request.getRequestDispatcher("product_information.jsp").forward(request, response);
    }

    public void filterProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int categoryId = Integer.parseInt(request.getParameter("category"));
        List<ProductDto> products = new ArrayList<>();
        List<ProductDto> productsResult = new ArrayList<>();

        products = productDao.listAll();
        for (ProductDto p : products) {
            if (p.getType().getTypeId() == categoryId) {
                productsResult.add(p);
            }
        }

        List<CategoryDto> categories = new CategoryDao().listAll();
        request.setAttribute("categories", categories);
        request.setAttribute("list", productsResult);
        request.getRequestDispatcher("list_products.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
