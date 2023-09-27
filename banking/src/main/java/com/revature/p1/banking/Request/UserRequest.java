package com.revature.p1.banking.Request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {
    private String
        username,
        password,
        firstName,
        lastName,
        role;
}
