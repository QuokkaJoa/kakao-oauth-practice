package com.quokkajoa.practice.kakaooauth.kakaooauthpractice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record KakaoCalendarEventDto(
    String title,
    Time time,
    String description
) {
  @Builder
  public record Time(
      @JsonProperty("start_at") String startAt,
      @JsonProperty("end_at")String endAt
  ){}

}
