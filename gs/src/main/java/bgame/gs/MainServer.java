package bgame.gs;

import java.util.concurrent.atomic.AtomicBoolean;

import bestan.common.log.Glog;
import bestan.common.lua.LuaConfigs;
import bestan.common.message.MessageFactory;
import bestan.common.module.IModule;
import bestan.common.module.ModuleManager;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.timer.BTimer;
import bestan.common.timer.BTimer.TimerModule;
import bgame.gs.config.server.GSServerConfig;

public class MainServer {
	private static AtomicBoolean runState = new AtomicBoolean(true);
	
	public static void main(String[] args) {
		LuaConfigs.loadConfig("E:\\gameserver\\server-common\\server_common_res\\server_config\\", "bgame.db.config.server");
		
		var cfg = GSServerConfig.getInstance();

		var dbCommonModule = new GSCommonModule(cfg);	//服务器通用模块
		var timerModule = new TimerModule();		//定时器模块
		var messageModule = new MessageFactory();	//消息模块
		var netServerModule = new BaseNetServerManager(cfg.netServerConfig);	//网络server
		
		IModule[] startModules = {
				dbCommonModule, timerModule, messageModule, netServerModule, 
		};
		IModule[] closeModules = {
				netServerModule, messageModule, timerModule, dbCommonModule,
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
