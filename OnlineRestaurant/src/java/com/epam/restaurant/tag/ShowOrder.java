package com.epam.restaurant.tag;

import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.util.ResourceBundleUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 * Created by Вероника on 18.02.2016.
 */
public class ShowOrder  extends TagSupport {
    public int doStartTag() throws JspException {

        //get resource bundle for current language
        String curLanguage = (String) pageContext.getSession().getAttribute("language");
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(curLanguage);

        //get current shopping cart from servlet request
        Order order = (Order) pageContext.getSession().getAttribute("cart"); // get current cart from session
        JspWriter out = pageContext.getOut();

        BigDecimal item;
        BigDecimal total = BigDecimal.ZERO;

        try {
            if (order == null || order.getOrderDishes().size() == 0) {
                out.print("<h2>"+rb.getString("order.empty")+"</h2>");
            } else {
                out.print("<table class = \"table\">");
                out.print("<caption><h3>"+ rb.getString("order.title") +"</h3></caption>");
                out.print("<thead><tr>");
                out.print("<th scope = \"col\" width=\"50px\">"+ rb.getString("order.product.title") +"</th>");
                out.print("<th scope = \"col\">"+ rb.getString("order.price.title") +"</th>");
                out.print(" <th></th><th></th><th></th  >");
                out.print("<th scope = \"col\">"+ rb.getString("order.total.title") +"</th>");
                out.print("</tr></thead>");
                out.print("<tbody>");

                // print every item
                for (OrderDish od : order.getOrderDishes()) {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<a href = \"/main?command=dish_command&id=" + od.getDishId() + "\">" + od.getDish().getName() + "</a>");
                    out.print("</td>");
                    out.print("<td>" + od.getDish().getPrice() + "</td>");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                    out.print("<td>");
                    out.print("<label for = \"quantity\">"+ rb.getString("order.quantity.title") +": </label>");
                    out.print("<input type = \"text\" name = \"quantity\" value = \"" + od.getQuantity() + "\" id = \"quantity\" size = \"1\" class = \"quantity\" maxlength = \"3\" />\n");

                    //multiply quantity by price
                    BigDecimal itemPrice = od.getDish().getPrice();
                    int itemQuantity = od.getQuantity();
                    item = itemPrice.multiply(new BigDecimal(itemQuantity));
                    total = total.add(item);


                    out.print("</td>");
                    out.print("<td>");
                    out.print("<input type = \"hidden\" name = \"action\" value = \"updateCart\" />");
                    out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + od.getId() + "\" />");
                    out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("order.update.button") +"\" class=\"btn btn-default\" />");
                    out.print("</td>");
                    out.print("</form>");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                    out.print("<td>");
                    out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + od.getId() + "\" />");
                    out.print("<input type = \"hidden\" name = \"command\" value = \"delete_item\" />");
                    out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("order.delete.button") +"\" class=\"btn btn-default\" />");
                    out.print("</td>");
                    out.print("<td>" + total + "</td>");
                    out.print("</form>");
                    out.print("</tr>");
                }

                out.print("</tbody>");


                out.print("<tfoot><tr><th colspan = \"5\">"+ rb.getString("order.subtotal.title") +":</th><th>" + total + "</th></tr><tr>\n");
                if (order != null && order.getOrderDishes() != null && order.getOrderDishes().size() != 0) {
                    out.print("<th colspan = \"6\">\n");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"cart\">");
                    out.print("<input type = \"hidden\" name = \"command\" value = \"checkout\" />\n");
                    out.print("<input type = \"submit\" name = \"submit\" value = \""+ rb.getString("order.checkout.button") +"\" class=\"btn btn-default\" />\n");
                    out.print("</form>");
                    out.print("</th></tr></tfoot>");
                }

                out.print("</table>");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // tag with empty body
        return SKIP_BODY;
    }
}
