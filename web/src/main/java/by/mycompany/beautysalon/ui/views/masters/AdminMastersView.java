package by.mycompany.beautysalon.ui.views.masters;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Service;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.ui.MainView;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.NativeButtonRenderer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Autowired
    public AdminMastersView(MasterService masterService, AdminMasterEditor editor, ScheduleEditor scheduleEditor) {
        setSizeFull();
        this.masterService = masterService;
        this.grid = new Grid<>(MasterDto.class);
        grid.setColumns("firstName", "lastName", "mainService");
//        grid.addColumn(masterDto -> masterDto.getServices().stream()
//                .map(Objects::toString).collect(Collectors.joining(", "))).setHeader("Services");
        grid.addColumn(new ComponentRenderer<>(()-> {
            return new Button("Services");
        })).setHeader("Services");
//        grid.addColumn(new NativeButtonRenderer<MasterDto>("Services", clickedItem -> {
//            editMasterServices();
//        })).setHeader("Services");
        grid.addColumn(new NativeButtonRenderer<MasterDto>("Schedule", clickedItem -> {
            scheduleEditor.setMasterSchedule(clickedItem.getId());
            scheduleDialog.open();
        })).setHeader("Schedules");

        this.editor = editor;
        this.dialog = new Dialog(new Label("Master Form"));
        dialog.add(editor);
        this.search = new TextField();
        search.setValueChangeMode(ValueChangeMode.EAGER);
        search.setPlaceholder("Search");
        search.addValueChangeListener(e-> updateList(e.getValue()));

        this.scheduleEditor = scheduleEditor;
        this.scheduleDialog = new Dialog(new Label("Schedules"));
        scheduleDialog.add(scheduleEditor);

        this.addNewMaster = new Button("Add Master", new Icon(VaadinIcon.PLUS_CIRCLE_O));
        addNewMaster.addClickListener(e-> {
            grid.asSingleSelect().clear();
            editor.setMaster(new MasterDto());
            dialog.open();
        });

        HorizontalLayout toolbar = new HorizontalLayout(search, addNewMaster);
        editor.setChangeHandler(()-> {
            editor.setVisible(false);
            updateList(null);
            dialog.close();
        });

//        scheduleEditor.setChangeHandler(() -> {
//            scheduleEditor.setVisible(false);
//            updateList(null);
//            scheduleDialog.close();
//        });
        add(toolbar, grid, dialog);
        updateList(null);

        grid.asSingleSelect().addValueChangeListener(event -> {
            if(event.getValue() == null) {
                editor.setVisible(false);
            }else {
                editor.setMaster(event.getValue());
                dialog.open();
            }
        });
    }

    public void editMasterServices() {
    }

    public void updateList(String search) {
        if (StringUtils.isEmpty(search)) {
            List<MasterDto> masters = masterService.getMasterDtos();
            grid.setItems(masters);
        } else {
            //implementation
            }
    }
}
