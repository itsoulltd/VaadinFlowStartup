package com.infoworks.lab.presenters;

import com.infoworks.lab.components.crud.components.editor.EmbeddedBeanEditor;
import com.infoworks.lab.domain.entities.Gender;
import com.it.soul.lab.sql.query.models.Property;
import com.vaadin.flow.component.HasValue;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.data.binder.Result;
import com.vaadin.flow.data.binder.ValueContext;
import com.vaadin.flow.data.converter.Converter;

import java.util.Arrays;

public class PassengerEditor extends EmbeddedBeanEditor {

    public PassengerEditor(Class beanType) {
        super(beanType);
    }

    @Override
    protected HasValue getValueField(Property prop) {
        if (prop.getKey().equals("sex")){
            ComboBox<String> comboBox = new ComboBox<>(prop.getKey());
            comboBox.setItems(Arrays.stream(Gender.values()).map(gender -> gender.name()));
            return comboBox;
        }else{
            return super.getValueField(prop);
        }
    }

    @Override
    protected Converter getValueConverter(Property prop) {
        if (prop.getKey().equals("sex")){
            return new GenderConverter();
        }else{
            return super.getValueConverter(prop);
        }
    }

    public static class GenderConverter implements Converter<String, Gender>{

        @Override
        public Result<Gender> convertToModel(String s, ValueContext valueContext) {
            if (s == null || s.isEmpty()) s = Gender.NONE.name();
            return Result.ok(Gender.valueOf(s));
        }

        @Override
        public String convertToPresentation(Gender gender, ValueContext valueContext) {
            if (gender == null) return Gender.NONE.name();
            return gender.name();
        }
    }
}
