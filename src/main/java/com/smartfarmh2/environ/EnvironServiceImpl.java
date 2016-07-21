package com.smartfarmh2.environ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnvironServiceImpl implements EnvironService {
    @Autowired
    EnvironDao environDao;

    @Override
    public Environ create(Environ environ) {
        return environDao.create(environ);
    }

    @Override
    public Environ update(Environ environ) {
        return environDao.update(environ);
    }

    @Override
    public void delete(Long id) {
        environDao.delete(id);
    }

    @Override
    public Environ getEnviron(Long id) {
        return environDao.getEnviron(id);
    }

    @Override
    public List<Environ> list() {
        return environDao.list();
    }

    @Override
    public Environ findNewestOne() {
        return environDao.findNewestOne();
    }
}
