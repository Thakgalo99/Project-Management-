package com.projectmanagement.api.responces;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class ExceptionResponce {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String message;
    private String details;
    private String httpCodeMessage;

    public ExceptionResponce(Date timestamp, String message, String details, String httpCodeMessage) {
        super();
        this.timestamp = timestamp;
        this.details = details;
        this.httpCodeMessage=httpCodeMessage;
        this.message = message;

    }
    public Date getTimestamp() {
        return timestamp;
    }
    public String getDetails() {
        return details;
    }
    public String getHttpCodeMessage() {
        return httpCodeMessage;
    }
    public String getMessage() { return message; }
}
