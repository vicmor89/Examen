/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.db.Mutantdb;
import com.example.dto.MutantDto;
import com.example.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.service.IMutant;
import com.example.db.MutantdbRepository;
import com.example.db.MutantdbService;
import com.example.dto.Stats;

/**
 *
 * @author vicmo
 */
@RestController
@RequestMapping("/proyecto")
public class MutantController {
    @Autowired
    private IMutant mutant;
    @Autowired
    private MutantdbService mutantdbService;
    
    @PostMapping(value = "/mutant",produces = "application/json")    
    public ResponseEntity<Mensaje> validaMutante(@RequestBody MutantDto mutantDto){
        Mensaje mensaje;
        Mutantdb mutantdb;
        boolean mutantBoolean = mutant.isMutant(mutantDto.getDna().toArray(new String[0]));        
        mutantdbService.registrar(new Mutantdb(mutantBoolean,mutantDto.getDna()));
        if(mutantBoolean){
            mensaje =
            new Mensaje(
                HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), "Es Mutante", null, true);
        return new ResponseEntity<>(mensaje, HttpStatus.OK);
        }else{
            mensaje =
            new Mensaje(
                HttpStatus.FORBIDDEN.value(),
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                "No es mutante",
                null,
                false);                        
        return new ResponseEntity<>(mensaje, HttpStatus.FORBIDDEN);
        }             
    }
    @GetMapping("/stats")
    public ResponseEntity<Stats> estadisticas() {                
        return new ResponseEntity<>(mutantdbService.estadisitcas(), HttpStatus.OK);
    }
}
