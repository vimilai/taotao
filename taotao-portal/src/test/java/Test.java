import java.util.HashMap;
import java.util.Map;

import com.taotao.common.utils.HttpClientUtil;

public class Test {
	
	
	
	@org.junit.Test
	public void test(){
		Map<String, String> a=new HashMap<>();
		a.put("q", "java");
		String string = HttpClientUtil.doGet("http://localhost:8083/search/query",a);
		System.out.println(string);
	}

}
