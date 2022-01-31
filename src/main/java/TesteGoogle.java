import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {
	
	private WebDriver driver;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void teste() {
		//WebDriver driver = new FirefoxDriver();
		
		// Para acessar pelo chrome
		// WebDriver driver = new ChromeDriver();
		
		// Para acessar pelo internet explore
		// WebDriver driver = new InternetExplorerDriver();
		
		// Para mudar a distancia em relação ao ponto 0,0 do monitor
		// driver.manage().window().setPosition(new Point(100, 100));
		
		// Para mudar o tamanho do browser que vai abrir
		//driver.manage().window().setSize(new Dimension(1200, 765));
		
		// Para o browser abrir na tela inteira esse comando é necessario
		// driver.manage().window().maximize();
		
		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		
		// Serve para fechar todas as abas abertas do browser // e driver.close(); -> somente fecha 1 das abas abertas
		//driver.quit();
	}
}
