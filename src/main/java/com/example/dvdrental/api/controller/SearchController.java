package com.example.dvdrental.api.controller;

import com.example.dvdrental.vo.SearchDataVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/search")
public class SearchController {

    @GetMapping
    public String searchPage(Model model) {

        SearchDataVo searchData = new SearchDataVo();

        model.addAttribute("search_data", searchData);

        return "search";

    }

    @PostMapping
    public String doSearch(@ModelAttribute SearchDataVo searchDataVo, Model model) {

        System.out.println(searchDataVo.toString());

        model.addAttribute("search_target", searchDataVo.getSearch_target());
        model.addAttribute("search_keyword", searchDataVo.getSearch_keyword());

        return "result";
    }

}
