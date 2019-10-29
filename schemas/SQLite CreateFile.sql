-- Creator:       MySQL Workbench 8.0.14/ExportSQLite Plugin 0.1.0
-- Author:        majortwip
-- Caption:       New Model
-- Project:       Name of the project
-- Changed:       2019-10-29 20:30
-- Created:       2019-10-13 16:30
PRAGMA foreign_keys = OFF;

-- Schema: escapeconnect
ATTACH "escapeconnect.sdb" AS "escapeconnect";
BEGIN;
CREATE TABLE "escapeconnect"."ecsettings"(
  "adminpass" VARCHAR(45) NOT NULL,
  "mqtturl" VARCHAR(45) NOT NULL,
  "mqttport" INTEGER NOT NULL DEFAULT 1883,
  "mqttuser" VARCHAR(45),
  "mqttpass" VARCHAR(45)
);
CREATE TABLE "escapeconnect"."firmware"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "label" VARCHAR(45) NOT NULL,
  "version" VARCHAR(45) NOT NULL,
  "name" VARCHAR(45) NOT NULL,
  "file" BLOB NOT NULL
);
CREATE TABLE "escapeconnect"."device"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "name" VARCHAR(45) NOT NULL,
  "mac" VARCHAR(12) NOT NULL,
  "basetopic" VARCHAR(45) NOT NULL,
  "deviceid" VARCHAR(45) NOT NULL,
  "supportsOTA" INTEGER NOT NULL DEFAULT 0,
  "firmware_id" INTEGER,
  CONSTRAINT "device_firmware_id"
    FOREIGN KEY("firmware_id")
    REFERENCES "firmware"("id")
    ON DELETE SET NULL
    ON UPDATE CASCADE
);
CREATE INDEX "escapeconnect"."device.device_firmware_id_idx" ON "device" ("firmware_id");
CREATE TABLE "escapeconnect"."panel"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "device_id" INTEGER,
  "name" VARCHAR(45),
  CONSTRAINT "device_id"
    FOREIGN KEY("device_id")
    REFERENCES "device"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX "escapeconnect"."panel.device_id_idx" ON "panel" ("device_id");
CREATE TABLE "escapeconnect"."value"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "panel_id" INTEGER,
  "label" VARCHAR(45) NOT NULL,
  "topic" VARCHAR(45) NOT NULL,
  CONSTRAINT "value_panel_id"
    FOREIGN KEY("panel_id")
    REFERENCES "panel"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX "escapeconnect"."value.action_panel_id_idx" ON "value" ("panel_id");
CREATE TABLE "escapeconnect"."action"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "panel_id" INTEGER,
  "label" VARCHAR(45) NOT NULL,
  "payload" VARCHAR(45) NOT NULL,
  "topic" VARCHAR(45) NOT NULL,
  CONSTRAINT "action_panel_id"
    FOREIGN KEY("panel_id")
    REFERENCES "panel"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX "escapeconnect"."action.action_panel_id_idx" ON "action" ("panel_id");
CREATE TABLE "escapeconnect"."setting"(
  "id" INTEGER PRIMARY KEY NOT NULL,
  "device_id" INTEGER NOT NULL,
  "panel_id" INTEGER,
  "label" VARCHAR(45) NOT NULL,
  "value" VARCHAR(45),
  "name" VARCHAR(45) NOT NULL,
  "type" VARCHAR(45) NOT NULL,
  "min" FLOAT,
  "max" FLOAT,
  CONSTRAINT "setting_device_id"
    FOREIGN KEY("device_id")
    REFERENCES "device"("id")
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
CREATE INDEX "escapeconnect"."setting.setting_device_id_idx" ON "setting" ("device_id");
COMMIT;
