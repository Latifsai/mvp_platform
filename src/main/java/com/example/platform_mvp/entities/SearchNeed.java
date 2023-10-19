package com.example.platform_mvp.entities;

import com.example.platform_mvp.entities.enums.Reputation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "search_needs")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SearchNeed {

    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * for exapmnple software, cosnetin, medizin
     * that will be written onyly with "," betwwen tables
     */
    @Column(name = "search_labels")
    private String searchLabels;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "reputation")
    @Enumerated(EnumType.STRING)
    private Reputation reputation;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchNeed that = (SearchNeed) o;
        return Objects.equals(id, that.id) && Objects.equals(searchLabels, that.searchLabels) && Objects.equals(price, that.price) && Objects.equals(experience, that.experience) && reputation == that.reputation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, searchLabels, price, experience, reputation);
    }

    @Override
    public String toString() {
        return "SearchNeed{" +
                "id=" + id +
                ", searchLabels='" + searchLabels + '\'' +
                ", price=" + price +
                ", experience=" + experience +
                ", reputation=" + reputation +
                '}';
    }
}
