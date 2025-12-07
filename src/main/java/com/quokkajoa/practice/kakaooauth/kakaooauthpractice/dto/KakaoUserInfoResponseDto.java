package com.quokkajoa.practice.kakaooauth.kakaooauthpractice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoUserInfoResponseDto(
    Long id,
    @JsonProperty("kakao_account") KakaoAccount kakaoAccount
) {

  public record KakaoAccount(Profile profile) {}
  public record Profile(String nickname) {}
}
