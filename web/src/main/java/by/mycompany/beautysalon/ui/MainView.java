package by.mycompany.beautysalon.ui;

import by.mycompany.beautysalon.ui.components.AppNavigation;
import by.mycompany.beautysalon.ui.entities.PageInfo;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("")
@Theme(Material.class)
public class MainView extends VerticalLayout {

    private AppNavigation appNavigation;
//    private Tabs tabs;

    @Autowired
    public MainView() {
        setSizeFull();
        List<PageInfo> pages = new ArrayList<>();
        pages.add(new PageInfo(AppConsts.PAGE_APPOINTMENTS, AppConsts.ICON_APPOINTMENTS,
                AppConsts.TITLE_APPOINTMENTS));
        pages.add(new PageInfo(AppConsts.PAGE_MASTERS, AppConsts.ICON_APPOINTMENTS,
                AppConsts.TITLE_MASTERS));
        appNavigation = new AppNavigation();
        appNavigation.init(pages);

        add(appNavigation);
        setHorizontalComponentAlignment(Alignment.CENTER, appNavigation);
    }
}
