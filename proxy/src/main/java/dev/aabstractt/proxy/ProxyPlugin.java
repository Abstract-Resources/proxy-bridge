package dev.aabstractt.proxy;

import dev.aabstractt.bridge.ProxyBridge;
import dev.aabstractt.bridge.datasource.RedisDataSource;
import dev.aabstractt.bridge.datasource.protocol.ProxyStatusUpdatePacket;
import dev.aabstractt.bridge.object.ProxyBridgeInfo;
import dev.aabstractt.bridge.object.ServerBridgeInfo;
import dev.waterdog.waterdogpe.plugin.Plugin;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public final class ProxyPlugin extends Plugin {

    @Override
    public void onEnable() {
        RedisDataSource.getInstance().init();

        RedisDataSource.getInstance().submit(new ProxyStatusUpdatePacket(0)); // TODO: Change the packet constructor because not need request the pid on that
        RedisDataSource.getInstance().query(jedis -> {
            Set<String> proxies = jedis.smembers("proxies"); // Get all proxies registered into redis cache
            Set<String> servers = jedis.smembers("servers"); // Get all servers registered into redis cache

            // Initialize bridge and cast all proxies/servers using the redis cache
            ProxyBridge.getInstance().init(
                    proxies.stream()
                            .map(proxyName -> new ProxyBridgeInfo(proxyName, new HashSet<>(), new HashSet<>()))
                            .collect(Collectors.toSet()),
                    servers.stream()
                            .map(serverName -> new ServerBridgeInfo(serverName, 0, new HashSet<>(), new HashSet<>()))
                            .collect(Collectors.toSet())
            );
        });
    }
}