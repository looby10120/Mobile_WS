package com.androiddev.mobile_ws.ui.controller;

import com.androiddev.mobile_ws.model.request.UpdateUserDetailsRequestModel;
import com.androiddev.mobile_ws.model.request.UserDetailsRequestModel;
import com.androiddev.mobile_ws.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    HashMap<String, UserRest> users;

    // require = false (optional param) can't use with primitive data type such int
    @GetMapping()
    public String getUsers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "50") int limit,
            @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
        // if not use defaultValue should have check condition to set value or do something
        return String.format("get all user with page %d and limit is %d and sort with %s", page, limit, sort);
    }

    @GetMapping(path = "/{userId}", produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

        if (users.containsKey(userId)) {
            return new ResponseEntity<>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetail) {

        UserRest result = new UserRest();

        result.setFirstName(userDetail.getFirstName());
        result.setLastName(userDetail.getLastName());
        result.setEmail(userDetail.getEmail());
        result.setUserId(userDetail.getPassword());

        String userId = UUID.randomUUID().toString();
        result.setUserId(userId);

        if (users == null) users = new HashMap<>();
        users.put(userId, result);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}", consumes = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    }, produces = {
            MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE
    })
    public UserRest updateUser(@Valid @RequestBody UpdateUserDetailsRequestModel userDetail, @PathVariable String userId) {

        UserRest storeData = users.get(userId);
        storeData.setFirstName(userDetail.getFirstName());
        storeData.setLastName(userDetail.getLastName());
        users.put(userId, storeData);

        return storeData;
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable String id) {
        users.remove(id);
        return new ResponseEntity<String>(String.format("%s has been deleted", id), HttpStatus.OK);
    }
}
