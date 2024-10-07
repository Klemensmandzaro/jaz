package org.example.tools.abstractions;

import org.example.model.Geography;
import org.example.model.abstraction.IHaveHierarchicalStructure;

import java.util.List;

public class HierarchyBuilder<TItem extends IHaveHierarchicalStructure<TItem>> {
    private int id;
    private List<TItem> children;
    private TItem parent;
    private Integer parentId;
    private List<TItem> elements;
    private TItem rootElement;


    public TItem getRootElement() {
        return (TItem) this.rootElement;
    }

    public void setElements(List<TItem> items){
        this.elements= items;
    }
}
