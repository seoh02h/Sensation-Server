package com.eureka.ationserver.controller;


import com.eureka.ationserver.dto.insight.InsightRequest;
import com.eureka.ationserver.model.user.User;
import com.eureka.ationserver.repository.user.UserRepository;
import com.eureka.ationserver.service.InsightService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
@Api(tags = {"Insight"})
@RequiredArgsConstructor
public class InsightController {

  private final InsightService insightService;
  private final UserRepository userRepository;


  @PostMapping("/insight")
  @ApiOperation(value = "인사이트 생성")
  public ResponseEntity savePublic(@RequestBody InsightRequest insightRequest) throws Exception {
    return new ResponseEntity(insightService.savePublic(insightRequest), null, HttpStatus.CREATED);
  }

  @PostMapping("/insight/image/{insightId}")
  @ApiOperation(value = "인사이트 썸네일 이미지 변경")
  public ResponseEntity saveImg(@PathVariable Long insightId,
      @RequestParam(value = "insightImg", required = true) MultipartFile insightImg)
      throws IOException {
    return new ResponseEntity(insightService.saveImg(insightId, insightImg), null, HttpStatus.OK);
  }


  @GetMapping("/insight")
  @ApiOperation(value = "인사이트 전체 조회")
  public ResponseEntity findPublicAll() {
    return new ResponseEntity(insightService.findPublicAll(), null, HttpStatus.CREATED);
  }

  @GetMapping("/insight/{insightId}")
  @ApiOperation(value = "인사이트 조회")
  public ResponseEntity findPublic(@AuthenticationPrincipal UserDetails userDetails,
      @PathVariable Long insightId) {
    User user = userRepository.findByIdentifyId(userDetails.getUsername()).get();

    return new ResponseEntity(insightService.findPublic(user, insightId), null, HttpStatus.CREATED);
  }

  @GetMapping("/insight/main-category/{mainCategoryId}")
  @ApiOperation(value = "인사이트 카테고리별 조회")
  public ResponseEntity findByMainCategory(@PathVariable Long mainCategoryId) {
    return new ResponseEntity(insightService.findByMainCategory(mainCategoryId), null,
        HttpStatus.CREATED);
  }

  @GetMapping("/insight/search")
  @ApiOperation(value = "인사이트 검색")
  public ResponseEntity search(@RequestParam String keyword) {
    return new ResponseEntity(insightService.search(keyword), null, HttpStatus.OK);
  }


}
