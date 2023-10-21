package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.service.SearchSuitableUsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/needs")
public class SearchNeedController {

    private final SearchSuitableUsersService searchSuitableUsersService;

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findSuitableUsers(@PathVariable(name = "username") String username) {
        return searchSuitableUsersService.findSuitableUsers(username);
    }

}
