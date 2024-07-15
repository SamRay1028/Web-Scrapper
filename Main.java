import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import java.time.Duration;
import java.util.List;
import java.time.Duration;
import java.util.ArrayList;


import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/samraya/Desktop/chromedriver-mac-arm64/chromedriver");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        int sizeMonitor = 0;
        driver.get("https://www.google.com/search?sca_esv=572214004&sxsrf=AM9HkKnnYQ9KVb5d4_7r1JPm2ZH3romHNw:1696946336989&q=cat&tbm=isch&source=lnms&sa=X&ved=2ahUKEwidpfnc0euBAxUghIkEHRKSAngQ0pQJegQIDhAB&biw=865&bih=904&dpr=2");
        String home = driver.getCurrentUrl();
        driver.manage().window().maximize();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000)", "");
        js.executeScript("window.scrollBy(0, -1000)", "");
        List<WebElement> elements = driver.findElements(new ByCssSelector("img.rg_i.Q4LuWd"));
        ArrayList<String> ImageURLs = new ArrayList<String>();
        ArrayList<String> PageURLs = new ArrayList<String>();
        System.out.println("Size: " + elements.size());
        int a = 0;
        int count = 0;
        int count2 = 0;
        while(elements.size() > sizeMonitor){
            sizeMonitor = elements.size();
            for(int i = 0 + a; i < elements.size(); i++){
                try{
                    //wait.until(ExpectedConditions.elementToBeClickable(new ByCssSelector("img.img.rg_i.Q4LuWd")));
                    js.executeScript("arguments[0].click();", elements.get(i));
                }
                catch(Exception e){
                    System.out.println("fail");
                }
                try{
                    wait.until(ExpectedConditions.elementToBeClickable(new ByCssSelector("img.sFlh5c.pT0Scc.iPVvYb")));
                    ImageURLs.add(driver.findElement(new ByCssSelector("img.sFlh5c.pT0Scc.iPVvYb")).getAttribute("src"));
                    count++;
                    System.out.println(count + ": " + ImageURLs.get(ImageURLs.size() - 1));
                    wait.until(ExpectedConditions.elementToBeClickable(new ByCssSelector("div.DFaQu.T38yZ.cS4Vcb-pGL6qe-lfQAOe")));
                    WebElement element = driver.findElement(new ByCssSelector("div.DFaQu.T38yZ.cS4Vcb-pGL6qe-lfQAOe"));
                    js.executeScript("arguments[0].click();", element);
                    PageURLs.add(driver.getCurrentUrl());
                    driver.navigate().back();
                }
                catch(Exception e){
                    System.out.println("Hi");
                    System.out.println("fail");
                }

            }
            a = elements.size();
            //System.out.println("loop#: " + a / 100);
            js.executeScript("window.scrollBy(0, document.body.scrollHeight)");
            js.executeScript("window.scrollBy(0, -1 * document.body.scrollHeight)");
            elements = driver.findElements(new ByCssSelector("img.rg_i.Q4LuWd"));
            System.out.println("Size (2): " + elements.size());
        }
        for(int i = 0; i < PageURLs.size(); i++){
            driver.get(PageURLs.get(i));
            js.executeScript("window.scrollBy(0, 1000)");
            js.executeScript("window.scrollBy(0, -1000)");
            wait.until(ExpectedConditions.elementToBeClickable(new ByCssSelector("img.rg_i.Q4LuWd")));
            List<WebElement> elements2 = driver.findElements(new ByCssSelector("img.rg_i.Q4LuWd"));
            for(int j = 0; j < elements2.size(); j++){
                //elements2.size()
                try{
                    js.executeScript("arguments[0].click()", elements2.get(j));
                    wait.until(ExpectedConditions.elementToBeClickable(new ByCssSelector("img.sFlh5c.pT0Scc.iPVvYb")));
                    ImageURLs.add(driver.findElement(new ByCssSelector("img.sFlh5c.pT0Scc.iPVvYb")).getAttribute("src"));
                    count2++;
                    count++;
                    System.out.println(count + ": " + ImageURLs.get(ImageURLs.size() - 1));
                }
                catch(Exception e){
                    System.out.println("fail");
                }
            }

        }


        //System.out.println("URL: " + s);

    }
}