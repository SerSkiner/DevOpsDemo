package com.ua.DevOpsDemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ua.DevOpsDemo.model.Skill;
import com.ua.DevOpsDemo.model.SkillForm;




@Controller
public class MainController {
    private static List<Skill> skills = new ArrayList<Skill>();

     static {
         skills.add(new Skill("Java", 1));
         skills.add(new Skill("Linux", 5));
         skills.add(new Skill("Docker", 2));
         }

    @Value("${welcome.message}")
    private String message;

    @Value("${error.message}")
    private String errorMessage;

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
    public String index(Model model) {

        model.addAttribute("message", message);

        return "index";
    }

    @RequestMapping(value = { "/skillList" }, method = RequestMethod.GET)
    public String skillList(Model model) {

        model.addAttribute("skills", skills);

        return "skillList";
    }

    @RequestMapping(value = { "/addSkill" }, method = RequestMethod.GET)
    public String showAddSkillPage(Model model) {

        SkillForm skillForm = new SkillForm();
        model.addAttribute("skillForm", skillForm);

        return "addSkill";
    }

    @RequestMapping(value = { "/addSkill" }, method = RequestMethod.POST)
    public String saveSkill(Model model, //
                             @ModelAttribute("skillForm") SkillForm skillForm) {

        String name = skillForm.getName();
        int expirience = skillForm.getExperience();


            Skill newSkill = new Skill(name, expirience);
            skills.add(newSkill);

            return "redirect:/skillList";
        }

        //.model.addAttribute("errorMessage", errorMessage);
        //return "addSkill";
    }

