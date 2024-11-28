package com.moon.RedisLabNTT.Service;

import java.util.List;
import java.util.Optional;

import com.moon.RedisLabNTT.Entity.Parametro;

public interface ParametroService {
    List<Parametro> getAllParametros();
    Optional<Parametro> getParametroById(Long id);
    Optional<Parametro> getParametroByClave(String clave);
    Parametro createParametro(Parametro parametro);
    Parametro updateParametro(Long id, Parametro updatedParametro);
    void deleteParametro(Long id);
}