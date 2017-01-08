package com.lami.tarsier.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class BlackKeyUtil {

	private static Logger logger = Logger.getLogger(BlackKeyUtil.class);
	private static Set<String> blackKeySet = new HashSet<String>();
	private static File blackKeyFile = null;
	private static long modify;
	
	private static boolean isModified(){
		return blackKeyFile.lastModified()!= modify;
	}
	
	public static void loadBlackKey(){
		FileInputStream in = null;
		InputStreamReader inR = null;
		BufferedReader br = null;
		try {
			String path = BlackKeyUtil.class.getClassLoader().getResource("blackkey.txt").getPath();
			blackKeyFile = new File(path);
			modify = blackKeyFile.lastModified();
			in = new FileInputStream(blackKeyFile);
			inR = new InputStreamReader(in,"utf-8");
			br = new BufferedReader(inR);
			String line;
			while((line=br.readLine()) != null){
				if(!"".equals(line.trim())){
					blackKeySet.add(line);
				}
			}
		}  catch (IOException e) {
			logger.info(e.getMessage());
		}finally{
			try {
				if(in != null){
					in.close();
				}
				if(inR != null){
					inR.close();
				}
				if(br != null){
					br.close();
				}
			} catch (IOException e) {
			}
		}
	}
	
	/**
	 * 检查是否含有关键词
	 */
	public static boolean isBlack(String word){
		if(isModified())loadBlackKey();
		for(String key : blackKeySet){
			if(word.indexOf(key)>=0){
				return true;
			}
		}
		return false;
	}
	
	

}
