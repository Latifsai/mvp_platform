package com.example.platform_mvp.entities;

import com.example.platform_mvp.entities.enums.TypeOfService;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "services")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Getter
@Setter
public class Service {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_title", nullable = false)
    private String serviceTitle;

    @Column(name = "max_price", nullable = false)
    private BigDecimal maxPrice;

    @Column(name = "min_price", nullable = false)
    private BigDecimal minPrice;

    @Column(name = "type_of_service", nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfService typeOfService;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.REMOVE, CascadeType.REFRESH})
    @JoinColumn(columnDefinition = "user_id", referencedColumnName = "id")
    private User user;

}
