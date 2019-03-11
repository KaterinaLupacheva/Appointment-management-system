package by.mycompany.beautysalon.ui.views.services;

import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.ServiceService;
import by.mycompany.beautysalon.ui.MainView;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import by.mycompany.beautysalon.ui.views.services.AdminServiceEditor;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Route(value = AppConsts.PAGE_SERVICES, layout = MainView.class)
@PageTitle(AppConsts.TITLE_SERVICES)
public class AdminServicesView extends VerticalLayout {

    private ServiceService serviceService;

    private Grid<Service> grid;
    private TextField search;
    private Button addNewService;
    private AdminServiceEditor editor;
    private Dialog dialog;

    @Autowired
    public AdminServicesView(ServiceService serviceService, AdminServiceEditor editor) {
        setSizeFull();
        this.serviceService = serviceService;
        this.grid = new Grid<>(Service.class);
        grid.setColumns("title", "duration");
        this.editor = editor;
        this.dialog = new Dialog(new Label("Service Form"));
        dialog.add(editor);
        this.search = new TextField();
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.setPlaceholder("Search");
        search.addValueChangeListener(e-> updateList(e.getValue()));
        this.addNewService = new Button("Add Service", new Icon(VaadinIcon.PLUS_CIRCLE_O));
//        addNewService.addThemeVariants(ButtonVariant.MATERIAL_CONTAINED);
        addNewService.addClickListener(e -> {
            grid.asSingleSelect().clear();
            editor.setService(new Service());
            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(search, addNewService);
        editor.setChangeHandler(()-> {
            editor.setVisible(false);
            updateList(null);
            dialog.close();
        });

        add(toolbar, grid, dialog);
        updateList(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() == null) {
                editor.setVisible(false);
            }else {
                editor.setService(event.getValue());
                dialog.open();
            }
        });
    }

    public void updateList(String search) {
        if (StringUtils.isEmpty(search)) {
            List<Service> services = serviceService.findAll();
            grid.setItems(services);
        } else {
            List<Service> services = serviceService.findByTitleStartsWithIgnoreCase(search);
            grid.setItems(services);
        }
    }
}
