package bgame.db;

import bestan.common.log.Glog;
import bestan.common.logic.ServerConfig;
import bestan.common.module.IModule;
import bestan.common.thread.BThreadPoolExecutors;
import bgame.common.message.GameMessageEnum;
import bgame.db.config.server.DBServerConfig;

public class DBCommonModule implements IModule {
	private DBServerConfig dbServerCfg;
	public DBCommonModule(DBServerConfig dbServerCfg) {
		this.dbServerCfg = dbServerCfg;
	}
	
	@Override
	public void startup(ServerConfig config) throws Exception {
		//创建工作线程
		config.workExecutor = BThreadPoolExecutors.newMutipleSingleThreadPool("db_workpool", dbServerCfg.nThreadPool);
		//指定消息索引类
		config.messageIndex = GameMessageEnum.class;
	}
	
	@Override
	public void close() throws Exception {
		dbServerCfg.netServerCfg.workdExecutor.shutdown();
		int waitSecs = 3;	//3秒
		for (int i = 0; i < waitSecs; ++i) {
			Glog.info("wait workExecutor close, left {} second", waitSecs - i);
			Thread.sleep(1000);
		}
		dbServerCfg.netServerCfg.workdExecutor.shutdownNow();
	}
}
