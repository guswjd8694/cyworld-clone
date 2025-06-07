package com.cyworld.cyworld_clone.controller.board;

import com.cyworld.cyworld_clone.dao.GuestbookDao;
import com.cyworld.cyworld_clone.dto.GuestbookDto;
import com.cyworld.cyworld_clone.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class GuestbookController {

    @Autowired
    private GuestbookDao guestbookDao;

    @GetMapping("/guestbook")
    public String guestbook(
            @RequestParam(defaultValue = "1") int page,
            Model model
    ) {

        int pageSize = 5;
        List<GuestbookDto> guestbookPages = guestbookDao.findEntriesByPage(page, pageSize);
        System.out.println("guesbookPages.size() = " + guestbookPages.size());

        int totalCount = guestbookPages.size();
        int totalPages = (int) Math.ceil(totalCount / (double) pageSize);

        model.addAttribute("guestbookPages", guestbookPages);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("totalPages", totalPages);

        return "/board/guestbook";
    }

    @PostMapping("/guestbook")
    public String guestbookSubmit(@ModelAttribute("guestbook") GuestbookDto guestbook, HttpSession session) {
        UserDto loginUser = (UserDto) session.getAttribute("loginUser");
        guestbook.setUserId(loginUser.getId());

        System.out.println("loginUser = " + loginUser  );

        if (loginUser != null) {
            System.out.println("loginUserId = " + loginUser.getId());
            guestbook.setUserId(loginUser.getId());
            guestbook.setWriter(loginUser.getName());
        }
        else {
            System.out.println("로그인 안했음");
        }

        guestbookDao.insertGuestbookEntry(guestbook);
        return "redirect:/guestbook";
    }
}
