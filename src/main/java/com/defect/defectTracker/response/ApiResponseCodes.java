package com.defect.defectTracker.response;
public class ApiResponseCodes {

    // post
    public static final int SUCCESS_SAVE = 2000;
    public static final String MSG_SAVE_SUCCESS = "Saved successfully.";

    public static final int ERROR_SAVE_FAILED = 4000;
    public static final String MSG_SAVE_FAILED = "Save Failed.";

    // DEL
    public static final int SUCCESS_DELETE = 2000;
    public static final String MSG_DELETE_SUCCESS = "Deleted successfully.";

    public static final int ERROR_DELETE_FAILED = 4000;
    public static final String MSG_DELETE_FAILED = "Delete Failed.";

    // get
    public static final int SUCCESS_RETRIEVE = 2000;
    public static final String MSG_RETRIEVE_SUCCESS = "Retrieved Successfully.";

    public static final int ERROR_RETRIEVE_FAILED = 4000;
    public static final String MSG_RETRIEVE_FAILED = "Retrieve Failed.";

    // put
    public static final int SUCCESS_UPDATE = 2000;
    public static final String MSG_UPDATE_SUCCESS = "Updated Successfully.";

    public static final int ERROR_UPDATE_FAILED = 4000;
    public static final String MSG_UPDATE_FAILED = "Update Failed.";

    // generl
    public static final int ERROR_DATA_NOT_EXIST = 4000;
    public static final String MSG_DATA_NOT_EXIST = "Data not exist.";

    public static final int SUCCESS_FILE_UPLOAD = 2000;
    public static final String MSG_FILE_UPLOAD_SUCCESS = "File uploaded successfully.";

    public static final int ERROR_DATA_NOT_FOUND = 4000;
    public static final String MSG_DATA_NOT_FOUND = "Data not found.";


    public static final int SUCCESS_LOGIN = 2000;
    public static final String MSG_LOGIN_SUCCESS = "Login Successfully.";

    public static final int ERROR_LOGIN_FAILED = 4000;
    public static final String MSG_LOGIN_FAILED = "Login Failed.";

    public static final int SUCCESS_LOGOUT = 2000;
    public static final String MSG_LOGOUT_SUCCESS = "Logout Successfully.";

    // val
    public static final int ERROR_EMAIL_EXISTS = 4000;
    public static final String MSG_EMAIL_EXISTS = "Email already exists.";

    public static final int ERROR_USER_EXISTS = 4000;
    public static final String MSG_USER_EXISTS = "User already exists.";

    public static final int ERROR_PHONE_EXISTS = 4000;
    public static final String MSG_PHONE_EXISTS = "Phone number already exists.";

    public static final int ERROR_MISSING_FIELDS = 4000;
    public static final String MSG_MISSING_FIELDS = "Missing required fields.";

    public static final int ERROR_MISSING_PARAMS = 4000;
    public static final String MSG_MISSING_PARAMS = "Missing parameter(s).";

}
