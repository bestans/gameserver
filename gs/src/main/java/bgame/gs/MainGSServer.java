package bgame.gs;

import java.util.concurrent.atomic.AtomicBoolean;

import com.google.protobuf.ByteString;

import bestan.common.log.Glog;
import bestan.common.lua.LuaConfigs;
import bestan.common.message.MessageFactory.MessageModule;
import bestan.common.module.IModule;
import bestan.common.module.ModuleManager;
import bestan.common.net.CommonProtocol;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.timer.BTimer.TimerModule;
import bgame.common.message.GameMessageEnum;
import bgame.common.message.NetCommon.RpcTest;
import bgame.common.message.NetCommon.TestRegister;
import bgame.gs.config.server.GSServerConfig;
import bgame.gs.net.DBNetClient;
import bgame.gs.test.TestManager;

public class MainGSServer {
	private static AtomicBoolean runState = new AtomicBoolean(true);
	
	public static void main(String[] args) {
		LuaConfigs.loadConfig("E:\\gameserver\\server-common\\server_common_res\\server_config\\", "bgame.gs.config.server");
		
		var cfg = GSServerConfig.instance;

		var gsCommonModule = new GSCommonModule(cfg);	//服务器通用模块
		var timerModule = new TimerModule(cfg.workExecutor, cfg.timerIickInterval);		//定时器模块
		var messageModule = new MessageModule(GameMessageEnum.class, cfg.messagePackages, cfg.messageHandlerPackages);	//消息模块
		var dbClientModule = new DBNetClient(cfg.dbNetClientConfig, cfg.workExecutor, new CommonProtocol());	//网络server
		var gsServerModule = new BaseNetServerManager(cfg.netServerCfg, cfg.workExecutor, new CommonProtocol()); 		//GS服务器
		IModule[] startModules = {
				gsCommonModule, timerModule, messageModule, dbClientModule, gsServerModule,
		};
		IModule[] closeModules = {
				gsServerModule, dbClientModule, messageModule, timerModule, gsCommonModule,
		};
		ModuleManager.register(startModules, closeModules);
		if (!ModuleManager.startup(cfg.serverConfig)) {
			ModuleManager.close();
			return;
		}

		var builder = TestRegister.newBuilder();
		builder.setMsg(ByteString.copyFromUtf8("aaaa"));
		
		var arg = RpcTest.newBuilder();
		arg.setArg(100);
		while (runState.get()) {
			try {
				Thread.sleep(1000);
				//DBNetClient.getInstance().sendMessage(builder.build());
				//DBNetClient.getInstance().sendRpc(arg.build(), RpcTestRes.class);
				TestManager.getInstance().test1(DBNetClient.getInstance());

				Thread.sleep(20000);
			} catch (Exception e) {
				Glog.error("gs:run error:message={},cause={}", e.getMessage(), e.getStackTrace());
				break;
			}
		}

		ModuleManager.close();
	}
}
