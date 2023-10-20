package com.example.platform_mvp.controller;

import com.example.platform_mvp.dto.searchNeed.AddNeedsRequest;
import com.example.platform_mvp.dto.searchNeed.SearchNeedsResponse;
import com.example.platform_mvp.dto.searchNeed.UpdateNeedsRequest;
import com.example.platform_mvp.dto.user.UserResponseForUsers;
import com.example.platform_mvp.service.SearchSuitableUsersService;
import com.example.platform_mvp.service.imp.SearchNeedServiceImp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/needs")
public class SearchNeedController {

    private final SearchNeedServiceImp needService;

    private final SearchSuitableUsersService searchSuitableUsersService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SearchNeedsResponse add(@RequestBody AddNeedsRequest request) {
        return needService.addNeedsToUser(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SearchNeedsResponse update(@RequestBody UpdateNeedsRequest request) {
        return needService.updateNeeds(request);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public List<UserResponseForUsers> findSuitableUsers() {
        return searchSuitableUsersService.findSuitableUsers();
    }

}
