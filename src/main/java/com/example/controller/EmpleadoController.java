/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.controller;

import com.example.dto.EmpleadoDto;
import com.example.service.IEmpleados;
import com.example.util.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author vicmo
 */
@RestController
@RequestMapping("/proyecto")
public class EmpleadoController {
    @Autowired
    private IEmpleados empleado;
    
    @GetMapping(value = "/saludar",produces = "application/json")
    public String holaMundo(){
        String respuesta = null;
        try{
            respuesta = empleado.holaMundo();
            System.out.println("llego");
        }catch(Exception e){
            System.out.println("Error " + e);
        }
        return respuesta;
    }
    
    @PostMapping(value = "/mutant",produces = "application/json")    
    public ResponseEntity<Mensaje> validaMutante(@RequestBody EmpleadoDto EmpleadoDto){
        Mensaje mensaje;
        System.out.println("entra");
        boolean mutant = empleado.isMutant(EmpleadoDto.getDna().toArray(new String[0]));
        if(mutant){
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
}
