package com.moon.RedisLabNTT.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.moon.RedisLabNTT.Entity.Parametro;

import java.util.Optional;

public interface ParametroRepository extends JpaRepository<Parametro, Long> {
    Optional<Parametro> findByClave(String clave);
}
