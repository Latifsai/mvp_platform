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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "service_title")
    private String serviceTitle;

    @Column(name = "max_price")
    private BigDecimal maxPrice;

    @Column(name = "min_price")
    private BigDecimal minPrice;

    @Column(name = "type_of_service")
    @Enumerated(EnumType.STRING)
    private TypeOfService typeOfService;
}
