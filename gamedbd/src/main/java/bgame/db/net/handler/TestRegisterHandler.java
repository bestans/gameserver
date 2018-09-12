package bgame.db.net.handler;

import bestan.common.log.Glog;
import bestan.common.net.AbstractProtocol;
import bestan.common.net.handler.IMessageHandler;
import bgame.common.message.NetCommon.TestRegister;

public class TestRegisterHandler implements IMessageHandler {

	@Override
	public void processProtocol(AbstractProtocol protocol) throws Exception {
		var test = (TestRegister)protocol.getMessage();
		Glog.debug("testregister:receive={}", test.getMsg().toStringUtf8());
	}
}
