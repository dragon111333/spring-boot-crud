package com.testsdemo.testcrud.models.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

import java.time.LocalDateTime;
//--------------------SQL---------------------------
//CREATE VIEW vw_user_exp AS
//SELECT
//    u.id AS user_id,
//    u.name AS user_name,
//    u.lastname AS user_lastname,
//    e.id AS exp_id,
//    e.name AS exp_name,
//    e.start_at AS exp_start_at,
//    e.end_at AS exp_end_at
//FROM
//        (SELECT * FROM user) as u
//INNER JOIN
//        (SELECT * FROM exp_info) as e
//ON u.id = e.user_id;

@Entity
@Immutable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vw_user_exp")
public class ViewUserExpInfo {
    @Id
    private Integer exp_id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "user_lastname")
    private String userLastname;

    @Column(name = "exp_name")
    private String expName;

    @Column(name = "exp_start_at")
    private LocalDateTime expStartAt;

    @Column(name = "exp_end_at")
    private LocalDateTime expEndAt;


}
