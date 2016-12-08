package groovy.test;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;

import java.beans.PropertyEditor;

/**
 * Created by chunlong.wchl on 2015/6/27.
 */
    public class DatePropertyEditorRegistrar implements PropertyEditorRegistrar {

        private PropertyEditor propertyEditor;
        public void registerCustomEditors(PropertyEditorRegistry peRegistry) {
            peRegistry.registerCustomEditor(java.util.Date.class, getPropertyEditor());
        }
        public PropertyEditor getPropertyEditor(){
            return propertyEditor;
        }

        public void setPropertyEditor(PropertyEditor propertyEditor){
            this.propertyEditor = propertyEditor;
        }
    }
