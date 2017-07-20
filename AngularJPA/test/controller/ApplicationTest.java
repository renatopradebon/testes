package controller;

import app.config.ConfigResource;
import app.config.Constants;
import app.security.SecurityUtils;
import app.security.jwt.JWTAuthenticationFilter;
import app.service.mail.MailService;
import app.util.RandomUtil;
import app.service.UserService;
import controller.dto.LoginDTO;
import controller.dto.UserDTO;
import py.com.angularjpa.AbstractAuditingEntity;
import py.com.angularjpa.AuditListner;
import py.com.angularjpa.Authority;
import py.com.angularjpa.User;
import repository.AuthorityRepository;
import repository.UserRepository;
import static controller.AbstractTest.buildArchive;
import java.util.Map;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.Response;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;

/**
 * Abstract class for application packaging.
 *
 */
public abstract class ApplicationTest extends AbstractTest {

    protected static final String USERNAME = "admin";
    protected static final String PASSWORD = "admin";
    protected static final String INVALID_PASSWORD = "invalid_password";
    protected static final String INCORRECT_PASSWORD = "pw";
    private static final String AUTH_RESOURCE_PATH = "api/authenticate";

    protected String tokenId;

    public static WebArchive buildApplication() {
        return buildArchive().addPackages(true, ConfigResource.class.getPackage(), MailService.class.getPackage(), UserDTO.class.getPackage(), SecurityUtils.class.getPackage(), RandomUtil.class.getPackage())
                .addClass(User.class).addClass(Authority.class).addClass(AbstractAuditingEntity.class).addClass(AuditListner.class)
                .addClass(UserRepository.class).addClass(AuthorityRepository.class).addClass(UserService.class)
                .addAsResource(new ClassLoaderAsset("config/application.properties"), "config/application.properties")
                .addAsResource(new ClassLoaderAsset("i18n/messages.properties"), "i18n/messages.properties")
                .addClass(UserJWTController.class).addPackage(JWTAuthenticationFilter.class.getPackage());
    }

    @Before
    public void setUp() throws Exception {
        login(USERNAME, PASSWORD);
    }

    @After
    public void tearDown() {
        logout();
    }

    protected Response login(String username, String password) {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername(username);
        loginDTO.setPassword(password);
        Response response = target(AUTH_RESOURCE_PATH).post(Entity.json(loginDTO));
        tokenId = response.getHeaderString(Constants.AUTHORIZATION_HEADER);
        return response;
    }

    protected void logout() {
        tokenId = null;
    }

    @Override
    protected Invocation.Builder target(String path) {
        return super.target(path).header(Constants.AUTHORIZATION_HEADER, tokenId);
    }

    @Override
    protected Invocation.Builder target(String path, Map<String, Object> params) {
        return super.target(path, params).header(Constants.AUTHORIZATION_HEADER, tokenId);
    }

}
