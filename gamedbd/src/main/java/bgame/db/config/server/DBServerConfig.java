package bgame.db.config.server;

import bestan.common.logic.ServerConfig;
import bestan.common.lua.BaseLuaConfig;
import bestan.common.net.server.NetServerConfig;

public class DBServerConfig extends BaseLuaConfig {
	public ServerConfig serverConfig;
	public int nThreadPool;
	public NetServerConfig netServerCfg;
	
	public static DBServerConfig instance;
}
