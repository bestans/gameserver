package bgame.gs.net;

import bestan.common.net.IProtocol;
import bestan.common.net.client.BaseNetClientManager;
import bestan.common.net.client.NetClientConfig;
import bestan.common.thread.BExecutor;

public class DBNetClient extends BaseNetClientManager {
	private static DBNetClient INSTANCE = null;

	public DBNetClient(NetClientConfig config, BExecutor executor, IProtocol protocol) {
		super(config, executor, protocol);
		
		if (INSTANCE != null) {
			throw new RuntimeException("duplicate DBNetClient");
		}

		INSTANCE = this;
	}

	public static DBNetClient getInstance() {
		return INSTANCE;
	}
}
