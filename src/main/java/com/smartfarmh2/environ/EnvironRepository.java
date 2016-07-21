package com.smartfarmh2.environ;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnvironRepository extends JpaRepository<Environ,Long>{
    public Environ findTopByOrderByCreatedDateDesc();
}
