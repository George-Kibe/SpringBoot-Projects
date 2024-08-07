package com.kibe.accountsMs.constants;
public final class AccountsConstants {

    private AccountsConstants() {
        // restrict instantiation no one can create an object of this class
    }

    public static final String  SAVINGS = "Savings";
    public static final String  CURRENT = "Current";
    public static final String  LOAN = "Loans";
    public static final String  ADDRESS = "123 Main Street, New York";
    public static final String  STATUS_201 = "201";
    public static final String  MESSAGE_201 = "Account created successfully";
    public static final String  STATUS_200 = "200";
    public static final String  MESSAGE_200 = "Request processed successfully";
    public static final String STATUS_400 = "400";
    public static final String MESSAGE_400 = "Bad Request";
    public static final String  STATUS_404 = "404";
    public static final String  MESSAGE_404 = "Not Found";
    public static final String  STATUS_417 = "417";
    public static final String STATUS_422 = "422";
    public static final String MESSAGE_422 = "Unprocessable Entity";
    public static final String  MESSAGE_417_UPDATE= "Update operation failed. Please try again or contact Dev team";
    public static final String  MESSAGE_417_DELETE= "Delete operation failed. Please try again or contact Dev team";
    public static final String  STATUS_500 = "500";
    public static final String  MESSAGE_500 = "An Internal server error occurred. Please try again or contact Development team";

}
