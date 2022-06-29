package com.example.security.user;

import com.example.security.configuration.AppUser;
import com.example.security.configuration.LoggedInUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/api/1.0/users")
    public User1 createUser(@RequestBody User1 user){
        return this.userService.save(user);

    }
    @PutMapping("/api/1.0/user/{id}")
    @PreAuthorize("@userAuthorizationService.canUpdate(principal.user.id, #id) or hasRole('ROLE_admin')")  // boolean
    public User1 updateUser(@PathVariable long id,
                            @RequestBody User1 user){

        return this.userService.updateUser(id, user);
    }
}
