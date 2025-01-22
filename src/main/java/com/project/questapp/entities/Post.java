package com.project.questapp.entities;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "post")
@Data

public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //dbde artarak gider
    Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //bir user silinirse tum postlarini sil demektir
    //@JsonIgnore
    //@JsonIgnore, bir sınıftaki bir özelliğin JSON serileştirme (serialization) ve/veya
    //JSON'dan nesneye dönüştürme (deserialization) işlemlerinde göz ardı edilmesini sağlar.
    USer user;

    String title;
    
    @Lob
    @Column(columnDefinition = "text")
    String text;

}
