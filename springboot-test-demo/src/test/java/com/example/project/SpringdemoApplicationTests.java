package com.example.project;

import com.example.project.dao.UserDao;
import com.example.project.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@Rollback(value=true)
public class SpringdemoApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private UserDao userDao;

	@Test
	public void testLogin() throws Exception {
		User user = new User();
		user.setNick("user001");
		user.setPassword("pw001");
		userDao.save(user);
		//存在
		mockMvc.perform(get("/login?userName=user001&password=pw001")
				.accept(MediaType.APPLICATION_JSON)) //执行请求
				.andExpect(content().contentType("application/json;charset=utf-8")) //验证响应contentType
				//打印请求和响应的详细信息
				//.andDo(print())
				.andExpect(jsonPath("$.meta.errCode").value(0)) //使用Json path验证JSON 请参考http://goessner.net/articles/JsonPath/
		;         //打印出请求和相应的内容;

		//不存在
		MvcResult result = mockMvc.perform(get("/login?userName=user002&password=pw002")
				.accept(MediaType.APPLICATION_JSON)) //执行请求
				.andExpect(jsonPath("$.meta.errCode").value(-1))
				.andReturn();
	}

}
