package com.defect.defectTracker.utils;

import com.defect.defectTracker.dto.responseDto.ReleaseResponse;

public class Constants {

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAILURE = "failure";

    public static final String RETRIEVED_SUCCESSFULLY = "2000";
    public static final String RETRIEVE_FAILED = "4000";
    public static final String MISSING_PARAMETER = "4000";
    public static final String DATA_NOT_FOUND = "4000";

    public static ReleaseResponse responseBuilder(int httpStatusCode, String status, String statusCode, String message, Object result) {
        return new ReleaseResponse();
    }
}