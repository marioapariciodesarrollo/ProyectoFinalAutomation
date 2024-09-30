package ProyectoBDD_POM.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    // Método que crea el WebDriver según el navegador definido en el archivo de configuración
    public static WebDriver createDriver() {
        Properties prop = new Properties();
        String browser = "chrome"; // Valor por defecto

        try (FileInputStream input = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(input);
            browser = prop.getProperty("browser", "chrome");
        } catch (IOException e) {
            e.printStackTrace();
        }

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }
        return driver;
    }
}
