package com.tao.tao.rest.test;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

public class JedisTest {
	@Test
	/**
	 * 测试单机版单例
	 */
	public  void testSingleJedis(){
		//创建jedis对象
		Jedis jedis=new Jedis("192.168.100.134", 6379);
		//调用jedis对象方法 。redis命令
		jedis.set("key1", "111");
		//关闭
		jedis.close();
	}
	/**
	 * 连接池
	 */
	@Test
	public void testRedisPool(){
		JedisPool pool=new JedisPool("192.168.100.134", 6379);
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		jedis.close();
		pool.close();
	}
	/**
	 * 集群版本
	 */
	@Test
	public  void testCluster(){
		Set<HostAndPort> nodes=new HashSet<HostAndPort>();
		nodes.add(new HostAndPort("192.168.100.134", 7001));
		nodes.add(new HostAndPort("192.168.100.134", 7002));
		nodes.add(new HostAndPort("192.168.100.134", 7003));
		nodes.add(new HostAndPort("192.168.100.134", 7004));
		nodes.add(new HostAndPort("192.168.100.134", 7005));
		nodes.add(new HostAndPort("192.168.100.134", 7006));
		JedisCluster cluster=new JedisCluster(nodes);
		cluster.set("keya", "aaaa");
		cluster.close();
	}
	
	/**
	 * 测试jedisspring单机
	 * @return 
	 */
	@Test
	public void testJedisSpringSingle(){
		
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:/resources/spring/applicationContext-*.xml");
		JedisPool pool = (JedisPool) applicationContext.getBean("redisClient");
		Jedis jedis = pool.getResource();
		System.out.println(jedis.get("key1"));
		jedis.close();
		pool.close();
	}
	/**
	 * 测试redis集群spring
	 */
	@Test
	public void testJedisSpringCluster(){
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:/resources/spring/applicationContext-*.xml");
		JedisCluster cluster=(JedisCluster) applicationContext.getBean("jedisClient");
		System.out.println(cluster.get("keya"));
		cluster.close();
	}
}
