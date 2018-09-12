package bgame.gs.net.handler;

import com.google.protobuf.Message;

import bestan.common.log.Glog;
import bestan.common.net.AbstractProtocol;
import bestan.common.net.RpcManager.RpcObject;
import bestan.common.net.handler.IRpcClientHandler;
import bgame.common.message.NetCommon.RpcTest;
import bgame.common.message.NetCommon.RpcTestRes;

public class RpcTestResHandler implements IRpcClientHandler {
	@Override
	public void client(AbstractProtocol protocol, Message arg, Message res, Object param) {
		var argMessage = (RpcTest)arg;
		var resMessage = (RpcTestRes)res;
		
		Glog.debug("RpcTestHandler:arg={},res={}", argMessage.getArg(), resMessage.getValue());
	}
	
	@Override
	public void OnTimeout(AbstractProtocol protocol, RpcObject rpc, Message arg, Object param) {
		Glog.debug("RpcTestHandler:timeout:arg={}", arg);
	}
}
