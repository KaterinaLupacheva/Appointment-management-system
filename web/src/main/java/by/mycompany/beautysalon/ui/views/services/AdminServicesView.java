package by.mycompany.beautysalon.ui.views.services;

import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.ServiceService;
import by.mycompany.beautysalon.ui.MainView;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Route(value = AppConsts.PAGE_SERVICES, layout = MainView.class)
@PageTitle(AppConsts.TITLE_SERVICES)
public class AdminServicesView extends VerticalLayout {

    private ServiceService serviceService;

    private Grid<Service> grid;
    private TextField search;
    private Button addNewService;
    private AdminServiceEditor editor;
    private Dialog dialog;

    Binder<Service> binder = new Binder<>(Service.class);

    @Autowired
    public AdminServicesView(ServiceService serviceService, AdminServiceEditor editor) {
        setSizeFull();
        this.serviceService = serviceService;
        this.editor = editor;

        this.grid = new Grid<>(Service.class);
        grid.setColumns("title");
        grid.addColumn(service -> service.getDuration().intValue()).setHeader("Duration, min");
        updateList(null);

        this.dialog = new Dialog(new Label("Service Form"));
        this.search = new TextField();
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.setPlaceholder("Search");
        search.addValueChangeListener(e-> updateList(e.getValue()));
        search.setClearButtonVisible(true);
        this.addNewService = new Button("Add Service", new Icon(VaadinIcon.PLUS_CIRCLE_O));
        addNewService.addClickListener(e -> {
            grid.asSingleSelect().clear();
            getEditor().setBinder(binder, new Service());
            binder.readBean(new Service());
            binder.addStatusChangeListener(status -> {
                getEditor().getButtons().getSaveButton().setEnabled(!status.hasValidationErrors());
            });
            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(search, addNewService);

        add(toolbar, grid, dialog);
        dialog.add(editor);

        grid.asSingleSelect().addValueChangeListener(event -> {
            getEditor().setBinder(binder, event.getValue());
            binder.readBean(event.getValue());
            binder.addStatusChangeListener(status -> {
                getEditor().getButtons().getSaveButton().setEnabled(!status.hasValidationErrors());
            });
            dialog.open();
        });

        setupEventListeners();
        grid.getDataProvider().refreshAll();
    }

    public void updateList(String search) {
        if (StringUtils.isEmpty(search)) {
            grid.setItems(serviceService.findAll());
        } else {
            grid.setItems(serviceService.findByTitleContaining(search));
        }
    }

    public void setupEventListeners() {
        getEditor().getButtons().addSaveListener(e -> save());
        getEditor().getButtons().addCancelListener(e -> cancel());
        getEditor().getButtons().addDeleteListener(e -> delete());

        getDialog().getElement().addEventListener("opened-changed", e -> {
            if(!getDialog().isOpened()) {
                cancel();
            }
        });
    }

    private void save() {
        Service service = getEditor().getService();
        try {
            binder.writeBean(service);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        serviceService.save(service);
        Notification.show(service.getTitle() + " сохранён");
        closeUpdate();
    }

    private void cancel() {
        getDialog().close();
        grid.getDataProvider().refreshAll();
    }

    private void delete() {
        Service service = getEditor().getService();
        serviceService.delete(service);
        Notification.show(service.getTitle() + " удалён");
        closeUpdate();
    }

    private void closeUpdate() {
        updateList(null);
        grid.getDataProvider().refreshAll();
        getDialog().close();
    }

    public Dialog getDialog() {
        return dialog;
    }

    public AdminServiceEditor getEditor() {
        return editor;
    }
}
