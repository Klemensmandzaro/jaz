package org.example.tools.abstractions;

import org.example.model.Geography;
import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class HierarchyBuilder<TItem extends IHaveHierarchicalStructure<TItem>> {
    private List<TItem> elements;
    private TItem rootElement;
    private List<TItem> childElement;


    public TItem getRootElement() {
        return (TItem) this.rootElement;
    }

    public void setElements(List<TItem> items){
        this.elements= items;
    }

    public void buildHierarchy() {
        for (TItem item : elements) {
            if (item.getParentId()==null) {
                this.rootElement = item;
            }
            else {
                Integer parentID=item.getParentId();
                for (TItem item2 : elements)
                {
                    if (parentID.equals(item2.getId()))
                    {
                        item.setParent(item2);
                        item2.getChildren().add(item);
                    }
                }
            }
        }
    }
}
