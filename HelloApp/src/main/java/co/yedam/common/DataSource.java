package co.yedam.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DataSource {
	private static SqlSessionFactory sqlSessionFactory;
	
	//싱글톤
	
	//생성자
	private DataSource() {}
	
	//인스턴스
	public static SqlSessionFactory getInstance() {
		String resource = "config/mybatis-config.xml"; //환경파일
		InputStream is = null;
		try {
			//위의 파일을 읽어서 인풋스트림을 만들어준다
			is = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//매개값으로 쓴다 환경파일을 만들어서 세션팩토리를 만든다
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
		return sqlSessionFactory;
	}
}
