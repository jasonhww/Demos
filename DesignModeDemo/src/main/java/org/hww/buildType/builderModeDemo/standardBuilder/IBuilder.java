package org.hww.buildType.builderModeDemo.standardBuilder;

public interface IBuilder {
    void name(String name);

    void model(String model);

    void ram(String ram);

    Phone2 build();
}
