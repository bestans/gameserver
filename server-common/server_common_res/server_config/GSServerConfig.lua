--1kb
local KB = 1024
--1兆
local MB = KB * KB

local serverConfig =
{
	zoneId = 1001,
	
	tickInterval = 1000,
	
	managerType = 0,
	
	playerTickInterval = 100,
	
	messagePackage = "bgame.common.message",

	messageHandlerPackage = "",	
}

local dbNetClientConfig=
{
	clientName = "gsDBClient",
	
	bossGroupThreadCount = 1,
	
	workerGroupThreadCount = 1,
	
	serverIP = "127.0.0.1",
	
	serverPort = 1019,
}

local netServerCfg=
{
	serverName = "GSServer",
	bossGroupThreadCount = 1,
	workerGroupThreadCount = 1,
	serverIP = "127.0.0.1",
	serverPort = 1020,
	--serverchanel(用来监听和接受连接)的接收缓冲区大小
	optionRcvbuf = 64 * KB,
	--serverchanel(用来监听和接受连接)的发送缓冲区大小
	optionSndbuf = 128 * KB,
	--clientchannel（每一个建立连接）的接收缓冲区大小
	childOptionRcvbuf = 16 * KB,
	--clientchannel（每一个建立连接）的发送缓冲区大小
	childOptionSndbuf = 32 * KB,
}

local config = 
{
	--通用配置
	serverConfig = serverConfig,
	
	--连接DB服务器的client
	dbNetClientConfig = dbNetClientConfig,
	
	--GS服务器配置
	netServerCfg = netServerCfg,
	
	--工作线程池数量
	nThreadPool = 10,
	
	--timer更新间隔（毫秒）
	timerIickInterval = 500,
	
	--消息包列表
	messagePackages = { "bgame.common.message" },
	
	--消息处理包列表
	messageHandlerPackages = { "" },
	
	
}

return config