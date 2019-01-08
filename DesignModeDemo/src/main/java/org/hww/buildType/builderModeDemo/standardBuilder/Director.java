package org.hww.buildType.builderModeDemo.standardBuilder;

public class Director {

    Phone2.Builder mBuilder = null;

    public Director(Phone2.Builder mBuilder) {
        this.mBuilder = mBuilder;
    }

    public Phone2 createPhone(String name, String model, String ram) {
        this.mBuilder.name(name);
        this.mBuilder.model(model);
        this.mBuilder.ram(ram);
        return mBuilder.build();
    }
}
