![Restart Server icon](docs/media/icon_128x128.png)

# Restart Server

Adds a restart command to dedicated servers.

![Restart command screenshot](docs/media/command_preview.png)

I made this so you don't have to run `/stop` and then run the start script again.  
Supports scheduling restarts at intervals, or when no players have been online for some time.

Everything is fully configurable in the `restart-server.json` config file. You can reload the mod's config using `/reload`.

## Dependencies

- [Fabric API](https://modrinth.com/mod/fabric-api) or [Quilt Standard Libraries](https://modrinth.com/mod/qsl)
- [Cloth Config API](https://modrinth.com/mod/cloth-config)

## Download

[![github](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/github_vector.svg)](https://github.com/Steveplays28/restart-server)
[![modrinth](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/modrinth_vector.svg)](https://modrinth.com/mod/restart-server)
[![curseforge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/available/curseforge_vector.svg)](https://www.curseforge.com/minecraft/mc-mods/restart-server)

![fabric](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/compact/supported/fabric_vector.svg)
![quilt](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/compact/supported/quilt_vector.svg)

See the version info in the filename for the supported Minecraft versions.  
Made for the Fabric and Quilt modloaders.  
Server side only.

## FAQ

![forge](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@2/assets/cozy/unsupported/forge_vector.svg)

- Q: Will you be backporting this to lower Minecraft versions?  
A: No.

- Q: Forge pls?  
A: Also no.

- Q: Does only the server need this mod or does the client need it too?  
A: Only the server needs this mod. Singleplayer/LAN is not supported.

## License

This project is licensed under LGPLv2.1, see [LICENSE](https://github.com/Steveplays28/restart-server/blob/main/LICENSE).
