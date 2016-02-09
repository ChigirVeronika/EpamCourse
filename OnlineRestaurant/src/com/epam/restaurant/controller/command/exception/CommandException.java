package com.epam.restaurant.controller.command.exception;

/**
 * Created by Вероника on 22.01.2016.
 */
public class CommandException extends Exception{
    private static final long serialVersionUID = 1;

    public CommandException(String message){
        super(message);
    }

    public CommandException(String message, Exception e){
        super(message, e);
    }
}
