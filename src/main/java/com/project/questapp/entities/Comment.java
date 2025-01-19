package com.project.questapp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "comment")
@Data

public class Comment {
    @Id
    Long id;

    Long postId;
    Long userId;

    @Lob
    //large oobject:buyuk bir veri saklanacak
    @Column(columnDefinition = "text")
    //Veritabanında bu özelliğin bir TEXT sütunu olacağını söyler.
    String text;

}
