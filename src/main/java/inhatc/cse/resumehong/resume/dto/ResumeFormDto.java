package inhatc.cse.resumehong.resume.dto;

import inhatc.cse.resumehong.resume.entity.Resume;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResumeFormDto {

    private Long id;
    private Long memberId;
    @NotBlank(message = "이력서 이름은 필수항목 입니다.")
    private String resumeName;
    private String name;
    private String phone;
    private String address;
    private String email;

    @NotBlank(message = "최종학력은 필수항목 입니다.")
    private String finalEducation;

    @NotBlank(message = "상품명은 필수항목 입니다. 없으면 없음으로 적어주세요")
    private String major;

    @NotBlank(message = "경력은 필수항목 입니다. 없으면 없음으로 적어주세요")
    private String career;

    @NotBlank(message = "기술은 필수항목 입니다. 없으면 없음으로 적어주세요")
    private String skill;

    @NotBlank(message = "자격증은 필수항목 입니다. 없으면 없음으로 적어주세요")
    private String certificate;

    @NotBlank(message = "언어는 필수항목 입니다. 없으면 없음으로 적어주세요")
    private String language;

    @NotBlank(message = "자기소개는 필수항목 입니다.")
    private String introduction;

}
