package com.ssgsakk.ssgdotcom.member.presentation;

import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.member.application.AuthService;
import com.ssgsakk.ssgdotcom.member.dto.SignInDto;
import com.ssgsakk.ssgdotcom.member.dto.SignUpDto;

import com.ssgsakk.ssgdotcom.member.vo.SignInRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignInResponseVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpRequestVo;
import com.ssgsakk.ssgdotcom.member.vo.SignUpResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "로그인", tags = {"User SignIn"})
    @PostMapping("/signin")
    public BaseResponse<SignInResponseVo> signIn(@RequestBody SignInRequestVo signInRequestVo) {

        SignInDto signInDto = SignInDto.builder()
                .userId(signInRequestVo.getUserId())
                .userPassword(signInRequestVo.getUserPassword())
                .build();

        SignInDto servicedSignInDto = authService.signIn(signInDto);

        return new BaseResponse<>("SignIn Success", SignInResponseVo.builder()
                .userName(servicedSignInDto.getUserName())
                .uuid(servicedSignInDto.getUuid())
                .token(servicedSignInDto.getToken())
                .build());
    }

    @Operation(summary = "회원가입", description = "회원가입", tags = {"User SignUp"})
    @PostMapping("/signup")
    public BaseResponse<SignUpResponseVo> signUp(@RequestBody SignUpRequestVo signUpRequestVo) {

        SignUpDto signUpDto = SignUpDto.builder()
                .userId(signUpRequestVo.getUserId())
                .userPassword(signUpRequestVo.getUserPassword())
                .userName(signUpRequestVo.getUserName())
                .userEmail(signUpRequestVo.getUserEmail())
                .userPhoneNum(signUpRequestVo.getUserPhoneNum())
                .userMobileNum(signUpRequestVo.getUserMobileNum())
                .build();

        SignUpDto servicedSignUpDto = authService.signUp(signUpDto);

        return new BaseResponse<>("SignUp Success", SignUpResponseVo.builder()
                .userName(servicedSignUpDto.getUserName())
                .uuid(servicedSignUpDto.getUuid())
                .build());
    }
}