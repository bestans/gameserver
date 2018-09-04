package bgame.db.config.server;

import java.util.List;

import bestan.common.logic.ServerConfig;
import bestan.common.lua.BaseLuaConfig;
import bestan.common.lua.LuaParamAnnotation;
import bestan.common.lua.LuaParamAnnotation.LuaParamPolicy;
import bestan.common.net.server.NetServerConfig;
import bestan.common.thread.BExecutor;

public class DBServerConfig extends BaseLuaConfig {
	/**
	 * 通用配置
	 */
	public ServerConfig serverConfig;
	/**
	 * netServer配置
	 */
	public NetServerConfig netServerCfg;
	/**
	 * 线程池数量
	 */
	public int nThreadPool;
	/**
	 * timer更新间隔（毫秒）
	 */
	public int timerIickInterval;
	/**
	 * message包列表
	 */
	public List<String> messagePackages;
	/**
	 * messageHandler包列表
	 */
	public List<String> messageHandlerPackages;
	
	/**
	 * 工作线程运行时设置
	 */
	@LuaParamAnnotation(policy=LuaParamPolicy.OPTIONAL)
	public BExecutor workExecutor;
	
	public static DBServerConfig instance;
}
