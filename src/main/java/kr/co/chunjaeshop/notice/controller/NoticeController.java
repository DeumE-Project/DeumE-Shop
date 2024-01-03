package kr.co.chunjaeshop.notice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
@RequiredArgsConstructor
@Log4j2
public class NoticeController {

    // 남원우


    // 최경락


    // 이무현
    @GetMapping("/save")
    public String saveForm(){
        return "noticeSaveForm";
    }

    // 유지호


    // 변재혁

}
