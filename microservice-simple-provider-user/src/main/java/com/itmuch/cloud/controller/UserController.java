package com.itmuch.cloud.controller;

import com.itmuch.cloud.entity.User;
import com.itmuch.cloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by BaronGuo on 2019/2/16.
 */
@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        User user = this.userRepository.findOne(id);
        return user;
    }

    @GetMapping("/allUsers")
    public List<User> findAll(){
        List<User> usersList = new ArrayList<>();
        usersList = this.userRepository.findAll();
        usersList.sort(new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        return usersList;
    }

    @GetMapping("/save")
    public User saveAndFlush(User user){
        //user.setId(new Long("4"));
        user.setAge(25);
        user.setBalance(new BigDecimal(5000));
        user.setName("BARON");
        user.setUsername("bg1131");
        this.userRepository.saveAndFlush(user);
        return user;
    }


}
