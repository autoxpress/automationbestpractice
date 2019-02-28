package core.common;

import org.openqa.selenium.WebElement;

import java.util.Map;

public class PropertyReader {

    private Map<String, WebElement> fieldMap = null;

    /**
     * This method is used to retrieve a element by using displayed field name from a
     */

    public WebElement getCellEditor(String fieldName) {
        return getCellEditor(fieldName, true, true);
    }

    private WebElement getCellEditor(String fieldName, boolean retryElementNotFound, boolean retryStaleElement) {

        if (fieldMap == null) {

        }
        return null;
    }
}
