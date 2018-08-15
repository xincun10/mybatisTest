package com.generate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * 这个是逆向工程的代码部分，和其他文件之间是独立的
 * 对应的配置文件是generatorConfig.xml
 * 对应的jar包mybatis-generator-core.jar
 * @author carazheng
 * 配置文件搞好了之后，直接执行下面的程序就可以生成代码了。
 */
public class GeneratorSqlmap {

	public void generator() throws Exception
	{
		List<String> warnings = new ArrayList<>();
		boolean overwrite = true;
		//指定逆向工程配置文件
		File configFile = new File("config/generatorConfig.xml");
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(configFile);
		DefaultShellCallback callback = new DefaultShellCallback(overwrite);
		MyBatisGenerator mybatisGenerator = new MyBatisGenerator(config, callback, warnings);
		mybatisGenerator.generate(null);
	}
	
	public static void main(String[] args) {
		try {
			GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();
			generatorSqlmap.generator();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
