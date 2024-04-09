package com.ssgsakk.ssgdotcom.review.dto;

import com.ssgsakk.ssgdotcom.contents.domain.ReviewContents;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class ReviewInfoDto {
    private String reviewParagraph;
    private Short reviewScore;
    private List<ReviewContents> contentsUrl;
    private String purchaseProductOption;
    private String userId;
    private LocalDateTime reviewDate;
}
