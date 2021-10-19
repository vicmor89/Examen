/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.impl;

import org.springframework.stereotype.Service;
import com.example.service.IMutant;

/**
 *
 * @author vicmo
 */
@Service
public class Mutant implements IMutant {

    
    @Override
    public String holaMundo() {
        return "Hola Mundo";
    }

     //Variables de trabajo
    boolean    returnCode;
    String[]   entrada;
    char a,b,c,d;
    //Metodo principal
    @Override
    public boolean isMutant(String[] dna){
        returnCode=true;
        entrada=dna;
        inicio();
        if (returnCode){
            proceso();            
        }
        /*if (returnCode){
            fin();          
        } */       
        return returnCode;
    }
    //Metodos auxiliares
    //Metodos auxiliares de validaciones datos de entrada 
    private void inicio() {
        validarDimension();
    }
    //Metodos auxiliares de procesamiento de datos
    private void proceso() {
        validaAdn();
    }
    //Metodos auxiliares de cierre y cifras de control.
    /*private void fin() {        
    }*/
    //Metodo Auxiliar que valida el dato de entrada sea el permitido y las dimensiones para dar paso al proceso
    private void validarDimension() {
        int longitud= entrada.length;
        if(longitud<4){
            returnCode=false;              
        }else{
            for(int i=0;i<longitud;i++){
                if (entrada[i].matches(".*[^ATCG].*")) {
                    returnCode=false;
                    break;
                }
                if(longitud != entrada[i].length()){
                    returnCode=false;
                    break;
                }          
            }
        }        
    }
//Metodo Auxiliar que recorre la matriz en los 3 sentidos y envia al metodo evaluar
    private void validaAdn() {
        int longitud= entrada.length;
        returnCode=false;
        for (int i=0;i < longitud;i++){
            for (int j=0;j < longitud;j++){
                //Valida sentido Horizontal
                if(j < (longitud - 3)){
                    a=entrada[i].charAt(j);
                    b=entrada[i].charAt(j+1);
                    c=entrada[i].charAt(j+2);
                    d=entrada[i].charAt(j+3); 
                    evaluar(a,b,c,d);
                }
                if(i < (longitud - 3) && !returnCode){               
                    //Valida sentido Vertical
                    a=entrada[i].charAt(j);
                    b=entrada[i+1].charAt(j);
                    c=entrada[i+2].charAt(j);
                    d=entrada[i+3].charAt(j);  
                    evaluar(a,b,c,d);                    
                }
                if (i < (longitud - 3) && j < (longitud - 3) && !returnCode){
                    //Valida sentido Diagonal
                    a=entrada[i].charAt(j);
                    b=entrada[i+1].charAt(j+1);
                    c=entrada[i+2].charAt(j+2);
                    d=entrada[i+3].charAt(j+3); 
                    evaluar(a,b,c,d);
                }                               
                if (returnCode){
                    break;
                }
            }
            if (returnCode){
                break;
            }
        }              
    }
//Metodo Auxiliar para evaluar si los 4 caracteres son iguales
    private void evaluar(char a,char b, char c, char d) {
        if(a==b && a==c && a==d){            
            returnCode=true;
        }
    }
    
}
