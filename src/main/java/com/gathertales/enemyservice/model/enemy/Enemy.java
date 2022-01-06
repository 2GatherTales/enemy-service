package com.gathertales.enemyservice.model.enemy;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLHStoreType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;

enum EnemyStat {
    BIGTHUG(new Integer[]{116, 120}, new Integer[]{4, 8}, new Integer[]{4, 8}),
    ROGUE(new Integer[]{18, 112}, new Integer[]{2, 6}, new Integer[]{12, 16}),
    BEAR(new Integer[]{120, 124}, new Integer[]{6, 10}, new Integer[]{1, 4}),
    BOSS(new Integer[]{120, 124}, new Integer[]{6, 10}, new Integer[]{10, 14}); // semicolon needed when fields / methods follow

    private final Integer[] hp, str, spd;

    EnemyStat(Integer[] hp, Integer[] str, Integer[] spd) {
        this.hp = hp;
        this.str = str;
        this.spd = spd;
    }

    public Integer[] getHp() {  return hp;  }
    public Integer[] getStr() { return str; }
    public Integer[] getSpd() { return spd; }
}

enum EnemyType {
    BIGTHUG(2.00, 4.00, 8.00),
    ROGUE(10.00, 4.00, 1.00),
    BEAR(1.00, 1.00, 6.00),
    BOSS(4.00, 0.00, 4.00); // semicolon needed when fields / methods follow

    private final Double fast, dodge, speed;

    EnemyType(Double fast, Double dodge, Double speed) {
        this.fast = fast;
        this.dodge = dodge;
        this.speed = speed;
    }

    public Double getFast() {   return fast; }
    public Double getDodge() {  return dodge; }
    public Double getSpeed() {  return speed; }
}

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
    @Column(name = "str")
    private Integer str;
    @Column(name = "spd")
    private Integer spd;
    @Column(name = "hp")
    private Integer hp;
    @Column(name = "dmg")
    private final Integer dmg = 20;
    @Column(name = "maxhp")
    private Integer maxHP;
    @Column(name = "dead", columnDefinition = "int2")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean dead;
    @Column(columnDefinition = "pattern_sequence")
    private String patternSequence = "SSDSSDD";
    private transient Integer patternLength = 7;
    @Getter(value= AccessLevel.PRIVATE)
    @Setter(value=AccessLevel.PRIVATE)
    private transient  EnemyStat enemyStat;
    @Getter(value=AccessLevel.PRIVATE)
    @Setter(value=AccessLevel.PRIVATE)
    private transient  EnemyType enemyType;

    public Enemy() {
        this.dead = false;
        this.type = genType();
        genAbilityScores(this.type);
    }

    private Integer randomize(int max){
        int range = (max-1);
        int index = (int)(Math.random() * range);
        return index;
    }

    private Integer randomize(Integer min, Integer max) {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

    private String genType(){
         int index = randomize(enemyType.values().length);
        return enemyType.values()[index].name();
    }
    private Integer generateHP(EnemyStat enemyStat){
        return randomize(enemyStat.getHp()[0], enemyStat.getHp()[1]);
    }

    private Integer generateSpd(EnemyStat enemyStat){
        return randomize(enemyStat.getHp()[0], enemyStat.getHp()[1]);
    }
    private Integer generateStr(EnemyStat enemyStat){
        return randomize(enemyStat.getHp()[0], enemyStat.getHp()[1]);
    }

    private void genAbilityScores(String type){
        EnemyStat enemyStat = EnemyStat.valueOf(type);
        this.maxHP = generateHP(enemyStat);
        this.hp = this.maxHP;
        this.spd = generateSpd(enemyStat);
        this.str = generateStr(enemyStat);
    }

    public void calcGetAttacked(Integer dmg){
        this.hp = (this.hp-dmg);
        isEnemyDead();
    }

    private void isEnemyDead(){
        if(this.hp<=0)
        this.dead = true;
    }
}