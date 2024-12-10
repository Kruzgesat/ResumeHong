package inhatc.cse.resumehong.resume.controller;

import inhatc.cse.resumehong.member.dto.MemberDto;
import inhatc.cse.resumehong.member.entity.Member;
import inhatc.cse.resumehong.member.service.MemberService;
import inhatc.cse.resumehong.resume.dto.ResumeFormDto;
import inhatc.cse.resumehong.resume.entity.Resume;
import inhatc.cse.resumehong.resume.service.ResumeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/resume")
public class ResumeController {
    private final ResumeService resumeService;
    private final MemberService memberService;

    @GetMapping("/list")
    public String myResume(Model model) {
        model.addAttribute("resumeList",resumeService.getResumeList());
        return "resume/list_resume";
    }

    @GetMapping("/list/{resumeId}")
    public String getResumeList(@PathVariable Long resumeId , Model model) {
        List<Resume> resumeList = resumeService.getResumesByMember(resumeId);
        model.addAttribute("resumeDetail", resumeList);
        return "resume/detail_resume";
    }

    @GetMapping("/list/add")
    public String resumeForm(Model model, @AuthenticationPrincipal User user) {
        Member member = memberService.getUserByUsername(user.getUsername());
        model.addAttribute("userDetail",member);
        if (!model.containsAttribute("resumeFormDto")) {
            ResumeFormDto resumeFormDto = new ResumeFormDto();
            resumeFormDto.setMemberId(member.getId());
            model.addAttribute("resumeFormDto", resumeFormDto);
        }

        return "resume/add_resume";
    }

    @PostMapping("/list/add")
    public String addResume(@Valid ResumeFormDto resumeFormDto, BindingResult bindingResult, Model model , RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("resumeFormDto", resumeFormDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.resumeFormDto", bindingResult);
            return "redirect:/resume/list/add";
        }
        resumeService.save(  Resume.builder()
                .member(Member.builder().id(resumeFormDto.getMemberId()).build())
                .resumeName(resumeFormDto.getResumeName())
                .finalEducation(resumeFormDto.getFinalEducation())
                .major(resumeFormDto.getMajor())
                .career(resumeFormDto.getCareer())
                .skill(resumeFormDto.getSkill())
                .certificate(resumeFormDto.getCertificate())
                .language(resumeFormDto.getLanguage())
                .introduction(resumeFormDto.getIntroduction())
                .build());

        return "redirect:/resume/list";
    }

    @GetMapping("/list/{resumeId}/delete")
    public String deleteResume(@PathVariable Long resumeId) {
        Resume resume = resumeService.getResumeById(resumeId);

        resumeService.resumeDelete(resume);
        return "redirect:/resume/list";
    }
}

