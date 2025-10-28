package com.kibe.SReactAuth.service;

import com.kibe.SReactAuth.io.ProfileRequest;
import com.kibe.SReactAuth.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest profileRequest);
}
