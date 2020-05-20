package ru.vtb.dis.pilot01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.dis.pilot01.structure.CommonServerResponse;

import java.util.UUID;

@RestController
public class ServerServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceController.class);

    @GetMapping("/getServiceResult")
    public CommonServerResponse getServiceResult(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "requestUID", defaultValue = "noUID") String requestUID,
            @RequestParam(value = "callerServiceName", defaultValue = "namelessService") String callerServiceName) {
        return processRequest(headers, requestUID, callerServiceName);
    }

    @GetMapping("/reserved/getServiceResult")
    public CommonServerResponse getServiceResultReserved(
            @RequestHeader HttpHeaders headers,
            @RequestParam(value = "requestUID", defaultValue = "noUID") String requestUID,
            @RequestParam(value = "callerServiceName", defaultValue = "namelessService") String callerServiceName) {
        return processRequest(headers, requestUID, callerServiceName);
    }

    private CommonServerResponse processRequest(HttpHeaders headers, String requestUID, String callerServiceName) {
        String responseUID = UUID.randomUUID().toString();
        String resultText = String.format("'%s': a response '%s' has been generated for the calling '%s' service",
                requestUID,responseUID, callerServiceName);

        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("{}\n\t\theader: {}", resultText, headers != null ? headers.toString() : "NULL");
        } else {
            LOGGER.info("{}", resultText);
        }
        return new CommonServerResponse(requestUID, responseUID, resultText);
    }

}
