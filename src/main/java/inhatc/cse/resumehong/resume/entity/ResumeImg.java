package inhatc.cse.resumehong.resume.entity;

import jakarta.persistence.*;

@Entity
public class ResumeImg {   @Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name="resume_id")
private Long id;

}
