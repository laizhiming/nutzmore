nutz-plugins-fiddler
==================================

简介(可用性:生产,维护者:wendal,rekoe)
==================================
代码来自 https://github.com/monkeyWie/proxyee
#### 暂且叫Fiddler吧 可以替代 Charles 数据抓取的一些功能 可以直接把控制台上的日志保存 编辑 或者修改

### HTTP代理服务器
    支持HTTP、HTTPS、WebSocket,HTTPS采用动态签发SSL证书,可以拦截http、https的报文并进行处理。
    例如：http(s)协议抓包,http(s)动态替换请求内容或响应内容等等。
#### HTTPS支持
    需要导入项目中的CA证书(src/resources/ca.crt)至受信任的根证书颁发机构。
    可以使用CertDownIntercept拦截器，开启网页下载证书功能，访问http://serverIP:serverPort即可进入。
    注：安卓手机上安装证书若弹出键入凭据存储的密码，输入锁屏密码即可。
#### 二级代理
    可设置二级代理服务器,支持http,socks4,socks5。
#### 启动
```
//new HttpProxyServer().start(9999);

  new HttpProxyServer()
//        .proxyConfig(new ProxyConfig(ProxyType.SOCKS5, "127.0.0.1", 1085))  //使用socks5二级代理
    .proxyInterceptInitializer(new HttpProxyInterceptInitializer() {
      @Override
      public void init(HttpProxyInterceptPipeline pipeline) {
        pipeline.addLast(new CertDownIntercept());  //处理证书下载
        pipeline.addLast(new HttpProxyIntercept() {
          @Override
          public void beforeRequest(Channel clientChannel, HttpRequest httpRequest,
              HttpProxyInterceptPipeline pipeline) throws Exception {
            //替换UA，伪装成手机浏览器
            httpRequest.headers().set(HttpHeaderNames.USER_AGENT,
                "Mozilla/5.0 (iPhone; CPU iPhone OS 9_1 like Mac OS X) AppleWebKit/601.1.46 (KHTML, like Gecko) Version/9.0 Mobile/13B143 Safari/601.1");
            //转到下一个拦截器处理
            pipeline.beforeRequest(clientChannel, httpRequest);
          }
  
          @Override
          public void afterResponse(Channel clientChannel, Channel proxyChannel,
              HttpRequest httpRequest, HttpResponse httpResponse,
              HttpProxyInterceptPipeline pipeline) throws Exception {
            //拦截响应，添加一个响应头
            httpResponse.headers().add("intercept", "test");
            //转到下一个拦截器处理
            pipeline.afterResponse(clientChannel, proxyChannel, httpRequest, httpResponse);
          }
        });
      }
    })
    .httpProxyExceptionHandle(new HttpProxyExceptionHandle() {
      @Override
      public void beforeCatch(Channel clientChannel, Throwable cause) {
        super.beforeCatch(clientChannel, cause);
      }
  
      @Override
      public void afterCatch(Channel clientChannel, Channel proxyChannel, Throwable cause) {
        super.afterCatch(clientChannel, proxyChannel, cause);
      }
    })
    .start(9999);
```

#### 流程
SSL握手

![SSL握手](https://sfault-image.b0.upaiyun.com/751/727/751727588-59ccbe3293bef_articlex)

HTTP通讯

![HTTP通讯](https://sfault-image.b0.upaiyun.com/114/487/1144878844-59ccbe42037b6_articlex)
