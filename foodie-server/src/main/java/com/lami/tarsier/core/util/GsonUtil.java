/**
 * 
 */
package com.lami.tarsier.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author yeming
 *
 */
public class GsonUtil {
	private static Gson getGson() {
		return new GsonBuilder().disableHtmlEscaping().create();
	}
	
	public static String toGson(Object obj) {
		return getGson().toJson(obj);
	}
	@SuppressWarnings("unchecked")
	public static Object fromGson(String json,Class classOfT){
		try{
		return getGson().fromJson(json, classOfT);
		}catch(Exception e){
			return null;
		}
	}
}
