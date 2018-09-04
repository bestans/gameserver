package bgame.gs;

import java.util.concurrent.atomic.AtomicBoolean;

import bestan.common.log.Glog;
import bestan.common.lua.LuaConfigs;
import bestan.common.message.MessageFactory.MessageModule;
import bestan.common.module.IModule;
import bestan.common.module.ModuleManager;
import bestan.common.net.CommonProtocol;
import bestan.common.net.client.BaseNetClientManager;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.timer.BTimer;
import bestan.common.timer.BTimer.TimerModule;
import bgame.common.message.GameMessageEnum;
import bgame.gs.config.server.GSServerConfig;

public class MainServer {
	private static AtomicBoolean runState = new AtomicBoolean(true);
	
	public static void main(String[] args) {
		LuaConfigs.loadConfig("E:\\gameserver\\server-common\\server_common_res\\server_config\\", "bgame.gs.config.server");
		
		var cfg = GSServerConfig.instance;

		var gsCommonModule = new GSCommonModule(cfg);	//服务器通用模块
		var timerModule = new TimerModule(cfg.workExecutor, cfg.timerIickInterval);		//定时器模块
		var messageModule = new MessageModule(GameMessageEnum.class, cfg.messagePackages, cfg.messageHandlerPackages);	//消息模块
		var dbClientModule = new BaseNetClientManager(cfg.dbNetClientConfig, cfg.workExecutor, new CommonProtocol());	//网络server
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

		while (runState.get()) {
			try {
				System.out.println(BTimer.getTime());
				Thread.sleep(1000);
			} catch (Exception e) {
				Glog.error("gamedb:run error:message={}", e.getMessage());
				break;
			}
		}

		ModuleManager.close();
	}
}
