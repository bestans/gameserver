package bgame.gs;

import bestan.common.log.Glog;
import bestan.common.logic.Gmatrix;
import bestan.common.module.IModule;
import bestan.common.thread.BThreadPoolExecutors;
import bgame.gs.config.server.GSServerConfig;

public class GSCommonModule implements IModule {
	private GSServerConfig dbServerCfg;
	public GSCommonModule(GSServerConfig dbServerCfg) {
		this.dbServerCfg = dbServerCfg;
		//创建工作线程
		this.dbServerCfg.workExecutor = BThreadPoolExecutors.newMutipleSingleThreadPool("gs_workpool", dbServerCfg.nThreadPool);
	}

	@Override
	public void startup() throws Exception {
		Gmatrix.getInstance().setServerConfig(dbServerCfg.serverConfig);
	}
	
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
