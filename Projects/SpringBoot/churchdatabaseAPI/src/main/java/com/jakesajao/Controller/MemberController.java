package com.jakesajao.Controller;


import com.jakesajao.Model.Attendance;
import com.jakesajao.Model.Member;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.AttendanceService;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.Service.MemberService;
import com.jakesajao.Service.UserService;
import com.jakesajao.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Controller
public class MemberController {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private MemberService memberService;
    private AttendanceServiceImpl attendanceServiceImpl;

    public MemberController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }
    @GetMapping("/add")
    public String addMember(Model model) {
        return "add";
    }

    @GetMapping("/attendance")
    public String updateMember(Model model) {
        List<MemberAttend> memberAttendList2 = new ArrayList<>();
       List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();//To be displayed as a list to users

        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getStatus().equals("Yes")) {
                memberAttend.setPresent(true);
            }
            else{
                memberAttend.setPresent(false);
            }
                MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(), memberAttend.getFirstName(),
                        memberAttend.getLastName(), memberAttend.getPresent(), memberAttend.getGender(),memberAttend.getCreatedDate());
                memberAttendList2.add(memberAttend1);

        });

        MemberAttendDto attendDto = new MemberAttendDto();
        memberAttendList2.forEach(memberAttend -> {
            attendDto.addMemberAttend(memberAttend);
        });

        System.out.println("attendDto List: " + attendDto);
        model.addAttribute("form", attendDto);

        return "attendance";
    }
    @PostMapping("/attendance")
    public String postAttendance(@ModelAttribute MemberAttendDto form,Model model){

        System.out.println("form: "+form);
        List<MemberAttend> attendList = form.getAttends();
        final int[] response = new int[1];
        attendList.forEach(attend->{
            if (attend.getPresent()==true) {
                attend.setStatus("Yes");
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getStatus(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
            else {
                attend.setStatus("No");
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getStatus(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
        });
        
        return "redirect:/attendance?success";

    }
    @PostMapping("/add")
    public String addNewMember(@ModelAttribute("memberAttend") @Valid MemberCreationDto memberDto,
                               BindingResult result) {
        System.out.println("Entry member");
        Member existing = memberService.findByMobilePhone1(memberDto.getMobilephone1());
        if (existing != null) {
            System.out.println("existing  null: "+existing);
            result.rejectValue("mobilephone1", null, "There is already a member created with that mobile phone.");
        }
        if (result.hasErrors()) {
            System.out.println("result.hasErrors(): "+result.toString());
            return "add";
        }
        memberService.save(memberDto);

        return "redirect:/add?success";

    }



}
