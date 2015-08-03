# 演示程序使用说明 #
下载struts4rcp-releases-0.1.zip，其中包括：
  * struts4rcpdemo 演示工程源代码
  * struts4rcpdemo-0.1.war 演示war包
  * struts4rcpminidemo-0.1.war 裁减最小演示war包(只包含C/S测试)
将演示war包放到：TOMCAT/webapp/struts4rcpdemo-0.1.war，
访问：http://localhost:8080/struts4rcpdemo-0.1/
##### 将看到： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/index.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/index.gif)
##### 六个超链接分别表示： #####
  * Client 演示客户端访问(使用jnlp启动，参见：[WebStart集成说明](http://code.google.com/p/struts4rcp/wiki/webstart))
  * JS 演示AJAX访问
  * JSP 演示JSP页面访问
  * CTL 演示CommonTemplate模板页面访问
  * FTL 演示FreeMarker模板页面访问
  * VTL 演示Velocity模板页面访问
##### 1. 点击"Client"，加载成功后，弹出配置窗口： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/config.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/config.gif)
##### 点击"确定"后，弹出窗口： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/client.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/client.gif)
##### 表单元素说明： #####
  * 用户名，密码，用于模拟登录业务逻辑数据，服务器端LonginAction将调用：loginService.login(username, password);
  * 模拟服务器端耗时操作(ms)，服务器端LonginAction将调用：Thread.sleep(delay);
  * 模拟服务器端抛出异常，服务器端LonginAction将调用：if (exception) throw new LoginException("模拟登录失败");
  * 是否允许转为后台运行，是否允许中止运行，用于控制下图中的两个按钮：
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/exetution.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/exetution.gif)
##### 按扭说明： #####
  * 同步运行：在UI线程内执行Action，执行过程中，整个UI线程将被阻塞
  * 新线程运行：启动一个新的线程来执行Action
  * 异步运行：与前面的新线程运行相同，用新的线程来执行Action，但由框架来管理线程和异常，通过ActionCallback接口代替Runnable
  * 后台运行：前面的几种执行方式都会发布前台执行事件，图形工具包将弹出模态窗口，后台运行方式将不弹出模态框
##### 状态栏说明： #####
你有没有注意到右下角那个按钮：
#####  #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/status.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/status.gif)
#####  #####
此按钮由框架实现，详细请参见：[图形工具包使用说明](http://code.google.com/p/struts4rcp/wiki/gui)，调用方式如：
```
ConnectionButton statusButton = new ConnectionButton(frame, Actions.getClient());
statusBar.add(BorderLayout.EAST, statusButton);
```
##### 2. 点击"JS"，打开JS测试页面： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js.gif)
##### 表单作用与上面的客户端界面相同 #####
##### 点击"Sync Loign"，将弹出：(点击时，按钮有卡住) #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_sync.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_sync.gif)
##### 点击"Async Loign"，将弹出： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_async.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_async.gif)
##### 如果选中“Server Side Thorw Exception”，将弹出： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_exception.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/js_exception.gif)
##### 3. 点击"JSP"，打开JSP测试页面： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/jsp.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/jsp.gif)
##### 对应页面： #####
struts4rcpdemo/web/jsp\_login.html
##### 点击"Loign"，将跳转到： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/jsp_result.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/jsp_result.gif)
##### 对应页面： #####
struts4rcpdemo/src/com/xxx/demo/action/LoginAction.jsp
##### 4. 点击"CTL"，打开CTL测试页面： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ctl.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ctl.gif)
##### 对应页面： #####
struts4rcpdemo/web/ctl\_login.html
##### 点击"Loign"，将跳转到： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ctl_result.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ctl_result.gif)
##### 对应页面： #####
struts4rcpdemo/src/com/xxx/demo/action/LoginAction.ctl
##### 5. 点击"FTL"，打开FTL测试页面： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ftl.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ftl.gif)
##### 对应页面： #####
struts4rcpdemo/web/ftl\_login.html
##### 点击"Loign"，将跳转到： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ftl_result.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/ftl_result.gif)
##### 对应页面： #####
struts4rcpdemo/src/com/xxx/demo/action/LoginAction.ftl
##### 6. 点击"VTL"，打开VTL测试页面： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/vtl.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/vtl.gif)
##### 对应页面： #####
struts4rcpdemo/web/vtl\_login.html
##### 点击"Loign"，将跳转到： #####
![http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/vtl_result.gif](http://struts4rcp.googlecode.com/svn/trunk/docs/views/demo/vtl_result.gif)
##### 对应页面： #####
struts4rcpdemo/src/com/xxx/demo/action/LoginAction.vtl