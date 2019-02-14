package by.mycompany.beautysalon.ui.components;

import by.mycompany.beautysalon.ui.entities.PageInfo;
import by.mycompany.beautysalon.ui.utils.AppConsts;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.tabs.TabsVariant;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;

import java.util.ArrayList;
import java.util.List;

public class AppNavigation extends HorizontalLayout implements AfterNavigationObserver {

    private Tabs tabs = new Tabs();

    private List<String> hrefs = new ArrayList<>();
    private String currentHref;
    private String defaultHref;

    public void init(List<PageInfo> pages, String defaultHref) {
        this.defaultHref = defaultHref;

        for(PageInfo page: pages) {
            Tab tab = new Tab(new Icon("vaadin", page.getIcon()), new Span(page.getTitle()));
            hrefs.add(page.getLink());
            tabs.add(tab);
        }
        setSizeUndefined();
        add(tabs);
        tabs.addSelectedChangeListener(e -> navigate());
    }

    private void navigate() {
        int tabId =tabs.getSelectedIndex();
        if (tabId >= 0 && tabId < hrefs.size()) {
            String href = hrefs.get(tabId);
            if (!href.equals(currentHref)) {
                UI.getCurrent().navigate(href);
            }
        }
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        String href =event.getLocation().getFirstSegment().isEmpty() ? defaultHref
                : event.getLocation().getFirstSegment();
        currentHref = href;
        tabs.setSelectedIndex(hrefs.indexOf(href));
    }
}
