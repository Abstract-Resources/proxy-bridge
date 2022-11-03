package dev.aabstractt.bridge;

import dev.aabstractt.bridge.object.ProxyBridgeInfo;
import dev.aabstractt.bridge.object.ServerBridgeInfo;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;

public final class ProxyBridge {

    @Getter private final static ProxyBridge instance = new ProxyBridge();

    @Getter private final @NonNull String currentProxyName = "";

    private @NonNull Set<ProxyBridgeInfo> proxyBridgeInfos = new HashSet<>();
    private @NonNull Set<ServerBridgeInfo> serverBridgeInfos = new HashSet<>();

    public void init(@NonNull Set<ProxyBridgeInfo> proxyBridgeInfos, @NonNull Set<ServerBridgeInfo> serverBridgeInfos) {
        this.proxyBridgeInfos = proxyBridgeInfos;

        this.serverBridgeInfos = serverBridgeInfos;
    }

    public @Nullable ServerBridgeInfo getServerBridgeInfo(@NonNull String serverName) {
        return this.serverBridgeInfos.stream()
                .filter(serverBridgeInfo -> serverBridgeInfo.getServerName().equalsIgnoreCase(serverName))
                .findAny().orElse(null);
    }

    public @Nullable ServerBridgeInfo getPlayerServerBridgeInfo(@NonNull String xuidOrName) {
        return this.serverBridgeInfos.stream()
                .filter(serverBridgeInfo -> serverBridgeInfo.hasJoinedPlayer(xuidOrName))
                .findAny().orElse(null);
    }

    public @Nullable ProxyBridgeInfo getProxyBridgeInfo(@NonNull String proxyName) {
        return this.proxyBridgeInfos.stream()
                .filter(proxyBridgeInfo -> proxyBridgeInfo.getProxyName().equalsIgnoreCase(proxyName))
                .findAny().orElse(null);
    }

    public @Nullable ProxyBridgeInfo getPlayerProxyBridgeInfo(@NonNull String xuidOrName) {
        return this.proxyBridgeInfos.stream()
                .filter(proxyBridgeInfo -> proxyBridgeInfo.hasJoinedPlayer(xuidOrName))
                .findAny().orElse(null);
    }
}