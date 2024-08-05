package com.jvsc.utils;

import java.util.Date;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import at.favre.lib.crypto.bcrypt.BCrypt;


public class Utils {
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$";
    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[0-2])/\\d{4}$";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final DateTimeFormatter LOCALDATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String NAME_REGEX = "^[A-Za-zÀ-ÖØ-öø-ÿ\\s]+$";

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidDate(String date) {
        Pattern pattern = Pattern.compile(DATE_REGEX);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    public static Date convertToDate(String date) throws ParseException {
        DATE_FORMAT.setLenient(false);
        return DATE_FORMAT.parse(date);
    }

    public static LocalDate convertToLocalDate(String date) {
        try {
            return LocalDate.parse(date, LOCALDATE_FORMAT);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida: " + date);
        }
    }

    public static boolean isOfLegalAge(String birthDateString) {
        if (!isValidDate(birthDateString)) {
            throw new IllegalArgumentException("Data de nascimento inválida: " + birthDateString);
        }
        
        LocalDate birthDate = convertToLocalDate(birthDateString);
        LocalDate today = LocalDate.now();
        Period age = Period.between(birthDate, today);

        return age.getYears() >= 18;
    }

    public static String convertDateToString(Date date) {
        return DATE_FORMAT.format(date);
    }

    public static String convertLocalDateToString(LocalDate date) {
        return date.format(LOCALDATE_FORMAT);
    }

    public static boolean verifyPassword(String password, String encoded){
        BCrypt.Result res = BCrypt.verifyer().verify(password.getBytes(StandardCharsets.UTF_8), encoded.getBytes(StandardCharsets.UTF_8));
        
        return res.verified;
    }

}
