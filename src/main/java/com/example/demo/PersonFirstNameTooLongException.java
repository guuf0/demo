package com.example.demo;


public class PersonFirstNameTooLongException extends Exception {

    public PersonFirstNameTooLongException(String text) {
        super(text);
    }
}
