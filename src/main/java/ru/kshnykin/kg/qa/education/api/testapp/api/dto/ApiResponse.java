package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ApiResponse {

    public static final String SUCCESS_MES = "additionalMetadata: %s\nFile uploaded to ./%s, %d bytes";
    public static final String NUM_FORMAT_EXC_MES = "java.lang.NumberFormatException: For input string: \"%s\"";
    public static final String MISS_START_BOUND_MES = "org.jvnet.mimepull.MIMEParsingException: Missing start boundary";
    public static final ApiResponse BAD_REQUEST = new ApiResponse(400, "unknown", "message");
    public static final ApiResponse NOT_FOUND = new ApiResponse(404, "unknown", "message");
    public static final ApiResponse SUCCESS = new ApiResponse(200, "unknown", "message");

    private Integer code;
    private String type;
    private String message;

    public ApiResponse() {
    }

    public ApiResponse(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public ApiResponse withMessage(String message, Object... param) {
        this.message = String.format(message, param);
        return this;
    }

}
