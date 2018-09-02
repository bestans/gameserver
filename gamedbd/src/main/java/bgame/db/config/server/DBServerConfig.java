package bgame.db.config.server;

import bestan.common.logic.ServerConfig;
import bestan.common.lua.BaseLuaConfig;
import bestan.common.net.server.NetServerConfig;

public class DBServerConfig extends BaseLuaConfig {
	public ServerConfig serverConfig;
	public int nThreadPool;
	public NetServerConfig netServerCfg;
	
	private static DBServerConfig instance;
	public static DBServerConfig getInstance() {
		return instance;
	}
	public static void setInstance(DBServerConfig config) {
		instance = config;
	}
}
