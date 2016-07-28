package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EnvironStatDaoImpl implements EnvironStatDao {
    @Autowired
    EnvironStatRepository environStatRepository;
    @Override
    public EnvironStat create(EnvironStat environStat) {
        return environStatRepository.save(environStat);
    }

    @Override
    public EnvironStat update(EnvironStat environStat) {
        return environStatRepository.save(environStat);
    }

    @Override
    public void delete(Long id) {
        environStatRepository.delete(id);
    }

    @Override
    public EnvironStat getEnvironStat(Long id) {
        return environStatRepository.getOne(id);
    }

    @Override
    public List<EnvironStat> list() {
        return environStatRepository.findAll();
    }

    @Override
    public List<EnvironStat> findByCreatedAtBetween(LocalDateTime start, LocalDateTime finish) {
        return environStatRepository.findByCreatedAtBetween(start, finish);
    }
}
