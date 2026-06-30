package com.masaalbeeahforcontracting.accountingwherehousesystem.Exception.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseError {


// this is the response error type
private int code ;
private String message ;
private long timeStamp;
}
