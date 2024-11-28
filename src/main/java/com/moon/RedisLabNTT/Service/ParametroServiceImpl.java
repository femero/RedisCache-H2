package com.moon.RedisLabNTT.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.moon.RedisLabNTT.Repository.ParametroRepository;
import com.moon.RedisLabNTT.Entity.Parametro;

@Service
public class ParametroServiceImpl implements ParametroService {

    private final ParametroRepository parametroRepository;

    public ParametroServiceImpl(ParametroRepository parametroRepository) {
        this.parametroRepository = parametroRepository;
    }

    @Override
    public List<Parametro> getAllParametros() {
        return parametroRepository.findAll();
    }

    @Override
    public Optional<Parametro> getParametroById(Long id) {
        return parametroRepository.findById(id);
    }

    @Override
    public Optional<Parametro> getParametroByClave(String clave) {
        return parametroRepository.findByClave(clave);
    }

    @Override
    public Parametro createParametro(Parametro parametro) {
        return parametroRepository.save(parametro);
    }

    @Override
    public Parametro updateParametro(Long id, Parametro updatedParametro) {
        return parametroRepository.findById(id).map(parametro -> {
            parametro.setClave(updatedParametro.getClave());
            parametro.setValor(updatedParametro.getValor());
            return parametroRepository.save(parametro);
        }).orElseThrow(() -> new RuntimeException("Parametro not found with id " + id));
    }

    @Override
    public void deleteParametro(Long id) {
        parametroRepository.deleteById(id);
    }
}