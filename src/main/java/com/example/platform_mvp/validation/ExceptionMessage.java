package com.example.platform_mvp.validation;

public class ExceptionMessage {
    public static String NOT_FOUND_SERVICE_MESSAGE = "NO SUCH SERVICE WAS FOUND WITH SEARCH PARAMETER: %s";
    public static String NOT_FOUND_USER_MESSAGE = "NO SUCH USER WAS FOUND WITH SEARCH PARAMETER: %s";
    public static String DOES_NOT_EXIST_TYPE_MESSAGE = "NO SUCH TYPES WAS FOUND WITH SEARCH PARAMETER: %s";
    public static String ALREADY_EXIST_MESSAGE = "USER WITH THIS PARAMETER : %s, WAS ALREADY CREATED";
    public static String INCORRECT_USERNAME_FORMAT_MESSAGE = """
                               THE USERNAME: %s ENTERED IS NOT SUITABLE, PLEASE REFILL USERNAME ONE TIME
                               USERNAME MUST CONTAIN MIN 5 SYMBOLS AND MIN 2 NUMBERS
                               EXAMPLE: manuel23.""";

    public static String INCORRECT_PASSWORD_FORMAT_MESSAGE = """
                               THE PASSWORD: %s ENTERED IS NOT SUITABLE, PLEASE REFILL PASSWORD ONE TIME
                               PASSWORD MUST CONTAIN MIN MIN 6 NUMBERS AND MIN 3 SYMBOLS 
                               EXAMPLE: 888333abs.""";



}
