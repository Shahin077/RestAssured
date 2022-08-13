package com.cydeo.pojo;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ZipCode {

    @JsonProperty("post code")
    private String postcode;

}
