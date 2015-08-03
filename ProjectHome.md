项目地址：http://struts4rcp.googlecode.com

#### 1. 简介 ####
Struts(for)RCP是一个适用于RCP/RIA应用的轻量级MVC框架，与[Struts](http://struts.apache.org)功能相似，提供远程MVC控制器支持，简化模型与视图的交互过程。
  * MVC: Model View Controller
  * RCP: Rich Client Platform
  * RIA: Rich Internet Application
  * Struts: 一个适用于Web页面"请求-响应"方式的MVC框架
#### 2. 对比 ####
常规C/S应用中，通常都采用透明化的远程方法调用方式与服务器端通讯，即：将Service接口同时部署于客户端，并在客户端生成Service接口的Stub实现，通过各种协议代理访问服务器端的Service实现，此类工具如：RMI, WebService, XML-RPC/JSON-RPC, HttpInvoker等。为什么还要加一层Action控制器？
  * 远程调用Service接口，相当于MVC中的视图直接调用了模型，增加控制器，使职责更清晰。
  * Serivce域模型通常是无状态的，增加远程控制器，管理服务器端状态。
  * Service接口粒度过细，使得业务逻辑向客户端倾斜，产生过多的请求次数，增加控制器，确保大粒度请求。
  * 客户端部署Service接口比较繁琐，增加控制器作为中介者，简化耦合。
  * 在控制器层，同样可以实现透明化调用，用户同样不需要关心交互层面的信息。
  * 在控制器中更易于处理天然的(不需要反射或字节码增强的)AOP截面和事件模型，方便拦截器，转换器，校验器，安全控制等的扩展。
  * 对于大量长期使用Struts的开发人员，转入RCP开发时，更易于理解和使用，降低学习成本。
#### 3. 特性 ####
  * 以数据为中心的控制器
  * 友好的执行过程拦截器
  * 完善的集成扩展点
  * 可管理的传输队列
  * 全面的事件通知
  * 多服务器传输支持
#### 4. 文档 ####
  * [演示程序使用说明](http://code.google.com/p/struts4rcp/wiki/demo)
  * [综合使用说明](http://code.google.com/p/struts4rcp/wiki/guide)
  * [服务器端使用说明](http://code.google.com/p/struts4rcp/wiki/server)
  * [客户端使用说明](http://code.google.com/p/struts4rcp/wiki/client)
  * [图形工具包使用说明](http://code.google.com/p/struts4rcp/wiki/gui)
  * [WebStart集成说明](http://code.google.com/p/struts4rcp/wiki/webstart)
  * [配置说明](http://code.google.com/p/struts4rcp/wiki/config)
  * [扩展说明](http://code.google.com/p/struts4rcp/wiki/extension)
  * [设计说明](http://code.google.com/p/struts4rcp/wiki/design)
#### 5. 支持 ####
如有疑问或建议，请发送邮件到：![http://struts4rcp.googlecode.com/svn/trunk/docs/www/images/lf.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/www/images/lf.gif)，谢谢！