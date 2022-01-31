import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
//import org.openqa.selenium.Alert;
//import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	private WebDriver driver;

	private DSL dsl;
	
	private CampoTreinamentoPage page;
	
	@Before
	public void inicializa() {
		driver = new FirefoxDriver();
		driver.manage().window().setSize(new Dimension(1200, 765));
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
		page = new CampoTreinamentoPage(driver);
	}
	
	@After
	public void finaliza() {
		driver.quit();
	}

	@Test
	public void deveRealizarCadastroComSucesso() {
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Rebeca");
		//dsl.escrever("elementosForm:nome", "Rebeca");
		page.setNome("Renan");
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Gonçalves Vanni");
		//dsl.escrever("elementosForm:sobrenome", "Gonçalves Vanni");
		page.setSobrenome("Gonçalves Vanni");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.setSexoMasculino();
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		page.setComidaCarne();
		//new Select(driver.findElement(By.id("elementosForm:escolaridade"))).selectByVisibleText("Mestrado");
		//dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		page.setEscolaridade("Mestrado");
		//new Select(driver.findElement(By.id("elementosForm:esportes"))).selectByVisibleText("Natacao");
		//dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		page.setEsportes("Natacao");
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		
		//Assert.assertTrue(driver.findElement(By.id("resultado")).getText().startsWith("Cadastrado!"));
		//Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		//Assert.assertTrue(page.obterResultadoCadastro().startsWith("Cadastrado!"));
		Assert.assertEquals("Cadastrado!", page.obterResultadoCadastro());
		//Assert.assertTrue(driver.findElement(By.id("descNome")).getText().endsWith("Rebeca"));
		//Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Rebeca"));
		//Assert.assertTrue(page.obterNomeCadastro().endsWith("Renan"));
		Assert.assertEquals("Renan", page.obterNomeCadastro());
		//Assert.assertEquals("Sobrenome: Gonçalves Vanni", driver.findElement(By.id("descSobrenome")).getText());
		//Assert.assertEquals("Sobrenome: Gonçalves Vanni", dsl.obterTexto("descSobrenome"));
		//Assert.assertEquals("Sobrenome: Gonçalves Vanni", page.obterSobrenomeCadastro());
		Assert.assertEquals("Gonçalves Vanni", page.obterSobrenomeCadastro());
		//Assert.assertEquals("Sexo: Masculino", driver.findElement(By.id("descSexo")).getText());
		//Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		//Assert.assertEquals("Sexo: Masculino", page.obterSexoCadastro());
		Assert.assertEquals("Masculino", page.obterSexoCadastro());
		//Assert.assertEquals("Comida: Carne", driver.findElement(By.id("descComida")).getText());
		//Assert.assertEquals("Comida: Carne", dsl.obterTexto("descComida"));
		//Assert.assertEquals("Comida: Carne", page.obterComidaCadastro());
		Assert.assertEquals("Carne", page.obterComidaCadastro());
		//Assert.assertEquals("Escolaridade: mestrado", driver.findElement(By.id("descEscolaridade")).getText());
		//Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		//Assert.assertEquals("Escolaridade: mestrado", page.obterEscolaridadeCadastro());
		Assert.assertEquals("mestrado", page.obterEscolaridadeCadastro());
		//Assert.assertEquals("Esportes: Natacao", driver.findElement(By.id("descEsportes")).getText());
		//Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
		//Assert.assertEquals("Esportes: Natacao", page.obterEsportesCadastro());
		Assert.assertEquals("Natacao", page.obterEsportesCadastro());
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		//Alert alert = driver.switchTo().alert();
		//Assert.assertEquals("Nome eh obrigatorio", alert.getText());
		Assert.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Renan");
		//dsl.escrever("elementosForm:nome", "Renan");
		page.setNome("Renan");
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		//Alert alert = driver.switchTo().alert();
		//Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
		Assert.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Renan");
		//dsl.escrever("elementosForm:nome", "Renan");
		page.setNome("Renan");
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Gonçalves Vanni");
		//dsl.escrever("elementosForm:sobrenome", "Gonçalves Vanni");
		page.setSobrenome("Gonçalves Vanni");
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		//Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Renan");
		//dsl.escrever("elementosForm:nome", "Renan");
		page.setNome("Renan");
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Gonçalves Vanni");
		//dsl.escrever("elementosForm:sobrenome", "Gonçalves Vanni");
		page.setSobrenome("Gonçalves Vanni");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.setSexoMasculino();
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		page.setComidaCarne();
		//driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:3");
		page.setComidaVegetariano();
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		//Alert alert = driver.switchTo().alert();
		//Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
		Assert.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Renan");
		//dsl.escrever("elementosForm:nome", "Renan");
		page.setNome("Renan");
		//driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Gonçalves Vanni");
		//dsl.escrever("elementosForm:sobrenome", "Gonçalves Vanni");
		page.setSobrenome("Gonçalves Vanni");
		//driver.findElement(By.id("elementosForm:sexo:0")).click();
		//dsl.clicarRadio("elementosForm:sexo:0");
		page.setSexoMasculino();
		//driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
		//dsl.clicarRadio("elementosForm:comidaFavorita:0");
		page.setComidaCarne();
		
		//Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
		//combo.selectByVisibleText("Karate");
		//combo.selectByVisibleText("O que eh esporte?");
		//dsl.selecionarCombo("elementosForm:esportes", "Karate");
		//dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		page.setEsportes("Karate", "O que eh esporte?");
		//driver.findElement(By.id("elementosForm:cadastrar")).click();
		//dsl.clicarBotao("elementosForm:cadastrar");
		page.cadastrar();
		//Alert alert = driver.switchTo().alert();
		//Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
		Assert.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
	}
}
