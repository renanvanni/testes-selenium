import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {
	
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
	public void testeTextField() {
		//inicializa(); -> eu posso deixar comentado porque a anotação @Before realiza o método inicializa() antes de qualquer outro método
		
		// findElement -> serve para encontrar o input que precisa ser testado
		// sendKeys -> serve para preencher o campo (input) para fazer o teste
		dsl.escrever("elementosForm:nome", "Teste de escrita");
		// este comando de baixo foi colocado na classe dsl. E o comado de cima esta funcinando exatamente igual o de baixo
		//driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
		
		Assert.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
		
		//driver.quit(); -> foi comentada essa linha porque agora tem o @After para realizar depois da execução dos métodos
	}
	
	@Test
	public void testeTextFieldDuplo() {
		dsl.escrever("elementosForm:nome", "Renan");
		Assert.assertEquals("Renan", dsl.obterValorCampo("elementosForm:nome"));
		dsl.escrever("elementosForm:nome", "Gonçalves Vanni");
		Assert.assertEquals("Gonçalves Vanni", dsl.obterValorCampo("elementosForm:nome"));
	}
	
	@Test
	public void deveInteragirComTextArea() {
		//todas essas linhas foram comentadas porque agora existe o método inicializa() para chamar o driver do firefox
		
		//WebDriver driver = new FirefoxDriver();
		//driver.manage().window().setSize(new Dimension(1200, 765));
		//driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		
		dsl.escrever("elementosForm:sugestoes", "teste");
		
		//driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("teste");
		Assert.assertEquals("teste", dsl.obterValorCampo("elementosForm:sugestoes"));
	}
	
	@Test
	public void deveInteragirComRadioButton() {
		dsl.clicarRadio("elementosForm:sexo:0");
		Assert.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
	}
	
	@Test
	public void deveInteragirComCheckBox() {
		//driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		dsl.clicarCheck("elementosForm:comidaFavorita:2");
		//Assert.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
		Assert.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
	}
	
	@Test
	public void deveInteragirComCombo() {
		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		//combo.selectByIndex(2);
		//combo.selectByValue("superior");
		//combo.selectByVisibleText("2o grau completo");
		dsl.selecionarCombo("elementosForm:escolaridade", "2o grau completo");
		Assert.assertEquals("2o grau completo", dsl.obterValorCombo("elementosForm:escolaridade"));
	}
	
	@Test
	public void deveVerificarValoresCombo() {
		//WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
		//Select combo = new Select(element);
		//List<WebElement> options = combo.getOptions();
		//Assert.assertEquals(8, options.size());
		Assert.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
		
		//boolean encontrou = false;
		//for(WebElement option: options) {
			//if(option.getText().equals("Mestrado")) {
				//encontrou = true;
				//break;
			//}
		//}
		//Assert.assertTrue(encontrou);
		Assert.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
	}
	
	@Test
	public void deveVerificarValoresComboMultiplo() {
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.selecionarCombo("elementosForm:esportes", "Corrida");
		dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
		
		//WebElement element = driver.findElement(By.id("elementosForm:esportes"));
		//Select combo = new Select(element);
		//combo.selectByVisibleText("Natacao");
		//combo.selectByVisibleText("Corrida");
		//combo.selectByVisibleText("O que eh esporte?");
		
		//List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
		List<String> allSelectedOptions = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(3, allSelectedOptions.size());
		
		//combo.deselectByVisibleText("Corrida");
		dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
		allSelectedOptions = dsl.obterValoresCombo("elementosForm:esportes");
		Assert.assertEquals(2, allSelectedOptions.size());
		Assert.assertTrue(allSelectedOptions.containsAll(Arrays.asList("Natacao", "O que eh esporte?")));
	}
	
	@Test
	public void deveInteragirComBotoes() {
		dsl.clicarBotao("buttonSimple");
		//WebElement botao = driver.findElement(By.id("buttonSimple"));
		//botao.click();
		
		Assert.assertEquals("Obrigado!", dsl.obterValueElemento("buttonSimple"));
	}
	
	// @Ignore -> faz o jUnit ignorar esse teste da lista geral, na tela de resposta aparece skipped
	@Test
	public void deveInteragirComLinks() {
		//driver.findElement(By.linkText("Voltar")).click();
		dsl.clicarLink("Voltar");
		//Assert.fail(); -> faz com que o teste falhe no console, mas ele executa perfeitamente tudo antes
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void deveBuscarTextosNaPagina() {
		//esse metodo as vezes pode demorar pra encontrar e ele não é tão eficiente quanto o outro abaixo
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		//Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		//Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void testJavascript() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		//js.executeScript("alert('Testando js via selenium')");
		js.executeScript("document.getElementById('elementosForm:nome').value = 'Escrito via js'");
		js.executeScript("document.getElementById('elementosForm:sobrenome').type = 'radio'");
		
		WebElement element = driver.findElement(By.id("elementosForm:nome"));
		js.executeScript("arguments[0].style.border = arguments[1]", element, "solid 4px red");
	}
	
	@Test
	public void deveClicarBotaoTabela() {
		dsl.clicarBotaoTabela("Nome", "Maria", "Botao", "elementosForm:tableUsuarios");
	}
}
