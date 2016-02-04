package com.epam.restaurant.controller;

import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 24.01.2016.
 */
public class MainController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String currentLanguage = (String) request.getSession().getAttribute("language");
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(currentLanguage);

        request.setAttribute("message",rb.getString("error.problems"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("hello.jsp");
        requestDispatcher.forward(request,response);
    }
}
