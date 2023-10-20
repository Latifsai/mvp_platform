package com.example.platform_mvp.entities;

import com.example.platform_mvp.entities.enums.Reputation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "surname", nullable = false)
    private String surname;

    @Column(name = "firma_title", nullable = false)
    private String firmaTitle;

    @Column(name = "experience", nullable = false)
    private Integer experience;

    @Column(name = "information_about_user", nullable = false)
    private String informationAboutUser;

    @Column(name = "credits", nullable = false)
    private Integer credits;

    @Column(name = "reputation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Reputation reputation;

    @OneToOne(mappedBy = "user", fetch = FetchType.EAGER, cascade = {REFRESH, PERSIST, REMOVE})
    private SearchNeed searchNeed;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", password='" + password + '\'' +
                ", surname='" + surname + '\'' +
                ", firmaTitle='" + firmaTitle + '\'' +
                ", experience=" + experience +
                ", informationAboutUser='" + informationAboutUser + '\'' +
                ", credits=" + credits +
                ", reputation=" + reputation +
                ", searchNeed=" + searchNeed +
                ", services=" + services +
                '}';
    }

    @OneToMany(mappedBy = "user", cascade = {PERSIST, REMOVE, REFRESH}, fetch = FetchType.EAGER)
    private List<Service> services;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username)
                && Objects.equals(firstName, user.firstName)
                && Objects.equals(password, user.password)
                && Objects.equals(surname, user.surname)
                && Objects.equals(firmaTitle, user.firmaTitle)
                && Objects.equals(experience, user.experience)
                && Objects.equals(informationAboutUser, user.informationAboutUser)
                && Objects.equals(credits, user.credits)
                && reputation == user.reputation
                && Objects.equals(searchNeed, user.searchNeed)
                && Objects.equals(services, user.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firstName, password, surname, firmaTitle, experience, informationAboutUser,
                credits, reputation, searchNeed, services);
    }
}
