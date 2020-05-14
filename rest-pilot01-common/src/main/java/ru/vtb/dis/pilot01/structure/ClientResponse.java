package ru.vtb.dis.pilot01.structure;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientResponse {


    private String clientName;
    private String resultUID;
    private String result;


    public ClientResponse() {
        super();
    }

    public ClientResponse(String clientServiceName, String resultUID, String result) {
        this();
        this.clientName = clientServiceName;
        this.resultUID = resultUID;
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getResultUID() {
        return resultUID;
    }

    public void setResultUID(String resultUID) {
        this.resultUID = resultUID;
    }
}
