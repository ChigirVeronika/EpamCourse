package main.com.epam.site.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

/**
 * Created by Вероника on 20.01.2016.
 */
public class NumberButtonController extends HttpServlet{
    private Integer number=0;
    public NumberButtonController(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(number);
        req.setAttribute("random_number",number);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(number);
        req.setAttribute("random_number",number);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }

    private void processRequest(Integer n){
        Random randomGenerator = new Random();
        n=randomGenerator.nextInt(100);
    }
}
