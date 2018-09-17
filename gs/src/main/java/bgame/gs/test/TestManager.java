package bgame.gs.test;

import bestan.common.log.Glog;
import bestan.common.logic.BaseManager;
import bestan.common.net.client.BaseNetClientManager;
import bestan.common.net.operation.CommonDBParam;
import bestan.common.timer.BTimer;
import bgame.common.message.NetCommon.DBTestTable;

public class TestManager extends BaseManager {
	private static class TestManagerHolder {
		private static TestManager INSTANCE = new TestManager();
	}
	
	private TestManager() {
		
	}
	
	public static TestManager getInstance() {
		return TestManagerHolder.INSTANCE;
	}
	
	public void test1(BaseNetClientManager net) {
		var data  = DBTestTable.newBuilder();
		//var data = RpcTest.newBuilder();
		data.setValue((int)BTimer.getTime());
		if (net.GetChannel() == null) {
			Glog.debug("nulllllllllllll");
			return;
		}
		Glog.debug("test1 start:value={}", (int)BTimer.getTime());
		net.rpcCommonSave("test", Integer.valueOf(100), data.build(), 2, this);
		test2(net);
		//DBOperationUtil.rpcCommonSave(net, net.GetChannel(), "test", Integer.valueOf(100), data.build(), new CommonSaveParam(1, guid));
		//DBOperationUtil.rpcCommonLoad(net, net.GetChannel(), "test", Integer.valueOf(100), new CommonLoadParam(2, guid));
	}

	public void test2(BaseNetClientManager net) {
		//net.rpcCommonLoad("test", Integer.valueOf(100), 2, this);
		net.rpcCommonLoad("test", Integer.valueOf(100), new CommonDBParam(2, guid, null, 13));
	}
}
