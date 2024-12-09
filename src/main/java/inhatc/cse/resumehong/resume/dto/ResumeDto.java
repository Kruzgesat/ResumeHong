package inhatc.cse.resumehong.resume.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ResumeDto {

    private Long id;
    private String resumeName;
    private String name;
    private String phone;
    private String address;
    private String email;
    private String finalEducation;
    private String major;
    private String career;
    private String skill;
    private String certificate;
    private String language;
    private String introduction;
}
