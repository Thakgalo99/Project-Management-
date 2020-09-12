package com.projectmanagement.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class ResourceNoContentException extends Exception{

    private static final long serialVersionUID = -580961944917443077L;
    public ResourceNoContentException(String message){
        super(message);
    }
}
