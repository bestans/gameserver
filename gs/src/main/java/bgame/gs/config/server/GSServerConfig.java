package bgame.gs.config.server;

import bestan.common.logic.ServerConfig;
import bestan.common.lua.BaseLuaConfig;
import bestan.common.net.server.NetServerConfig;

public class GSServerConfig extends BaseLuaConfig {
	public ServerConfig serverConfig;
	public NetServerConfig netServerConfig;
	public int nThreadPool;
	
	public static GSServerConfig instance;
}
