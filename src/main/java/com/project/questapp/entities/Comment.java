package com.project.questapp.entities;

import java.sql.Date;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // bir user silinirse tum postlarini sil demektir
    @JsonIgnore
    // @JsonIgnore, bir sınıftaki bir özelliğin JSON serileştirme (serialization)
    // ve/veya
    // JSON'dan nesneye dönüştürme (deserialization) işlemlerinde göz ardı
    // edilmesini sağlar.
    Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    // bir user silinirse tum postlarini sil demektir
    @JsonIgnore
    // @JsonIgnore, bir sınıftaki bir özelliğin JSON serileştirme (serialization)
    // ve/veya
    // JSON'dan nesneye dönüştürme (deserialization) işlemlerinde göz ardı
    // edilmesini sağlar.
    USer user;

    @Lob
    // large oobject:buyuk bir veri saklanacak
    @Column(columnDefinition = "text")
    // Veritabanında bu özelliğin bir TEXT sütunu olacağını söyler.
    String text;

    @Temporal(TemporalType.TIMESTAMP)
    Date createDate;

}
