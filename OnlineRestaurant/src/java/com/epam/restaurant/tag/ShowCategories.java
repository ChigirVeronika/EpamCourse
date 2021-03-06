package com.epam.restaurant.tag;

import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.List;
import org.apache.log4j.Logger;

/**
 * Tag print table with all categories to jsp.
 */
public class ShowCategories extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(ShowCategories.class);

    /**
     * Prints tag which shows list of categories
     *
     * @return SKIP_BODY constant because of empty body
     * @throws JspException by default
     */
    public int doStartTag() throws JspException {
        List<Category> categoryList = (List<Category>) pageContext.getRequest().getAttribute(RequestParameterName.CATEGORIES);

        User user = (User) pageContext.getSession().getAttribute(RequestParameterName.USER);
        JspWriter out = pageContext.getOut();
        if(categoryList!=null){
            try{
                for (Category c:categoryList) {
                    out.println("<div class = \"row\">");
                    out.println("<h3 class=\"trendhead-brand\">");
                    if (user != null && user.getRole() == User.Role.ADMIN) {
                        out.print("<button type=\"button\" class=\"btn btn-default btn-xs\" data-toggle=\"modal\" " +
                                "data-target=\"#edit\" data-name=\""+c.getName()+"\" data-description=\""+c.getDescription()+"\">" +
                                "<span class = \"glyphicon glyphicon-pencil\"/>" +
                                "</button>");
                    }
                    out.println("<a href = \"/main?command=category_command&id=" + c.getId() + "\">");
                    out.println(c.getName());
                    out.println("</a>");
                    out.print("</h3></div>");
                }

                // is user is ADMIN - print buttons to create and edit categories
                if (user != null && user.getRole() == User.Role.ADMIN) {
                    out.print("<div class=\"row\"><div class=\"col-sm-4\">");
                    out.print("<button type=\"button\" class=\"btn btn-default btn-xs\" data-toggle=\"modal\" data-target=\"#add\">" +
                            "<span class=\"glyphicon glyphicon-plus\"></span> Category" +
                            "</button>");
                    out.print("</div></div>");
                }
            } catch (IOException e) {
                LOGGER.error("ShowCategories (Custom Tag) Exception",e);
            }
        }
        // tag with empty body returns
        return SKIP_BODY;
    }
}
