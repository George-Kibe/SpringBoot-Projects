package com.kibe.SReactAuth.io;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileResponse {
    private String userId;
    private String username;
    private String email;
    private Boolean isAccountVerified;
}
