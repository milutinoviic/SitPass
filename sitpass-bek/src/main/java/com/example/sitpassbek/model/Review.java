package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime createdAt;

    private Integer exerciseCount;

    private Boolean hidden;

    private boolean isDeleted;

    @ManyToOne
    private User user;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Rate rate;

    @ManyToOne
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Comment comment;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", exerciseCount=" + exerciseCount +
                ", hidden=" + hidden +
                ", isDeleted=" + isDeleted +
                ", user=" + (user != null ? user.getId() : null) +
                ", rate=" + (rate != null ? rate.getId() : null) +
                ", comment=" + (comment != null ? comment.getId() : null) +
                '}';
    }

}