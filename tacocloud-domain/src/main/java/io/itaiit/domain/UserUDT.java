package io.itaiit.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

/**
 * @author itaiit
 * @date 2022/9/13 9:23
 */
@UserDefinedType("user")
@Data
@RequiredArgsConstructor
public class UserUDT {

    private final String username;
    private final String fullname;
    private final String phoneNumber;

}
