package dev.aabstractt.bridge.datasource.protocol;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;

@AllArgsConstructor
public abstract class BridgePacket {

    private final int pid;

    @Getter private final @NonNull String from;
    @Getter private final @Nullable String to;

    public final int pid() {
        return this.pid;
    }

    // TODO: I need implement netty to allow use ByteBuf as reader method
    public abstract void encode();

    public abstract void decode();
}