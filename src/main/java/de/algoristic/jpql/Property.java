package de.algoristic.jpql;

public abstract class Property {

    protected String name;

    protected Property(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
//    public AbstractProperty of(String qualifier) {
//        
//    }
    
    public Property of(String tableAlias, String name) {
        return new NonBindingProperty(name, tableAlias);
    }
    
//    public AbstractProperty of(Class<?> clazz, String name) {
//        
//    }
    
//    public AbstractProperty of(Table table, String name) {
//        
//    }

}
