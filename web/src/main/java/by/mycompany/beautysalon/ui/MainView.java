package by.mycompany.beautysalon.ui;

import by.mycompany.beautysalon.ui.components.AppNavigation;
import by.mycompany.beautysalon.ui.entities.PageInfo;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Route("")
@Theme(Material.class)
@PageTitle("Admin Panel")
@Viewport(AppConsts.VIEWPORT)
public class MainView extends VerticalLayout implements RouterLayout {

    private AppNavigation appNavigation;

    @Autowired
    public MainView() {
        setSizeFull();
        List<PageInfo> pages = new ArrayList<>();
        pages.add(new PageInfo(AppConsts.PAGE_APPOINTMENTS, AppConsts.ICON_APPOINTMENTS,
                AppConsts.TITLE_APPOINTMENTS));
        pages.add(new PageInfo(AppConsts.PAGE_MASTERS, AppConsts.ICON_MASTERS,
                AppConsts.TITLE_MASTERS));
        pages.add(new PageInfo(AppConsts.PAGE_SERVICES, AppConsts.ICON_SERVICES,
                AppConsts.TITLE_SERVICES));
        appNavigation = new AppNavigation();
        appNavigation.init(pages, AppConsts.PAGE_DEFAULT);

        add(appNavigation);
        setHorizontalComponentAlignment(Alignment.CENTER, appNavigation);
    }
}
