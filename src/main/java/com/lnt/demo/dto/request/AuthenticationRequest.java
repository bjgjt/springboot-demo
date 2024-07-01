package com.lnt.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AuthenticationRequest implements Serializable {
    String username;
    String password;
}
