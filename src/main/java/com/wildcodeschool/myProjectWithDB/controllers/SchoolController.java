package com.wildcodeschool.myProjectWithDB.controllers;

import com.wildcodeschool.myProjectWithDB.entities.School;
import com.wildcodeschool.myProjectWithDB.repositories.SchoolRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@Controller
@ResponseBody
public class SchoolController {

    @GetMapping("/api/school")
    public List<School> getSchools(@RequestParam(defaultValue = "%") String countries) {
        return SchoolRepository.selectByName(countries);
    }

    @DeleteMapping("/api/school/{id}")
    public void delete(@PathVariable int id) {
        SchoolRepository.delete(id);
    }
}