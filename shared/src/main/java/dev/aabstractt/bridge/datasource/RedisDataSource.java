package dev.aabstractt.bridge.datasource;

import dev.aabstractt.bridge.datasource.protocol.BridgePacket;
import dev.aabstractt.bridge.datasource.protocol.handler.VanillaBridgePacketHandler;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class RedisDataSource {

    @Getter private final static @NonNull RedisDataSource instance = new RedisDataSource();

    private final @NonNull Set<Object> packetHandlers = new HashSet<>();

    public void init() {
        this.registerPacketHandler(new VanillaBridgePacketHandler());
    }

    public void query(@NonNull Consumer<Jedis> consumer) {

    }

    public void submit(@NonNull BridgePacket packet) {
        // TODO: Initialize ByteBuf class and writeInt the packet#pid before execute packet#encode
        packet.encode();

        // TODO: After encode the packet submit it to all servers
    }

    public void registerPacketHandler(Object object) {
        this.packetHandlers.add(object);
    }

    private void handlePacket(@NonNull BridgePacket packet) throws InvocationTargetException, IllegalAccessException {
        for (Object object : this.packetHandlers) {
            Method method = this.getClassMethod(object.getClass(), packet.getClass());

            if (method == null) continue;

            method.invoke(object, packet);
        }
    }

    private @Nullable Method getClassMethod(@NonNull Class<?> clazz, @NonNull Class<? extends BridgePacket> packetClass) {
        try {
            return clazz.getMethod("handle" + packetClass.getSimpleName(), packetClass);
        } catch (Exception e) {
            return null;
        }
    }
}