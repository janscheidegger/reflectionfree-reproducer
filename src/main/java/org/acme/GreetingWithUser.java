package org.acme;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties("someOtherField")
public class GreetingWithUser {

    public String message;

    public String someOtherField;

    @JsonUnwrapped
    @JsonIgnoreProperties("password")
    public User user;
}
