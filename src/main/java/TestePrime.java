import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TestePrime {
	
	private WebDriver driver;
	
	private DSL dsl;

	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		dsl = new DSL(driver);
	}
	
	@After
	public void finaliza() {
		//driver.quit();
	}
	
	@Test
	public void deveInteragirComRadioPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml?jfwid=4682d");
		dsl.clicarRadio(By.xpath("//table[@id='j_idt312:console']//td//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt312:console:0"));
		
		dsl.clicarRadio(By.xpath("//label[.='Option2']/..//span"));
		Assert.assertTrue(dsl.isRadioMarcado("j_idt312:console:1"));
	}
	
	@Test
	public void daveInteragirComSelectPrime() {
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml?jfwid=5e92c");
		//dsl.clicarRadio(By.xpath("//select[@id='j_idt311:option_input']/../..//span"));
		//dsl.clicarRadio(By.xpath("//ul[@id='j_idt311:option_items']//li[.='Option2']"));
		dsl.selecionarComboPrime("j_idt311:option", "Option2");
		Assert.assertEquals("Option2", dsl.obterTexto("j_idt311:option_label"));
	}
}
