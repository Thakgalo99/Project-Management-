package com.projectmanagement.api.responces;

public enum APIStatus {

    //region Common status
    OK(200, null),
    CREATED(201, "Data created successfully"),
    ERR_NO_CONTENT(204, "No content found"),
    ERR_INTERNAL_SERVER(500, "Internal Error"),
    SQL_ERROR(501, "SQL Error"),
    ERR_BAD_REQUEST(400, "Bad request"),
    ERR_Data_Already_Existe(409, "resource already exists"),
    ERR_SESSION_DATA_INVALID(603, "Session data invalid"),
    ERR_SESSION_NOT_FOUND(604, "Session not found"),
    // File upload error
    ERR_UPLOAD_FILE(900, "Upload file error.");
    //endregion

    private final int code;
    private final String description;

    private APIStatus(int s, String v) {
        code = s;
        description = v;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
