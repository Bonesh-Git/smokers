package com.example.wallet.controller;

import com.example.wallet.carrier.ExceptionCarrier;
import com.example.wallet.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerAdvice {
    @ExceptionHandler(InvalidToken.class)
    public ResponseEntity<ExceptionCarrier> handleInvalidToken(InvalidToken e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionCarrier(10, e.getMessage()));
    }

    @ExceptionHandler(PermissionDeniedException.class)
    public ResponseEntity<ExceptionCarrier> handleNotAdmin(PermissionDeniedException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionCarrier(20, e.getMessage()));
    }

    @ExceptionHandler(NotSufficientFund.class)
    public ResponseEntity<ExceptionCarrier> handleNotSufficientFund(NotSufficientFund e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionCarrier(30, e.getMessage()));
    }
    @ExceptionHandler(OptimisticLockException.class)
    public ResponseEntity<ExceptionCarrier> handleOptimisticLockException(OptimisticLockException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionCarrier(40, e.getMessage()));
    }

    @ExceptionHandler(PessimisticLockException.class)
    public ResponseEntity<ExceptionCarrier> handlePessimisticLockException(PessimisticLockException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionCarrier(50, e.getMessage()));
    }

    @ExceptionHandler(SourceAndDestinationAreSameException.class)
    public ResponseEntity<ExceptionCarrier> handleSameSourceAndDestination(SourceAndDestinationAreSameException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionCarrier(60, e.getMessage()));
    }

    @ExceptionHandler(TransactionNotAllowedException.class)
    public ResponseEntity<ExceptionCarrier> handleTransactionNotAllowed(TransactionNotAllowedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body(new ExceptionCarrier(70, e.getMessage()));
    }

    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ExceptionCarrier> handleUserAlreadyExist(UserAlreadyExist e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ExceptionCarrier(80, e.getMessage()));
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionCarrier> handleUserNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionCarrier(90, e.getMessage()));
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<ExceptionCarrier> handleWalletNotFound(WalletNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ExceptionCarrier(100, e.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionCarrier> handleGeneric(Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionCarrier(500, "Unexpected error: " + e.getMessage()));
    }
}
