package bgame.db.net;

import bestan.common.net.IProtocol;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.net.server.NetServerConfig;
import bestan.common.thread.BExecutor;

public class DBNetServer extends BaseNetServerManager {
	private static DBNetServer INSTANCE = null;
	
	public DBNetServer(NetServerConfig config, BExecutor executor, IProtocol protocol) {
		super(config, executor, protocol);
		
		if (INSTANCE != null) {
			throw new RuntimeException("duplicate DBNetServer");
		}
		INSTANCE = this;
	}

	public static DBNetServer getInstance() {
		return INSTANCE;
	}
}
