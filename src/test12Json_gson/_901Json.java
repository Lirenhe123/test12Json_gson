package test12Json_gson;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.JavaIdentifierTransformer;

//使用json将json不能识别的字符转换成json可识别的字符，然后使用反射机制生成javabean
//注意那个Time，首字母大写是json不可识别的，toBean是调用setTime时不可以
public class _901Json {
	public static void main(String[] args) {
//		String json = "{\"AutoCode\":\"1\"}";
		String json = "{\"Time\":\"20170724174800\",\"title\":\"关于永春县广播电视事业局地面数字电视发射机及配套系统采购及服务项目招标文件征求意见公告\"}";
		JSONObject jsonObject = JSONObject.fromObject(json);
		
		JsonConfig config = new JsonConfig();
		config.setJavaIdentifierTransformer(new JavaIdentifierTransformer() {
			@Override
			public String transformToJavaIdentifier(String str) {
				char[] chars = str.toCharArray();
				chars[0] = Character.toLowerCase(chars[0]);
				return new String(chars);
			}
		});
		config.setRootClass(TestBean.class);
		Object bean = JSONObject.toBean(jsonObject, config);
		TestBean testBean = (TestBean) bean;
		System.out.println(testBean.getTime());
		
	}
}


