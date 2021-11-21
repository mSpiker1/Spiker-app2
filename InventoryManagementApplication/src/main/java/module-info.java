module inv.inventorymanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;
    requires com.google.gson;

    opens inv.inventorymanagementapplication to javafx.fxml, com.google.gson;
    exports inv.inventorymanagementapplication;
}