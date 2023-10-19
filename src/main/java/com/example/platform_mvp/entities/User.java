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
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "username")
    private String username;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @Column(name = "surname")
    private String surname;

    @Column(name = "firma_title")
    private String firmaTitle;

    @Column(name = "experience")
    private Integer experience;

    @Column(name = "information_about_user")
    private String informationAboutUser;

    @Column(name = "credits")
    private Integer credits;

    @Column(name = "reputation")
    @Enumerated(EnumType.STRING)
    private Reputation reputation;

    @Column(name = "services")
    @ManyToMany(cascade = {PERSIST, PERSIST, REMOVE}, fetch = FetchType.LAZY)
    private List<Service> services;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", firmaTitle='" + firmaTitle + '\'' +
                ", experience=" + experience +
                ", informationAboutUser='" + informationAboutUser + '\'' +
                ", credits=" + credits +
                ", reputation=" + reputation +
                ", services=" + services +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(username, user.username) && Objects.equals(firmaTitle, user.firmaTitle) && Objects.equals(experience, user.experience) && Objects.equals(informationAboutUser, user.informationAboutUser) && Objects.equals(credits, user.credits) && Objects.equals(services, user.services);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, firmaTitle, experience, informationAboutUser, credits, services);
    }
}
