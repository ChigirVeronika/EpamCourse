package com.epam.restaurant.util;

import java.math.BigDecimal;

/**
 * Calculate total price for user order for ShowOrder tag class.
 */
public class TotalUtil {

    /**
     * Multiply one item price on quantity
     *
     * @param itemQuantity quantity of dish
     * @param itemPrice price of dish
     * @return total
     */
    public static BigDecimal calculateCost(int itemQuantity, BigDecimal itemPrice) {
        BigDecimal itemCost;
        BigDecimal totalCost = BigDecimal.ZERO;
        itemCost  = itemPrice.multiply(new BigDecimal(itemQuantity));
        totalCost = totalCost.add(itemCost);
        return totalCost;
    }

    /**
     * Multiply BigInteger on int value
     */
    public static BigDecimal multiply(int itemQuantity, BigDecimal itemPrice) {
        BigDecimal itemCost  = itemPrice.multiply(new BigDecimal(itemQuantity));
        return itemCost;
    }
}

