package cn.fd.fdutilities

import taboolib.common.env.RuntimeDependencies
import taboolib.common.env.RuntimeDependency

/**
 * @author MC~蛟龙
 * @since 2022/5/28 20:37
 * 自动安装依赖
 */
@RuntimeDependencies(
    RuntimeDependency(
        value = "!net.kyori:adventure-api:4.10.1",
        test = "!net.kyori.adventure.Adventure",
        initiative = true
    ),
    RuntimeDependency(
        value = "!net.kyori:adventure-platform-bukkit:4.1.0",
        test = "!net.kyori.adventure.platform.bukkit.BukkitAudiences",
        repository = "https://repo.maven.apache.org/maven2",
        initiative = true
    ),
    RuntimeDependency(
        value = "!net.kyori:adventure-text-minimessage:4.10.1",
        repository = "https://repo.maven.apache.org/maven2",
        //repository = "https://s01.oss.sonatype.org/content/repositories/snapshots",
        initiative = true
    )
)

class BukkitEnv {
}