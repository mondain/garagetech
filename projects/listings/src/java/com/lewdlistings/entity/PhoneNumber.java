package com.lewdlistings.entity;

import com.lewdlistings.exception.PhoneNumberFormatException;
import org.apache.commons.lang.CharUtils;
import org.apache.commons.lang.StringUtils;

import static java.lang.Integer.parseInt;

public class PhoneNumber {

    private String areaCode;
    private String prefix;
    private String suffix;

    // Requires well formatted input and assumes a format of (###) ###-####
    public static PhoneNumber parse(String input) throws PhoneNumberFormatException {
        if (StringUtils.isBlank(input)) {
            return new PhoneNumber();
        }
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            if (CharUtils.isAsciiNumeric(c)) {
                sb.append(c);
            }
        }
        String numbers = sb.toString();
        if (numbers.length() == 10) {
            try {
                String areaCode = numbers.substring(0, 3);
                String prefix = numbers.substring(3, 6);
                String suffix = numbers.substring(6, 10);
                return new PhoneNumber(areaCode, prefix, suffix);
            } catch (NumberFormatException e) {
                throw new PhoneNumberFormatException(e);
            }
        } else {
            throw new PhoneNumberFormatException("You must provide a valid phone number string");
        }
    }

    public PhoneNumber() {}

    public PhoneNumber(String areaCode, String prefix, String suffix) {
        this.areaCode = areaCode;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    @Override
    public String toString() {

        return new StringBuilder()
                .append("(").append(areaCode).append(") ").append(prefix).append("-").append(suffix)
                .toString();
    }
}
