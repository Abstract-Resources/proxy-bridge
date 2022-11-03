package dev.aabstractt.bridge.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@AllArgsConstructor @Getter
public final class ProxyBridgeInfo {

    private final @NonNull String proxyName;

    private final @NonNull Set<String> playersXuid;
    private final @NonNull Set<String> playersName;

    public boolean hasJoinedPlayer(@NonNull String xuidOrName) {
        return this.playersXuid.contains(xuidOrName) || this.playersName.contains(xuidOrName);
    }
}