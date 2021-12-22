package com.gathertales.enemyservice.model.enemy;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.Data;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name="enemy", schema = "avarum_game")

@TypeDef(name = "hstore", typeClass = PostgreSQLHStoreType.class)
@Data
public class Enemy implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "type")
    private String type;

    @Type(type = "hstore")
    @Column(columnDefinition = "ability_scores")
    private Map<String, String> abilityScores = new HashMap<>();

    @Type(type = "hstore")
    @Column(name = "stats")
    //Always an array of size 2
    private Map<String, String> stats = new HashMap<>();

    @Column(columnDefinition = "pattern_length")
    private Integer patternLength;


}
