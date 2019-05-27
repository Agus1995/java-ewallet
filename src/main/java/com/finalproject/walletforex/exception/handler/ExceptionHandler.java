package com.finalproject.walletforex.exception.handler;

import com.finalproject.walletforex.dto.CommonResponse;
import com.finalproject.walletforex.exception.AccountNotFoundException;
import com.finalproject.walletforex.exception.InvalidUsernameOrPasswordException;
import com.finalproject.walletforex.exception.UserAlreadyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;


@ControllerAdvice
public class ExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UserAlreadyException.class)
    public ResponseEntity<CommonResponse> resp(UserAlreadyException e){
//        LOGGER.error(e.getMesage());
//        LOGGER.warn(e.getMesage());
//        LOGGER.info(e.getMesage());
//        LOGGER.debug(e.getMesage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidUsernameOrPasswordException.class)
    public ResponseEntity<CommonResponse> resp(InvalidUsernameOrPasswordException e){
//        LOGGER.error(e.getMesage());
//        LOGGER.warn(e.getMesage());
//        LOGGER.info(e.getMesage());
//        LOGGER.debug(e.getMesage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = AccountNotFoundException.class)
    public ResponseEntity<CommonResponse> resp(AccountNotFoundException e){
//        LOGGER.error(e.getMesage());
//        LOGGER.warn(e.getMesage());
//        LOGGER.info(e.getMesage());
//        LOGGER.debug(e.getMesage());
        return new ResponseEntity<CommonResponse>(new CommonResponse(e.getCode(), e.getMessage()), HttpStatus.OK);
    }
}
