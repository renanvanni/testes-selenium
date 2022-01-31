import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	private WebDriver driver;
	
	private DSL dsl;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveInteragirComAlertSimples() {
		//driver.findElement(By.id("alert")).click();
		dsl.clicarBotao("alert");
		//Alert alert = driver.switchTo().alert();
		//String texto = alert.getText();
		String texto = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Alert Simples", texto);
		//alert.accept();
		
		//driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		dsl.escrever("elementosForm:nome", texto);
	}

	@Test
	public void deveInteragirComAlertConfirm() {
		//driver.findElement(By.id("confirm")).click();
		dsl.clicarBotao("confirm");
		//Alert alerta = driver.switchTo().alert();
		//Assert.assertEquals("Confirm Simples", alerta.getText());
		//alerta.accept();
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
		Assert.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
		//alerta.accept();
		
		//driver.findElement(By.id("confirm")).click();
		dsl.clicarBotao("confirm");
		//alerta = driver.switchTo().alert();
		//Assert.assertEquals("Confirm Simples", alerta.getText());
		//alerta.dismiss();
		Assert.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
		//Assert.assertEquals("Negado", alerta.getText());
		//alerta.dismiss();
		Assert.assertEquals("Negado", dsl.alertaObterTextoENega());
	}
	
	@Test
	public void deveInteragirComAlertPrompt() {
		//driver.findElement(By.id("prompt")).click();
		dsl.clicarBotao("prompt");
		//Alert alerta = driver.switchTo().alert();
		//Assert.assertEquals("Digite um numero", alerta.getText());
		Assert.assertEquals("Digite um numero", dsl.alertaObterTexto());
		//alerta.sendKeys("12");
		dsl.alertaEscrever("12");
		//alerta.accept();
		//Assert.assertEquals("Era 12?", alerta.getText());
		Assert.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
		//alerta.accept();
		//Assert.assertEquals(":D", alerta.getText());
		Assert.assertEquals(":D", dsl.alertaObterTextoEAceita());
		//alerta.accept();
	}
}
