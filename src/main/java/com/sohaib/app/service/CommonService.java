package com.sohaib.app.service;

import com.sohaib.app.feignclient.AddressFeignClient;
import com.sohaib.app.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    static Logger logger= LoggerFactory.getLogger(CommonService.class);

    static long count=0;
    @Autowired
    private AddressFeignClient addressFeignClient;

    @CircuitBreaker(name = "address-service", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long id) {
        logger.info("count= "+count);
        count++;
        // return webClient.get().uri("/getById/" + id).retrieve().bodyToMono(AddressResponse.class).block();
        AddressResponse byId = addressFeignClient.getById(id);
        return byId;
    }

    public AddressResponse fallbackGetAddressById(long id,Throwable th) {
        logger.error("Error "+th);
        return new AddressResponse();
    }
}
