package com.mobile.controller;

import com.mobile.dao.MobileDAO;
import com.mobile.model.Mobile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MobileController {

    @Autowired
    private MobileDAO mobileDAO;

    @PostMapping("/add-mobile")
    public ResponseEntity<MobileDAO.MobileResponse> addMobile(@RequestHeader String modelName, @RequestHeader int version, @RequestHeader int ramCode, @RequestHeader int displayCode) {
        Mobile mobile = Mobile.builder().displayCode(displayCode).modelName(modelName).version(version).ramCode(ramCode).build();
        return ResponseEntity.status(HttpStatus.CREATED).body(mobileDAO.addMobile(mobile));
    }

    @GetMapping("/get-all")
    public ResponseEntity<MobileDAO.MobileResponse> getAllMobiles() {
        return ResponseEntity.ok(mobileDAO.getAll());
    }

    @GetMapping("/filter")
    public ResponseEntity<MobileDAO.MobileResponse> filter(@RequestHeader String modelName, @RequestHeader int version, @RequestHeader int ramSize) {
        return ResponseEntity.ok(mobileDAO.filter(modelName, version, ramSize));
    }

    @GetMapping("/clear")
    public ResponseEntity<MobileDAO.MobileResponse> clear() {
        return ResponseEntity.ok(mobileDAO.clear());
    }
}