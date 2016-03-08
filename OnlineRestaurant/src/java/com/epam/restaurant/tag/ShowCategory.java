package com.epam.restaurant.tag;

import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;

/**
 * Tag prints all dishes from category.
 */
public class ShowCategory  extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(ShowCategory.class);

    private static String IMAGE_URL = "images/";

    public int doStartTag() throws JspException{
        String currentLanguage = (String) pageContext.getSession().getAttribute("language");
        ResourceBundle resourceBundle = ResourceBundleUtil.getResourceBundle(currentLanguage);

        List<Dish> dishList = (List<Dish>) pageContext.getRequest().getAttribute("dishes");
        JspWriter out = pageContext.getOut();


        if(dishList!=null){
            try{
                for(Dish d:dishList){
                    out.println("<tr><td>");
                    out.println("<div class=\"row\">");
                    out.println("<div class=\"col-xs-7\">");
                    out.println("<img src=\""+IMAGE_URL+d.getImage()+"\"alt=\""+d.getName()+"\"height=\"100\" data-tooltip=\"DESCRIPTION\"/>");
                    out.println("</div>");
                    out.println("</td>");

                    out.println("<td>");
                    out.println("<div class = \"col-xs-5\">");
                    out.println("<p>"+d.getName() + " </p>");
                    out.println("<p>"+ resourceBundle.getString("dish.price") +": " + d.getPrice() + " </p>");
                    out.println("</td>");

                    out.println("<td>");
                    out.println("<p><a href = \"/main?command=dish_command&id="+d.getId()+"\" class=\"btn btn-default\">"+ resourceBundle.getString("dish.more.button") +"</a></p>\n");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</td></tr>");
                    out.println("<br><br>");

                }
            }catch (Exception e){
                LOGGER.error("ShowCategory (Custom Tag) Exception",e);
            }
        }

        return SKIP_BODY;
    }
}
