package com.gathertales.enemyservice.repository;

import com.gathertales.enemyservice.model.enemy.Enemy;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnemyRepository<T> extends JpaRepository<Enemy, Long> {
}
