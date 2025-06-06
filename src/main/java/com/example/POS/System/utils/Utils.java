package com.example.POS.System.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.logging.Logger;

import org.hibernate.Hibernate;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.POS.System.models.User;
import com.example.POS.System.repositories.GlobalRepository;

public class Utils {

    Logger logger = Logger.getLogger(this.getClass().getName());

    //Get optional field update
    //Don't return null value from Object
    public static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        return emptyNames.toArray(new String[0]);
    }

    public static <T> ResponseEntity<GlobalListResponse<T>> List(String message, T data, Integer total, Integer pageSize, Integer currentPage) {
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalListResponse<>(message, 200, data, total, pageSize, currentPage));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> Success(String message, T data) {
        return ResponseEntity.status(HttpStatus.OK).body(new GlobalResponse<>(message, 200, data));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> CreateSuccess(String message, T data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(new GlobalResponse<>(message, 201, data));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> BadRequest(String message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new GlobalResponse<>(message, 400, null));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> Duplicate(String message) {
        return ResponseEntity.status(409).body(new GlobalResponse<>(message, 409, null));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> ServerError(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new GlobalResponse<>(message, 500, null));
    }

    public static <T> ResponseEntity<GlobalResponse<T>> NotFound(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new GlobalResponse<>(message, 404, null));
    }

    //Get 5 Random Secret Code
    public static String generateSecretCode() {
        Random random = new Random();
        int number = 1000 + random.nextInt(9000);
        return String.valueOf(number);
    }

    //For Get Encrypted password
    public static String encryptPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    //For Compare Encrypted password
    public static Boolean ComparePassword(String passwordEncrypt, String password) {
        return BCrypt.checkpw(password, passwordEncrypt);
    }

    //For Log something
    public static <T> void logger(T data) {
        Utils util = new Utils();
        util.logger.info(String.valueOf(data));
    }

    //For Debugging Purpose
    public static <T> void initialize(T data) {
        Hibernate.initialize(data);
    }

    public static User getUserFromAuth(GlobalRepository db) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String username = userDetails.getUsername();
            Optional<User> userOpt = db.users.findOneByEmail(username);
            if (userOpt.isPresent()) {
                return userOpt.get();
            }
        }
        return null;
    }

}
