package bgame.gs.test;

import bestan.common.log.Glog;
import bestan.common.logic.BaseManager;
import bestan.common.net.client.BaseNetClientManager;
import bestan.common.net.operation.CommonSaveParam;
import bestan.common.net.operation.DBOperationUtil;
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
		data.setValue(1314);
		if (net.GetChannel() == null) {
			Glog.debug("nulllllllllllll");
			return;
		}
		DBOperationUtil.RpcCommonSave(net, net.GetChannel(), "test", Integer.valueOf(100), data.build(), new CommonSaveParam(1, guid));
	}
}
