package bgame.db;

import java.util.concurrent.atomic.AtomicBoolean;

import bestan.common.log.Glog;
import bestan.common.lua.LuaConfigs;
import bestan.common.message.MessageFactory;
import bestan.common.module.IModule;
import bestan.common.module.ModuleManager;
import bestan.common.net.server.BaseNetServerManager;
import bestan.common.thread.BThreadPoolExecutors;
import bestan.common.timer.BTimer.TimerModule;
import bgame.common.message.GameMessageEnum;
import bgame.db.config.server.DBServerConfig;

public class MainServer {
	private static AtomicBoolean runState = new AtomicBoolean(true);
	
	public static void main(String[] args) {
		LuaConfigs.loadConfig("bestan.db.config");
		
		var cfg = DBServerConfig.getInstance();
		
		//创建工作线程
		cfg.workExecutor = BThreadPoolExecutors.newMutipleSingleThreadPool("db_workpool", cfg.nThreadPool);
		//指定消息索引类
		cfg.messageIndex = GameMessageEnum.class;
		
		IModule[] modules = {
				new TimerModule(),		//定时器模块
				new MessageFactory(),	//消息模块
		};
		var netServer = new BaseNetServerManager(cfg.serverCfg);
		ModuleManager.register(modules);
		try
		{
			netServer.start();
			ModuleManager.startup(cfg);
		} catch (Exception e) {
			Glog.error("gamedb start failed:message=", e.getMessage());
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
		
		try {
			netServer.stop();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ModuleManager.close();
	}
}
