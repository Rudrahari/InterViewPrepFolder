package DesignPatterns.BehavioralPatterns;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// memento
class SchemaMemento {
    private final Map<Object, Object> schema;

    SchemaMemento(Map<Object, Object> schema) {
        this.schema = new HashMap<>(schema);
    }

    Map<Object, Object> getSchema() {
        return schema;
    }
}

// Originator
class SchemaEditor {
    Map<Object, Object> schema;

    SchemaEditor() {
        schema = new HashMap<>();
    }

    void addPropertiesToSchema(Object key, Object value) {
        schema.put(key, value);
    }

    void showCurrentSchema() {
        System.out.println(schema);
    }

    SchemaMemento save() {
        return new SchemaMemento(schema);
    }

    void restoreToLastSavedSchema(SchemaMemento schemaMemento) {
        this.schema = new HashMap<>(schemaMemento.getSchema());
    }
}
// Caretaker
class SchemaHistory {
    Stack<SchemaMemento> schemaHistory;

    SchemaHistory() {
        schemaHistory = new Stack<>();
    }

    void undo(SchemaEditor schemaEditor) {
        if (schemaHistory.isEmpty()) {
            System.out.println("Don't have any version versions to rollback");
            return;
        }
        schemaEditor.restoreToLastSavedSchema(schemaHistory.pop());
    }

    void saveSchema(SchemaMemento schemaMemento) {
        schemaHistory.push(schemaMemento);
    }
}
// memento means an object kept as a reminder of a person
// in design patterns it is used to store previous states of an object
public class MementoPattern {
    public static void main(String[] args) {
        SchemaHistory schemaHistory = new SchemaHistory();
        SchemaEditor schemaEditor = new SchemaEditor();

        schemaEditor.addPropertiesToSchema("key", 1);
        schemaEditor.addPropertiesToSchema("keyS", "value");
        schemaHistory.saveSchema(schemaEditor.save());

        schemaEditor.addPropertiesToSchema("key1", "val1");
        schemaEditor.addPropertiesToSchema("key2", "val01");
        schemaHistory.saveSchema(schemaEditor.save());

        schemaEditor.showCurrentSchema();
        schemaEditor.addPropertiesToSchema("k", "v");
        schemaEditor.addPropertiesToSchema("ka", "lo");
        schemaEditor.showCurrentSchema();

        schemaHistory.undo(schemaEditor);
        schemaEditor.showCurrentSchema();
        schemaHistory.undo(schemaEditor);
        schemaEditor.showCurrentSchema();
        schemaHistory.undo(schemaEditor);
    }
}
