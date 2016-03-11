package com.epam.restaurant.tag;

import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.entity.Order;
import com.epam.restaurant.entity.OrderDish;
import com.epam.restaurant.util.ResourceBundleUtil;
import com.epam.restaurant.util.TotalUtil;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ResourceBundle;

/**
 * Tag prints all order items.
 */
public class ShowOrder extends TagSupport {
    public int doStartTag() throws JspException {

        //get resource bundle for current language
        String curLanguage = (String) pageContext.getSession().getAttribute(RequestParameterName.LANGUAGE);
        ResourceBundle rb = ResourceBundleUtil.getResourceBundle(curLanguage);

        //get current restaurant order from servlet request
        Order order = (Order) pageContext.getSession().getAttribute(RequestParameterName.ORDER); // get current cart from session
        JspWriter out = pageContext.getOut();

        BigDecimal item;
        BigDecimal total = BigDecimal.ZERO;

        try {
            if (order == null || order.getOrderDishes().size() == 0) {
                out.print("<h2>" + rb.getString("order.empty") + "</h2>");
            } else {
                out.print("<table class = \"table\" >");
                out.print("<caption><h3>" + rb.getString("order.title") + "</h3></caption>");
                out.print("<thead><tr>");
                out.print("<th scope = \"col\" width=\"50px\">" + rb.getString("order.dish.title") + "</th>");
                out.print("<th scope = \"col\">" + rb.getString("order.price.title") + "</th>");
                out.print(" <th></th><th></th><th></th>");
                out.print("<th scope = \"col\">" + rb.getString("order.total.title") + "</th>");
                out.print("</tr></thead>");
                out.print("<tbody>");
                // print every item

                for (OrderDish ci : order.getOrderDishes()) {
                    out.print("<tr>");
                    out.print("<td>");
                    out.print("<a href = \"/main?command=dish_command&id=" + ci.getId() + "\">" + ci.getDish().getName() + "</a>");
                    out.print("</td>");
                    out.print("<td>" + ci.getDish().getPrice() + "</td>");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"order\">");
                    out.print("<td>");
                    out.print("<label for = \"quantity\">" + rb.getString("order.quantity.title") + ": </label>");
                    out.print("<input type = \"text\" name = \"quantity\" value = \"" + ci.getQuantity() + "\" id = \"quantity\" size = \"1\" class = \"quantity\" maxlength = \"3\" />\n");

                    total = total.add(TotalUtil.calculateCost(ci.getQuantity(), ci.getDish().getPrice()));

                    out.print("</td>");
                    out.print("<td>");
                    out.print("<input type = \"hidden\" name = \"command\" value = \"update_order_command\" />");
                    out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                    out.print("<input type = \"submit\" name = \"submit\" value = \"" + rb.getString("order.update.button") + "\" class=\"btn btn-default\" />");
                    out.print("</td>");
                    out.print("</form>");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"order\">");
                    out.print("<td>");
                    out.print("<input type = \"hidden\" name = \"item_id\" value = \"" + ci.getId() + "\" />");
                    out.print("<input type = \"hidden\" name = \"command\" value = \"delete_item_command\" />");
                    out.print("<input type = \"submit\" name = \"submit\" value = \"" + rb.getString("order.delete.button") + "\" class=\"btn btn-default\" />");
                    out.print("</td>");
                    item = TotalUtil.multiply(ci.getQuantity(), ci.getDish().getPrice());
                    out.print("<td>" + item + "</td>");
                    out.print("</form>");
                    out.print("</tr>");
                }

                out.print("</tbody>");

                out.print("<tfoot><tr><th colspan = \"5\">" + rb.getString("order.subtotal.title") + ":</th><th>" + total + "</th></tr><tr>\n");
                if (order != null && order.getOrderDishes() != null && order.getOrderDishes().size() != 0) {
                    out.print("<th colspan = \"6\">\n");
                    out.print("<form method = \"POST\" action = \"/main\" class = \"order\">");
                    out.print("<input type = \"hidden\" name = \"total\" value = \"" + total + "\" />\n");
                    out.print("<input type = \"hidden\" name = \"command\" value = \"checkout_command\" />\n");
                    out.print("<input type = \"submit\" name = \"submit\" value = \"" + rb.getString("order.checkout.button") + "\" class=\"btn btn-default\" />\n");
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
