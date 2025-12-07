package com.quokkajoa.practice.kakaooauth.kakaooauthpractice.service;

import com.quokkajoa.practice.kakaooauth.kakaooauthpractice.dto.KakaoTokenResponseDto;
import com.quokkajoa.practice.kakaooauth.kakaooauthpractice.dto.KakaoUserInfoResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {

  @Value("${kakao.client-id}")
  private String clientId;

  @Value("${kakao.redirect-uri}")
  private String redirectUri;

  @Value("${kakao.token-uri}")
  private String tokenUri;

  @Value("${kakao.user-info-uri}")
  private String userInfoUri;

  private final RestTemplate restTemplate = new RestTemplate();

  public String getAccessToken(String code) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

    MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
    body.add("grant_type", "authorization_code");
    body.add("client_id", clientId);
    body.add("redirect_uri", redirectUri);
    body.add("code", code);

    HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(body, headers);

    ResponseEntity<KakaoTokenResponseDto> response = restTemplate.exchange(
        tokenUri,
        HttpMethod.POST,
        requestEntity,
        KakaoTokenResponseDto.class
    );
    return response.getBody().accessToken();
  }

  public KakaoUserInfoResponseDto getUserInfo(String accessToken) {
    HttpHeaders headers = new HttpHeaders();
    headers.add("Authorization", "Bearer " + accessToken);
    headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

    ResponseEntity<KakaoUserInfoResponseDto> response = restTemplate.exchange(
        userInfoUri,
        HttpMethod.GET,
        requestEntity,
        KakaoUserInfoResponseDto.class
    );

    return response.getBody();
  }

}
