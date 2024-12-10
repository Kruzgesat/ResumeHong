package inhatc.cse.resumehong.resume.repository;

import inhatc.cse.resumehong.resume.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ResumeRepository extends JpaRepository<Resume, Long> {
    @Query("SELECT r FROM Resume r JOIN FETCH r.member WHERE r.member.id = :memberId")
    List<Resume> findByMemberId(Long memberId);  // 특정 memberId로 이력서 조회
}
