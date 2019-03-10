package core.common;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.Map;
import java.util.stream.Collectors;

public class PropertyReader {

    private Map<String, WebElement> fieldMap = null;

    /**
     * This method is used to retrieve a element by using displayed field name from a table form
     */

    public WebElement getCellEditor(String fieldName) {
        return getCellEditor(fieldName, true, true);
    }

    private WebElement getCellEditor(String fieldName, boolean retryElementNotFound, boolean retryStaleElement) {

        if (fieldMap == null) {
            fieldMap = TestBaseApp.getDriver().findElements(By.tagName("table")).get(1).findElements(By.tagName("tr"))
                    .stream()
                    .collect(Collectors.toMap(webElement -> webElement.findElement(By.tagName("td")).getText(), webElement -> webElement.findElements(By.tagName("td")).get(1)));
        }
        WebElement webElement = fieldMap.get(fieldName);
        if (webElement == null) {
            if (retryElementNotFound) {
                fieldMap = null;
                return getCellEditor(fieldName, false, retryStaleElement);
            }
            Assert.fail("Field " + fieldName + " not found");
            throw new RuntimeException("Field " + fieldName + " not found");
        }
        try {
            webElement.getTagName();
        } catch (StaleElementReferenceException e) {
            if (retryStaleElement) {
                fieldMap = null;
                return getCellEditor(fieldName, retryElementNotFound, false);
            }
            throw e;
        }
        return webElement;
    }
}
