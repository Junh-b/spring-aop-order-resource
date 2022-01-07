package net.junhabaek.aoporder.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter @Builder(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED) @AllArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "student")
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name = "name")
    private String name;

    public static Student newStudent(String name){
        return Student.builder()
                .name(name)
                .build();
    }
}
