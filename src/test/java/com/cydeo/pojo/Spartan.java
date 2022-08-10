package com.cydeo.pojo;

import lombok.Data;

@Data
public class Spartan {

    /**
     * {
     *     "id": 10,
     *     "name": "Lorenza",
     *     "gender": "Female",
     *     "phone": 3312820936
     * }
     */
    private int id;
    private String name;
    private String gender;
    private long phone;
}
