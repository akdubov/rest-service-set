package ru.vtb.dis.pilot01.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {


    private String clientServiceName;
    private String resultUID;
    private String result;


    public ClientResponse() {
        super();
    }

    public ClientResponse(String clientServiceName, String resultUID, String result) {
        this();
        this.clientServiceName = clientServiceName;
        this.resultUID = resultUID;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getClientServiceName() {
        return clientServiceName;
    }

    public void setClientServiceName(String clientServiceName) {
        this.clientServiceName = clientServiceName;
    }

    public String getResultUID() {
        return resultUID;
    }

    public void setResultUID(String resultUID) {
        this.resultUID = resultUID;
    }
}
