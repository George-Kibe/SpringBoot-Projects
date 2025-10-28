package com.kibe.SReactAuth.io;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileRequest {
    private String username;
    private String email;
    private String password;

}
