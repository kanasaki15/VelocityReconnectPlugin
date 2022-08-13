package xyz.n7mn.dev.velocityreconnectplugin;

import com.google.inject.Inject;
import com.velocitypowered.api.event.ResultedEvent;
import com.velocitypowered.api.event.connection.PostLoginEvent;
import com.velocitypowered.api.event.player.KickedFromServerEvent;
import com.velocitypowered.api.event.player.ServerConnectedEvent;
import com.velocitypowered.api.event.player.ServerPreConnectEvent;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.proxy.ConnectionRequestBuilder;
import com.velocitypowered.api.proxy.ProxyServer;
import com.velocitypowered.api.proxy.server.RegisteredServer;
import com.velocitypowered.api.proxy.server.ServerPing;
import net.kyori.adventure.text.Component;
import org.slf4j.Logger;

import java.util.Optional;

@Plugin(
        id = "velocityreconnectplugin",
        name = "VelocityReconnectPlugin",
        version = BuildConstants.VERSION,
        description = "VelocityReconnectPlugin",
        authors = {"7mi_chan"}
)
public class VelocityReconnectPlugin {

    @Inject
    private Logger logger;

    @Inject
    private ProxyServer proxyServer;

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent event) {


    }


    @Subscribe
    public void a(ResultedEvent e){
        System.out.println(e.getClass().getName());
        System.out.println(e.getResult());
    }

    @Subscribe
    public void PostLoginEvent(KickedFromServerEvent e){
        String name = e.getServer().getServerInfo().getName();
        if (name.equals("lobby")){
            return;
        }
        e.setResult(KickedFromServerEvent.RedirectPlayer.create(proxyServer.getServer("lobby").get()));

        e.getPlayer().sendMessage(Component.text("あなたは以下の理由でkickされたようです。"));
        e.getPlayer().sendMessage(e.getServerKickReason().get());
        e.getPlayer().sendMessage(Component.text("BANされてない、バージョン間違ってないなら「/server "+name+"」で戻れます。きっと。"));
    }
}
