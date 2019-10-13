package com.infoworks.lab.views;

import com.infoworks.lab.components.crud.Configurator;
import com.infoworks.lab.components.crud.Crud;
import com.infoworks.lab.components.crud.components.grid.CachedSource;
import com.infoworks.lab.components.crud.components.grid.GridDataSource;
import com.infoworks.lab.components.crud.components.utils.EditorDisplayType;
import com.infoworks.lab.domain.entities.Gender;
import com.infoworks.lab.domain.entities.Passenger;
import com.infoworks.lab.presenters.PassengerEditor;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

import java.util.ArrayList;
import java.util.List;

/**
 * The main view contains a button and a click listener.
 */
@Route
@CssImport(value = "./styles/view-styles.css", id = "view-styles")
@CssImport(value = "./styles/shared-styles.css", include = "view-styles")
@PWA(name = "Time Tracking", shortName = "Ticker")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainView extends VerticalLayout {

    public MainView() {

        GridDataSource source = new CachedSource();
        getPassengers().stream().forEach(passenger -> source.save(passenger));

        Configurator configurator = new Configurator(Passenger.class)
                .setDisplayType(EditorDisplayType.COMBINED)
                .setDataSource(source)
                .setEditor(PassengerEditor.class)
                .setDialog(PassengerEditor.class);

        Crud crud = new Crud(configurator);
        configureGrid(crud);
        add(crud);

    }

    private void configureGrid(Crud curd){
        Grid<Passenger> grid = curd.getGrid();
        grid.setColumns(curd.propertyKeys(getPassengers().get(0)));
    }

    private List<Passenger> getPassengers() {
        List<Passenger> personList = new ArrayList<>();
        personList.add(new Passenger("Lucas", Gender.MALE, 68));
        personList.add(new Passenger("Peter", Gender.MALE, 38));
        personList.add(new Passenger("Jack", Gender.MALE, 28));
        personList.add(new Passenger("Peter", Gender.MALE, 38));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        personList.add(new Passenger("Jack", Gender.MALE, 28));
        personList.add(new Passenger("Peter", Gender.MALE, 38));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        personList.add(new Passenger("Anton", Gender.MALE, 37));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        personList.add(new Passenger("Aaron", Gender.FEMALE, 18));
        personList.add(new Passenger("Jack", Gender.MALE, 28));
        personList.add(new Passenger("Peter", Gender.MALE, 38));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        personList.add(new Passenger("Anton", Gender.MALE, 37));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        personList.add(new Passenger("Jack", Gender.MALE, 28));
        personList.add(new Passenger("Aaron", Gender.FEMALE, 18));
        personList.add(new Passenger("Jack", Gender.MALE, 28));
        personList.add(new Passenger("Peter", Gender.MALE, 38));
        personList.add(new Passenger("Samuel", Gender.MALE, 53));
        return personList;
    }
}
