问题一：

​	环境：docker version 26.1.3  centos9 x86x64架构，虚拟机、桥接网络

​		docker安转后会关闭桥接网卡ens33（名称可能不同），并删除ipv4配置，安装当时网络正常，重启网卡后或者重启系统后，docker对网卡的配置生效，宿主机无法连接网络；查询网卡信息时，ipv6信息存在，不清楚ipv6是否可以连接网络（未测试）

​	解决：

​		配置静态ip、子网掩码、网关、dns，重启网卡，网络恢复

问题二：

​	环境：docker version 26.1.3  centos9 x86x64架构，虚拟机、桥接网络

​	docker 镜像 启动报错 library initialization failed - unable to allocate file descriptor table - out of memory，机翻过来是内存不足，我也不知道是存储不足还是运行内存不足，直接丢给百度，最终试过种种办法，在命令中增加 --ulimit nofile=1024  指定大小，解决

命令示例

docker run --ulimit nofile=1024 -d -p 8080:8080 networkadmin:latest

实际上centos9默认的ulimit 大小是1024，不生效的原因未知，根据百度的查询结果来看，貌似和docker拥有的权限有关，docker默认无法读取到ulimit 信息
