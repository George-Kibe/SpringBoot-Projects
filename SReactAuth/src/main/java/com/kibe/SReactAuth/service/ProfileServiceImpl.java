package com.kibe.SReactAuth.service;

import com.kibe.SReactAuth.entity.UserEntity;
import com.kibe.SReactAuth.io.ProfileRequest;
import com.kibe.SReactAuth.io.ProfileResponse;
import com.kibe.SReactAuth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor

public class ProfileServiceImpl  implements  ProfileService{
    private final UserRepository userRepository;

    @Override
    public ProfileResponse createProfile(ProfileRequest profileRequest) {
        UserEntity newProfile  = convertToUserEntity(profileRequest);
        newProfile = userRepository.save(newProfile);
        return convertToProfileResponse(newProfile);
    }
    private UserEntity convertToUserEntity(ProfileRequest profileRequest) {
        try {
            UserEntity u = UserEntity.builder()
                    .userId("123")
                    .username("george")
                    .email("g@x.com")
                    .password("pass")
                    .build();
            System.out.println(u);

            return UserEntity.builder()
                    .email(profileRequest.getEmail())
                    .userId(UUID.randomUUID().toString())
                    .username(profileRequest.getUsername())
                    .password(profileRequest.getPassword())
                    .isAccountVerified(false)
                    .resetOtpExpiresAt(0L)
                    .verifyOtp(null)
                    .verifyOtpExpiresAt(0L)
                    .resetOtp(null)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    private ProfileResponse convertToProfileResponse(UserEntity newProfile) {
        return ProfileResponse.builder()
                .username(newProfile.getUsername())
                .email(newProfile.getEmail())
                .userId(newProfile.getUserId())
                .isAccountVerified(newProfile.getIsAccountVerified())
                .build();
    }


}
