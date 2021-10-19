/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.db;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author vicmo
 */
public interface MutantdbRepository extends MongoRepository<Mutantdb, String> {
    
}
