package com.moon.RedisLabNTT.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moon.RedisLabNTT.Entity.Parametro;
import com.moon.RedisLabNTT.Service.RedisService;


import java.util.Set;

@RestController
@RequestMapping("/api/redis")
public class RedisController {

    private final RedisService redisService;

    public RedisController(RedisService redisService) {
        this.redisService = redisService;
    }

    @GetMapping("/parametros")
    public Set<String> getAllParametros() {
        return redisService.getAllParametros();
    }
    
    @GetMapping("/parametros/{clave}")
    public ResponseEntity<Object> getParametroFromRedis(@PathVariable String clave) {
        String redisKey = "map-parametros:" + clave;
        try {
            Parametro parametro = redisService.get(redisKey);
        
            if (parametro != null) {
            	System.out.println("Parametro encontrado: " + parametro);
                return ResponseEntity.ok(parametro);
            } else {
                System.out.println("Parametro no encontrado para la clave: " + clave);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parametro no encontrado para la clave: " + clave);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno al obtener el parámetro.");
        }
    }
}