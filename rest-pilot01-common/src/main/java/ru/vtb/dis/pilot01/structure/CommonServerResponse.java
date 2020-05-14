package ru.vtb.dis.pilot01.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonServerResponse {

    private String requestUID;
    private String responseUID;
    private String resultText;

    public CommonServerResponse() {
        super();
    }

    public CommonServerResponse(String requestUID, String responseUID, String resultText) {
        this.requestUID = requestUID;
        this.responseUID = responseUID;
        this.resultText = resultText;
    }

    public String getRequestUID() {
        return requestUID;
    }

    public String getResponseUID() {
        return responseUID;
    }

    public String getResultText() {
        return resultText;
    }

    public void setRequestUID(String requestUID) {
        this.requestUID = requestUID;
    }

    public void setResponseUID(String responseUID) {
        this.responseUID = responseUID;
    }

    public void setResultText(String resultText) {
        this.resultText = resultText;
    }
}
