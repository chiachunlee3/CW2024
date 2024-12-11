module com.example.demo {
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.controller;
    exports com.example.demo.actors;
    exports com.example.demo.projectiles;
    exports com.example.demo.managers;
    exports com.example.demo.levels;
    exports com.example.demo.states;
    exports com.example.demo.visuals;
    exports com.example.demo.utility;
}