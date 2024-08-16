package com.sohaib.app.feignclient;

import com.sohaib.app.response.AddressResponse;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(url = "${address.Service.url}", value = "address-feign-client",path = "/api/address")
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

    @GetMapping("address-service/api/address/getById/{id}")
    AddressResponse getById(@PathVariable Long id);
}
