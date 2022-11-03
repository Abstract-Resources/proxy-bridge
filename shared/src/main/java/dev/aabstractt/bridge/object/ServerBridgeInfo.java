package dev.aabstractt.bridge.object;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import java.util.Set;

@AllArgsConstructor @Getter
public final class ServerBridgeInfo {

    private final @NonNull String serverName;
    private final int maxPlayers;

    private Set<String> playersXuid;
    private Set<String> playersName;

    public boolean hasJoinedPlayer(@NonNull String xuidOrName) {
        return this.playersXuid.contains(xuidOrName) || this.playersName.contains(xuidOrName);
    }
}