package org.example.board.Entity;


import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "board")
@Getter
public class Board extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "longtext")
    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Board() {
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
