package org.example.tools.abstractions;

import org.example.model.abstraction.IHaveHierarchicalStructure;

public class Hierarchy<TItem extends IHaveHierarchicalStructure<TItem>> {
    private TItem rootElement;

    public void setRootElement(TItem item) {
        this.rootElement=item;
    }

    public TItem findElementById(int id) {
        for (TItem item : rootElement.getChildren()) {
            if (item.getId() == id) {
                return item;
            } else {
                if (item.getChildren().size() != 0) {
                    Hierarchy<TItem> found = new Hierarchy<>();
                    found.setRootElement(item);

                    TItem result = found.findElementById(id);

                    if (result != null) {
                        return result;
                    }

                }
            }
        }
        return null;
    }
}
