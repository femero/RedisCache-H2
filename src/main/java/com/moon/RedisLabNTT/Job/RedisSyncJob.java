package com.moon.RedisLabNTT.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.moon.RedisLabNTT.Entity.Parametro;
import com.moon.RedisLabNTT.Service.ParametroService;
import com.moon.RedisLabNTT.Service.RedisService;

public class RedisSyncJob implements Job{

	@Autowired
    private ParametroService parametroService;

    @Autowired
    private RedisTemplate<String, Parametro> redisTemplate;

    @Autowired
    private RedisService redisService;
    
    @Override
    public void execute(JobExecutionContext context) {
        List<Parametro> parametros = parametroService.getAllParametros();
        
        for (Parametro parametro : parametros) {
            String redisKey = "map-parametros:" + parametro.getClave();
            if (!redisTemplate.hasKey(redisKey)) {
                redisTemplate.opsForValue().set("map-parametros:" + parametro.getClave(), parametro,30, TimeUnit.MINUTES);
                System.out.println("Parametro agregado a Redis: " + parametro.getClave());
            } else {
                Parametro parametroRedis = redisService.get(redisKey);

                if (parametro.getFechaActualizacion().isAfter(parametroRedis.getFechaActualizacion())) {
                    redisTemplate.opsForValue().set(redisKey, parametro, 30, TimeUnit.MINUTES);
                    System.out.println("Parametro actualizado en Redis: " + parametro.getClave());
                }
            }
        }
    }

}
