package com.beki.AuthProject.service;

import com.beki.AuthProject.io.ProfileRequest;
import com.beki.AuthProject.io.ProfileResponse;

public interface ProfileService {
    ProfileResponse createProfile(ProfileRequest profileRequest);
}
