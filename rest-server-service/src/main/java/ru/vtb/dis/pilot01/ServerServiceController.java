package ru.vtb.dis.pilot01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.vtb.dis.pilot01.structure.CommonServerResponse;

import java.util.UUID;

@RestController
public class ServerServiceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ServerServiceController.class);

    @GetMapping("/getServiceResult")
    public CommonServerResponse getServiceResult(
            @RequestParam(value = "requestUID", defaultValue = "noUID") String requestUID,
            @RequestParam(value = "callerServiceName", defaultValue = "namelessService") String callerServiceName) {

        String responseUID = UUID.randomUUID().toString();
        String resultText = String.format("'%s': a response '%s' has been generated for the calling '%s' service",
                requestUID,responseUID, callerServiceName);
        LOGGER.info("{}", resultText);
        return new CommonServerResponse(requestUID, responseUID, resultText);
    }
}
