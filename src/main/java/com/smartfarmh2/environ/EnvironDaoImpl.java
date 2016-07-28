package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class EnvironDaoImpl implements EnvironDao {
    @Autowired
    EnvironRepository environRepository;

    @Override
    public Environ create(Environ environ) {
        return environRepository.save(environ);
    }

    @Override
    public Environ update(Environ environ) {
        return environRepository.save(environ);
    }

    @Override
    public void delete(Long id) {
        environRepository.delete(id);
    }

    @Override
    public Environ getEnviron(Long id) {
        return environRepository.findOne(id);
    }

    @Override
    public List<Environ> list() {
        return environRepository.findAll();
    }

    @Override
    public List<Environ> findByCreatedDateBetween(LocalDateTime start, LocalDateTime finish) {
        return environRepository.findByCreatedDateBetween(start, finish);
    }
}
