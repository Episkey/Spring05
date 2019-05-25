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

    @PutMapping("/api/school/{id}")
    public School update(
            @PathVariable int id,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer capacity,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String graduate

    ) {
        School schools = SchoolRepository.selectById(id);
        SchoolRepository.update(
                id,
                name != null ? name : schools.getName(),
                capacity != null ? capacity : schools.getCapacity(),
                country != null ? country : schools.getCountry(),
                graduate != null ? graduate : schools.getGraduate()

        );
        return SchoolRepository.selectById(id);
    }
}