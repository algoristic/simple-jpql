# simple-jpql

Plan to use this like:

```java
//select complete table
Select.from("<class>::simpleName");
Select.from(Table.class);

//select specific properties
Select.properties(new String[] {
    "prop1",
    "prop2",
    "prop3"
}).from(Table.class);

Select.properties("prop1, prop2, prop3").from(Table.class);

List<String> props = //define properties

Select.properties(props).from(Table.class);

//select specific properties from specific table
Select.properties(
    "t.prop1",
    "t.prop2",
    "t.prop3"
).from("table t");

Select.properties(
    Property.of("t", "prop1"),
    Property.of("t", "prop2"),
    Property.of("t", "prop3")
).from(Table.class, "t");

Select.properties(
    Property.of(Table.class, "prop1"),
    Property.of("prop2").originatingFrom(Table.class),
    Property.of(Table.class, "prop3")
).from(Table.class);

Select.property(Property.of("t", "prop")).from(Table.of(Table.class).as("t"));

//select
Select.all.from(Table.of(Table1.class).join(Table2.class));

Select.all.from(Table.of(Table1.class).leftJoin(Table2.class).on(/* TODO */));

//select with conditions
Select.all.from(
    Table.clas
).where(
    Condition.biggerThan("prop1", 5).and(
    Condition.lessThan("prop1", 10));

Select.all.from(
    Table.class
).where(
    Condition.and(
        Condition.biggerThan("prop1", 5),
        Condition.lessThan("prop1", 10));

Select.all.from(
    Table.class
).where(
    Logic.and(
        Condition.biggerThan("prop1", 5),
        Condition.lessThan("prop1", 10));

Select.all.from(
    Table.class
).where(
    Condition.equals(Property.of("prop1").originatingFrom(Table.class), "foo");

Select.all.from(
    Table.class
).where(
    Condition.and(
        Condition.equals(/* ... */),
        Condition.notNull(/* ... */));
```
