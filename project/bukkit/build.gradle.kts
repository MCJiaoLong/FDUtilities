val taboolibVersion: String by project

plugins {
    id("io.izzel.taboolib") version "1.40"
}


repositories {
    mavenCentral()
    //MiniMessage: https://docs.adventure.kyori.net/minimessage/api.html
    maven(url = "https://s01.oss.sonatype.org/content/repositories/snapshots/") {
        name = "sonatype-oss-snapshots1"
    }
    //PlaceholderAPI: https://www.spigotmc.org/resources/placeholderapi.7339/
    maven(url = "https://repo.extendedclip.com/content/repositories/placeholderapi/") {
        name = "PlaceholderAPI"
    }

}

taboolib {
    install(
        "common",
        "common-5",
        "module-chat",
        "module-configuration",
        "module-database",
        "module-kether",
        "module-lang",
        "platform-bukkit",
//        //暂时测试
//        "platform-bungee",
//        "module-porticus"
    )

    classifier = null
    version = taboolibVersion

    description {
        // 插件名称
        name(rootProject.name)
        contributors {
            // 作者名称
            name("qq1610105206")
            // 插件介绍
            desc("一个基于Taboolib开发的多功能插件")
        }
        // 依赖插件
        dependencies {
            // 依赖插件名称（不要误会成写自己，会触发 self-loop 错误）
            name("PlaceholderAPI")
                // 限制平台（该依赖会在其他平台被抹去）
                .with("bukkit")
                // 软依赖（仅在 Bukkit、Nukkit、Bungee、Sponge8 平台有效）
                .optional(true)
            name("Vault")
                .with("bukkit")
                .optional(true)
        }
    }

}


dependencies {
    //api(project(":project:api"))
    implementation(project(":project:api"))

    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11802:11802-minimize:mapped")
    compileOnly("ink.ptms.core:v11802:11802-minimize:universal")

    //MiniMessage: https://docs.adventure.kyori.net/minimessage/api.html
    compileOnly("net.kyori:adventure-api:4.11.0")
    compileOnly("net.kyori:adventure-platform-bukkit:4.1.1")
    compileOnly("net.kyori:adventure-text-minimessage:4.11.0")

    //PlaceholderAPI: https://www.spigotmc.org/resources/placeholderapi.7339/
    compileOnly("me.clip:placeholderapi:2.11.1")
}

