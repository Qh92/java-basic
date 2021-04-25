# JDK  的安装与配置



## **一、安装环节**



### 1.打开网页

http://www.oracle.com

![image-20210426004915549](assets\image-20210426004915549.png)



下载对应平台的合适的 JDK 版本

![image-20210426005045681](assets\image-20210426005045681.png)



### 2.双击下载的 exe,如 jdk-8u131-windows-x64.exe。



### 3.进入安装向导。

![](assets\image-20210426005206232.png)

### 

### 4.下一步,更改安装路径，选择安装所有组件

![image-20210426005336615](assets\image-20210426005336615.png)

更改为 D:\developer_tools\Java\jdk1.8.0_131\

![image-20210426005356265](assets\image-20210426005356265.png)



点击确定

![image-20210426005424761](assets\image-20210426005424761.png)



### 5.下一步，开始安装

![image-20210426005501442](assets\image-20210426005501442.png)



6. ### 安装 jre，可以更改 jre 安装路径。(过程同上述安装目录的选择)

注意：如果提示需要将 jre 安装在一个空目录下，那自己创建一个目录即可。

![image-20210426005541311](assets\image-20210426005541311.png)



### 7.点击下一步，开始安装



![image-20210426005630818](assets\image-20210426005630818.png)



### 8.结束安装

![image-20210426005716479](assets\image-20210426005716479.png)



## **二、配置环节**



### 9.选中桌面”我的电脑”-右键选择属性，选择高级系统设置。

![image-20210426005828626](assets\image-20210426005828626.png)



### 10.点击环境变量：

![image-20210426005905702](assets\image-20210426005905702.png)



### 11.新建一项系统变量“JAVA_HOME”,值为 jdk 的安装路径。



![image-20210426005954120](assets\image-20210426005954120.png)



![image-20210426010016494](assets\image-20210426010016494.png)



### 12.配置系统变量:双击系统变量的 path，在变量值最前端添加 %JAVA_HOME%\bin; 然后确定-确定



![image-20210426010104832](assets\image-20210426010104832.png)



### 13.检验是否配置成功：通过运行-cmd 指令，进入命令行窗口。

输入：javac.exe



![image-20210426010154975](assets\image-20210426010154975.png)



### 14.检验 java.exe 命令

![image-20210426010229324](assets\image-20210426010229324.png)



### 15.获取当前安装的 jdk 的版本信息



![image-20210426010309973](assets\image-20210426010309973.png)



## **三、下载** API



同时学习 java 少不了 API（Application Programming Interface）文档。下载地址：

http://www.oracle.com/technetwork/java/javase/downloads/index.html

将页面上的滚动条向下滚动，找到”Additional Resources”部分。如下：

![image-20210426010412186](assets\image-20210426010412186.png)



或者通过主页面，找到链接地址进来：

![image-20210426010441967](assets\image-20210426010441967.png)