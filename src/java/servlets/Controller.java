/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import entity.Customer;
import entity.Product;
import entity.Purchase;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import secure.SecureLogic;
import session.CustomerFacade;
import session.ProductFacade;
import session.PurchaseFacade;
import util.EncriptPass;
import util.PageReturner;

/**
 *
 * @author Melnikov
 */
 @WebServlet(name = "Purchase", urlPatterns = {
    "/welcome",
    "/newProduct",
    "/addProduct",
    "/newCustomer",
    "/newPurchase",
    "/addCustomer",
    "/listProduct",
    "/showPurchase",
    "/listCustomer",
    "/showBuyProduct",
    "/findTakeProduct",
    "/buyProduct"
  
    
    
})
    
    

public class Controller extends HttpServlet {
    @EJB ProductFacade productFacade;
    @EJB CustomerFacade customerFacade;
    @EJB PurchaseFacade purchaseFacade;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF8");
        Calendar c = new GregorianCalendar();

        HttpSession session = request.getSession(false);
        Customer regUser = null;
        if(session != null){
            try {
                regUser = (Customer) session.getAttribute("regUser");
            } catch (Exception e) {
                regUser = null;
            }
        }
            
        SecureLogic sl = new SecureLogic();
        String path = request.getServletPath();
        if(null != path)
            switch (path) {
                case "/showLogin":
                    request.getRequestDispatcher(PageReturner.getPage("welcome")).forward(request, response);
                    break;
                case "/newProduct":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    request.getRequestDispatcher(PageReturner.getPage("newProduct")).forward(request, response);
                    break;
                case "/addProduct":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    String nameProduct = request.getParameter("nameProduct");
                    String count = request.getParameter("count");
                    String price = request.getParameter("price");
                    Product product = new Product(nameProduct, new Integer(price), new Integer(count));
                    productFacade.create(product);
                    request.setAttribute("info", "Продукт добавлен");
                    request.getRequestDispatcher(PageReturner.getPage("newProduct")).forward(request, response);
                    break;
                case "/newCustomer":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    request.getRequestDispatcher(PageReturner.getPage("newCustomer")).forward(request, response);
                    break;
                case "/addCustomer":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String phone = request.getParameter("phone");
                    String city = request.getParameter("city");
                    String login = request.getParameter("login");
                    String password = request.getParameter("password");
                    String money = request.getParameter("money");
                    EncriptPass encriptPass = new EncriptPass();
                    String salts = encriptPass.createSalts();
                    String encPass = encriptPass.setEncriptPass(password, salts);
                    Customer customer = new Customer(name,surname,phone,city,login,encPass,salts);
                    customer.setMoney(new Integer(money));
                    customerFacade.create(customer);
                    request.setAttribute("info", "добавлен");
                    request.getRequestDispatcher(PageReturner.getPage("newCustomer")).forward(request, response);
                    break;
                case "/listCustomer":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    List<Customer> listCustomers = customerFacade.findAll();
                    request.setAttribute("listCustomers", listCustomers);
                    request.getRequestDispatcher(PageReturner.getPage("listCustomer")).forward(request, response);
                    break;
                case "/listProduct":
                    if(!"ADMIN".equals(sl.getRole(regUser))){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    List<Product> listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    request.getRequestDispatcher(PageReturner.getPage("listProduct")).forward(request, response);
                    break;
                
                
                case "/showPurchase":
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    listCustomers = customerFacade.findAll();
                    request.setAttribute("listCustomers", listCustomers);
                    request.getRequestDispatcher(PageReturner.getPage("showPurchase")).forward(request, response);
                    break;
                
                case "/newPurchase":
                    String selectedProduct = request.getParameter("selectedProduct");
                     
                    String quantity = request.getParameter("quantity");
                    product = productFacade.find(new Long(selectedProduct));
                    customer = (Customer) session.getAttribute("regUser");
                    if(customer == null){
                        request.getRequestDispatcher(PageReturner.getPage("showLogin"))
                            .forward(request, response);
                        break;
                    }
                    Purchase purchase;
                    Integer iquantity = new Integer(quantity);
                    if((product.getCount()-iquantity>=0) 
                            && (customer.getMoney()-product.getPrice()*iquantity>=0)){
                        product.setCount(product.getCount()-new Integer(quantity));
                        productFacade.edit(product);
                        customer.setMoney(customer.getMoney()-product.getPrice()*iquantity);
                        customerFacade.edit(customer);
                        purchase = new Purchase(product, customer, new Integer(quantity), c.getTime());
                        purchaseFacade.create(purchase);
                        
                        request.setAttribute("info", "товар успешно куплен");
                        request.setAttribute("info", "деньги на счету");
                    }else{ 
                        request.setAttribute("info", "Нет товара"); 
                        
                        request.setAttribute("info", "нет денег");
                    }
                    listProducts = productFacade.findAll();
                    request.setAttribute("listProducts", listProducts);
                    List<Purchase> listPurchase = purchaseFacade.findTakeProduct(product,customer);
                    request.setAttribute("listPurchase", listPurchase);
                    request.getRequestDispatcher(PageReturner.getPage("showPurchase")).forward(request, response);
                    break;
                    
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
    
 