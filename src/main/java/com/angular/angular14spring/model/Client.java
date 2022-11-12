package com.angular.angular14spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
@Entity

public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(length = 15, nullable = false)
    private String cpf;

    @Column(length = 200, nullable = false)
    private String email;

    @Column(length = 30, nullable = true)
    private int phone;

    @Column(length = 200, nullable = false)
    private String name;

}