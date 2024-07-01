package com.jvsc.Response;

import java.util.List;

public class FitException extends RuntimeException {
    
    private List<ErroResponse> erros;
    private int status;

    public FitException(String message, int status) {
        super(message);
        this.status = status;
    }

    public FitException(List<ErroResponse> errors, int status) {
        this.erros = errors;
        this.status = status;
    }
}
