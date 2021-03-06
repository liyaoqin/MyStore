package com.taotao.rest.jedis;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

import java.io.IOException;
import java.util.HashSet;


public class JedisTest {
    private static final Logger LOGGER=LoggerFactory.getLogger(JedisTest.class);

    @Test
    public void testJedisSingle() {

        //创建一个jedis的对象。
        Jedis jedis = new Jedis("192.168.5.132", 6379);
        //调用jedis对象的方法，方法名称和redis的命令一致。
        jedis.set("key1", "jedis test");
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis。
        jedis.close();
    }
    @Test
    public void testJedisPool() {
        //创建jedis连接池
        JedisPool pool = new JedisPool("192.168.5.132", 6379);
        //从连接池中获得Jedis对象
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        //关闭jedis对象
        jedis.close();
        pool.close();
    }

    @Test
    public void testJedisCluster() throws IOException {
        LOGGER.debug("调用redisCluster开始");
        HashSet<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.5.132", 7001));
        nodes.add(new HostAndPort("192.168.5.132", 7002));
        nodes.add(new HostAndPort("192.168.5.132", 7003));
        nodes.add(new HostAndPort("192.168.5.132", 7004));
        nodes.add(new HostAndPort("192.168.5.132", 7005));
        nodes.add(new HostAndPort("192.168.5.132", 7006));
        LOGGER.info("创建一个JedisCluster对象");
        JedisCluster cluster = new JedisCluster(nodes);
        LOGGER.debug("设置可以key1的值为1000");
        cluster.set("key1", "1000");
        LOGGER.debug("从Redis中取key1的值");
        String string = cluster.get("key1");
        System.out.println(string);
        cluster.close();
        LOGGER.error("关闭连接失败");
    }

    @Test
    public void testSpringJedisSingle() {
      ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
       JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
        Jedis jedis = pool.getResource();
        String string = jedis.get("key1");
        System.out.println(string);
        jedis.close();
        pool.close();
    }
    @Test
    public void testSpringJedisCluster() throws IOException {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        JedisCluster jedisCluster =  (JedisCluster) applicationContext.getBean("redisClient");
        String string = jedisCluster.get("key1");
        System.out.println(string);
        jedisCluster.close();
    }


}
