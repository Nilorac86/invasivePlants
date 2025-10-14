package com.carolin.invasiveplants.Entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        private Long userId;

        @Column(name = "first-name", length = 100)
        private String firstName;

        @Column(name = "last-name", length = 100)
        private String lastName;

        @Column(length = 255)
        private String email;

        @Column(length = 255)
        private String password;

        @Column(name = "phonenumber", length = 50)
        private String phoneNumber;

        @Column(name = "points")
        private Integer points;

        @Column(name = "rating")
        private Integer rating;

        @ManyToOne
        @JoinColumn(name = "role_id")
        private Role role;

        // Relationship to rewards
        @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
        private Set<UserReward> userRewards = new HashSet<>();

        // Constructors
        public User() {
        }

        // Getters and Setters
        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Integer getPoints() {
            return points;
        }

        public void setPoints(Integer points) {
            this.points = points;
        }

        public Set<UserReward> getUserRewards() {
            return userRewards;
        }

        public void setUserRewards(Set<UserReward> userRewards) {
            this.userRewards = userRewards;
        }
    }
