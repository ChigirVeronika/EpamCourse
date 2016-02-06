package main.com.epam.site.controller;

import main.com.epam.site.service.CommandException;
import main.com.epam.site.service.CommandHelper;
import main.com.epam.site.service.ICommand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Controller class is a link between application and a server.
 */
public class Controller extends HttpServlet {
    public static final long serialVersionUID = 1;
    private static int count = 0;

    public Controller(){
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName=req.getParameter(RequestParameterName.COMMAND_NAME);

        ICommand command = CommandHelper.getInstance().getCommand(commandName);
        String page;
        try{
            page = command.execute(req);
        }catch (CommandException e){
            page = JspPageName.ERROR_PAGE;
        }catch (Exception e){
            page = JspPageName.ERROR_PAGE;
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher(page);

        if(dispatcher!=null){
            dispatcher.forward(req,resp);
        }else{
            errorMessageDirectlyFormResponse(resp);
        }

       // resp.sendRedirect("http://www.bsuir.by/schedule/schedule.xhtml?id=351001");
    }
    private void errorMessageDirectlyFormResponse(HttpServletResponse r) throws IOException{
        r.setContentType("text/html");
        r.getWriter().println("ERROR!");
    }
}
