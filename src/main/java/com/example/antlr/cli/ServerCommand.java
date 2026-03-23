package com.example.antlr.cli;

import com.example.antlr.api.ApiServer;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

/**
 * server 子命令 — 启动 REST API 服务器
 */
@Command(
    name = "server",
    description = "启动 REST API 服务器（基于 Javalin）",
    mixinStandardHelpOptions = true
)
public class ServerCommand implements Runnable {

    @Option(names = {"-p", "--port"}, description = "监听端口 (默认: 7070)", defaultValue = "7070")
    int port;

    @Override
    public void run() {
        System.out.println("🚀 正在启动 REST API 服务器 (端口: " + port + ") ...");
        ApiServer.start(port);
    }
}
