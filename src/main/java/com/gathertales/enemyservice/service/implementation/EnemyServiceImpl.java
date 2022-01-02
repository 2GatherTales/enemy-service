package com.gathertales.enemyservice.service.implementation;


import com.gathertales.enemyservice.model.enemy.Enemy;
import com.gathertales.enemyservice.repository.EnemyRepository;
import com.gathertales.enemyservice.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("enemyService")
public class EnemyServiceImpl implements GenericService<Enemy> {

    @Autowired
    private EnemyRepository enemyRepository;

    @Override
    public Iterable<Enemy> findAll() { return enemyRepository.findAll(); }

    @Override
    public Enemy find(Long id) { return (Enemy) enemyRepository.findById(id).get(); }

    @Override
    public Enemy create(Enemy enemy) {   return (Enemy) enemyRepository.save(enemy); }

    @Override
    public void update(Enemy enemy) { enemyRepository.save(enemy); }

    @Override
    public void delete(String id) { enemyRepository.delete(id); }
}
