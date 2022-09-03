package ru.netology;

public class NotSetupException extends RuntimeException{

    public NotSetupException(String msg) {
        super(msg);
    }
}