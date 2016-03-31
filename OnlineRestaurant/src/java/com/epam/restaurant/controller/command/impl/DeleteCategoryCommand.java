package com.epam.restaurant.controller.command.impl;

import com.epam.restaurant.controller.command.Command;
import com.epam.restaurant.controller.command.exception.CommandException;
import com.epam.restaurant.controller.name.JspPageName;
import com.epam.restaurant.controller.name.RequestParameterName;
import com.epam.restaurant.entity.Category;
import com.epam.restaurant.entity.Dish;
import com.epam.restaurant.service.CategoryService;
import com.epam.restaurant.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.ResourceBundle;

import static com.epam.restaurant.util.SessionUtil.*;
import static com.epam.restaurant.controller.name.RequestParameterName.*;

/**
 * Delete category from category list by user with role ADMIN.
 */
public class DeleteCategoryCommand implements Command {

    /**
     * Service provides work with database (category table)
     */
    private static final CategoryService categoryService = CategoryService.getInstance();

    /**
     * At first, check session expiration. If it's expired, return login page.
     * If not, get from request name and check if category is not empty.
     * If everything is fine, delete category and return menu page value.
     *
     * @return page to forward to
     * @throws CommandException if can't delete category
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        String result = JspPageName.CONCRETE_MENU_JSP;

        if (sessionExpired(request)) {
            result = JspPageName.LOGIN_JSP;
            return result;
        }
        try {
            String name = request.getParameter(NAME);
            Category category = categoryService.getByName(name);
            if (category != null) {
                //if there dishes of this category
                List<Dish> dishList = categoryService.getAllFromCategory(category.getId());
                if (dishList != null && dishList.size()>0) {

                    String path = RequestParameterName.I18N;
                    String curLan = (String) request.getSession().getAttribute(LANGUAGE);
                    if (curLan != null && !curLan.equals(EN))
                        path += UNDERLINE + curLan;
                    ResourceBundle rb = ResourceBundle.getBundle(path);

                    request.setAttribute(MESSAGE, rb.getString(CATEGORY_WITH_DISHES));
                    return result;
                } else {
                    categoryService.delete(category);
                }
            }
        } catch (ServiceException e) {
            throw new CommandException("Cant't execute EditCategoryCommand", e);
        }
        return result;
    }
}
