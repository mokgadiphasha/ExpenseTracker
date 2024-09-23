package com.example.ExpenseTracker.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(value="User")

public class User{
        @Id
        private Long id;
        private String username;
        private String password;
        private String email;

        public User(String username, String password, String email) {
                this.username = username;
                this.password = password;
                this.email = email;
        }

        public User(){}

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public @NotBlank String getPassword() {
                return password;
        }

        public void setPassword(@NotBlank String password) {
                this.password = password;
        }

        public @NotBlank String getUsername() {
                return username;
        }

        public void setUsername(@NotBlank String username) {
                this.username = username;
        }

        public @NotBlank @Email String getEmail() {
                return email;
        }

        public void setEmail(@NotBlank @Email String email) {
                this.email = email;
        }
}
