import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteFramesEJanelas {
	
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
		//driver.quit();
	}
	
	@Test
	public void deveInteragirComFrames() {
		//driver.switchTo().frame("frame1");
		dsl.entrarFrame("frame1");
		//driver.findElement(By.id("frameButton")).click();
		dsl.clicarBotao("frameButton");
		//Alert alert = driver.switchTo().alert();
		//String msg = alert.getText();
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
		//alert.accept();
		
		//driver.switchTo().defaultContent();
		dsl.sairFrame();
		//driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
		dsl.escrever("elementosForm:nome", msg);
	}
	
	@Test
	public void deveInteragirComFrameEscondido() {
		WebElement frame = driver.findElement(By.id("frame2"));
		dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
		dsl.entrarFrame("frame2");
		dsl.clicarBotao("frameButton");
		String msg = dsl.alertaObterTextoEAceita();
		Assert.assertEquals("Frame OK!", msg);
	}
	
	@Test
	public void deveInteragirComJanelas() {
		//driver.findElement(By.id("buttonPopUpEasy")).click();
		dsl.clicarBotao("buttonPopUpEasy");
		//driver.switchTo().window("Popup");
		dsl.trocarJanela("Popup");
		//driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		driver.close();
		//driver.switchTo().window("");
		dsl.trocarJanela("");
		//driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		dsl.escrever(By.tagName("textarea"), "E agora?");
		}
	
	@Test
	public void deveInteragirComJanelasSemTitulo() {
		//driver.findElement(By.id("buttonPopUpHard")).click();
		dsl.clicarBotao("buttonPopUpHard");
		System.out.println(driver.getWindowHandle());
		System.out.println(driver.getWindowHandles());
		//driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
		//driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo?");
		dsl.escrever(By.tagName("textarea"), "Deu certo?");
		//driver.close();
		//driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
		dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
		//driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
		dsl.escrever(By.tagName("textarea"), "E agora?");
	}
}
