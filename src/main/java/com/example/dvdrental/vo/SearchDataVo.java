package com.example.dvdrental.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchDataVo {

    private String search_target;
    private String search_keyword;

}
