package bgame.db.config.server;

import bestan.common.logic.ServerConfig;
import bestan.common.net.server.NetServerConfig;

public class DBServerConfig extends ServerConfig {
	public int nThreadPool;
	public NetServerConfig serverCfg;
	
	private static DBServerConfig instance;
	public static DBServerConfig getInstance() {
		return instance;
	}
	public static void setInstance(DBServerConfig config) {
		instance = config;
	}
}
