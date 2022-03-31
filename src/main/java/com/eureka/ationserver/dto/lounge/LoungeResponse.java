package com.eureka.ationserver.dto.lounge;

import com.eureka.ationserver.dto.persona.PersonaResponse;
import com.eureka.ationserver.dto.persona.category.SenseResponse;
import com.eureka.ationserver.model.lounge.ELoungeStatus;
import com.eureka.ationserver.model.lounge.Lounge;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoungeResponse {

  private Long id;

  private String title;

  private Integer limitMember;

  private ELoungeStatus status;

  private String introduction;

  private String notice;

  private Long totalMember;

  private String imgPath;

  private String whiteboard;

  private LocalDateTime createdAt;

  private PersonaResponse.SimpleOut persona;

  private SenseResponse.Out sense;

  private MainCategoryResponse mainCategory;

  private List<SubCategoryResponse> subCategoryList;

  private List<LoungeMemberResponse> memberList;


  public LoungeResponse(Lounge lounge) {

    MainCategoryResponse mainCategoryResponse = new MainCategoryResponse(
        lounge.getLoungeMainCategory());
    List<SubCategoryResponse> subCategoryResponseList = new ArrayList<>();
    lounge.getLoungeSubCategoryList().stream()
        .forEach(x -> subCategoryResponseList.add(new SubCategoryResponse(x)));
    List<LoungeMemberResponse> memberList = new ArrayList<>();
    lounge.getLoungeMemberList().stream()
        .forEach(x -> memberList.add(new LoungeMemberResponse(x)));

    this.id = lounge.getId();
    this.title = lounge.getTitle();
    this.limitMember = lounge.getLimitMember();
    this.status = lounge.getStatus();
    this.introduction = lounge.getIntroduction();
    this.notice = lounge.getNotice();
    this.createdAt = lounge.getCreatedAt();
    this.imgPath = lounge.getImgPath();
    this.whiteboard = lounge.getWhiteboard();
    this.sense = SenseResponse.toOut(lounge.getSense());
    this.persona = PersonaResponse.toSimpleOut(lounge.getPersona());
    this.mainCategory = mainCategoryResponse;
    this.subCategoryList = subCategoryResponseList;
    this.memberList = memberList;

  }

}
