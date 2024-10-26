module to.grindelf.apartmentmanager {
    requires javafx.controls;
    requires javafx.fxml;

    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.web;
    requires org.jetbrains.annotations;
    requires spring.context;
    requires spring.core;
    requires spring.beans;

    requires javafx.graphics;

    exports to.grindelf.apartmentmanager to javafx.graphics;

    exports to.grindelf.apartmentmanager.application to javafx.graphics, spring.core;

    exports to.grindelf.apartmentmanager.exceptions to javafx.base, spring.core;

    opens to.grindelf.apartmentmanager to spring.core, spring.beans, spring.context;
    opens to.grindelf.apartmentmanager.application.desktop.controllers to javafx.fxml;
    exports to.grindelf.apartmentmanager.application.desktop.controllers;

    opens to.grindelf.apartmentmanager.application.desktop to javafx.fxml;
    opens to.grindelf.apartmentmanager.utils to javafx.fxml;
    exports to.grindelf.apartmentmanager.utils;

    opens to.grindelf.apartmentmanager.domain to javafx.fxml;
    exports to.grindelf.apartmentmanager.domain;
    opens to.grindelf.apartmentmanager.application to spring.beans, spring.context, spring.core;

}
