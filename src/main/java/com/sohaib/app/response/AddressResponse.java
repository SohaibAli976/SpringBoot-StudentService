package com.sohaib.app.response;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressResponse {
    private String street;

    private String city;

    private Long addressId;

}
