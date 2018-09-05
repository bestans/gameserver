package bgame.db.net.handler;

import com.google.protobuf.Message;
import com.google.protobuf.Message.Builder;

import bestan.common.message.IRpcServerHandler;
import bestan.common.net.AbstractProtocol;
import bgame.common.message.NetCommon.RpcTest;
import bgame.common.message.NetCommon.RpcTestRes;

public class RpcTestHandler implements IRpcServerHandler {
	@Override
	public void server(AbstractProtocol protocol, Message arg, Builder res) {
		var argMessage = (RpcTest)arg;
		var resMessage = (RpcTestRes.Builder)res;
		
		resMessage.setValue(argMessage.getArg() + 1);
	}
}
