package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.entity.User;
import com.example.service.UserService;

@Controller
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/getPageUserAdd", method = RequestMethod.GET)
    private ModelAndView getPageUserAdd() {
        ModelAndView view = new ModelAndView("addUser");
        view.addObject("user", new User());
        return view;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users/list";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users/list";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView getAll() {
        ModelAndView view = new ModelAndView("homePage");
        List<User> data = userService.list();
        view.addObject("datas", data);
        return view;
    }

    @RequestMapping(value = "/userDelete/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.removeUser(id);
        return "redirect:/users/list";
    }

    @RequestMapping("/edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", this.userService.getUser(id));
        return "editUser";
    }
}
