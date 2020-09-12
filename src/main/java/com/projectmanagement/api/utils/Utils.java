package com.projectmanagement.api.utils;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Random;
import java.security.SecureRandom;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Utils {

    private final Random RANDOM = new SecureRandom();
    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";



    public String generateStringId(int length) {
        StringBuilder returnValue = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }

        return new String(returnValue);
    }

    public static boolean isEmailFormat(String valueToValidate) throws IOException{
        // Regex
        String regexExpression = "([A-Za-z0-9\\.\\_\\-]+[\\.\\_\\-]*[A-Za-z0-9\\.\\_\\-]*)+@([A-Za-z0-9\\.\\_\\-]+[\\.]*[A-Za-z0-9\\.\\_\\-]+)+\\.[A-Za-z]+";
        Pattern regexPattern = Pattern.compile(regexExpression);
        boolean valid = false;
        if (valueToValidate != null) {
            if (valueToValidate.indexOf("@") <= 0) {
                return false;
            }
            Matcher matcher = regexPattern.matcher(valueToValidate);
            valid = matcher.matches();
        } else { // The case of empty Regex expression must be accepted
            Matcher matcher = regexPattern.matcher("");
            valid = matcher.matches();
        }
        return valid;
    }

    public static String convertString(String text) {
        String formattedText = "";
        for(Character character : text.toCharArray()) {
            if(Character.isUpperCase(character)) {
                formattedText = formattedText+" "+character;
            } else {
                formattedText = formattedText+character;
            }
            formattedText = formattedText.substring(0, 1).toUpperCase()+formattedText.substring(1, formattedText.length());
        }
        return formattedText.trim();
    }

}
