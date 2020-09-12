package com.projectmanagement.api.controllers;

public class APIName {

    // version
    public static final String VERSION = "api/v1/{company_id}";

    // charset
    public static final String CHARSET_JSON = "application/json;charset=utf-8";
    public static final String CHARSET_XML = "application/xml;charset=utf-8";

    // User API
    public static final String USER = "/users";
    public static final String USER_BY_USER_ID = "/{userid}";
    public static final String USER_BY_USER_UID= "/{useruid}";

    // User API
    public static final String USERPROFILE = "/userprofile";
    public static final String USERPROFILE_BY_USER_ID = "/{userprofileid}";
    public static final String USERPROFILE_BY_USER_UID= "/{userprofileuid}";
    public static final String USERPROFILE_IMAGE_BY_USER_UID= "/imageuserprofile/{userprofileuid}";

    // Client API
    public static final String CLIENT = "/clients";
    public static final String CLIENT_BY_CLIENT_ID = "/{clientid}";
    public static final String CLIENT_BY_CLIENT_UID= "/{clientuid}";

    // Project API
    public static final String PROJECT = "/projects";
    public static final String PROJECT_BY_PROJECT_ID = "/{projectid}";
    public static final String PROJECT_BY_PROJECT_UID= "/{projectuid}";

    // ProjectIssue API
    public static final String PROJECTISSUE = "/projectissues";
    public static final String PROJECTISSUE_BY_PROJECTISSUE_ID = "/{projectissueid}";
    public static final String PROJECTISSUE_BY_PROJECTISSUE_UID= "/{projectissueuid}";

    // Notice API
    public static final String NOTICE = "/notices";
    public static final String NOTICE_BY_NOTICE_ID = "/{noticeid}";
    public static final String NOTICE_BY_NOTICE_UID= "/{noticeuid}";

    // Task API
    public static final String TASK = "/tasks";
    public static final String TASK_BY_TASK_ID = "/{taskid}";
    public static final String TASK_BY_TASK_UID= "/{taskuid}";

    // Category API
    public static final String CATEGORY = "/categories";
    public static final String CATEGORY_BY_CATEGORY_ID = "/{categoryid}";
    public static final String CATEGORY_BY_CATEGORY_UID= "/{categoryuid}";

    // Status API
    public static final String STATUS = "/statuses";
    public static final String STATUS_BY_STATUS_ID = "/{statusid}";
    public static final String STATUS_BY_STATUS_UID= "/{statusuid}";
}
