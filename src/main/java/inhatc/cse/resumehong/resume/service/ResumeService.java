package inhatc.cse.resumehong.resume.service;

import inhatc.cse.resumehong.resume.entity.Resume;
import inhatc.cse.resumehong.resume.repository.ResumeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ResumeService {
    private final ResumeRepository resumeRepository;

    // 특정 member_id로 이력서 조회
    public List<Resume> getResumesByMember(Long memberId) {
        return resumeRepository.findByMemberId(memberId);
    }

    public List<Resume> getResumeList(){
        return resumeRepository.findAll();
    }

    public void save(Resume resume){
        resumeRepository.save(resume);
    }
    public Resume getResumeById(Long resumeId){
        return resumeRepository.findById(resumeId).orElseThrow(() -> new RuntimeException("이력서가 존재하지 않습니다."));
    }
    public void resumeDelete(Resume resume){
        resumeRepository.deleteAll();
    }

}
