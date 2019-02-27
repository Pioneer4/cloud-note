package note;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class Md5Test {
	@Test
	public void testMd5() {
		String str = "123456";
		String md5 = DigestUtils.md5Hex(str);
		System.out.println(md5);
		
		String salt = "今天吃了吗？";
		md5 = DigestUtils.md5Hex(salt + str.trim());
		System.out.println(md5);
	}

}
