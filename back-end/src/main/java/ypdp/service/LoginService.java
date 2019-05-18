package ypdp.service;

import ypdp.api.LoginRequest;
import ypdp.api.LoginResponse;

public interface LoginService {

    LoginResponse login(LoginRequest loginRequest);
}
