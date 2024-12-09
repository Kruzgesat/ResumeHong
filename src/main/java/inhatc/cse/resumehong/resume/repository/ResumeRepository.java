package inhatc.cse.resumehong.resume.repository;

import inhatc.cse.resumehong.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}
