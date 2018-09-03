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

local netServerCfg=
{
	serverName = "DBServer",
	bossGroupThreadCount = 1,
	workerGroupThreadCount = 1,
	serverIP = "127.0.0.1",
	serverPort = 1019,
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
	serverConfig = serverConfig,
	netServerCfg = netServerCfg,
	nThreadPool = 10,
}

return config