package com.quokkajoa.practice.kakaooauth.kakaooauthpractice.controller;

import com.quokkajoa.practice.kakaooauth.kakaooauthpractice.dto.KakaoUserInfoResponseDto;
import com.quokkajoa.practice.kakaooauth.kakaooauthpractice.service.KakaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class KaKaoController {

  private final KakaoService kakaoService;

  @GetMapping("/login/oauth2/code/kakao")
  public String kakaoLogin(@RequestParam("code") String code) {
    System.out.println("인가 코드(Code) 도착: " + code);

    String accessToken = kakaoService.getAccessToken(code);
    System.out.println("엑세스 토근 발급 성공: " + accessToken);

    KakaoUserInfoResponseDto userInfo = kakaoService.getUserInfo(accessToken);

    return "로그인 성공! 닉네임: " + userInfo.kakaoAccount().profile().nickname();
  }
}
