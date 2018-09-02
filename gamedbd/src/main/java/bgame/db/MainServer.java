package bgame.db;

import java.util.concurrent.atomic.AtomicBoolean;

import bestan.common.db.DBModule;
import bestan.common.log.Glog;
import bestan.common.lua.LuaConfigs;
import bestan.common.message.MessageFactory;
import bestan.common.module.IModule;
import bestan.common.module.ModuleManager;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.timer.BTimer.TimerModule;
import bgame.db.config.server.DBConfig;
import bgame.db.config.server.DBServerConfig;

public class MainServer {
	private static AtomicBoolean runState = new AtomicBoolean(true);
	
	public static void main(String[] args) {
		LuaConfigs.loadConfig("E:\\gameserver\\server-common\\server_common_res\\server_config\\", "bgame.db.config.server");
		
		var cfg = DBServerConfig.getInstance();
		var dbConfig = LuaConfigs.get(DBConfig.class);
		Glog.debug("dbconfig={}", dbConfig);
		if (dbConfig == null) {
			Glog.error("start failed.dbConfig is null");
			return;
		}

		var dbCommonModule = new DBCommonModule(cfg);	//服务器通用模块
		var timerModule = new TimerModule();		//定时器模块
		var messageModule = new MessageFactory();	//消息模块
		var netServerModule = new BaseNetServerManager(cfg.netServerCfg);	//网络server
		var dbModule = new DBModule(dbConfig);		//db数据库
		
		IModule[] startModules = {
				dbCommonModule, timerModule, messageModule, dbModule, netServerModule, 
		};
		IModule[] closeModules = {
				netServerModule, dbModule, messageModule, timerModule, dbCommonModule,
		};
		ModuleManager.register(startModules, closeModules);
		if (!ModuleManager.startup(cfg.serverConfig)) {
			ModuleManager.close();
			return;
		}

		while (runState.get()) {
			try {
				Thread.sleep(500);
			} catch (Exception e) {
				Glog.error("gamedb:run error:message={}", e.getMessage());
				break;
			}
		}

		ModuleManager.close();
	}
}
