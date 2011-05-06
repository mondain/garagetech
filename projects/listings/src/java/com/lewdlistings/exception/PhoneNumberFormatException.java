package com.lewdlistings.exception;

public class PhoneNumberFormatException extends IllegalArgumentException {

    public PhoneNumberFormatException() {
    }

    public PhoneNumberFormatException(String s) {
        super(s);
    }

    public PhoneNumberFormatException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public PhoneNumberFormatException(Throwable throwable) {
        super(throwable);
    }
}
