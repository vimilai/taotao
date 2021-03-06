package com.taotao.rest.dao;

public interface JedisClient {
	String get(String key);
	String set(String key,String value);
	String hget(String hkey, String key);
	long hset(String hkey, String key, String value);
	long expire(String key, int second);
	long ttl(String key);
	long incr(String key);
	long del(String  key);
	long hdel(String  hkey,String  key);
}
