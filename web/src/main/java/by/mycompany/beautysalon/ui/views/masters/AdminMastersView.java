package by.mycompany.beautysalon.ui.views.masters;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.service.MasterService;
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
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Route(value = AppConsts.PAGE_MASTERS, layout = MainView.class)
@PageTitle(AppConsts.TITLE_MASTERS)
public class AdminMastersView extends VerticalLayout {

    private MasterService masterService;

    private Grid<MasterDto> grid;
    private TextField search;
    private Button addNewMaster;
    private AdminMasterEditor editor;
    private Dialog dialog;
    private ScheduleEditor scheduleEditor;
    private Dialog scheduleDialog;

    Binder<MasterDto> binder = new Binder<>(MasterDto.class);

    @Autowired
    public AdminMastersView(MasterService masterService, AdminMasterEditor editor, ScheduleEditor scheduleEditor) {
        setSizeFull();
        this.masterService = masterService;
        this.grid = new Grid<>();
        grid.addColumn(masterDto -> masterDto.getFirstName() + " " + masterDto.getLastName(), "firstName", "lastName")
                .setHeader("Name");
        grid.addColumn(MasterDto::getMainService).setHeader("Main Service");
//        grid.addColumn(masterDto -> masterDto.getServices().stream()
//                .map(Objects::toString).collect(Collectors.joining(", "))).setHeader("Services");
        grid.addColumn(new ComponentRenderer<>(()-> {
            return new Button("Services");
        })).setHeader("Services");
        Grid.Column<MasterDto> scheduleColumn = grid.addComponentColumn(masterDto -> {
            Button scheduleButton = new Button("Schedule");
            scheduleButton.addClickListener(buttonClickEvent -> {
                scheduleEditor.setMasterSchedule(masterDto.getId());
                scheduleDialog.open();
            });
            return scheduleButton;
        });
        scheduleColumn.setHeader("Schedules");

        updateList(null);

        this.editor = editor;
        this.dialog = new Dialog(new Label("Master Form"));

        this.search = new TextField();
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.setPlaceholder("Search");
        search.addValueChangeListener(e-> updateList(e.getValue()));
        search.setClearButtonVisible(true);

        this.scheduleEditor = scheduleEditor;
        this.scheduleDialog = new Dialog(new Label("Schedules"));
        scheduleDialog.add(scheduleEditor);

        this.addNewMaster = new Button("Add Master", new Icon(VaadinIcon.PLUS_CIRCLE_O));
        addNewMaster.addClickListener(e-> {
            grid.asSingleSelect().clear();
            getEditor().setBinder(binder, new MasterDto());
            binder.readBean(new MasterDto());
            binder.addStatusChangeListener(status -> {
                getEditor().getButtons().getSaveButton().setEnabled(!status.hasValidationErrors());
            });
            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(search, addNewMaster);

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

    public void editMasterServices() {
    }

    public void updateList(String search) {
        if (StringUtils.isEmpty(search)) {
            grid.setItems(masterService.getMasterDtos());
        } else {
            grid.setItems(masterService.findAllByNameContaining(search));
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
        MasterDto masterDto = getEditor().getMasterDto();
        try {
            binder.writeBean(masterDto);
        } catch (ValidationException e) {
            e.printStackTrace();
        }
        masterService.saveMasterDto(masterDto);
        Notification.show(masterDto.getFirstName() + " " + masterDto.getLastName() + " сохранён");
        closeUpdate();
    }

    private void cancel() {
        getDialog().close();
        grid.getDataProvider().refreshAll();
    }

    private void delete() {
        MasterDto masterDto = getEditor().getMasterDto();
        masterService.deleteMasterDto(masterDto);
        Notification.show(masterDto.getFirstName() + " " + masterDto.getLastName() + " удалён");
        closeUpdate();
    }

    private void closeUpdate() {
        updateList(null);
        grid.getDataProvider().refreshAll();
        getDialog().close();
    }

    private AdminMasterEditor getEditor() {
        return editor;
    }

    public Dialog getDialog() {
        return dialog;
    }
}
