package dev.aabstractt.proxy;

import dev.aabstractt.bridge.ProxyBridge;
import dev.aabstractt.bridge.datasource.RedisDataSource;
import dev.waterdog.waterdogpe.plugin.Plugin;

import java.util.HashSet;

public final class ProxyPlugin extends Plugin {

    @Override
    public void onEnable() {
        RedisDataSource.getInstance().init();

        ProxyBridge.getInstance().init(new HashSet<>());
    }
}