package com.moon.RedisLabNTT.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.RedisLabNTT.Entity.Parametro;
import com.moon.RedisLabNTT.Service.ParametroService;

@RestController
@RequestMapping("/api/parametros")
public class ParametroController {

    private final ParametroService parametroService;

    public ParametroController(ParametroService parametroService) {
        this.parametroService = parametroService;
    }

    @GetMapping
    public ResponseEntity<List<Parametro>> getAllParametros() {
        List<Parametro> parametros = parametroService.getAllParametros();
        return parametros.isEmpty() 
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(parametros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parametro> getParametroById(@PathVariable Long id) {
        return parametroService.getParametroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/clave/{clave}")
    public ResponseEntity<Parametro> getParametroByClave(@PathVariable String clave) {
        return parametroService.getParametroByClave(clave)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parametro> updateParametro(@PathVariable Long id, @RequestBody Parametro parametro) {
        try {
            return ResponseEntity.ok(parametroService.updateParametro(id, parametro));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}