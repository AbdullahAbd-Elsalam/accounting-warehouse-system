package com.masaalbeeahforcontracting.accountingwherehousesystem.controller;

import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.NotFoundVerification;
import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.TypeNotMatchException;
import com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.response.UserResponseError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserNotFoundException {


    // handle your exception of supplier
@ExceptionHandler
public ResponseEntity<UserResponseError> UserError(TypeNotMatchException exception){

  UserResponseError userResponseError=new UserResponseError();
  userResponseError.setCode(HttpStatus.NOT_FOUND.value());
  userResponseError.setMessage(exception.getMessage());
  userResponseError.setTimeStamp(System.currentTimeMillis());

  return new ResponseEntity<>(userResponseError,HttpStatus.NOT_FOUND);
}


  @ExceptionHandler
  public ResponseEntity<UserResponseError> UserError(NotFoundVerification exception){

    UserResponseError userResponseError=new UserResponseError();
    userResponseError.setCode(HttpStatus.NOT_FOUND.value());
    userResponseError.setMessage(exception.getMessage());
    userResponseError.setTimeStamp(System.currentTimeMillis());

    return new ResponseEntity<>(userResponseError,HttpStatus.NOT_FOUND);
  }

}
