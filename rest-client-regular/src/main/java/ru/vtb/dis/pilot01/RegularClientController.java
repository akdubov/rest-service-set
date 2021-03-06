package ru.vtb.dis.pilot01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import ru.vtb.dis.pilot01.structure.ClientResponse;
import ru.vtb.dis.pilot01.structure.CommonServerResponse;

import java.util.Date;
import java.util.UUID;

@RestController
public class RegularClientController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegularClientController.class);

    @Autowired
    private Environment env;

    @GetMapping("/callFarService")
    public ClientResponse callFarService(
            @RequestParam(value = "farServiceUrl", defaultValue = "") String farServiceUrl) {
        String serviceName = env.getProperty("serviceName");
        String defaultFarUrl = env.getProperty("defaultFarUrl");
        LOGGER.debug("Params:\n\tfarServiceURL={}\n\tserviceName={}", farServiceUrl, serviceName);
        String callUrl = String.format("%s/getServiceResult?requestUID=%s&callerServiceName=%s",
                StringUtils.isEmpty(StringUtils.trimWhitespace(farServiceUrl)) ? defaultFarUrl : farServiceUrl,
                UUID.randomUUID().toString(),
                serviceName);
        LOGGER.debug("call URL: {}", callUrl);

        long startTime = new Date().getTime();
        RestTemplate restTemplate = new RestTemplate();
        CommonServerResponse response = restTemplate.getForObject(callUrl, CommonServerResponse.class);
        if (response != null) {
            LOGGER.info("{} Response: ({} -> {}): {}",
                    String.format("[%.3f sec.]", (new Date().getTime() - startTime) / 1000.0),
                    response.getRequestUID(), response.getResponseUID(), response.getResultText());
            return new ClientResponse(serviceName, response.getResponseUID(), response.getResultText());
        } else {
            LOGGER.warn("Response = NULL");
            throw new RestClientException("Response = NULL");
        }
    }
}
