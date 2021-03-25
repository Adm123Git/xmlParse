package ru.adm123.xmlparse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.w3c.dom.Element;

/**
 * @author Dmitry Ushakov on 25.03.21
 */
@AllArgsConstructor
@Setter
@Getter
public class Color {

    private int r;
    private int g;
    private int b;

    public void setToElement(Element colorNode) {
        if (colorNode != null) {
            colorNode.setAttribute("R", String.valueOf(r));
            colorNode.setAttribute("G", String.valueOf(g));
            colorNode.setAttribute("B", String.valueOf(b));
        }
    }

}
