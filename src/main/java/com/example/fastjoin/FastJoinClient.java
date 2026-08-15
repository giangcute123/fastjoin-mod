package com.example.fastjoin;

import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FastJoinClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("fastjoin");

    @Override
    public void onInitializeClient() {
        LOGGER.info("[FastJoin] Da kich hoat toi uu ket noi server (TCP_NODELAY + buffer lon hon).");
    }
}
