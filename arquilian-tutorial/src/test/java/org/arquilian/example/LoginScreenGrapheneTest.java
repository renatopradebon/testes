package org.arquilian.example;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;
import static org.jboss.arquillian.graphene.Graphene.waitAjax;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@RunWith(Arquillian.class)
public class LoginScreenGrapheneTest {

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return Deployments.createLoginScreenDeployment();
	}

	@Drone
	private WebDriver browser;

	@ArquillianResource
	private URL deploymentUrl;

	@FindBy(id = "loginForm:userName") // 2. injects an element
	private WebElement userName;

	@FindBy(id = "loginForm:password")
	private WebElement password;

	@FindBy(id = "loginForm:login")
	private WebElement loginButton;

	@Test
	public void should_login_successfully() {
		browser.get(deploymentUrl.toExternalForm() + "login.jsf"); // 1. open the tested page

		userName.sendKeys("demo"); // 3. control the page
		password.sendKeys("demo");

		guardHttp(loginButton).click();

		assertEquals("Welcome", facesMessage.getText().trim());
		whoAmI.click();
		waitAjax().until().element(signedAs).is().present();
		assertTrue(signedAs.getText().contains("demo"));
	}
}