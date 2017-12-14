/**
 * @jiang
 * 下午2:45:39
 */
package co.fy.gateway.server.utils;
/*
import org.junit.Test;*/

import java.util.Random;

/**
 * @author jk
 *
 */
public class GetRandomNumberUtil {

	//生成随机数字和字母,
	public static String getStringIntegerRandom(int length) {
		 String val="";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
			//输出字母还是数字
			if( "char".equalsIgnoreCase(charOrNum) ) {
				//输出是大写字母还是小写字母
				int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
				val += (char)(random.nextInt(26) + temp);
			} else if( "num".equalsIgnoreCase(charOrNum) ) {
				val += String.valueOf(random.nextInt(10));
			}
		}
		return val;
	};
	//生成随机数字
	public static String getIntegerRandom(int length) {
		String val="";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			val += String.valueOf(random.nextInt(10));
		}
		return val;
	};
	//生成随机字母
	public static String getStringRandom(int length) {
		String val="";
		Random random = new Random();
		//参数length，表示生成几位随机数
		for(int i = 0; i < length; i++) {
			//输出是大写字母还是小写字母
			int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
			val += (char)(random.nextInt(26) + temp);
		}
		return val;
	}
	/*@Test
	public void test(){
		System.out.println(GetRandomNumberUtil.getStringIntegerRandom(19));
	}*/
}
