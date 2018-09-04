package bgame.db;

import bestan.common.log.Glog;
import bestan.common.module.IModule;
import bestan.common.thread.BThreadPoolExecutors;
import bgame.db.config.server.DBServerConfig;

public class DBServerModule implements IModule {
	private DBServerConfig dbServerCfg;
	public DBServerModule(DBServerConfig dbServerCfg) {
		this.dbServerCfg = dbServerCfg;
		//创建工作线程
		dbServerCfg.workExecutor = BThreadPoolExecutors.newMutipleSingleThreadPool("db_workpool", dbServerCfg.nThreadPool);
	}
	
	@Override
	public void startup() throws Exception {
	}
	
	@Override
	public void close() throws Exception {
		dbServerCfg.workExecutor.shutdown();
		int waitSecs = 3;	//3秒
		for (int i = 0; i < waitSecs; ++i) {
			Glog.info("wait workExecutor close, left {} second", waitSecs - i);
			Thread.sleep(1000);
		}
		dbServerCfg.workExecutor.shutdownNow();
	}
}
