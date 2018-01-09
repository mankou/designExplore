package mang.demo.designExplore.runinfo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunInfoTest {
	private static final Logger log = LoggerFactory.getLogger(RunInfoTest.class);
	
	@Test
	public void testRunInfo(){
		//需求描述
		//主要记录下程序运行时间 以及其它一些个性化的统计需求
		//TODO 目前我感觉这种方式也不好,希望用到装饰器模式 一层一层继续来实现自己的功能
		
		//程序初始化
		RunInfoUtil.runinfo=new SimpleRunInfo();
		
		
		//中间做一些业务逻辑,然后触发该方法
		SimpleRunInfo.addDownloadCount(30);
		
		
		log.info(RunInfoUtil.getRunInfoStr());
		
	}
}
