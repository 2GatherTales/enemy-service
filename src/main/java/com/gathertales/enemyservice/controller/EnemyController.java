package com.gathertales.enemyservice.controller;

import com.gathertales.enemyservice.model.customprincipal.CustomPrincipal;
import com.gathertales.enemyservice.model.enemy.Enemy;
import com.gathertales.enemyservice.service.implementation.EnemyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@RestController
@RequestMapping("api/enemy")
public class EnemyController {
    @Autowired
    private EnemyServiceImpl enemyRepository;

    public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";

    public static String now() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
        return sdf.format(cal.getTime());
    }

    @RequestMapping(value = "health",
            method = RequestMethod.GET,
            produces =  {MimeTypeUtils.APPLICATION_JSON_VALUE},
            headers = "Accept=application/json")
    public ResponseEntity<String> health() {
        try {
            UUID uuid = UUID.randomUUID();
            String response = "OK   " + now();
            return new ResponseEntity<String>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/findall")
    public ResponseEntity<Iterable<Enemy>> findAll() {

        try {
            CustomPrincipal principal = (CustomPrincipal) SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();
            if(!principal.getUsername().equals("admin"))
                return new ResponseEntity<Iterable<Enemy>>(
                        HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<Iterable<Enemy>>(
                    enemyRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Iterable<Enemy>>(
                    HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping(value = "create")
    public ResponseEntity<Enemy> create(@RequestBody Enemy enemy) {
        try {
            Enemy nenemy = enemyRepository.create(enemy);
            return new ResponseEntity<Enemy>(nenemy,
                    HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Enemy>(
                    HttpStatus.BAD_REQUEST);
        }
    }
}
