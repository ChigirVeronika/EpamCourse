package com.veronika.cool.util;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Понятие факториала.
 * Это функция, вычисляющая
 * произведение последовательных натуральных чисел от 1 до N
 * включительно: N! = 1 * 2 * 3 *… * N. Факториал —
 * быстрорастущая функция, уже для небольших
 * значений N значение N! имеет много значащих цифр.
 */
public class Factorial {
    static HashMap<Integer,BigInteger> cache = new HashMap<Integer,BigInteger>();

    public Factorial(){}

    public static int factorialNotRecursion(int n){
        int result = 1;
        for (int i = 1; i <=n ; i++) {
            result*=i;
        }
        return result;
    }

    public static int factorialRecursion(int n){
        if(n==0){
            return 1;
        }
        return n*factorialRecursion(n-1);
    }


    public static BigInteger factorial(int n){
        BigInteger result;
        if(n==0){
            return BigInteger.ONE;
        }
        if(null!=(result=cache.get(n))){
            return result;
        }
        result=BigInteger.valueOf(n).multiply(factorial(n-1));
        cache.put(n,result);
        return result;
    }
}
