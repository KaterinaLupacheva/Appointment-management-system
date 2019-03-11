package by.mycompany.beautysalon.ui.views.masters;

import by.mycompany.beautysalon.dto.MasterDto;
import by.mycompany.beautysalon.entity.Master;
import by.mycompany.beautysalon.entity.Schedule;
import by.mycompany.beautysalon.service.MasterService;
import by.mycompany.beautysalon.service.ScheduleService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.grid.FooterRow;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.editor.Editor;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.timepicker.TimePicker;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@SpringComponent
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ScheduleEditor extends VerticalLayout {

    private MasterService masterService;
    private ScheduleService scheduleService;
    private MasterDto masterDto;
    private Integer masterDtoId;
    private List<Schedule> schedules;

    private Grid<Schedule> scheduleGrid = new Grid<>(Schedule.class);
    private Binder<Schedule> binder = new Binder<>(Schedule.class);
    private Button addSchedule = new Button("Add New Schedule");
//    private ChangeHandler changeHandler;

    @Autowired
    private ScheduleEditor(MasterService masterService, ScheduleService scheduleService) {
        this.masterService = masterService;
        this.scheduleService = scheduleService;
        scheduleGrid.setColumns("day", "startTime", "end_time");
        Grid.Column<Schedule> dayColumn = scheduleGrid.getColumnByKey("day");
        Grid.Column<Schedule> startTimeColumn = scheduleGrid.getColumnByKey("startTime");
        Grid.Column<Schedule> endTimeColumn = scheduleGrid.getColumnByKey("end_time").setHeader("End Time");
        add(addSchedule, scheduleGrid);
        setVisible(false);
        setWidth("550px");

        addSchedule.addClickListener(e-> {
            schedules.add(new Schedule());
            scheduleGrid.getDataProvider().refreshAll();
        });

        gridEditor(dayColumn, startTimeColumn, endTimeColumn);
    }

    private void gridEditor(Grid.Column<Schedule> dayColumn, Grid.Column<Schedule> startTimeColumn, Grid.Column<Schedule> endTimeColumn) {
        final Schedule[] editedSchedule = {null};
        Editor<Schedule> editor = scheduleGrid.getEditor();
        editor.setBinder(binder);
        editor.setBuffered(true);

        DatePicker workingDay = new DatePicker();
        binder.forField(workingDay).bind("day");
        dayColumn.setEditorComponent(workingDay);

        TimePicker startTime = new TimePicker();
        startTime.setLocale(Locale.GERMANY);
        binder.forField(startTime).bind("startTime");
        startTimeColumn.setEditorComponent(startTime);

        TimePicker endTime = new TimePicker();
        endTime.setLocale(Locale.GERMANY);
        binder.forField(endTime).bind("end_time");
        endTimeColumn.setEditorComponent(endTime);

        Collection<Button> editButtons = Collections
                .newSetFromMap(new WeakHashMap<>());

        Grid.Column<Schedule> editorColumn = scheduleGrid.addComponentColumn(schedule -> {
            Button edit = new Button("Edit");
            edit.addClassName("edit");
            edit.addClickListener(e -> {
                editor.editItem(schedule);
                editedSchedule[0] = schedule;
            });
            edit.setEnabled(!editor.isOpen());
            editButtons.add(edit);
            return edit;
        });

        editor.addOpenListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));
        editor.addCloseListener(e -> editButtons.stream()
                .forEach(button -> button.setEnabled(!editor.isOpen())));

        Button save = new Button("Save", e -> editor.save());
        save.addClassName("save");

        Button cancel = new Button("Cancel", e -> editor.cancel());
        cancel.addClassName("cancel");

        scheduleGrid.getElement().addEventListener("keyup", event -> editor.cancel())
                .setFilter("event.key === 'Escape' || even.key === 'Esc'");

        Div buttons = new Div(save, cancel);
        editorColumn.setEditorComponent(buttons);

        editor.addSaveListener(event -> {
            if (editedSchedule[0].getId() == 0) {
                editedSchedule[0].setDay(event.getItem().getDay());
                editedSchedule[0].setStartTime(event.getItem().getStartTime());
                editedSchedule[0].setEnd_time(event.getItem().getEnd_time());
                Master master = masterService.findMasterById(masterDtoId);
                master.addSchedule(editedSchedule[0]);
                scheduleService.add(editedSchedule[0]);
            } else {
                editedSchedule[0].setDay(event.getItem().getDay());
                editedSchedule[0].setStartTime(event.getItem().getStartTime());
                editedSchedule[0].setEnd_time(event.getItem().getEnd_time());
                scheduleService.add(editedSchedule[0]);
            }
        });
    }

    public void setMasterSchedule(Integer masterDtoId) {
        this.masterDtoId = masterDtoId;
        Master master = masterService.findMasterById(masterDtoId);
        schedules = master.getSchedules();
        scheduleGrid.setItems(schedules);
        setVisible(true);
    }

//    public interface ChangeHandler{
//        void onChange();
//    }
//
//    public void setChangeHandler(ChangeHandler ch) {
//        this.changeHandler = ch;
//    }

}
