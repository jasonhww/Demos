package org.hww.buildType.builderModeDemo.standardBuilder;

public class Phone2 {
    private String mName;
    private String mModel;
    private String mRam;

    public Phone2() {

    }

    public Phone2(String name, String model, String ram) {
        this.mName = name;
        this.mModel = model;
        this.mRam = ram;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "mName='" + mName + '\'' +
                ", mModel='" + mModel + '\'' +
                ", mRam='" + mRam + '\'' +
                '}';
    }

    public static class Builder implements IBuilder {
        private String name;
        private String model;
        private String ram;

        @Override
        public void name(String name) {
            this.name = name;
        }

        @Override
        public void model(String model) {
            this.model = model;
        }

        @Override
        public void ram(String ram) {
            this.ram = ram;
        }

        @Override
        public Phone2 build() {
            return new Phone2(name, model, ram);
        }
    }
}
