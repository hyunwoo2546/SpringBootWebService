package com.hyunwoo.book.web;

import com.hyunwoo.book.config.auth.LoginUser;
import com.hyunwoo.book.config.auth.dto.SessionUser;
import com.hyunwoo.book.service.posts.PostsService;
import com.hyunwoo.book.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    /*# 전체 페이지 목록*/
    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());

        if(user != null) {
            model.addAttribute("userName", user.getName());
        }

        return "index";
    }

    /*# 글 등록*/
    @GetMapping("/posts/save")
    public String postsSave() {

        return "posts-save";
    }

    /*# 글 수정*/
    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {

        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}
