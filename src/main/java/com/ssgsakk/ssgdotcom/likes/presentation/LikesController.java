package com.ssgsakk.ssgdotcom.likes.presentation;

import com.ssgsakk.ssgdotcom.common.exception.BusinessException;
import com.ssgsakk.ssgdotcom.common.exception.ErrorCode;
import com.ssgsakk.ssgdotcom.common.response.BaseResponse;
import com.ssgsakk.ssgdotcom.likes.application.LikesService;
import com.ssgsakk.ssgdotcom.likes.dto.AddProductOrCategoryLikesDto;
import com.ssgsakk.ssgdotcom.likes.dto.DeleteProductLikesDto;
import com.ssgsakk.ssgdotcom.likes.vo.AddProductOrCategoryLikesResponseVo;
import com.ssgsakk.ssgdotcom.likes.vo.DeleteProductLikesResponseVo;
import com.ssgsakk.ssgdotcom.security.JWTUtil;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/likes")
@RequiredArgsConstructor
public class LikesController {
    private final LikesService likesService;
    private final JWTUtil jwtUtil;


    @Operation(summary = "상품 또는 카테고리 찜 추가", description = "상품 또는 카테고리 찜 추가")
    @GetMapping("/add")
    public BaseResponse<AddProductOrCategoryLikesResponseVo> addProductLikes(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "product-seq", required = false) Long productSeq
            , @RequestParam(value = "category-seq", required = false) Long categorySeq) {
        String uuid = getUuid(accessToken);

        // 입력받은 product-seq 또는 category-seq 를 추가
        likesService.addProductOrCategoryLikes(AddProductOrCategoryLikesDto.builder()
                .uuid(uuid)
                .productSeq(productSeq)
                .categorySeq(categorySeq)
                .build());

        return new BaseResponse<>("Add Product Or Category Likes Success", AddProductOrCategoryLikesResponseVo.builder()
                .productSeq(productSeq)
                .build());
    }

    @Operation(summary = "상품 찜 삭제", description = "상품 찜 삭제")
    @DeleteMapping("/delete")
    public BaseResponse<DeleteProductLikesResponseVo> deleteProductLikes(@RequestHeader("Authorization") String accessToken
            , @RequestParam(value = "product-seq", required = false) Long productSeq) {
        String uuid = getUuid(accessToken);

        likesService.deleteProductLikes(DeleteProductLikesDto.builder()
                .uuid(uuid)
                .productSeq(productSeq)
                .build());

        return new BaseResponse<>("Delete Product Likes Success", DeleteProductLikesResponseVo.builder()
                .productSeq(productSeq)
                .build());
    }

//    @Operation(summary = "상품 찜 조회", description = "상품 찜 조회")
//    @DeleteMapping("/user/product")
//    public BaseResponse<?> userProductLikes(@RequestHeader("Authorization") String accessToken) {
//        String uuid = getUuid(accessToken);
//
//        likesService.deleteProductLikes(DeleteProductLikesDto.builder()
//                .uuid(uuid)
//                .productSeq(productSeq)
//                .build());
//
//        return new BaseResponse<>("User Product Likes List", null);
//    }


    // JWT에서 UUID 추출 메서드
    public String getUuid(String jwt) {
        String uuid;
        uuid = jwtUtil.getUuid(jwt.split(" ")[1]);
        checkUuid(uuid);
        return uuid;
    }

    // UUID 확인
    // 정상이면 true 반환
    public void checkUuid(String uuid) {
        if (uuid == null) {
            throw new BusinessException(ErrorCode.TOKEN_NOT_VALID);
        }
    }
}
