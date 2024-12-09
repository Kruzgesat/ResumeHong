package inhatc.cse.resumehong.resume.entity;

import inhatc.cse.resumehong.common.entity.BaseEntity;
import inhatc.cse.resumehong.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Resume extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="resume_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)  //Loading Basic Info
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(nullable = false)           //Resume Title
    private String resumeName;

    @Column(nullable = false)           //Mmeber's Final Education
    private String finalEducation;

    @Column(nullable = false)           //Mmeber's Major
    private String major;

    @Column(nullable = false)           //Mmeber's Career
    private String career;

    @Column(nullable = false)           //Member's Acceptable skills
    private String skill;

    @Column(nullable = false)           //Member's Certificate
    private String certificate;

    @Column(nullable = false)           //Member's Human Language
    private String language;

    @Column(nullable = false)           //Member's Introduction
    private String introduction;


}
