package model;

import generate.Movie;
import generate.MovieDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MovieDAO {

	MovieDao movieDao;

	public MovieDAO() {
		SqlSessionFactory sqlSessionFactory = null;
		try {
			sqlSessionFactory = getSqlSessionFactory();
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession sqlSession = sqlSessionFactory.openSession();
		movieDao = sqlSession.getMapper(MovieDao.class);


	}

	public SqlSessionFactory getSqlSessionFactory() throws IOException {
		String resource = "mybatis-config.xml";
//        System.out.println(Resources.getResourceAsFile(".").getAbsolutePath());

		InputStream inputStream = Resources.getResourceAsStream(resource);
		return new SqlSessionFactoryBuilder().build(inputStream);
	}

	public boolean add(Movie movie) {
		return movieDao.insert(movie) > 0;
	}

	public boolean del(int id) {
		return movieDao.deleteByPrimaryKey(id) > 0;
	}
}
