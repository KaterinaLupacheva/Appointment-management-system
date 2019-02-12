package by.mycompany.beautysalon.ui.entities;

import com.vaadin.flow.component.icon.Icon;

public class PageInfo {

    private final String link;
    private final Icon icon;
    private final String title;

    public PageInfo(String link, Icon icon, String title) {
        this.link = link;
        this.icon = icon;
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public Icon getIcon() {
        return icon;
    }

    public String getTitle() {
        return title;
    }
}
