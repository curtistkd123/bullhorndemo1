package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    MessagesRepository messagesRepository;

    @RequestMapping("/")
    public String messageList (Model model) {
        model.addAttribute("messages", messagesRepository.findAll());
        return "list";
    }

    @PostMapping("/process")
    public String processform(@Valid Messages messages, BindingResult result) {
        if (result.hasErrors()){
            return "messageform";
        }
        messagesRepository.save(messages);
        return "redirect:/";
    }

    @GetMapping("/add")
    public String messageform(Model model) {
        model.addAttribute("messages", new Messages());
        return "messageform";
    }
}
