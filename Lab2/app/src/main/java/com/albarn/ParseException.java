package com.albarn;

public class ParseException extends Exception{
    private int index;
    private String expected;

    public ParseException(int index, String expected){
        this.index =index;
        this.expected=expected;
    }

    @Override
    public String getMessage() {
        return "failed to parse, expected \'"+expected+"\' at "+index;
    }
}
