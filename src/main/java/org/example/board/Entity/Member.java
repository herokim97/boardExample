package org.example.board.Entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "member")
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String password;

    private Integer age;

    public Member() {
    }

    public Member(String userName, String password, Integer age) {
        this.userName = userName;
        this.password = password;
        this.age = age;
    }

    public void updatePassword(String password) {
        this.password = password;
    }
}
