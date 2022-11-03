package dev.aabstractt.bridge.object.profile;

import com.google.common.collect.Maps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;

import javax.annotation.Nullable;
import java.util.Map;

@AllArgsConstructor @Getter
public final class Profile {

    private final static Map<String, Profile> profilesStored = Maps.newConcurrentMap();
    private final static Map<String, String> xuidsStored = Maps.newConcurrentMap();

    private @NonNull String xuid;
    private @NonNull String name;

    private @NonNull String serverName;

    public static void store(@NonNull Profile profile) {
        profilesStored.put(profile.getXuid(), profile);

        xuidsStored.put(profile.getName().toLowerCase(), profile.getXuid());
    }

    public static void flush(@NonNull String xuid) {
        // TODO: Flush profile cache
    }

    public static @Nullable Profile getIfLoaded(@NonNull String xuidOrName) {
        Profile profile = profilesStored.get(xuidOrName);

        if (profile != null) return profile;

        xuidOrName = xuidsStored.get(xuidOrName);

        return xuidOrName != null ? profilesStored.get(xuidOrName) : null;
    }
}