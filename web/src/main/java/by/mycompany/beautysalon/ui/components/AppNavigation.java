package by.mycompany.beautysalon.ui.components;

import by.mycompany.beautysalon.ui.entities.PageInfo;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;

import java.util.ArrayList;
import java.util.List;

public class AppNavigation extends HorizontalLayout {

    private Tabs tabs = new Tabs();

    private List<String> hrefs = new ArrayList<>();

    public void init(List<PageInfo> pages) {
        for(PageInfo page: pages) {
            Tab tab = new Tab(new Icon(VaadinIcon.CALENDAR), new Span(page.getTitle()));
            tab.getElement().setAttribute("theme", "icon-on-top");
            hrefs.add(page.getLink());
            tabs.add(tab);
        }
        setSizeUndefined();
        add(tabs);
    }
}
