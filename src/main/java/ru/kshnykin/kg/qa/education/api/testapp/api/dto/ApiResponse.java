package ru.kshnykin.kg.qa.education.api.testapp.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    public static final String SUCCESS_MES = "additionalMetadata: %s\nFile uploaded to ./%s, %d bytes";
    public static final String NUM_FORMAT_EXC_MES = "java.lang.NumberFormatException: For input string: \"%s\"";
    public static final String MISS_START_BOUND_MES = "org.jvnet.mimepull.MIMEParsingException: Missing start boundary";
    public static final ApiResponse BAD_REQUEST = new ApiResponse(400, "unknown", "message");
    public static final ApiResponse NOT_FOUND = new ApiResponse(404, "unknown", "message");
    public static final ApiResponse SUCCESS = new ApiResponse(200, "unknown", "message");
    public static final ApiResponse ERROR = new ApiResponse(500, "unknown", "something bad happened");

    private Integer code;
    private String type;
    private String message;

    public ApiResponse withMessage(String message, Object... param) {
        this.message = String.format(message, param);
        return this;
    }

}
