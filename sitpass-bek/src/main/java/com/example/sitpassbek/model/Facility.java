package com.example.sitpassbek.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate createdAt;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    private Double totalRating;

    private Boolean active;

    private boolean isDeleted;

    @OneToMany(mappedBy = "facility")
    private List<Exercise> exercises;

    @OneToMany(mappedBy = "facility")
    private List<Image> images;

    @OneToMany(mappedBy = "facility")
    private List<Description> descriptions;

    @ManyToMany
    @JoinTable(
            name = "facility_discipline",
            joinColumns = @JoinColumn(name = "facility_id"),
            inverseJoinColumns = @JoinColumn(name = "discipline_id")
    )
    private List<Discipline> disciplines;

    @OneToMany(mappedBy = "facility")
    private List<WorkDay> workDays;

    @OneToMany(mappedBy = "facility")
    private List<Manages> manages;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews;

    @OneToMany(mappedBy = "facility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Description> descriptionFile;

}
