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
    requires com.fasterxml.jackson.databind;
    requires java.sql;
    requires org.apache.tomcat.embed.core;

    exports to.grindelf.apartmentmanager.exceptions to javafx.base;

    opens to.grindelf.apartmentmanager.application.desktop.controllers to javafx.fxml;
    exports to.grindelf.apartmentmanager.application.desktop.controllers;

    opens to.grindelf.apartmentmanager.application.desktop to javafx.fxml;
    opens to.grindelf.apartmentmanager.utils to javafx.fxml;
    exports to.grindelf.apartmentmanager.utils;

    opens to.grindelf.apartmentmanager.domain to javafx.fxml, com.fasterxml.jackson.databind;
    exports to.grindelf.apartmentmanager.domain;
    exports to.grindelf.apartmentmanager.utils.database;
    opens to.grindelf.apartmentmanager.utils.database to javafx.fxml;
    exports to.grindelf.apartmentmanager.utils.json;
    opens to.grindelf.apartmentmanager.utils.json to javafx.fxml;
    exports to.grindelf.apartmentmanager to javafx.graphics;

//    opens to.grindelf.apartmentmanager to spring.beans;
//    opens to.grindelf.apartmentmanager.auth to spring.beans;
//    opens to.grindelf.apartmentmanager.application.spring.controllers to spring.beans, spring.web;
//    opens to.grindelf.apartmentmanager.application.spring.services to spring.beans, spring.core;
}


