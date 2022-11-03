package dev.aabstractt.bridge.datasource.protocol;

import dev.aabstractt.bridge.ProxyBridge;

public final class ProxyStatusUpdatePacket extends BridgePacket {

    public ProxyStatusUpdatePacket(int pid) {
        super(pid, ProxyBridge.getInstance().getCurrentProxyName(), null);
    }

    @Override
    public void encode() {}

    @Override
    public void decode() {}
}