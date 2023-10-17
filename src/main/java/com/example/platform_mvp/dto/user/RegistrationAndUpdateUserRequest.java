package com.example.platform_mvp.dto.user;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class RegistrationAndUpdateUserRequest {
    String username;
    String firstName;
    String surname;
    String jobTitle;
    Integer experience;
    String userInfo;
    String service_title;
}
