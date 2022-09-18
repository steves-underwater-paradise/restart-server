<h1 align="center">
<img src="./src/main/resources/assets/restart-server/icon.png" width="256px" align="center">

Restart Server

[![GitHub](https://img.shields.io/github/license/steveplays28/restart-server)](https://github.com/steveplays28/restart-server/blob/main/LICENSE)
![GitHub](https://img.shields.io/github/repo-size/steveplays28/restart-server)
[![GitHub](https://img.shields.io/github/forks/steveplays28/restart-server)](https://github.com/steveplays28/restart-server/network/members)
[![GitHub](https://img.shields.io/github/issues/steveplays28/restart-server)](https://github.com/steveplays28/restart-server/issues)
[![GitHub](https://img.shields.io/github/issues-pr/steveplays28/restart-server)](https://github.com/steveplays28/restart-server/pulls)

![GitHub](https://img.shields.io/badge/environment-server-4caf50?style=flat-square)
![GitHub](https://img.shields.io/badge/mod%20loader-fabric-d64541?style=flat-square)
[![Discord](https://img.shields.io/discord/746681304111906867?label=chat%20on%20Discord%20%7C%20Steve%27s%20underwater%20paradise)](https://discord.gg/KbWxgGg)
</h1>

<p align="center">
Minecraft Fabric mod that adds a restart command to dedicated servers.
</p>

---
So you don't have to run `/stop` and run the start script again. Also supports scheduling restarts at intervals, or when no players have been online for some time.  

Everything is fully configurable in the `restart-server.json` config file. You can reload the mod's config using `/reload`.

![Command preview](command_preview.png)

## Dependencies
- [Fabric API](https://modrinth.com/mod/fabric-api)
- [Cloth Config API](https://modrinth.com/mod/cloth-config)

## Incompatibilities
- None that I'm aware of right now.

## FAQ
Q: Forge pls?  
A: No. I don't have the time to learn another modding framework, however you can port over the mod yourself if you want, the [source code](https://github.com/steveplays28/restart-server) is open.

Q: Will you backport this mod?  
A: No.

Q: Does this mod work in multiplayer?  
A: Yes.

Q: Does only the server need this mod or does the client need it too?  
A: Only the server needs this mod. Singleplayer/LAN is not supported.
