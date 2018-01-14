package mang.demo.designExplore;

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import mang.demo.designExplore.runinfo.RunInfoUtil;
import mang.demo.designExplore.runinfo.SimpleRunInfo;
import mang.util.common.ConfigUtil;

/**
 * 演示一般的java工程加载spring示例
 *
 */
public class AppRun {
	private static final transient Logger log = LoggerFactory.getLogger(AppRun.class);

	public static void main(String[] args) {
		System.out.println("Hello World!");
		String env = System.getProperty("service.env");
		System.out.println("env:" + env);

		//log4j
		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
//		URL url = ConfigUtil.getUrlFromDefault("config/log4j.xml");
//		DOMConfigurator.configure(url);
		
		
		//log4j2 如果路径不在默认的类路径根路径下 需要指定路径时可按如下方式写
		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
//		File file = new File("path/to/a/different/log4j2.xml");
		URL url = ConfigUtil.getUrlFromDefault("config/log4j2.xml",false);
		try {
			context.setConfigLocation(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		// 优先从工作空间的 conf config目录下取 如果取不到,再从类路径下取
		String springXmlParentPath = ConfUtil.getSpringXml(env);
		System.out.println("spring xml path:" + springXmlParentPath);
		// XXX 对于maven打出的shade包 则用java -jar 启动就会报错 目前我是用分发包所以没有问题
		
		// 为什么加file:? 因为在pszxjob项目中测试在windows中正常，在linux上报找不到文件 后来网上搜了说加上这句就好了
		// fix 在linux下运行如果不加file 其会按相对路径处理 导致找不到配置文件
		if (springXmlParentPath.startsWith("/")) {
			springXmlParentPath = "file:" + springXmlParentPath;
		}
		//TODO 以后研究指定多个spring配置文件的方式,我不想在spring的配置文件中import了,这样不灵活
		ApplicationContext ctx = new FileSystemXmlApplicationContext(springXmlParentPath);

		log.info("program start ok");
		RunInfoUtil.runinfo=new SimpleRunInfo();

//		TestBO testBO = (TestBO) ctx.getBean("testBO");
//		List lis = testBO.testQuery();
//
//		testBO.testSaveOrUpdate();
//		List lis2 = testBO.testQuery();

		log.info(RunInfoUtil.getRunInfoStr());
	}
}
