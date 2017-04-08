package cn.itcast.zt;

import cn.itcast.zt.web.HelloWorldController;
import cn.itcast.zt.web.UserController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRestfulApplication.class)
public class SpringbootRestfulApplicationTests {

	private MockMvc mvc ;

	@Before
	public void setUp() {
		mvc = MockMvcBuilders.standaloneSetup(
							new UserController(),
							new HelloWorldController()
						).build() ;
	}

	@Test
	public void getHello() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/hello").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Hello World")));
	}

	@Test
	public void testUserController() throws  Exception {
		//  	测试UserController
		RequestBuilder requestBuilder = null ;

		// 1、get查一下user列表，应该为空
		requestBuilder = get("/users/");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]"))) ;

		// 2、post提交一个user
		requestBuilder = post("/users/")
						.param("id", "1000")
						.param("name","张三")
						.param("age", "12");
		mvc.perform(requestBuilder)
//				.andDo(MockMvcResultHandlers.print())
				.andExpect(content().string(equalTo("success"))) ;

		// 3、get获取user列表，应该有刚才插入的数据
		requestBuilder = get("/users/");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1000,\"name\":\"张三\",\"age\":12}]"))) ;

		// 4、put修改id为1的user
		requestBuilder = put("/users/1000")
				.param("name", "李四")
				.param("age", "22") ;

		mvc.perform(requestBuilder)
				.andExpect(content().string(equalTo("success")));

		// 5、get一个id为1000的user
		requestBuilder = get("/users/");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[{\"id\":1000,\"name\":\"李四\",\"age\":22}]"))) ;

		// 6、del删除id为1的user
		requestBuilder = delete("/users/1000") ;
		mvc.perform(requestBuilder)
				.andExpect(content().string(equalTo("success"))) ;

		// 7、get查一下user列表，应该为空
		requestBuilder = get("/users/");
		mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("[]"))) ;
	}
}
