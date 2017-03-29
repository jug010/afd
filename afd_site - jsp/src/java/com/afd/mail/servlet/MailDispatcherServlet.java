/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.afd.mail.servlet;

import com.afd.mail.ejb.MailSenderBean;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ryanrush
 */
@WebServlet(name = "MailDispatcherServlet", urlPatterns = {"/MailDispatcherServlet"})
public class MailDispatcherServlet extends HttpServlet {
   
    @EJB
    private MailSenderBean mailSender;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        //Creating this to test if clicking the Submit button actually calls this servlet
        /* PrintWriter printer = response.getWriter();
        printer.println("test!"); */
        
        String name = request.getParameter("name");
        String fromEmail = request.getParameter("email");
        String phone = request.getParameter("phone");
        String message = request.getParameter("message");
        
        
        //Below are properties that should be stored in an external file or server properties
        //Hard-coding here because we don't need high-level security
        
        String toEmail = "ryan.rush@gmail.com"; //TASK: NEED TO CHANGE THIS BEFORE OFFICIALLY DEPLOYING THIS NEW SITE
        String username = "mail@advancedfloor.net";
        String password = "newpass1#";
        
        try (PrintWriter out = response.getWriter()) {
            
            //Call to mail sender bean
            
            mailSender.sendMail(name, fromEmail, phone, message, toEmail, username, password);
            
            // ----------------------------------------------------------------------

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Mail Status</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet MailDispatcherServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>Mail Sent. Please click <a href='http://www.advancedfloor.net'>here</a> to navigate back to the home page of Advanced Floor Design.</body>");
            out.println("</html>");
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
