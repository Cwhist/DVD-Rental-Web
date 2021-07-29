package com.example.dvdrental.api.controller;

import com.example.dvdrental.entity.Actor;
import com.example.dvdrental.service.ActorService;
import com.example.dvdrental.vo.SearchDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        model.addAttribute("search_target", searchDataVo.getSearch_target());
        model.addAttribute("search_keyword", searchDataVo.getSearch_keyword());

        if(searchDataVo.getSearch_target().equals("actor"))
            return "actor_result";
        else
            return "film_result";
    }

    @GetMapping(path = "/actor-film/{id}")
    public String searchFilmByActorId(@PathVariable("id") int id, Model model) {

        model.addAttribute("actor_id", id);

        return "actor_film_result";
    }

}
