package top.itjee.www.zchain.webcontroller.conf;


import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import top.itjee.www.zchain.webcontroller.info.AppInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Configuration
public class ZookeeperConf {

    @Value("${zk.url}")
    private String zkUrl;

    @Autowired
    private CuratorFramework zkClient;

    @Autowired
    private AppInfo appInfo;

    @Bean(value = "zkClient")
    public CuratorFramework getCuratorFramework() {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
        return client;
    }

    @PostConstruct
    public void getInfo() {
        try {
            Stat stat = zkClient.checkExists().forPath("/clc-web");
            System.out.println("我是:" + stat);
            if (stat == null) {
                String s = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/clc-web/app/name", appInfo.appName.getBytes());
                String v = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath("/clc-web/app/version", appInfo.version.getBytes());

                System.out.println("appname:" + s);
                System.out.println("appversion:" + v);


            } else {
                byte[] bs = zkClient.getData().forPath("/clc-web/app/name");
                String name = new String(bs);

                byte[] vs = zkClient.getData().forPath("/clc-web/app/version");
                String version = new String(vs);

                System.out.println("appname:" + name);
                System.out.println("appversion:" + version);
            }


            final NodeCache nodeCache = new NodeCache(zkClient, "/clc-web/app/name", false);
            nodeCache.start(true);
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println("变化：" + new String(nodeCache.getCurrentData().getData()));
                }
            });

            /**
             * 监听子节点的变化情况
             */
            final PathChildrenCache childrenCache = new PathChildrenCache(zkClient, "/clc-web/app", true);
            childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            childrenCache.getListenable().addListener(
                    new PathChildrenCacheListener() {
                        @Override
                        public void childEvent(CuratorFramework client, PathChildrenCacheEvent event)
                                throws Exception {
                            switch (event.getType()) {
                                case CHILD_ADDED:
                                    System.out.println("CHILD_ADDED: " + event.getData().getPath());
                                    break;
                                case CHILD_REMOVED:
                                    System.out.println("CHILD_REMOVED: " + event.getData().getPath());
                                    break;
                                case CHILD_UPDATED:
                                    System.out.println("CHILD_UPDATED: " + event.getData().getPath());
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
            );

            /**
             * 监听多级子节点的变化情况
             */
            final TreeCache treeCache = TreeCache.newBuilder(zkClient, "/clc-web/app").setCacheData(true).setMaxDepth(5).build();
            treeCache.getListenable().addListener(new TreeCacheListener() {
                public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent treeCacheEvent) throws Exception {
                    System.out.println("treeCacheEvent: " + treeCacheEvent);
                    if(treeCacheEvent.getData() != null){
                        System.out.println("path: " + treeCacheEvent.getData().getPath());
                    }
                }
            });
            //没有开启模式作为入参的方法
            treeCache.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}