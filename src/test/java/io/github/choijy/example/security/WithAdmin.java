package io.github.choijy.example.security;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithMockUser;

/**
 * Description :
 *
 * Created by jychoi on 2022/06/27.
 */
@Retention(RetentionPolicy.RUNTIME)
@WithMockUser(username = "admin", roles = "ADMIN")
public @interface WithAdmin {
}
