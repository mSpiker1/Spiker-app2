module inv.inventorymanagementapplication {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires javafx.graphics;

    opens inv.inventorymanagementapplication to javafx.fxml;
    exports inv.inventorymanagementapplication;
}