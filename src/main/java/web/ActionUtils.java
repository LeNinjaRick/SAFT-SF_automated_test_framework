package web;

import configUtils.ConfigFramework;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActionUtils extends ConfigFramework {

    public static void openURL(WebDriver driver, String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }

    private static void scrollIntoView(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({ behavior: 'auto', block: 'center', inline: 'center' });", element);
    }

    private static Wait<WebDriver> fluentWait(WebDriver driver, int seconds){
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(100))
                .ignoring(org.openqa.selenium.NoSuchElementException.class);
    }
    public static WebElement fluentWaitElementPresent(WebDriver driver, By locator, int seconds) {
        Wait<WebDriver> wait = fluentWait(driver,seconds);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public static WebElement fluentWaitClickableElementWithScroll(WebDriver driver, By locator, int seconds) {
        Wait<WebDriver> wait = fluentWait(driver,seconds);
        return wait.until(webDriver -> {
            WebElement element = webDriver.findElement(locator);
            scrollIntoView(webDriver, element);
            if (element.isDisplayed()) {
                return wait.until(ExpectedConditions.elementToBeClickable(locator));
            } else {
                scrollToElement(driver, locator, seconds);
                return null; // keep waiting
            }
        });
    }

    public static WebElement fluentWaitVisibleElementWithScroll(WebDriver driver, By locator, int seconds) {
        Wait<WebDriver> wait = fluentWait(driver,seconds);
        return wait.until(webDriver -> {
            WebElement element = webDriver.findElement(locator);
            scrollIntoView(webDriver, element);
            if (element.isDisplayed()) {
                return element;
            } else {
                scrollToElement(driver, locator, seconds);
                return null; // keep waiting
            }
        });
    }


    public static List<WebElement> fluentWaitArray(WebDriver driver, By by, int seconds) {
        Wait<WebDriver> wait = fluentWait(driver,seconds);
        try {
            return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (Exception e) {
            System.out.println("Os elementos não foram encontrados dentro do tempo limite.");
            return Collections.emptyList();  // Retorna uma lista vazia
        }
    }


    public static boolean isElementoPresente(WebDriver driver, By by, int seconds) {
        boolean isPresente;
        try {
            WebElement element = fluentWaitElementPresent(driver, by, seconds);
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            isPresente = true;
        } catch (Exception e) {
            isPresente = false;
        }
        return isPresente;
    }

    public static String getAtributteOfWebElement(WebDriver driver, By by, String attribute, int time) {
        WebElement element = fluentWaitVisibleElementWithScroll(driver, by, time);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
        return element.getAttribute(attribute);
    }

    public static boolean isElementoVisivel(WebDriver driver, By by, int time) {
        boolean isVisivel;
        try {
            WebElement element = fluentWaitVisibleElementWithScroll(driver, by, time);
            Assert.assertTrue(element.isDisplayed());
            isVisivel = true;
        } catch (Exception e) {
            isVisivel = false;
        }
        return isVisivel;
    }

    public static void clickjs(WebDriver driver, By locator, int time) {
        try {
            WebElement element = fluentWaitClickableElementWithScroll(driver, locator, time);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            System.err.println("Elemento " + locator + ", NAO detectado");
            System.out.println("O elemento [{ " + locator.toString() + "}] nao foi identificado");
            e.printStackTrace();
        }
    }

    public static void fillInputjs(WebDriver driver, By locator, String text, int time) {
        try {
            WebElement element = fluentWaitVisibleElementWithScroll(driver, locator, time);
            JavascriptExecutor jse = (JavascriptExecutor) driver;
            jse.executeScript("arguments[0].scrollIntoViewIfNeeded(true);", element);
            jse.executeScript("arguments[0].value='" + text + "'", element);
        } catch (Exception e) {
            System.err.println("Elemento " + locator + ", NAO detectado");
            System.out.println("O elemento [{ " + locator.toString() + "}] nao foi identificado");
            e.printStackTrace();
        }
    }

    public static void scroll(WebDriver driver, int ammount) {
        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
        jse2.executeScript("window.scrollBy(0," + ammount + ")", "");
    }


    public static String getText(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente = isElementoPresente(getBrowser(), by, tempoEspera);
        String valorObtido = "";
        if (isPresente) {
            scrollToElement(driver, by, tempoEspera);
            WebElement element = driver.findElement(by);
            valorObtido = element.getText();
            return valorObtido;
        } else {
            Assert.fail("Erro ao procurar o elemento " + by + ", se certifique que o layout dos objetos se manteve!");
            return valorObtido;
        }
    }

    public static String getAtributeTitle(WebDriver driver, By by, int tempoEspera) {
        boolean isPresente = isElementoPresente(getBrowser(), by, tempoEspera);
        String valorObtido = "";
        if (isPresente) {
            WebElement element = driver.findElement(by);
            valorObtido = element.getAttribute("title");
            return valorObtido;
        } else {
            Assert.fail("Erro ao procurar o elemento " + by + ", se certifique que o layout dos objetos se manteve!");
            return valorObtido;
        }
    }

    public static void implicitWait(WebDriver driver, By by, int tempoEspera) {
        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(tempoEspera));
        wait1.until(ExpectedConditions.presenceOfElementLocated(by));
        wait1.until(ExpectedConditions.elementToBeClickable(by));
        JavascriptExecutor jse = (JavascriptExecutor) driver;
    }

    public static WebElement fillInput(WebDriver driver, By by, String valor, int tempoEspera) {
        WebElement element = null;
        try {
            element = fluentWaitClickableElementWithScroll(driver, by, tempoEspera);
            element.click();
            element.clear();
            element.sendKeys(valor.trim());
        } catch (Exception e) {
            System.err
                    .println("Ação input, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s: " + e.toString());
            Assert.fail("Ação input, elemento - " + by + " não encontrado, " + "tempo de espera = " + tempoEspera + "s: " + e.toString());
        }
        return element;
    }


    public static void scrollToElement(WebDriver driver, By by, int tempoEspera) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0);");
        js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
        boolean isPresente = isElementoPresente(driver, by, tempoEspera);
        int i = 0;
        while (!isPresente && i < 5) {
            scroll(driver, 350);
            i++;
        }
        if (!isPresente) {
            Assert.fail("Erro ao obter elemento " + by + " para realizar scroll");
        }
    }


    public static void click(WebDriver driver, By by, int time) {
        try {
            WebElement element = fluentWaitClickableElementWithScroll(driver, by, time);
            element.click();
        } catch (Exception e) {
            System.err.println("Ação click, elemento - " + by + " interceptado, " + e);
            Assert.fail("Ação click, elemento - " + by + " interceptado, " + e);
        }
    }

}
