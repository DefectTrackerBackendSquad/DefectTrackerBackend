package com.defect.defectTracker.response;
public class ApiResponseCodes {

    // post
    public static final int SUCCESS_SAVE = 10001;
    public static final String MSG_SAVE_SUCCESS = "Saved successfully.";

    public static final int ERROR_SAVE_FAILED = 10002;
    public static final String MSG_SAVE_FAILED = "Save Failed.";

    // DEL
    public static final int SUCCESS_DELETE = 10003;
    public static final String MSG_DELETE_SUCCESS = "Deleted successfully.";

    public static final int ERROR_DELETE_FAILED = 10004;
    public static final String MSG_DELETE_FAILED = "Delete Failed.";

    // get
    public static final int SUCCESS_RETRIEVE = 10005;
    public static final String MSG_RETRIEVE_SUCCESS = "Retrieved Successfully.";

    public static final int ERROR_RETRIEVE_FAILED = 10006;
    public static final String MSG_RETRIEVE_FAILED = "Retrieve Failed.";

    // put
    public static final int SUCCESS_UPDATE = 10007;
    public static final String MSG_UPDATE_SUCCESS = "Updated Successfully.";

    public static final int ERROR_UPDATE_FAILED = 10008;
    public static final String MSG_UPDATE_FAILED = "Update Failed.";

    // generl
    public static final int ERROR_DATA_NOT_EXIST = 10009;
    public static final String MSG_DATA_NOT_EXIST = "Data not exist.";

    public static final int SUCCESS_FILE_UPLOAD = 10010;
    public static final String MSG_FILE_UPLOAD_SUCCESS = "File uploaded successfully.";

    public static final int ERROR_DATA_NOT_FOUND = 10011;
    public static final String MSG_DATA_NOT_FOUND = "Data not found.";


    public static final int SUCCESS_LOGIN = 10012;
    public static final String MSG_LOGIN_SUCCESS = "Login Successfully.";

    public static final int ERROR_LOGIN_FAILED = 10013;
    public static final String MSG_LOGIN_FAILED = "Login Failed.";

    public static final int SUCCESS_LOGOUT = 10014;
    public static final String MSG_LOGOUT_SUCCESS = "Logout Successfully.";

    // val
    public static final int ERROR_EMAIL_EXISTS = 10015;
    public static final String MSG_EMAIL_EXISTS = "Email already exists.";

    public static final int ERROR_USER_EXISTS = 10016;
    public static final String MSG_USER_EXISTS = "User already exists.";

    public static final int ERROR_PHONE_EXISTS = 10017;
    public static final String MSG_PHONE_EXISTS = "Phone number already exists.";

    public static final int ERROR_MISSING_FIELDS = 10018;
    public static final String MSG_MISSING_FIELDS = "Missing required fields.";

    public static final int ERROR_MISSING_PARAMS = 10019;
    public static final String MSG_MISSING_PARAMS = "Missing parameter(s).";

}
