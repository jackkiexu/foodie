package com.lami.filter;

import java.io.File;

/**
 * Created by xujiankang on 2017/11/29.
 */
public interface  DynamicCodeCompiler {

    Class compile(String sCode, String sName) throws Exception;

    Class compile(File file) throws Exception;
}
