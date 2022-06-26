package io.github.choijy.example.security;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import io.github.choijy.example.security.account.AccountService;

/**
 * Description : Sample Controller test class.
 *
 * Created by jychoi on 2022/06/27.
 */
@WebMvcTest(controllers = SampleController.class)
@Import(SecurityConfiguration.class)
class SampleControllerTest {

	@Autowired
	private SampleController sampleController;

	@MockBean
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@WithAnonymousUser
	@Test
	void index_anonymous() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isUnauthorized());
	}

	@WithMockUser(username = "user", roles = "USER")
	@Test
	void index_user() throws Exception {
		mockMvc.perform(get("/"))
			.andDo(print())
			.andExpect(status().isOk());
	}

	@WithMockUser(username = "admin", roles = "USER")
	@Test
	void admin_user() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isForbidden());
	}

	@WithAdmin
	@Test
	void admin_admin() throws Exception {
		mockMvc.perform(get("/admin"))
			.andDo(print())
			.andExpect(status().isOk());
	}
}