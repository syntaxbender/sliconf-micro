package javaday.istanbul.sliconf.micro.provider;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import javaday.istanbul.sliconf.micro.model.token.SecurityToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

/**
 * Hazelcast ile alakali islemlerin yapildigi nesne.
 *
 * Auth tokenlerin tutuldugu mapin getirilmesi ve mape yeni elemanlar eklenmesi ile alakali
 * islemler gerceklestiriliyor
 *
 */
@Component
public class DistributedMapProvider {

    private final HazelcastInstance hazelcastInstance;

    @Autowired
    public DistributedMapProvider(HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    public IMap<String, SecurityToken> getSecurityTokenMap(String mapName) {
        return hazelcastInstance.getMap(mapName);
    }

    public void putSecurityToken(String mapName, String key, SecurityToken securityToken,
                                   long timeToLive, TimeUnit timeUnit) {
        getSecurityTokenMap(mapName).put(key, securityToken, timeToLive, timeUnit);
    }

    public void removeSecurityToken(String mapName, String key) {
        getSecurityTokenMap(mapName).remove(key);
    }
}