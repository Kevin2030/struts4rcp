# 主工程SVN目录 #
http://struts4rcp.googlecode.com/svn/trunk/code/struts4rcp
##### 使用SVN检出以上URL，可直接作为项目导入Eclipse #####
##### 工程结构说明 #####
  * build.xml 构建脚本
  * lib 依赖包
  * src 源代码
    * main 主框架 (包括server和client)
      * java 源代码
      * resources 资源文件
    * view 视图层扩展 (包括swing，jface和extjs)
      * java 源代码
      * resources 资源文件
    * test 测试用例
      * java 源代码
      * resources 资源文件
    * web 脚本源代码(包括struts4rcp.js)
# 演示工程SVN目录 #
http://struts4rcp.googlecode.com/svn/trunk/code/struts4rcpdemo
##### 使用SVN检出以上URL，可直接作为项目导入Eclipse #####
##### 工程结构说明 #####
  * build.xml 构建脚本
  * lib 客户端依赖包
  * src 源代码 (包括客户端与服务器端)
  * web 服务器端WEB应用根目录
    * client 客户端构建并签名后的jar包发布目录(JNLP读取)
    * scripts JS脚本库
    * WEB-INF 服务器端依赖包
      * lib 服务器端依赖包
      * web.xml