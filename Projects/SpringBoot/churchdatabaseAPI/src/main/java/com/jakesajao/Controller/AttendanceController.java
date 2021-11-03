package com.jakesajao.Controller;

import com.jakesajao.Model.*;
import com.jakesajao.Repository.AttendanceRepository;
import com.jakesajao.Repository.MemberRepository;
import com.jakesajao.Service.AttendanceServiceImpl;
import com.jakesajao.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class AttendanceController {
    @Autowired
    AttendanceRepository attendanceRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    AttendanceServiceImpl attendanceService;
    private int PERCENTAGE = 0;
    private AttendanceServiceImpl attendanceServiceImpl;
    private final int mark = 25;

    @ModelAttribute("chartFormDto")
    public ChartFormDto chartFormDto() {
        return new ChartFormDto();
    }
    public AttendanceController(AttendanceRepository attendanceRepository, AttendanceServiceImpl attendanceServiceImpl) {
        this.attendanceRepository = attendanceRepository;
        this.attendanceServiceImpl = attendanceServiceImpl;
    }
    @PostMapping("/search")
    public String GetMemberByMonthYear(@ModelAttribute @Valid ChartFormDto chartFormDto,
                                       BindingResult result,Model model){
        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        System.out.println("searchchart chartFormDto: " + chartFormDto);
        if (chartFormDto==null){
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("response", "Oops! No record.");
            return "/charts";
        }
        if (chartFormDto==null){
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("success", "Oops! No record.");
            return "/charts";
        }
        if (chartFormDto.getMonth().equals("Select the Month")) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("error", "Selected Month is invalid.");
            return "/charts";
        }
        else if (chartFormDto.getYear().equals("Select the Year")) {
            System.out.println("chartFormDto is null. ");
            model.addAttribute("memberAttendList3", null);
            model.addAttribute("error1", "Selected Year is invalid.");
            return "/charts";
        }

        List<MemberAttend> memberAttendList = attendanceRepository.FindMemberAttendanceByMonthYear(Integer.parseInt(chartFormDto.getMonth()),
                Integer.parseInt(chartFormDto.getYear()));

        System.out.println("FindMemberAttendanceByMonthYear: " + memberAttendList);
        List memberAttendList3 = attendanceServiceImpl.ProcessChart(mark,memberAttendList);
        System.out.println("searchchart memberAttendList3: " + memberAttendList3);
        model.addAttribute("success", "Attendance records generated successfully!");
        model.addAttribute("memberAttendList3", memberAttendList3);

        return "/charts";
    }


    //    @RequestParam(required = false) String title,
//    @RequestParam(defaultValue = "0") int page,
//    @RequestParam(defaultValue = "3") int size,
//    @RequestParam(defaultValue = "id,firstName") String[] sort
//    @GetMapping
//    public String attendance(
//            @RequestParam(value = "pageNumber", required = false, defaultValue = "1") int pageNumber,
//            @RequestParam(value = "size", required = false, defaultValue = "5") int size, Model model) {
//        model.addAttribute("posts", attendanceServiceImpl.getPage(pageNumber, size));
//        return "posts";
//    }
    @GetMapping("/attendance")
    public String updateMember(Model model) {

        List<MemberAttend> memberAttendList2 = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttendCurrentDate(currentDate);//To be displayed as a list to users
        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        memberAttendList.forEach(memberAttend -> {
            if (memberAttend.getPresent().equals("Yes")) {
                memberAttend.setPresent1(true);
            }
            else{
                memberAttend.setPresent1(false);
            }
            MemberAttend memberAttend1 = new MemberAttend(memberAttend.getId(),memberAttend.getTitle(), memberAttend.getFirstName(),
                    memberAttend.getLastName(),memberAttend.getPresent1(),memberAttend.isWeek1(),memberAttend.isWeek2(),
                    memberAttend.isWeek3(),memberAttend.isWeek4(), memberAttend.getPercentage(),memberAttend.getGender(),memberAttend.getCreatedDate());
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
            if (attend.getPresent1()==true) {
                attend.setPresent("Yes");
                System.out.println("Attend: " + attend);
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getPresent(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
            else {
                attend.setPresent("No");
                response[0] = attendanceServiceImpl.UpdateMemberAttendance_NewWeek(attend.getPresent(), attend.getCreatedDate(), attend.getId());
                System.out.println("Posted successfully id: " + attend.getId());
            }
        });
    model.addAttribute("response","Member updated successfully!");
        return "redirect:/attendance?success";

    }
    @GetMapping("/charts")
    public String getCharts(Model model){
        attendanceServiceImpl.SaveMemberAttendance_NewWeek();
        List<MemberAttend> memberAttendList = attendanceRepository.findMemberAttend();
        List memberAttendList3 = attendanceServiceImpl.ProcessChart(mark,memberAttendList);

        System.out.println("memberAttendList3 List: " + memberAttendList3);
        model.addAttribute("memberAttendList3", memberAttendList3);

        return "/charts";
    }


}
